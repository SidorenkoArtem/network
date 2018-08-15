<%--
  Created by IntelliJ IDEA.
  User: дима
  Date: 15.08.2018
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="Header.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<button id="loadConversations" onclick="addElement()">More conversations</button>

<script>
    var page = 0;
    var limit = 0;
    function addElement() {
        $.ajax({
            url:"/user/conversations/${conversationId}/messages",
            method:"GET",
            success: function(result) {
                // console.log(result.conversations);
                 console.log(result);
                 var messages = result.lastMessageDtos;
                messages.forEach(function (el) {
                    var domElement =
                        "<a href=\"/" + el.user.userId + "\">" +// redirect user message page
                            "<div class='row'>" +
                                "<div class='col-sm-1'>" +
                                    "<img height=\"60\" width=\"60\" src=\"" + el.user.photoUrl + "\"/>" +
                                "</div>" +
                            "<div class='col-sm-2'>" +
                                "<p class=\"media-body pb-3 mb-0 small lh-125 border-bottom border-gray\">" +
                                    "<strong class=\"d-block text-gray-dark\">" + el.user.firstName + " " + el.user.name + "</strong>" +
                                "</p>" +
                                "<p>" +
                                    el.text +
                                "</p>" +
                            "</div>" +
                        "</a>";
                    $("#messagesContainer").append(domElement);
                });
            }
        });
    }
</script>

<div class="container">
    <div id="messagesContainer" class="my-3 p-3 bg-white rounded shadow-sm">
    </div>
</div>

</body>
</html>
