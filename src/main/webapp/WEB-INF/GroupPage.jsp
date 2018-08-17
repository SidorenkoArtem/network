<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="Header.jsp"/>
    <title>${groupData.socialGroup.header}</title>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page=".jsp"/>
<div>
    <h1>${groupData.socialGroup.name}</h1>
    <p>${groupData.socialGroup.description}</p>
    <c:if test="${groupData.subscriber == true}">
        <button onclick="leaveFromGroup()">Отписаться</button>
        <script>
            function leaveFromGroup() {
                $.ajax({
                    url: "/user/groups/${groupData.socialGroup.id}",
                    method: "delete"
                });
            }
        </script>
    </c:if>
    <c:if test="${groupData.subscriber == false}">
        <button id="subscribe" onclick="subscribe()">Подписаться</button>
        <script>
            function subscribe() {
                $.ajax({
                    url: "/user/groups/${groupData.socialGroup.id}",
                    method: "post"
                });
            }
        </script>
    </c:if>
    <c:if test="${groupData.socialGroup.hideReaders != true}">
        <h1>Users(${groupData.usersCount}):</h1>
        <c:forEach var="user" items="${groupData.users}">
            <div class="row">
                <a href="/${user.userId}">
                    <div class="col-sm-1">
                        <img src="${user.photoUrl}" width="50" height="50"/>
                    </div>
                    <div class="col-sm-3">
                        <p><c:out value="${user.firstName}"/> <c:out value="${user.name}"/></p>
                    </div>
                </a>
            </div>
        </c:forEach>
    </c:if>
</div>
</body>
</html>
