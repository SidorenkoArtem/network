<%--
  Created by IntelliJ IDEA.
  User: Irene
  Date: 18.08.2018
  Time: 16:55
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
<div hidden="true" id="afterRegistration">
    <h1>Регистрация прошла успешно <a href="/login">Log in</a></h1>
</div>
<div id="registration" class="container">
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
            <select id="sex">
                <option value="MALE">Male</option>
                <option value="FEMALE">Female</option>
            </select>
        </div>
        <div class="col-sm-3">
            <p>Пол</p>
        </div>
    </div>

    <div class="row" hidden>
        <div class="col-sm-3">
            <input id="photoUrl" placeholder="Фотография"/>
        </div>
        <div class="col-sm-3">
            <p>Фотография</p>
        </div>
    </div>

    <form method="post" id="fileinfo" name="fileinfo" onsubmit="return submitForm();">
        <label>Выберите фотографию:</label><br>
        <input type="file" name="file" required />
        <input type="submit" value="Сохранить" />
    </form>

    <div class="row">
        <div class="col-sm-3">
            <input id="country" placeholder="Страна"/>
        </div>
        <div class="col-sm-3">
            <p>Страна</p>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-3">
            <input id="city" placeholder="Город"/>
        </div>
        <div class="col-sm-3">
            <p>Город</p>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-3">
            <input id="login" placeholder="Login"/>
        </div>
        <div class="col-sm-3">
            <p>Login</p>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-3">
            <input id="password" type="password" placeholder="Password"/>
        </div>
        <div class="col-sm-3">
            <p>Password</p>
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
            <button type="submit" onclick="registration()" class="btn btn-default">Зарегистрировать</button>
    </div>
</div>
<script>

    function submitForm() {
        var fd = new FormData(document.getElementById("fileinfo"));
        fd.append("label", "WEBUPLOAD");
        $.ajax({
            url: "user/image",
            type: "POST",
            data: fd,
            processData: false,  // tell jQuery not to process the data
            contentType: false   // tell jQuery not to set contentType
        }).done(function( data ) {
            console.log( data );
            $("#photoUrl").val(data.path);
        });
        return false;
    }

    function registration() {
        var userRequest = readData();
        if (userRequest == false) {
            return;
        }
        var request = $.ajax({
            url:"/user/registration",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            method: "POST",
            data: JSON.stringify(userRequest),
        });
        $("#registration").hide();
        $("#afterRegistration").show();
    }

    function readData() {
        var name = $("#name").val();
        if (name.length < 3) {
            alert("Введите имя");
            return false;
        }
        var firstName = $("#firstName").val();
        if (firstName.length < 3) {
            alert("Введите фамилию");
            return false;
        }
        var surname = $("#surname").val();
        if (surname.length < 3) {
            alert("Введите отчество");
            return false;
        }
        var sex = $("#sex").val();
        var photoUrl = $("#photoUrl").val();
        var country = $("#country").val();
        var city = $("#city").val();
        var userLogin = $("#login").val();
        if (userLogin.length < 5) {
            alert("Введите login. Колличество символов должно быть больше 5");
            return false;
        }
        var password = $("#password").val();
        if (password.length<5) {
            alert("Введите password. Колличество символов должно быть больше 5");
            return false;
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
            "sex": sex,
            "country": country,
            "city": city,
            "photoUrl": photoUrl,
            "login": userLogin,
            "password": password,
            "birthday":"",
            "role":"USER",
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
