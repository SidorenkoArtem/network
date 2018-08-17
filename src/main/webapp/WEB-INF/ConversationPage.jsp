<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="Header.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<script>
    var page = 0;
    var count = 0;

    $(document).ready(function(){
        addElement();
    });

    function addElement() {
        $.ajax({
            url: "/user/conversations/${conversationId}/messages?page=" + page,
            method: "GET",
            success: function (result) {
                var messages = result.lastMessageDtos;
                messages.forEach(function (el) {
                    var domElement = createDomElement(el);
                    $("#messagesContainer").append(domElement);
                    count++;
                    if (count >= result.count) {
                        $("#loadConversations").hide();
                    }
                });
            }
        });
        if (count >= result.count) {
            $("#loadConversations").hide();
        }
        page++;
    }

    function createDomElement(el) {
        return "<div class='row'>" +
            "<a href=\"/" + el.user.userId + "\">" +
                "<div class='col-sm-1'>" +
                    "<img height=\"60\" width=\"60\" src=\"" + el.user.photoUrl + "\"/>" +
                "</div>" +
                "<div class='col-sm-2'>" +
                    "<p class=\"media-body pb-3 mb-0 small lh-125 border-bottom border-gray\">" +
                        "<strong class=\"d-block text-gray-dark\">" + el.user.firstName + " " + el.user.name + "</strong>" +
                    "</p>" +
            "</a>" +
                    "<p>" +
                        el.text +
                    "</p>" +
                    "<p>" +
                        "<font size=\"1\">" +
                            el.createTimestamp +
                        "</font></p>" +
                "</div>" +
            "</div>";
    }

    function sendMessage() {
        var content = {
            "receiverUserId":"0",
            "text":$("#inputMessages").val(),
            "fileUrl":""
        };
        $.ajax({
            url: "/user/messages",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            method: "POST",
            data: JSON.stringify(content),
            success: function(post) {
                $(createDomElement(post.message)).insertAfter("#messagesContainerBefore");
            }
        });
    }

    function submitForm() {
        console.log("submit event");
        var fd = new FormData(document.getElementById("fileinfo"));
        fd.append("label", "WEBUPLOAD");
        $.ajax({
            url: "user/upload",
            type: "POST",
            data: fd,
            processData: false,  // tell jQuery not to process the data
            contentType: false   // tell jQuery not to set contentType
        }).done(function( data ) {
            console.log("PHP Output:");
            console.log( data );
        });
        return false;
    }
</script>

<div class="container">
    <form method="post" id="fileinfo" name="fileinfo" onsubmit="return submitForm();">
        <label>Select a file:</label><br>
        <input type="file" name="file" required />
        <input type="submit" value="Upload" />
    </form>
    <input id="inputMessages" type="text" placeholder="Введите сообщение"/>

    <button id="sendMessage" onclick="sendMessage()">Отправить</button>
    <div id="messagesContainerBefore" class="my-3 p-3 bg-white rounded shadow-sm">
    </div>
    <div id="messagesContainer" class="my-3 p-3 bg-white rounded shadow-sm">
    </div>
    <div id="buttonContainer" class="my-3 p-3 bg-white rounded shadow-sm">
        <button id="loadConversations" onclick="addElement()">Load more messages...</button>
    </div>
</div>
</body>
</html>
