<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="Header.jsp"/>
    <title>${groupData.socialGroup.header}</title>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div>
<h1>${groupData.socialGroup.name}</h1>
<p>${groupData.socialGroup.description}</p>
<c:if test="${groupData.subscriber == true}">
    <button onclick="leaveFromGroup()">Отписаться</button>
    <script>
        function leaveFromGroup() {
            $.ajax({
                url:"/user/groups/${groupData.socialGroup.id}",
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
                url:"/user/groups/${groupData.socialGroup.id}",
                method: "post"
            });
        }
    </script>
</c:if>
<c:if test="${groupData.socialGroup.hideReaders != true}">
    <h1>Users(${groupData.usersCount}):</h1>
    <c:forEach var="user" items="${groupData.users}">
        <a href="/${user.userId}">
            <div>
                <img src="${user.photoUrl}"/>
            </div>
            <p><c:out value="${user.firstName}"/> <c:out value="${user.name}"/></p>
        </a>
    </c:forEach>
</c:if>
</div>
</body>
</html>
