<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    <script src=" //cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="//cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">

    <title>Accounts</title>
</head>
<body>

<div class="modal fade" id="addAccountModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times;</a>
                <h4 id="ModalTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="addForm">
                    <fieldset id="submitForm">
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
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Password" width="100px">
                        </div>
                        <div class="form-group">
                            <a href="#" class="btn btn-block btn-danger" id="saveRecord">Save</a>
                        </div>

                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="updateAccountModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a href="#" class="close" data-dismiss="modal">&times</a>
                <h4 id="ModalTitleUpdate"></h4>
            </div>
            <div class="modal-body">
                <form id="updateForm">
                    <fieldset id="submitFormUpdate">
                        <div class="form-group">
                            <input type="hidden" id="idUpdate" name="id">
                            <label for="name">Username</label>
                            <input type="text" class="form-control" id="nameUpdate" name="name"
                                   placeholder="Enter username">
                        </div>
                        <div class="form-group">
                            <label for="name">Phone</label>
                            <input type="text" class="form-control" id="phoneUpdate" name="phone"
                                   placeholder="Enter phone">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="emailUpdate" name="email"
                                   placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <a href="#" class="btn btn-block btn-danger" id="updateRecord">Save</a>
                        </div>

                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<a href="#" class="btn btn-info" onclick="AddNew(0)">Add New User</a> <br/><br/>

<div id="accountsTable"></div>
<script type="text/javascript">
    $(document).ready(function () {

        var html = "<table id='table' class='table table-hover table-bordered table-sm' cellspacing='0' width='100%'>";
        html += "<thead class=\"black white-text\"><tr><th scope=\"col\"></th><th scope=\"col\">Username</th>" +
            "<th scope=\"col\">Phone</th><th scope=\"col\">Email</th><th scope=\"col\">Delete row</th>" +
            "<th scope=\"col\">Update row</th></tr></thead>";
        html += "<tbody>";

        $.get("/account/loadAccounts", function (data) {
            for (var i = 0; i < data.length; i++) {

                html += "<tr id='row_" + data[i].id + "'><td name='id'>" + data[i].id + "</td><td>" + data[i].name + "</td><td>" + data[i].phone + "</td><td>" +
                    data[i].email + "</td>";
                html += "<td>" + "<a href='#' class='btn btn-danger' onclick='DeleteRecord(" + data[i].id +
                    ")'><span class='glyphicon glyphicon-trash'>Delete</span></a>" + "</td>";
                html += "<td>" + "<a href='#' class='btn btn-primary' onclick='UpdateRecord(" + data[i].id + ")'>Update</a>" + "</td></tr>";

            }
            $('#accountsTable').append(html);
            $('#table').dataTable();
        });
    });

    var DeleteRecord = function (id) {
        $.ajax({
            type: "POST",
            url: "/account/delete?id=" + id,
            success: function (result) {
                $('#row_' + id).remove();
            }
        });
    };

    function AddNew() {
        $("#addForm")[0].reset();
        $("#addAccountModal").modal();

    }

    function UpdateRecord(id) {
        var url = "/account/update?id=" + id;
        $("#updateAccountModal").modal();
        $.ajax({
            type: "GET",
            url: url,
            success: function (data) {
                $("#idUpdate").val(data.id);
                $("#nameUpdate").val(data.name);
                $("#phoneUpdate").val(data.phone);
                $("#emailUpdate").val(data.email);

            }
        })
    }


    $("#saveRecord").click(function () {
        var data = $("#submitForm").serialize();
        $.ajax({
            type: "Post",
            url: "/account/registration",
            data: data,
            success: function (result) {
                location.reload();
                $("#addAccountModal").modal("hide");

            }
        })
    })

    $("#updateRecord").click(function () {
        var data = $("#submitFormUpdate").serialize();
        $.ajax({
            type: "Post",
            url: "/account/saveAfterUpdate",
            data: data,
            success: function (result) {
                location.reload();
                $("#UpdateAccountModal").modal("hide");

            }
        })
    })
</script>
</tbody>
</table>

</body>
</html>

