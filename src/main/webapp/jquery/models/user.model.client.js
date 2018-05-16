
function User(username, password, firstName, lastName) {
    this.username = username;
    this.password = password;
    // ...same for rest of properties...
    this.firstName = firstName;
    this.lastName = lastName;

    this.setUsername = setUsername;
    this.getUsername = getUsername;
    // ...same for rest of properties...
    this.setPassword = setPassword;
    this.getPassword = getPassword;
    this.setFirstName = setFirstName;
    this.getFirstName = getFirstName;
    this.setLastName = setLastName;
    this.getLastName = getLastName;

    function setUsername(username) {
        this.username = username;
    }

    function getUsername() {
        return this.username;
    }
    // ... same for rest of properties...

    function setPassword(password) {
        this.password = password;
    }

    function getPassword() {
        return this.password;
    }

    function setFirstName(firstName) {
        this.firstName = firstName;
    }

    function getFirstName() {
        return this.firstName;
    }

    function setLastName(lastName) {
        this.lastName = lastName;
    }

    function getLastName() {
        return this.lastName;
    }

}
