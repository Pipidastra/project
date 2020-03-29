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
    <link rel="stylesheet" href="/resources/css/tourPage.css" type="text/css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<br>

<jsp:include page="blocks/navbar.jsp"/>

<div id="tours">

    <div class="blog-post">
        <img class='textWrapLeft' src='/resources/images/lists.jpg' alt='' width='132' height='174' >
        <h2 class="blog-post-title">${tour.name}</h2>
        <div> ${tour.cost}</div>
        <div><span class="glyphicon glyphicon-globe"/> ${tour.country}</div>
        <div><span class="glyphicon glyphicon-calendar"/> ${tour.exitDate}</div>
        <div><span class="glyphicon glyphicon-time"/> ${tour.numberDays}</div>
        <div>${tour.description}</div>

        <a href='#'class='btn btn-default'>Reserve</a>
    </div>
</div>
</br><br/><br/>
<%--<label for="comment">Shadow and placeholder</label>--%>
<div class="form-group shadow-textarea">
    <textarea class="commentForm" id="comment" rows="4" cols="70" placeholder="Write comment here..."></textarea>
</div>
<jsp:include page="blocks/footer.jsp"/>
</body>
</html>
