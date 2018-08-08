<%--
  Created by IntelliJ IDEA.
  User: дима
  Date: 08.08.2018
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${groupData.socialGroup.header}</title>
</head>
<body>
<h1>${groupData.socialGroup.name}</h1>
<p>${groupData.socialGroup.description}</p>
<button>Подписаться</button>
<button>Отписаться</button>
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
</body>
</html>
