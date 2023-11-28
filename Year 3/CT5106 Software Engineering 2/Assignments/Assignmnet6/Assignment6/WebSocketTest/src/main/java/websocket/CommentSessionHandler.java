/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.JsonObject;
import jakarta.json.spi.JsonProvider;
import jakarta.websocket.Session;
import model.Comment;

/**
 *
 * @author cathal_lawlor
 */
@ApplicationScoped
public class CommentSessionHandler {

    private int commentId = 0;
    private final Set<Session> sessions = new HashSet<>();
    private final Set<Comment> comments = new HashSet<>();

    public void addSession(Session session) {
        sessions.add(session);
        for (Comment comment : comments) {
            JsonObject addMessage = createAddMessage(comment);
            sendToSession(session, addMessage);
        }
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public void addComment(Comment comment) {
        comment.setId(commentId);
        comments.add(comment);
        commentId++;
        JsonObject addMessage = createAddMessage(comment);
        sendToAllConnectedSessions(addMessage);
    }

    public void removeComment(int id) {
        Comment comment = getCommentById(id);
        if (comment != null) {
            comments.remove(comment);
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder()
                    .add("action", "remove")
                    .add("id", id)
                    .build();
            sendToAllConnectedSessions(removeMessage);
        }
    }

    public void toggleComment(int id) {
        JsonProvider provider = JsonProvider.provider();
        Comment comment = getCommentById(id);
        if (comment != null) {
            if ("On".equals(comment.getStatus())) {
                comment.setStatus("Off");
            } else {
                comment.setStatus("On");
            }
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "toggle")
                    .add("id", comment.getId())
                    .add("status", comment.getStatus())
                    .build();
            sendToAllConnectedSessions(updateDevMessage);
        }
    }

    private Comment getCommentById(int id) {
        for (Comment comment : comments) {
            if (comment.getId() == id) {
                return comment;
            }
        }
        return null;
    }

    private JsonObject createAddMessage(Comment comment) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", comment.getId())
                .add("name", comment.getName())
                .add("type", comment.getType())
                .add("status", comment.getStatus())
                .add("description", comment.getDescription())
                .build();
        return addMessage;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(CommentSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
