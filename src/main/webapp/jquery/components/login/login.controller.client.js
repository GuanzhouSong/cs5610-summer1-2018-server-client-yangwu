
(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#username");
        $passwordFld = $("#password");
        $loginBtn = $("#signinBtn")
            .click(login);
    }

    function login() {
        var new_user = {
            username: $usernameFld.val(),
            password: $passwordFld.val()
        };

        console.log(new_user);
        userService
            .login(new_user)
            .then(success);
    }

    function success(response) {
        if (response === null) {
            alert('unable to update');
        } else {
            alert('success');
            goToProfile();
        }
    }

    function goToProfile() {
        var url = "../profile/profile.template.client.html";
        window.location.href = url;
    }

})();