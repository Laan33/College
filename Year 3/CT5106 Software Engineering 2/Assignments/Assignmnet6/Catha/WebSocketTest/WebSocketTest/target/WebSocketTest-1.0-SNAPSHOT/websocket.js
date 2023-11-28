window.onload = init;
var socket;
var selectedForum;
var username;

function connectWebSocket() {
    socket = new WebSocket("ws://localhost:8080/WebSocketTest/actions");

    socket.onopen = function (event) {
        console.log('WebSocket connection opened:', event);
    };

    socket.onmessage = function (event) {
        onMessage(event);
    };

    socket.onclose = function (event) {
        console.log('WebSocket connection closed:', event);
    };

    socket.onerror = function (error) {
        console.error('WebSocket Error:', error);
    };
}

function joinForum() {
    username = document.getElementById("device_name").value;

    if (username.trim() === "") {
        alert('Please enter a username before joining.');
        return;
    }

    if (selectedForum) {
        // Send a join request to the server
        var joinRequest = {
            action: "join",
            forum: selectedForum,
            username: username
        };
        socket.send(JSON.stringify(joinRequest));

        // Request previous messages for the joined forum
        var requestPreviousMsg = {
            action: "requestPrevious",
            forum: selectedForum
        };
        socket.send(JSON.stringify(requestPreviousMsg));

        // Show the corresponding chat section and hide others
        var chatSections = document.getElementsByClassName("chat-section");
        for (var i = 0; i < chatSections.length; i++) {
            var section = chatSections[i];
            if (section.id === selectedForum.toLowerCase() + "Chat") {
                section.style.display = "block";
            } else {
                section.style.display = "none";
            }
        }

        // Hide the forum selection form
        var forumSelectionForm = document.getElementById("addDeviceForm");
        forumSelectionForm.style.display = "none";
    } else {
        alert('Please select a forum before joining.');
    }
}

function selectForum(forum) {
    selectedForum = forum;
}

function sendMsg(forum) {
    var msgField = document.getElementById(forum + "MessageField");
    var msgToSend = msgField.value;

    if (msgToSend.trim() !== "") {
        // Send the message to the server
        var message = {
            action: "message",
            forum: selectedForum,
            username: username,
            message: msgToSend
        };
        socket.send(JSON.stringify(message));

        // Display the sent message in the corresponding chat box
        var divMsg = document.getElementById(forum + "MsgBox");
        divMsg.innerHTML += "<div><b>" + username + ":</b> " + msgToSend + "</div>";

        // Clear the input field
        msgField.value = "";
    }
}

// Function to handle messages received from the server
function onMessage(event) {
    var message = JSON.parse(event.data);

    if (message.action === "add") {
    } else if (message.action === "remove") {
    } else if (message.action === "toggle") {
    } else if (message.action === "message") {
        // Handle incoming messages
        var forum = message.forum.toLowerCase();
        var msgBox = document.getElementById(forum + "MsgBox");
        msgBox.innerHTML += "<div>" + message.message + "</div>";
    } else if (message.action === "previousMessages") {
        // Handle previous messages for the joined forum
        var forum = selectedForum.toLowerCase();
        var msgBox = document.getElementById(forum + "MsgBox");
        var previousMessages = message.messages;
        for (var i = 0; i < previousMessages.length; i++) {
            msgBox.innerHTML += "<div>" + previousMessages[i] + "</div>";
        }
    }
}

function init() {
    connectWebSocket();
}
