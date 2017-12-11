package com.example.ariel.aimclothing;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ian on 12/11/2017.
 */

public class User {

    private String username;
    private String password;
    private String contactNo;
    private String name;
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

    public User(String name, String username, String password, String contactNo) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.contactNo = contactNo;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
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
                "\nContact: " + contactNo +
                "\nDate Registered: " + dateRegistered +"\n\n";

    }

}
