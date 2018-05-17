
(function () {
    var $usernameFld, $passwordFld;
    var $signinBtn, $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#username");
        $passwordFld = $("#password");
        $signinBtn = $("#signinBtn")
            .click(login);
        $registerBtn = $("#registerBtn")
            .click(goToRegister);
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
            alert('Login is unsuccessful. Please make sure your username and password are correct!');
        } else {
            alert('success');
            goToProfile();
        }
    }

    function goToProfile() {
        var url = "../profile/profile.template.client.html";
        window.location.href = url;
    }

    function goToRegister() {
        var url = "../register/register.template.client.html";
        window.location.href = url;
    }

})();