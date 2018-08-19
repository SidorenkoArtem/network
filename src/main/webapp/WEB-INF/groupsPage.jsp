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
<h1>Группы: </h1>
<button id="loadGroups" onclick="addElement()">More groups</button>
<script>
    var page = 0;
    var count = 0;
    $(document).ready(function () {
        addElement();

    });
    function addElement() {
        $.ajax({
            url:"/user/${userId}/groups",
            method:"GET",
            success: function(response) {
                var groups = response.socialGroups;
                groups.forEach(function(el) {
                    console.log(el);
                    var domRowElement =
                        "<div class='row'>" +
                            "<a href=\"/group/" + el.id + "\">" +
                                "<div class='col-sm-1'>" +
                                    "<img height=\"60\" width=\"60\" src=\"" + el.imageUrl + "\"/>" +
                                "</div>" +
                                "<div class='col-sm-4'>" +
                                    "<p class=\"media-body pb-3 mb-0 small lh-125 border-bottom border-gray\">" +
                                        "<strong class=\"d-block text-gray-dark\">" + el.name + "</strong>" +
                                    "</p>" +
                                "</div>" +
                            "</a>" +
                        "</div>";
                    $("#groupsContainer").append(domRowElement);
                    count++;
                    if (count === response.count) {
                       $("#loadGroups").hide();
                    }
                });
                page++;
            }
        });
        page++;
    }
</script>

<div class="container">
    <div id="groupsContainer" class="my-3 p-3 bg-white rounded shadow-sm">
    </div>
</div>
</body>
</html>