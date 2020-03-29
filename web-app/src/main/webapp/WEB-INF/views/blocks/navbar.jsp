<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--    <meta name="description" content="">--%>
    <%--    <meta name="author" content="">--%>

    <title>Only Travel</title>

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/resources/css/navbar-style.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/model.css" type="text/css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>


<%--<div class="wrapper fadeInDown">--%>
<%--    <div id="formContent">--%>
<%--        <!-- Tabs Titles -->--%>

<%--        <!-- Icon -->--%>
<%--        <div class="fadeIn first">--%>
<%--            <img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon" alt="User Icon" />--%>
<%--        </div>--%>

<%--        <!-- Login Form -->--%>
<%--        <form>--%>
<%--            <input type="text" id="login" class="fadeIn second" name="login" placeholder="login">--%>
<%--            <input type="text" id="password" class="fadeIn third" name="login" placeholder="password">--%>
<%--            <input type="submit" class="fadeIn fourth" value="Log In">--%>
<%--        </form>--%>

<%--        <!-- Remind Passowrd -->--%>
<%--        <div id="formFooter">--%>
<%--            <a class="underlineHover" href="#">Forgot Password?</a>--%>
<%--        </div>--%>

<%--    </div>--%>
<%--</div>--%>
<div class="modal fade" id="loginModal">
    <div class="modal-dialog" id="formContent">
        <div>
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
                <h5 it="title">Sign In</h5>
            </div>
            <div class="modal-body">
                <form id="loginForm">
                    <div class="form-label-group">
                        <input type="email" id="inputEmail" class="form-control"  placeholder="Email" required
                               autofocus>
                        <label for="inputEmail">Email</label>
                    </div>
                    <div class="form-label-group">
                        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                        <label for="inputPassword">Password</label>
                    </div>
                    <div><h5 id="mistake"></h5></div>
                    <button class="btn btn-lg btn-primary btn-block text-uppercase" id="loginBtn" onclick="login()"
                            type="submit">Sign in
                    </button>
                </form>

            </div>
        </div>

    </div>
</div>

<nav class="navbar navbar-expand-md  fixed-top">
    <div class="container">
        <div id="navbar" class="collapse navbar-collapse">
            <a href="/" id="home">
                <img src='/resources/images/home_label.svg' width="50" height="50" class="d-inline-block align-top"
                     alt="">OnlyTravel
            </a>
            <div class="position-1">
                <button type="button" class="btn btn-outline-primary">Last-minute offer</button>
                <button type="button" class="btn btn-outline-primary ">Bus tours</button>
                <button type="button" class="btn btn-outline-primary ">Air tours</button>
                <button type="button" class="btn btn-outline-primary ">Travel agency</button>
            </div>
            <div id="user"></div>
            <script>
                if (sessionStorage.length == 0) {
                    $('#user').append('   <a href="#" class="btn btn-info position-6" id="login" onclick="loginForm(0)">Login</a> <br/><br/>')
                } else {
                    $('#user').append('<span class="glyphicon glyphicon-user">' + sessionStorage.getItem('name'))
                }
            </script>


        </div><!--/.navbar-collapse -->
    </div>
</nav>


<script>
    function loginForm() {
        $("#loginForm")[0].reset();
        $("#loginModal").modal();

    }


    function login() {
        const email = $("#inputEmail").val();
        const password = $("#inputPassword").val();

        $.ajax({
            type: 'POST',
            data: {
                email,
                password
            },
            url: '/login',
            success: function (res) {
                alert("here");
                alert(res.status);
                // $("#loginModal").modal("hide");
                // window.location.replace("http://localhost:8080/");
            },
            error: function (res) {
                alert(res.status);
            $('#mistake').append("<span class='glyphicon glyphicon-remove-circle' style='color: red'>" +
                "<h5>Wrong login or password</h5>");
            }
        });

    };
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != '') {
                window.location.replace('/?lang=' + selectedOption);
            }
        });
    });
</script>

</body>
</html>
