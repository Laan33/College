/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


window.onload = init;
let socket = new WebSocket("ws://localhost:8080/WebSocketTest/actions");
socket.onmessage = onMessage;

function onMessage(event) {
    let comment = JSON.parse(event.data);
    switch (comment.action) {
        case "add":
            printCommentElement(comment);
            break;
        case "remove":
            document.getElementById(comment.id).remove();
            break;
        case "toggle":
            let node = document.getElementById(comment.id);
            let statusText = node.children[2];
            if (comment.status === "On") {
                statusText.innerHTML = "Status: " + comment.status + " (<a href=\"#\" OnClick=toggleComment(" + comment.id + ")>Turn off</a>)";
            } else if (comment.status === "Off") {
                statusText.innerHTML = "Status: " + comment.status + " (<a href=\"#\" OnClick=toggleComment(" + comment.id + ")>Turn on</a>)";
            }
            break;
        default:
            break;
    }
}

function addComment(name, type, description) {
    let CommentAction = {
        action: "add",
        name: name,
        type: type,
        description: description
    };
    socket.send(JSON.stringify(CommentAction));
}

function removeComment(element) {
    let id = element;
    let CommentAction = {
        action: "remove",
        id: id
    };
    socket.send(JSON.stringify(CommentAction));
}

function toggleComment(element) {
    let id = element;
    let CommentAction = {
        action: "toggle",
        id: id
    };
    socket.send(JSON.stringify(CommentAction));
}

function printCommentElement(comment) {
    let content = document.getElementById("content");
    
    let commentDiv = document.createElement("div");
    commentDiv.setAttribute("id", comment.id);
    commentDiv.setAttribute("class", "comment " + comment.type);
    content.appendChild(commentDiv);

    let commentName = document.createElement("span");
    commentName.setAttribute("class", "commentName");
    commentName.innerHTML = comment.name;
    commentDiv.appendChild(commentName);

    let commentType = document.createElement("span");
    commentType.innerHTML = "<b>Type:</b> " + comment.type;
    commentDiv.appendChild(commentType);

    let commentStatus = document.createElement("span");
    if (comment.status === "On") {
        commentStatus.innerHTML = "<b>Status:</b> " + comment.status + " (<a href=\"#\" OnClick=toggleComment(" + comment.id + ")>Turn off</a>)";
    } else if (comment.status === "Off") {
        commentStatus.innerHTML = "<b>Status:</b> " + comment.status + " (<a href=\"#\" OnClick=toggleComment(" + comment.id + ")>Turn on</a>)";
    }
    commentDiv.appendChild(commentStatus);

    let commentDescription = document.createElement("span");
    commentDescription.innerHTML = "<b>Comments:</b> " + comment.description;
    commentDiv.appendChild(commentDescription);

    let removeComment = document.createElement("span");
    removeComment.setAttribute("class", "removeComment");
    removeComment.innerHTML = "<a href=\"#\" OnClick=removeComment(" + comment.id + ")>Remove comment</a>";
    commentDiv.appendChild(removeComment);
}

function showForm() {
    document.getElementById("addCommentForm").style.display = '';
}

function hideForm() {
    document.getElementById("addCommentForm").style.display = "none";
}

// Forum topic selection function - has a dropdown with politics, sports, nature and technology as options
function forumSelect() {
    let x = document.getElementById("forumSelect").value;
    switch (x) {
        case "politics":
            document.getElementById("forumSelect").innerHTML = "PoliticsForum";
            break;
        case "sports":
            document.getElementById("forumSelect").innerHTML = "SportsForum";
            break;
        case "nature":
            document.getElementById("forumSelect").innerHTML = "NatureForum";
            break;
        case "technology":
            document.getElementById("forumSelect").innerHTML = "TechnologyForum";
            break;
    }
}


function formSubmit() {
    let form = document.getElementById("addCommentForm");
    let name = form.elements["comment_name"].value;
    let type = form.elements["comment_type"].value;
    let description = form.elements["comment_description"].value;
    hideForm();
    document.getElementById("addCommentForm").reset();
    addComment(name, type, description);
}

function init() {
    hideForm();
}

