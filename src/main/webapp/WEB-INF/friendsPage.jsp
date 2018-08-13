<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<jsp:include page="Header.jsp"/>--%>
        <jsp:include page="Header.jsp"/>
    <title>Friends</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div>
    <jsp:include page="mainMenu.jsp"/>
</div>
<div class="col-sm-3">
    <div>
        <jsp:include page="leftMenu.jsp"/>
    </div>
</div>
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
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            method: "GET",
            success: function(response) {
                var userFriends = response.userFriends;
                userFriends.forEach(function(el) {
                    console.log(el);
                    var domElement =
                        '<div class=\"container\">' +
                            '<div class=\"row\">' +
                                '<a href="/' + el.friend.userId + '">' +
                                    '<div class=\"col-sm-2\">' +
                                        '<img src="'+ el.friend.photoUrl + '"/>' +
                                    '</div>' +
                                    '<div class=\"col-sm-4\">' +
                                        '<p>' + el.friend.firstName + ' ' + el.friend.name + '</p>' +
                                    '</div>' +
                                '</a>' +
                            '</div>' +
                            '<div>' +
                                '<input type=\"button\" onclick=\"deleteFriend(' + el.friend.userId + ')\">Убрать из друзей</input>' +
                            '</div>' +
                        '</div>';
                    console.log(domElement);
                    $("#friendsContainer").append(domElement);
                    if (count === response.count) {
                        $("#loadFriends").hide();
                    }
                    count++;
                });
            }
        });
        page++;
    }

    function deleteFriend(userId) {
        $.ajax({
            url : "/user/" + userId + "/friends",
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            method : "DELETE",
            error: function(request,msg,error) {
                console.log(request);
            }
        });
    }
</script>
</body>
</html>