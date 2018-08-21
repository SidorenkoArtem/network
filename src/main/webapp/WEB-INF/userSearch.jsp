<%--
  Created by IntelliJ IDEA.
  User: Irene
  Date: 19.08.2018
  Time: 23:05
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
<div class="container">
    <div>
        <jsp:include page="mainMenu.jsp"/>
    </div>
    <div class="col-sm-3">
        <div>
            <jsp:include page="leftMenu.jsp"/>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-3">
            <input id="name" placeholder="name">
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <input id="firstName" placeholder="first name">
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <input id="city" placeholder="city">
        </div>
    </div>
    <div class="col-sm-1">
        <button onclick="addElement()">Поиск</button>
    </div>
</div>
<h1>Пользователи: </h1>

<script>

    $(document).ready(function () {
        addElement();

    });
    function addElement() {
        $("#usersContainer").remove();
        $("#content").append("<div id=\"usersContainer\" class=\"my-3 p-3 bg-white rounded shadow-sm\"></div>");
        $.ajax({
            url: "/user/search?name="+$("#name").val()+"&firstName="+$("#firstName").val()+"&city="+$("#city").val(),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            method: "GET",
            success: function(response) {
                console.log(response);
                var userFriends = response.users;
                userFriends.forEach(function(el) {
                    console.log(el.friend);
                    var domElement =
                        '<div class=\"container\">' +'<div class=\"row\">' +
                        '<a href="/' + el.userId + '">' +
                        '<div class=\"col-sm-2\">' +
                        '<img src="'+ el.photoUrl + '" height="50" width="50"/>' +
                        '</div>' +
                        '<div class=\"col-sm-4\">' +
                        '<p>' + el.firstName + ' ' + el.name + '</p>';
                        if (el.userPermission.showLocation) {
                            domElement = domElement + '<p>' + el.country + '</p>';
                            domElement = domElement + '<p>' + el.city + '</p>';
                        }
                        if (el.userPermission.showGender) {
                            domElement = domElement + '<p>' + el.gender + '</p>';
                        }
                        domElement = domElement + '</a>';
                    domElement = domElement
                        + '<button id="' + el.userId + 'add" onclick="sendRequestOnFriendship(' + el.userId + ')">Добавить в друзья' + '</button>';
                    domElement = domElement
                        + '<button id="' + el.userId + 'decline" onclick="declineFriend(' + el.userId + ')">Удалить из друзей' + '</button>';
                        domElement = domElement +
                        '</div>' +

                        '</div>' +
                        '<div>' +
                        '</div>' +
                        '</div>';
                    $("#usersContainer").append(domElement);
                    if (el.friend) {
                        $("#" + el.userId + "add").hide();
                    } else {
                        $("#" + el.userId + "decline").hide();
                    }
                });
            }
        });
    }
    function declineFriend(id) {
        $.ajax({
            url: "/user/" + id + "/friends",
            type: "delete"
        });
        $("#" + id + "decline").hide();
        $("#" + id + "add").show();

    }

    function sendRequestOnFriendship(id) {
        $.ajax({
            url: "/user/" + id + "/friends",
            type: "post"
        });
        $("#" + id + "add").hide();
        $("#" + id + "decline").show();
    }
</script>
<div id="content" class="container">
    <div id="usersContainer" class="my-3 p-3 bg-white rounded shadow-sm">
    </div>
</div>
</body>
</html>
