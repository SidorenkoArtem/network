<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${user.name}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div>
    <jsp:include page="mainMenu.jsp" />
</div>
<div>
    <jsp:include page="leftMenu.jsp" />
</div>

<div>
    <p>"${otherUserPageData.user.photoUrl}"</p>
<picture/>
</div>
<c:if test="${otherUserPageData.socialGroups != null}">
    <div>
        <h1>Groups:</h1>
        <c:forEach var="i" items="${otherUserPageData.socialGroups}">
            <p><c:out value="${i.name}"/></p>
        </c:forEach>
    </div>
</c:if>
<c:if test="${otherUserPageData.friends != null}">
    <div>
        <h1>Friends:</h1>
        <c:forEach var="i" items="${otherUserPageData.friends}">
            <a href="/${i.friend.userId}">
                <div>
                    <img width="20" height="20" src="${i.friend.photoUrl}"/>
                </div>
                <p><c:out value="${i.friend.firstName}"/> <c:out value="${i.friend.name}"/></p>
            </a>
        </c:forEach>
    </div>
</c:if>
<c:if test="${otherUserPageData.gifts != null}">
    <div>
        <h1>Gifts:</h1>
        <c:forEach var="i" items="${otherUserPageData.gifts}">
            <div>
                <img width="30" height="30" src="${i.gift.imageUrl}"/>
            </div>
            <p><c:out value="${i.user.firstName}"/> <c:out value="${i.user.name}"/></p>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
