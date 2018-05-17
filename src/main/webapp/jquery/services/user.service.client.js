
function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.updateProfile = updateProfile;
    this.popularProfile = popularProfile;
    this.login = login;
    this.logout = logout;
    this.register = register;

    this.url =
        'http://localhost:8080/api/user';
    this.login_url =
        'http://localhost:8080/api/login';
    this.register_url =
        'http://localhost:8080/api/register';
    this.profile_url =
        'http://localhost:8080/api/profile';
    var self = this;

    function register(user) {
        return fetch(self.register_url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function(response) {
            if (response.status === 409) {
                return null;
            } else {
                return response.json();
            }
        });
    }

    function login(user) {
        return fetch(self.login_url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function(response) {
            if (response.status === 409) {
                return null;
            } else {
                return response.json();
            }
        });
    }

    function logout() {
        return fetch(self.profile_url, {
            method: 'post'
        })
    }

    function updateProfile(user) {
        return fetch(self.profile_url, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function(response) {
            if (response.status == 409) {
                return null;
            } else {
                return response.json();
            }
        });
    }

    function popularProfile() {
        return fetch(self.profile_url)
            .then(function(response) {
            if (response.status === 409) {
                return null;
            } else {
                return response.json();
            }
        });
    }

    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type' : 'application/json'
            }
        }).then(function(response) {
            if (response.status === 409) {
                return null;
            } else {
                return response.json();
            }
        });
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function(response){
                return response.json();
            });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        })
    }

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }
}

