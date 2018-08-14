<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="Header.jsp"/>
    <title>${user.name}</title>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<form method="POST" action="/user/image" enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>
<div>
    <jsp:include page="mainMenu.jsp"/>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-3">
            <div>
                <jsp:include page="leftMenu.jsp"/>
            </div>
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
            <div>
                <c:if test="${otherUserPageData.user.country != null && otherUserPageData.user.city != null}">
                    <h3>Страна: ${otherUserPageData.user.country}</h3>
                    <h3>Город: ${otherUserPageData.user.city}</h3>
                </c:if>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-3">
            <c:if test = "${otherUserPageData.currentUser == false && otherUserPageData.authenticated}">
                <button id="writeMessage" onclick="openWindow()">Написать сообщение</button>
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
                            url:"/user/${otherUserPageData.user.id}/friends",
                            type:"post"
                        });
                    }

                    function removeFriend(){
                        $("#removeFromFriend").hide();
                        $.ajax({
                            url: "/user/${otherUserPageData.user.id}/friends",
                            type:"delete",
                            success: function(result) {
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
            <c:if test="${otherUserPageData.socialGroups != null}">
                <div>
                    <a href="/user/friends/${otherUserPageData.user.id}"><h3>Groups:</h3></a>
                    <c:forEach var="group" items="${otherUserPageData.socialGroups}">
                        <a href="/groups/${group.id}">
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
                            <p><c:out value="${friend.friend.firstName}"/> <c:out value="${friend.friend.name}"/></p>
                        </a>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${otherUserPageData.gifts != null}">
                <div>
                    <h1>Gifts:</h1>
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
                    <input type="text" placeholder="Введите сообщение"/>
                </c:if>
                <div id="wall">
                    <input id="loadPosts" type="button" onclick="addElements()"></div>
                </div>
                <script>
                    var page = 0;
                    var count = 0;

                    function addElements() {
                        console.log("до ajax");
                        $.ajax({
                            url:"/wall/user/${otherUserPageData.user.id}",
                            method:"GET",
                            success: function (response) {
                                var posts = response.posts;
                                console.log(response);
                                console.log(response.posts);
                                posts.forEach(function(el) {
                                    var domElement =
                                        "<div class=\"container\">" +
                                                "<div class=\"row\">" +
                                                    "<div class=\"col-sm-2\">" +
                                                        "<img src=\" + el.user.photoUrl + \">" +
                                                    "</div>" +
                                                    "<div class=\"col-sm-6\">" +
                                                        "<p>" + el.user.firstName + " " + el.user.name + "</p>" +
                                                    "</div>" +
                                                "</div>" +
                                                "<div class=\"row\">" +
                                                    "<p>" + el.text + "</p>" +
                                                "</div>" +
                                        "</div>";
                                    $("#wall").append(domElement);
                                    count++;
                                    if (count === response.count) {
                                        $("#loadPosts").hide();
                                    }
                                });
                            }
                        });
                    }
                    page++;
                </script>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
