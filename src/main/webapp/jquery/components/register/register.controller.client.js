
(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn, $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $verifyPasswordFld = $("#verifyPasswordFld");
        $registerBtn = $("#registerBtn").click(register);
        $loginBtn = $("#loginBtn").click(goToLogin);
    }

    function register() {
        if ($passwordFld.val() != $verifyPasswordFld.val()) {
            alert('password does not match!');
        } else {
            var new_user = {
                username: $usernameFld.val(),
                password: $passwordFld.val()
            };
            userService
                .register(new_user)
                .then(success);
        }
    }

    function success(response) {
        if (response === null) {
            alert('Unable to register! There exists this username! Please use another username.');
        } else {
            alert('success');
        }

    }

    function goToLogin() {
        var url = "../login/login.template.client.html";
        window.location.href = url;
    }
}) ();