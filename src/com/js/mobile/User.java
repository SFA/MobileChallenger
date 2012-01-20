package com.js.mobile;

/**
 * Created by IntelliJ IDEA.
 * User: steveahlers
 * Date: 1/20/12
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    
    String userName;
    String lastName;
    String firstName;
    String password;
    String email;

    public User(String userName, String lastName, String firstName, String password, String email) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
