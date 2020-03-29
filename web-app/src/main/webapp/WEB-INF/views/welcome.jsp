<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Only Travel</title>

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/resources/css/navbar-style.css" type="text/css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>

<jsp:include page="blocks/navbar.jsp" />

<div class="jumbotron">
    <div class="container">
<%--        <h1><spring:message code="greeting"/></h1>--%>
        <h1>hello!!!!</h1>
        <p>think about it!!!!!!!!!!</p>
    </div>
</div>

<div class="container">
    <div id="tours">
        <script type="text/javascript">
            $(document).ready(function () {

                $.get("/tours", function (data) {
                    var rowsHtml;
                    for (var j = 0; j < data.length; j = j + 3) {

                        var date = new Date(data[j].exitDate);
                        var dd = date.getDate();
                        var mm = date.getMonth() + 1;
                        var yyyy = date.getFullYear();
                        if (dd < 10) {
                            dd = '0' + dd;
                        }
                        if (mm < 10) {
                            mm = '0' + mm;
                        }
                        var exitDate = dd + '/' + mm + '/' + yyyy;

                        rowsHtml = "<div class='row'>";
                        rowsHtml += "<div class='col-md-4'>";
                        rowsHtml += "<img class='textWrapLeft' src='/resources/images/lists.jpg' alt='' width='132' height='174' >";
                        rowsHtml += "<p>" + data[j].name + "</a><p>";
                        rowsHtml += "<p><span class='glyphicon glyphicon-calendar' > " + exitDate + "</a><p>";
                        rowsHtml += "<p><span class='glyphicon glyphicon-time' > " + data[j].numberDays + "</p>";
                        rowsHtml+=" <a href='/tour?id="+data[j].id+"'class='btn btn-default'>View details</a><br/><br/>";
                        rowsHtml += "</div>";
                        if (j + 1 < data.length) {

                            var date = new Date(data[j+1].exitDate);
                            var dd = date.getDate();
                            var mm = date.getMonth() + 1;
                            var yyyy = date.getFullYear();
                            if (dd < 10) {
                                dd = '0' + dd;
                            }
                            if (mm < 10) {
                                mm = '0' + mm;
                            }
                            var exitDate = dd + '/' + mm + '/' + yyyy;

                            rowsHtml += "<div class='col-md-4'>";
                            rowsHtml += "<img class='textWrapLeft' src='/resources/images/lists.jpg' alt='' width='132' height='174' >";
                            rowsHtml += "<p><a href='#'>" + data[j+1].name + "</a><p>";
                            rowsHtml += "<p><span class='glyphicon glyphicon-calendar' > " + exitDate + "</a><p>";
                            rowsHtml += "<p><span class='glyphicon glyphicon-time'> " + data[j+1].numberDays + "</p>";
                            rowsHtml+=" <p><a class='btn btn-default' href='#' role='button'>View details &raquo;</a></p>";
                            rowsHtml += "</div>";
                        }


                        if (j + 2 < data.length) {
                            var date = new Date(data[j+2].exitDate);
                            var dd = date.getDate();
                            var mm = date.getMonth() + 1;
                            var yyyy = date.getFullYear();
                            if (dd < 10) {
                                dd = '0' + dd;
                            }
                            if (mm < 10) {
                                mm = '0' + mm;
                            }
                            var exitDate = dd + '/' + mm + '/' + yyyy;

                            rowsHtml += "<div class='col-md-4'>";
                            rowsHtml += "<img class='textWrapLeft' src='/resources/images/lists.jpg' alt='' width='132' height='174' >";
                            rowsHtml += "<p><a href='#'>" + data[j+2].name + "</a><p>";
                            rowsHtml += "<p><span class='glyphicon glyphicon-calendar' > " + exitDate + "</a><p>";
                            rowsHtml += "<p><span class='glyphicon glyphicon-time' > " + data[j+1].numberDays + "</p>";
                            rowsHtml+=" <p><a class='btn btn-default' href='#' role='button'>View details &raquo;</a></p>";
                            rowsHtml += "</div>";
                        }

                        rowsHtml += "</div>";
                        $('#tours').append(rowsHtml);
                    }

                });
            });

            var showTour = function (id) {
                $.ajax({
                    type: "GET",
                    url: "/tour?id=" + id,
                    // success: function (result) {
                    //     alert(result);
                    // }
                });
            };

        </script>
    </div>





</div>

<jsp:include page="blocks/footer.jsp" />

<script>window.jQuery || document.write('<script src="/webjars/jquery/3.1.1/jquery.min.js"><\/script>')</script>

</body>
</html>
