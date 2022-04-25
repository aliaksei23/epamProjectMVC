package com.example.epamprojectmvc.entity;

public class User extends AbstractEntity {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String login;
    private String password;
    private UserRole userRole;

    public User() {
    }

    public static UserBuilder newBuilder() {
        return new User().new UserBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public class UserBuilder {
        public UserBuilder setName(String name) {
            User.this.name = name;
            return this;
        }

        public UserBuilder setSurname(String surname) {
            User.this.surname = surname;
            return this;
        }

        public UserBuilder setPhoneNumber(String phoneNumber) {
            User.this.phoneNumber = phoneNumber;
            return this;
        }


        public UserBuilder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public UserBuilder setLogin(String login) {
            User.this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public UserBuilder setUserRole(UserRole userRole) {
            User.this.userRole = userRole;
            return this;

        }
    }
}
