function login() {

    const email = $("#email").val();
    const password = $("#password").val();

    $.ajax({
        type: 'POST',
        url: '/j_spring_security_check',
        contentType: "application/json;charset=utf-8",
        data: {
            email,
            password
        },
        success: function (res) {
            // window.location.replace("http://localhost:8080/welcome");
        },
        error: function (res) {
            $('#mistake').append("<p id='mistakeMessage' class='alert alert-danger' role='alert'>Wrong login or password</p>");
        }
    });
};



$(document).ready(function(){
    $(".dropdown-toggle").dropdown();
});

function loginForm() {
    $("#loginForm")[0].reset();
    $("#loginModal").modal();
}

function signUpForm() {
    $("#signUpForm")[0].reset();
    $("#signUpModal").modal();
}

function signUp() {
    var name=$('#nameSignUp').val();
    var phone = $('#phoneSignUp').val();
    var email = $('#emailSignUp').val();
    var password = $('#passwordSignUp').val();
    var accountDto = ({
        "name":name,
        "phone": phone,
        "email": email,
        "password": password
    });
    $.ajax({
        type: "Post",
        url: "/registration",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(accountDto),
        success: function (result) {
            loginForm();
            $("#signUpModal").modal("hide");
        },
        error: function (result) {
            alert(JSON.stringify(result))
        }
    })
};

function updateAccountSettings() {
    var url = "/account/settings";
    $("#settingsForm")[0].reset();
    $("#accountSettingsModal").modal();
    $.ajax({
        type: "GET",
        url: url,
        success: function (data) {
            $("#idUpdateViaUser").val(data.id);
            $("#nameUpdateViaUser").val(data.name);
            $("#phoneUpdateViaUser").val(data.phone);
            $("#emailUpdateViaUser").val(data.email);

        },
        error: function (data) {
            $("#errorForm")[0].reset();
            $("#errorModal").modal();
        }
    })
}

function updateAccount() {
    $('#uniqueFieldMistake').hide();
    $('#emptyFieldMistake').hide();
    var name = $('#nameUpdateViaUser').val();
    var phone = $('#phoneUpdateViaUser').val();
    var email = $('#emailUpdateViaUser').val();
    var id = $('#idUpdateViaUser').val();
    var accountDto = ({
        "id": id,
        "name": name,
        "phone": phone,
        "email": email
    });

    alert(JSON.stringify(accountDto));
    $.ajax({
        type: "Post",
        contentType: "application/json;charset=utf-8",
        url: "/account/settings/save",
        data: JSON.stringify(accountDto),
        success: function (result) {
            window.location.reload();
        },
        error: function (result) {
            alert(result.status);
            if (result.status === 500) {
                $('#uniqueFieldMistake').show();
            } else if (result.status === 400) {
                $('#emptyFieldMistake').show();
            }

        }
    });
}