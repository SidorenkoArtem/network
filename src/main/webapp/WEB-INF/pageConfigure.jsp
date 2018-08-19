<%--
  Created by IntelliJ IDEA.
  User: Irene
  Date: 19.08.2018
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="Header.jsp"/>
    <title>Title</title>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div hidden="true" id="afterSave">
    <h1>Настройки страницы сохранены</h1>
</div>
<div id="configuration" class="container">
    <div class="row">
        <div class="col-sm-3">
            <input id="name" placeholder="Имя"/>
        </div>
        <div class="col-sm-3">
            <p>Имя</p>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-3">
            <input id="firstName" placeholder="Фамилия"/>
        </div>
        <div class="col-sm-3">
            <p>Фамилия</p>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-3">
            <input id="surname" placeholder="Отчество"/>
        </div>
        <div class="col-sm-3">
            <p>Отчество</p>
        </div>
    </div>

    <div class="row">
    <div class="col-sm-3">
        <input id="showPage" type="checkbox"/>
    </div>
    <div class="col-sm-3">
        <p>Страница видна не зарегистрированным пользователям</p>
    </div>
</div>

<div class="row">
    <div class="col-sm-3">
        <input id="showFriends" type="checkbox"/>
    </div>
    <div class="col-sm-3">
        <p>Отображать друзей</p>
    </div>
</div>

<div class="row">
    <div class="col-sm-3">
        <input id="showGifts" type="checkbox"/>
    </div>
    <div class="col-sm-3">
        <p>Отображать подарки</p>
    </div>
</div>

<div class="row">
    <div class="col-sm-3">
        <input id="showGroups" type="checkbox"/>
    </div>
    <div class="col-sm-3">
        <p>Отображать группы</p>
    </div>
</div>

<div class="row">
    <div class="col-sm-3">
        <input id="showAdditionalInformation" type="checkbox"/>
    </div>
    <div class="col-sm-3">
        <p>Отображать дополнительную информацию</p>
    </div>
</div>
<div class="row">
    <div class="col-sm-3">
        <input id="showBirthday" type="checkbox"/>
    </div>
    <div class="col-sm-3">
        <p>Отображать день рождения</p>
    </div>
</div>

<div class="row">
    <div class="col-sm-3">
        <input id="showSex" type="checkbox"/>
    </div>
    <div class="col-sm-3">
        <p>Отображать пол</p>
    </div>
</div>
    <div class="row" >
        <button type="submit" onclick="configure()" class="btn btn-default">Сохранить</button>
    </div>
</div>



<script>

    $(document).ready(function(){
        getUser();
    });

    function getUser() {
        $.ajax({
            url:"/user",
            method:"GET",
            success: function (result) {
                console.log(result);
                $("#name").val(result.name);
                $("#firstName").val(result.firstName);
                $("#surname").val(result.surname);
                $("#showPage").prop('checked', result.showPage);
                $("#showFriends").prop('checked', result.showFriends);
                $("#showGifts").prop('checked', result.showGifts);
                $("#showGroups").prop('checked', result.showGroups);
                $("#showAdditionalInformation").prop('checked', result.showLocation);
                $("#showBirthday").prop('checked', result.showBirthday);
                $("#showSex").prop('checked', result.showSex);
            }
        });
    }

    function configure() {
        var userRequest = readData();
        console.log(userRequest);
        var request = $.ajax({
            url:"/user/configuration",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            method: "PUT",
            data: JSON.stringify(userRequest),
        });
        $("#configuration").hide();
        $("#afterSave").show();
    }

    function readData() {
        var name = $("#name").val();
        if (name.length < 3) {
            alert("Введите имя");
            return;
        }
        var firstName = $("#firstName").val();
        if (firstName.length < 3) {
            alert("Введите фамилию");
            return;
        }
        var surname = $("#surname").val();
        if (surname.length < 3) {
            alert("Введите отчество");
            return;
        }
        var showPage = $("#showPage").prop('checked');
        var showFriends = $("#showFriends").prop('checked');
        var showGifts = $("#showGifts").prop('checked');
        var showGroups = $("#showGroups").prop('checked');
        var showAdditional = $("#showAdditionalInformation").prop('checked');
        var showSex = $("#showSex").prop('checked');
        var showBirthday = $("#showBirthday").prop('checked');
        return {
            "name": name,
            "firstName": firstName,
            "surname": surname,
            "showPage": showPage,
            "showWall":"true",
            "showFriends": showFriends,
            "showGifts": showGifts,
            "showGroups": showGroups,
            "showLocation": showAdditional,
            "showSex": showSex,
            "showBirthday": showBirthday
        };
    }
</script>
</body>
</html>
