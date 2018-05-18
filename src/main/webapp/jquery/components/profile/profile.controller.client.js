(function() {
    $(init);
    var $username;
    var $firstname;
    var $lastname;
    var $phone;
    var $email;
    var $role;
    var $dateOfBirth;
    var $updateBtn;
    var $logoutBtn;
    var userId;
    var userService = new UserServiceClient();

    function init() {
        userService.populateProfile()
            .then(populateProfile);
        $username = $("#username");
        $firstname = $("#firstname");
        $lastname = $("#lastname");
        $phone = $("#phone");
        $email = $("#email");
        $role = $("#role");
        $dateOfBirth = $("#dateOfBirth");
        $updateBtn = $("#updateBtn")
            .click(updateProfile);
        $logoutBtn = $("#logoutBtn")
            .click(logout);
    }

    function populateProfile(temp) {
        if (temp != null) {
            $('#username').val(temp.username);
            $('#firstname').val(temp.firstName);
            $('#lastname').val(temp.lastName);
            $('#phone').val(temp.phone);
            $('#email').val(temp.email);
            $('#role').val(temp.role);
            var date = parseISOString(temp.dateOfBirth);
            $('#dateOfBirth').val(date);
        }
    }

    function parseISOString(s) {
        var index = s.indexOf("T");
        return s.substring(0, index);
    }

    function updateProfile() {
        var date = new Date($dateOfBirth.val()).toISOString();
        var user = {
            username: $username.val(),
            phone: $phone.val(),
            email: $email.val(),
            role: $role.val(),
            dateOfBirth: date
        };

        userService
            .updateProfile(user)
            .then(success);
    }

    function logout() {
        userService.logout();
        var url = "../login/login.template.client.html";
        window.location.href = url;
    }

    function success(response) {
        if (response === null) {
            alert('unable to update');
        } else {
            alert('success');
        }
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function renderUser(user) {
        console.log(user);
        $username.val(user.username);
        $phone.val(user.phone);
        $email.val(user.email);
        $role.val(user.role);
        $dateOfBirth.val(user.dateOfBirth);
    }
}) ();