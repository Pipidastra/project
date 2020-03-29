<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<form id="registration" action="/account/registration" method="post">
    <div class="form-group">
        <label for="name">Username</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="Enter username">
    </div>
    <div class="form-group">
        <label for="name">Phone</label>
        <input type="text" class="form-control" id="phone" name="phone" placeholder="Enter phone">
    </div>
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Password" width="100px">
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>
