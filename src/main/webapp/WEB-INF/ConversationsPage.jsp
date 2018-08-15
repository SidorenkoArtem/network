<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="Header.jsp"/>
    <title>Messages</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div>
    <button id="loadConversations" onclick="addElement()">More conversations</button>
</div>
    <script>
    var page = 0;
    var limit = 0;
    function addElement() {
        $.ajax({
            url:"/user/conversations",
            method:"GET",
            success: function(result) {
                console.log(result.conversations);
                console.log(result);
                var conversations = result.conversations;
                conversations.forEach(function (el) {
                    var domElement =
                        "<a href=\"/conversation/" + el.id + "\">" +
                            "<div class='row'>" +
                                "<div class='col-sm-1'>" +
                                    "<img height=\"60\" width=\"60\" src=\"" + el.creatorConversation.photoUrl + "\"/>" +
                                "</div>" +
                                "<div class='col-sm-2'>" +
                                    "<p class=\"media-body pb-3 mb-0 small lh-125 border-bottom border-gray\">" +
                                        "<strong class=\"d-block text-gray-dark\">" + el.creatorConversation.firstName + " " + el.creatorConversation.name + "</strong>" +
                                    "</p>" +
                                "</div>" +

                                "<div class='col-sm-1'>" +
                                    "<img height=\"60\" width=\"60\" src=\"" + el.creatorConversation.photoUrl + "\"/>" +
                                "</div>" +
                                    "<div class='col-sm-2'>" +
                                        "<p class=\"media-body pb-3 mb-0 small lh-125 border-bottom border-gray\">" +
                                            "<strong class=\"d-block text-gray-dark\">" + el.creatorConversation.firstName + " " + el.creatorConversation.name + "</strong>" +
                                        "</p>" +
                                    "</div>" +
                                "</div>" +
                            "</div>" +
                        "</a>";
                    $("#conversationsContainer").append(domElement);
                });
            }
        });
    }
</script>
<div class="container">
    <div id="conversationsContainer" class="my-3 p-3 bg-white rounded shadow-sm">
    </div>
</div>
</body>
</html>
