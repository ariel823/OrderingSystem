package com.example.ariel.aimclothing.library;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ian on 12/11/2017.
 */

public class User {

    private String username;
    private String password;
    private String name;
    private int userType;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    private String dateRegistered;

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public User(){
        setDateRegistered();
    }

    public User(String name, String username, String password, int userType) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
        setDateRegistered();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateRegistered() {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateRegistered = dateFormat.format(date);

    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nUsername: " + username +
                "\nPassword: " + password +
                "\nUser_Type: " + userType +
                "\nDate Registered: " + dateRegistered +"\n\n";

    }

}
