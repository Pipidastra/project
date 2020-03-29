
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="addingCompany" action="/company/addingCompany" method="post">
    <label>Company name: </label>
    <input type="text" name="name">
    <br/>
    <label>Phone: </label>
    <input type="text" name="phone">
    <br/>
    <label>Email: </label>
    <input type="text" name="email">
    <br/>
    <label>Address: </label>
    <input type="text" name="address">
    <br/>
    <button>Submit</button>
</form>
</body>
</html>
