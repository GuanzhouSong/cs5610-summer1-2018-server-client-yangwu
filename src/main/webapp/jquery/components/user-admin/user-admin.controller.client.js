
//IIFE immediately invoke
(function () {
    jQuery(main);

    var $usernameFld, $passwordFld;
    var $deleteBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $userRowTemplate, $tbody;
    var userService = new UserServiceClient();

    function main() {
        tbody = $('tbody');
        template = $('.template');
        createBtn = $('#create');
        createBtn.click(createUser);

        findAllUsers();
    }

    function findAllUsers() {
        var promise = fetch('http://localhost:8080/api/user');
        promise.then(function (response) {
            return response.json();
        }).then(renderUsers);
    }

    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

        userService
            .createUser(user)
            .then(findAllUsers);
    }

    function renderUsers(users) {
        tbody.empty();

        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);
            clone.find('#wbdv-remove').click(deleteUser);
            clone.find('#wbdv-edit').click(editUser);

            clone.find('.wbdv-username').html(user.username);
            clone.find('.wbdv-password').html(user.password);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            clone.find('.wbdv-role').html(user.role);
            tbody.append(clone);
        }
    }

    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn.parent().parent().parent().attr('id');
        userService.deleteUser(userId)
            .then(findAllUsers);
    }

    function editUser(event) {
        console.log(event);
        var editBtn = $(event.currentTarget);
        var userId = editBtn.parent().parent().parent().attr('id');
        userService.findUserById(userId)
            .then(renderUser);
    }

    function renderUser(user) {
        document.getElementById("usernameFld").value = user.username;
        document.getElementById("passwordFld").value = user.password;
        document.getElementById("firstNameFld").value = user.firstName;
        document.getElementById("lastNameFld").value = user.lastName;
        document.getElementById("roleFld").value = user.role;

        $updateBtn = $('#update');
        $updateBtn.click(function() {
            console.log("im here");
            $usernameFld = $('#usernameFld').val();
            $passwordFld = $('#passwordFld').val();
            $firstNameFld = $('#firstNameFld').val();
            $lastNameFld = $('#lastNameFld').val();
            $roleFld = $('#roleFld').val();

            var new_user = {
                username: $usernameFld,
                password: $passwordFld,
                firstName: $firstNameFld,
                lastName: $lastNameFld,
                role: $roleFld
            };

            userService
                .updateUser(user.id, new_user)
                .then(findAllUsers);

            $('#usernameFld').val("");
            $('#passwordFld').val("");
            $('#firstNameFld').val("");
            $('#lastNameFld').val("");
            $('#roleFld').val("");
        });
    }

})();