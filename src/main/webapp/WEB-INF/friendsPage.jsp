<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<jsp:include page="Header.jsp"/>--%>
    <title>Friends</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<h1>Friends:</h1>
<div id="friendsContainer">
</div>
<button id="loadFriends" onclick="addElement()">More friends</button>
<script>
    var page = 0;
    var count = 0;
    function addElement() {
        $.ajax({
            url: "/user/${userId}/friends?page=" + page,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            method: "GET",
            success: function(response) {
                var userFriends = response.userFriends;
                userFriends.forEach(function(el) {
                    var domElement = '<div><a href="/' + el.friend.userId + '"> <img src="'+ el.friend.photoUrl +
                        '"></div><p>' + el.friend.firstName + ' ' + el.friend.name + '</p>';
                    $("#friendsContainer").append(domElement);
                    count++;
                    if (count === response.count) {
                        $("#loadFriends").hide();
                    }
                });
            }
        });
        page++;
    }
</script>
</body>
</html>