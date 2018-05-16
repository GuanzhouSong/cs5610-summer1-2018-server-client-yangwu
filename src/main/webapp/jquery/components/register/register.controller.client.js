
(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $verifyPasswordFld = $("#verifyPasswordFld");
        $registerBtn = $("#registerBtn");
        $registerBtn.click(register);
    }

    function register() {
        var new_user = {
            username: $usernameFld.val(),
            password: $passwordFld.val()
        };
        userService
            .register(new_user)
            .then(success);
    }

    function success(response) {
        if (response === null) {
            alert('unable to update');
        } else {
            alert('success');
        }

    }


}) ();