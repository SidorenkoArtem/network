<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<h1>Groups:</h1>
<div id="groupsContainer">
</div>
<button id="loadGroups" onclick="addElement()">More groups</button>
<script>
    var page = 0;
    var count = 0;

    function addElement() {
        $.ajax({
            url:"/user/${userId}/groups",
            method:"GET",
            success: function(response) {
                var groups = response.socialGroups;
                groups.forEach(function(el){
                    var domElement = '<div><a href="/' + el.id + '"> <img src="'+ el.imageUrl +
                        '"></div><p>' + el.name + ' ' + '</p>';
                    $("#groupsContainer").append(domElement);
                   count++;
                   if (count === response.count) {
                       $("#loadGroups").hide();
                   }
                });
                page++;
            }
        });
    }
</script>
</body>
</html>
