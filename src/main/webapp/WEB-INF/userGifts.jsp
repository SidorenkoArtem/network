
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="Header.jsp"/>
    <title>Gifts</title>
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
        <button class="w3-bar-item w3-button tablink" onclick="openPage(event, 'gifts')">Подарки</button>
        <button class="w3-bar-item w3-button tablink" onclick="openPage(event, 'giftRequests')">Принять подарки</button>
    </div>
    <div style="margin-left:130px">
        <div id="gifts" class="w3-container city" style="display:none">
            <h2></h2>
        </div>
        <div id="giftRequests" class="w3-container city" style="display:none">
            <h2></h2>
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
        }
        $.ajax({
            url: "/user/${userId}/gifts?status=" + name,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            method: "GET",
            success: function(response) {
                console.log(response);
                var userGifts = response.userGifts;
                userGifts.forEach(function(el) {
                    var domElement =
                        '<div id=\"' + el.id + '\" class=\"container\">' +'<div class=\"row\">';
                    if (name =="REQUESTED") {
                        domElement = domElement +
                            "<button onclick=\"approve("+el.id+")\">approve</button>" +
                            "<button onclick=\"decline("+el.id+")\">decline</button>";
                    }
                    domElement = domElement +
                        '<a href="/' + el.user.userId + '">' +
                        '<div class=\"col-sm-2\">' +
                        '<img src="'+ el.gift.imageUrl + '"/>' +
                        '</div>' +
                        '<div class=\"col-sm-4\">' +
                        '<p>' + el.user.firstName + ' ' + el.user.name + '</p>' +
                        '<p>' + el.createTimestamp + '</p>' +
                        '</div>' +
                        '</a>' +
                        '</div>' +
                        '<div>' +
                        '</div>' +
                        '</div>';
                    switch (name) {
                        case "APPROVED" :
                            $("#gifts").append(domElement);
                            break;
                        case "REQUESTED" :
                            $("#giftRequests").append(domElement);

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
        }
    }

    function approve(id) {
        $.ajax({
            url:"/user/gift/" + id,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            method: "PUT",
            data: JSON.stringify("APPROVED")
        });
        $("#" + id).hide();
    }

    function decline(id) {
        $.ajax({
            url:"/user/gift/" + id,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            method: "PUT",
            data: JSON.stringify("BLOCKED")
        });
        $("#" + id).hide();
    }
</script>
</body>
</html>
