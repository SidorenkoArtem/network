<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="Header.jsp"/>
    <title>${user.name}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
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
                <script>
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
                    <h1>Groups:</h1>
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
                    <h1>Friends:</h1>
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
        </div>
    </div>
</div>
</body>
</html>
