package websocket;

import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import model.Device;

@ApplicationScoped
@ServerEndpoint("/actions")
public class DeviceWebSocketServer {

    @Inject
    private DeviceSessionHandler sessionHandler;

    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(DeviceWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            if ("join".equals(jsonMessage.getString("action"))) {
                String forum = jsonMessage.getString("forum");
                sessionHandler.addSession(session);
                sendPreviousMessages(session, forum);
            }

            if ("message".equals(jsonMessage.getString("action"))) {
                String forum = jsonMessage.getString("forum");
                String user = jsonMessage.getString("username");
                String userMessage = jsonMessage.getString("message");
                sessionHandler.addMessage(forum, user + ": " + userMessage);
            }

            if ("requestPrevious".equals(jsonMessage.getString("action"))) {
                String forum = jsonMessage.getString("forum");
                sendPreviousMessages(session, forum);
            }
        }
    }

    private void sendPreviousMessages(Session session, String forum) {
        List<String> previousMessages = sessionHandler.getPreviousMessages(forum);

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (String message : previousMessages) {
            jsonArrayBuilder.add(message);
        }

        JsonArray jsonPreviousMessages = jsonArrayBuilder.build();

        JsonObject previousMsgObj = Json.createObjectBuilder()
                .add("action", "previousMessages")
                .add("messages", jsonPreviousMessages)
                .build();

        try {
            session.getBasicRemote().sendText(previousMsgObj.toString());
        } catch (Exception e) {
            Logger.getLogger(DeviceWebSocketServer.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
