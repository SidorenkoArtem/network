<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="Header.jsp"/>
    <title>Friends</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
    <div class="w3-sidebar w3-bar-block w3-light-grey w3-card" style="width:150px">
        <button class="w3-bar-item w3-button tablink" onclick="openPage(event, 'friends')">Друзья</button>
        <button class="w3-bar-item w3-button tablink" onclick="openPage(event, 'friendshipRequest')">Заявки в друзья</button>
        <button class="w3-bar-item w3-button tablink" onclick="openPage(event, 'blocked')">Блокированные</button>
    </div>

    <div style="margin-left:130px">
        <div id="friends" class="w3-container city" style="display:none">
            <h2></h2>
            <button id="loadFriends" onclick="addElement('APPROVED')">Загрузить...</button>
        </div>
        <div id="friendshipRequest" class="w3-container city" style="display:none">
            <h2></h2>
            <button id="loadFriendshipRequest" onclick="addElement('REQUESTED')">Загрузить...</button>
        </div>
        <div id="blocked" class="w3-container city" style="display:none">
            <h2></h2>
            <button id="loadBlocked" onclick="addElement('BLOCKED')">Загрузить...</button>
        </div>

    </div>
</div>
<script>


    function openPage(evt, cityName) {
        var i, x, tablinks;
        x = document.getElementsByClassName("city");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
        }
        document.getElementById(cityName).style.display = "block";
        evt.currentTarget.className += " w3-red";
    }
</script>

<script>
    var menu ={
        page : {
            approved : 0,
            requested : 0,
            blocked :0
        },
        count : {
            approved : 0,
            requested : 0,
            blocked :0
        }
    };

    $(document).ready(function () {
        addElement("APPROVED");
        addElement("REQUESTED");
        addElement("BLOCKED");

    });
    function addElement(name) {
        var page;
        var count;
        switch (name) {
            case "APPROVED" :
                page = menu.page.approved;
                count = menu.count.approved;
                break;
            case "REQUESTED" :
                page = menu.page.requested;
                count = menu.count.requested;
                break;
            case "BLOCKED" :
                page = menu.page.blocked;
                count = menu.count.blocked;
                break;
        }
        $.ajax({
            url: "/user/${userId}/friends?page=" + page + "&status=" + name,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            method: "GET",
            success: function(response) {
                var userFriends = response.userFriends;
                userFriends.forEach(function(el) {
                    var domElement =
                        '<div id=\"' + el.friend.userId + '\" class=\"container\">' +'<div class=\"row\">';
                    if (name =="REQUESTED") {
                        domElement = domElement +
                            "<button onclick=\"approve("+el.friend.userId+")\">approve</button>" +
                            "<button onclick=\"decline("+el.friend.userId+")\">decline</button>";
                    }
                        domElement = domElement +
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
                        '</div>' +
                        '</div>';
                    switch (name) {
                        case "APPROVED" :
                            $("#friends").append(domElement);
                            if (count === response.count) {
                                $("#loadFriends").hide();
                            }
                            break;
                        case "REQUESTED" :
                            $("#friendshipRequest").append(domElement);
                            if (count === response.count) {
                                $("#loadFriendshipRequest").hide();
                            }
                            break;
                        case "BLOCKED" :
                            $("#blocked").append(domElement);
                            if (count === response.count) {
                                $("#loadBlocked").hide();
                            }
                            break;
                    }
                    count++;
                });
            }
        });
        page++;
        switch (name) {
            case "APPROVED" :
                menu.page.approved = page;
                menu.count.approved = count;
                break;
            case "REQUESTED" :
                menu.page.requested = page;
                menu.count.requested = count;
                break;
            case "BLOCKED" :
                menu.page.blocked = page;
                menu.count.blocked = count;
                break;
        }
    }

    function approve(id) {
        $.ajax({
            url:"/user/" + id + "/friends",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            method: "PUT",
            data: JSON.stringify("APPROVED")
        });
        $("#" + id).hide();
    }

    function decline(id) {
        $.ajax({
            url:"/user/" + id + "/friends",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            method: "PUT",
            data: JSON.stringify("BLOCKED")
        });
        $("#" + id).hide();
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