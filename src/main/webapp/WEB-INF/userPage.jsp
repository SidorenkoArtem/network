<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="Header.jsp"/>
    <title>${user.name}</title>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}

        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }

        /* Modal Content */
        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        /* The Close Button */
        .close {
            color: #aaaaaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>


    <div>
        <jsp:include page="mainMenu.jsp"/>
    </div>
    <c:if test="${otherUserPageData.authenticated == false}">
        <div>
            <form method="post">
                <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input name="username" type="text" class="form-control" id="username" placeholder="Username">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input name="password" type="password" class="form-control" id="password" placeholder="Password">
                </div>
                <input type="submit" name="login" class="login loginmodal-submit" value="Login">
            </form>
        </div>
    </c:if>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <c:if test="${otherUserPageData.authenticated == true}">
                    <div>
                        <jsp:include page="leftMenu.jsp"/>
                    </div>
                </c:if>
            </div>
            <div class="col-sm-3">
                <img width="200" height="200" src="${otherUserPageData.user.photoUrl}"/>
            </div>

            <div class="col-sm-6">
                <div>
                    <h2>${otherUserPageData.user.firstName} ${otherUserPageData.user.name}</h2>
                </div>
                <div>
                    <h2>${otherUserPageData.user.surname}</h2>
                </div>
                <c:if test="${otherUserPageData.friend == true || otherUserPageData.showPage == true}">
                <div>
                    <c:if test="${otherUserPageData.user.country != null && otherUserPageData.user.city != null}">
                        <h3>Страна: ${otherUserPageData.user.country}</h3>
                        <h3>Город: ${otherUserPageData.user.city}</h3>
                    </c:if>
                </div>
                <div>
                    <c:if test="${otherUserPageData.showSex == true}">
                        <h3>Пол: ${otherUserPageData.user.sex} </h3>
                    </c:if>
                </div>
                <div>
                    <c:if test="${otherUserPageData.showBirthday == true}">
                        <h3>День рождения ${otherUserPageData.user.birthday}</h3>
                    </c:if>
                </div>
                </c:if>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <c:if test="${otherUserPageData.currentUser == false && otherUserPageData.authenticated}">

                    <button id="myBtn">Написать сообщение</button>


                    <div id="myModal" class="modal">


                        <div class="modal-content">
                            <span class="close">&times;</span>
                            <input id="inputMessage" type="text" id="text"/>
                            <button class="btn btn-dafault" onclick="sendMessageToUser()">Отправить</button>
                        </div>

                    </div>
                    <script>
                        function sendMessageToUser() {
                            var content = {
                                "receiverUserId":"${otherUserPageData.user.id}",
                                "text":$("#inputMessage").val(),
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
                            modal.style.display = "none";
                        }
                        // Get the modal
                        var modal = document.getElementById('myModal');

                        // Get the button that opens the modal
                        var btn = document.getElementById("myBtn");

                        // Get the <span> element that closes the modal
                        var span = document.getElementsByClassName("close")[0];

                        // When the user clicks the button, open the modal
                        btn.onclick = function() {
                            modal.style.display = "block";
                        }

                        // When the user clicks on <span> (x), close the modal
                        span.onclick = function() {
                            modal.style.display = "none";
                        }

                        // When the user clicks anywhere outside of the modal, close it
                        window.onclick = function(event) {
                            if (event.target == modal) {
                                modal.style.display = "none";
                            }
                        }
                    </script>
                    <c:if test="${otherUserPageData.hasRequestOnFriendship == false}">

                        <button id="addToFriend" onclick="addToFriend()">Добавить в друзья</button>

                    </c:if>
                    <c:if test="${otherUserPageData.hasRequestOnFriendship && otherUserPageData.friend}">
                        <button id="removeFromFriend" onclick="removeFriend()">Удалить из друзей</button>
                    </c:if>
                    <script>
                        function addToFriend() {
                            $("#removeFromFriend").hide();
                            $.ajax({
                                url: "/user/${otherUserPageData.user.id}/friends",
                                type: "post"
                            });
                        }

                        function removeFriend() {
                            $("#removeFromFriend").hide();
                            $.ajax({
                                url: "/user/${otherUserPageData.user.id}/friends",
                                type: "delete",
                                success: function (result) {
                                    console.log(result);
                                }
                            });
                        }

                        function openWindow() {
                            var request = JSON.stringify({
                                receiverUserId: "${otherUserPageData.user.id}",
                                text: "faded",
                                fileUrl: "/chtotoGdeto"
                            });
                            $.ajax({
                                url: "/user/messages",
                                dataType: 'json',
                                contentType: "application/json; charset=utf-8",
                                method: "POST",
                                data: request
                            });
                        }
                    </script>
                </c:if>
                <c:if test="${otherUserPageData.friend == true || otherUserPageData.showPage == true}">
                <c:if test="${otherUserPageData.socialGroups != null}">
                    <div>
                        <a href="/user/groups/${otherUserPageData.user.id}"><h3>Groups:</h3></a>
                        <c:forEach var="group" items="${otherUserPageData.socialGroups}">
                            <a href="/group/${group.id}">
                                <div>
                                    <img width="20" height="20" src="${group.imageUrl}"/>
                                </div>
                                <p><c:out value="${group.name}"/></p>
                            </a>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${otherUserPageData.friends != null}">
                    <div>
                        <a href="/user/friends/${otherUserPageData.user.id}"><h3>Friends:</h3></a>
                        <c:forEach var="friend" items="${otherUserPageData.friends}">
                            <a href="/${friend.friend.userId}">
                                <div>
                                    <img width="20" height="20" src="${friend.friend.photoUrl}"/>
                                </div>
                                <p><c:out value="${friend.friend.firstName}"/> <c:out
                                        value="${friend.friend.name}"/></p>
                            </a>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${otherUserPageData.gifts != null}">
                    <div>
                        <a href="/user/${otherUserPageData.user.id}/allGifts""><h1>Gifts:</h1></a>
                        <c:forEach var="gift" items="${otherUserPageData.gifts}">
                            <div>
                                <img width="20" height="20" src="${gift.gift.imageUrl}"/>
                            </div>
                            <p><c:out value="${gift.user.firstName}"/> <c:out value="${gift.user.name}"/></p>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
            <div class="col-sm-6">
                <c:if test="${otherUserPageData.user.showWall}">
                    <c:if test="${otherUserPageData.authenticated}">
                        <input id="postText" type="text" placeholder="Введите сообщение"/>
                        <button onclick="sendMessage()">Отправить сообщение</button>
                    </c:if>
                    <div id="beforeWall" class="my-3 p-3 bg-white rounded shadow-sm">
                    </div>
                    <div id="wall" class="my-3 p-3 bg-white rounded shadow-sm">
                    </div>
                    <div class="my-3 p-3 bg-white rounded shadow-sm">
                        <button onclick="addElements()">Load more ...</button>
                    </div>
                    <script>
                        var page = 0;
                        var count = 0;

                        $(document).ready(function () {
                            addElements();
                        });

                        function sendMessage() {
                            var text = {text: $("#postText").val()};
                            $.ajax({
                                url: "/wall/user/${otherUserPageData.user.id}",
                                dataType: "json",
                                contentType: "application/json; charset=utf-8",
                                method: "POST",
                                data: JSON.stringify(text),
                                success: function (result) {
                                    var post = result.wallPostDto;
                                    $(createDomElement(post)).insertAfter("#beforeWall");
                                }
                            });
                        }

                        function createDomElement(el) {
                            return "<div class=\"container\">" +
                                "<div class=\"row\">" +
                                "<c:if test="${otherUserPageData.currentUser == true}"><button onclick=\"deleteU("+el.postId+")\">X</button> </c:if> " +
                                "<div class=\"col-sm-1\">" +
                                "<img height=\"60\" width=\"60\" src=\"" + el.user.photoUrl + "\">" +
                                "</div>" +
                                "<div class=\"col-sm-6\">" +
                                "<div class='row'>" +
                                "<p>" + el.user.firstName + " " + el.user.name + "</p>" +
                                "</div>" +
                                "<div>" +
                                "<p>" + el.text + "</p>" +
                                "</div>" +
                                "</div>" +
                                "</div>" +
                                "</div>";
                        }

                        function deleteU(id) {
                            console.log(id);
                            $("#" + id).remove();
                            $.ajax({
                                url:"/wall/user/post/" + id,
                                method: "delete"
                            });
                        }
                        function addElements() {
                            $.ajax({
                                url: "/wall/user/${otherUserPageData.user.id}?page=" + page + "&limit=5",
                                method: "GET",
                                success: function (response) {
                                    var posts = response.posts;
                                    posts.forEach(function (el) {
                                        var domElement =
                                            "<div id=\"" + el.postId + "\" class=\"container\">" +
                                            "<div class=\"row\">" +
                                            "<c:if test="${otherUserPageData.currentUser == true}"><button onclick=\"deleteU("+el.postId+")\">X</button> </c:if> " +
                                            "<div class=\"col-sm-1\">" +
                                            "<img height=\"60\" width=\"60\" src=\"" + el.user.photoUrl + "\">" +
                                            "</div>" +
                                            "<div class=\"col-sm-6\">" +
                                            "<div class='row'>" +
                                            "<p>" + el.user.firstName + " " + el.user.name + "</p>" +
                                            "</div>" +
                                            "<div>" +
                                            "<p>" + el.text + "</p>" +
                                            "</div>" +
                                            "</div>" +
                                            "</div>" +
                                            "</div>";
                                        $("#wall").append(domElement);
                                        count++;
                                        if (count >= response.count) {
                                            $("#loadPosts").hide();
                                        }
                                    });
                                }
                            });
                            page++;
                        }
                    </script>
                </c:if>
                </c:if>
            </div>
        </div>
    </div>

</body>
</html>
