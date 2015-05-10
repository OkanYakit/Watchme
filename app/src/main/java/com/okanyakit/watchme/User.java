package com.okanyakit.watchme;

/**
 * Created by okan on 4/15/2015.
 */
public class User {
    String username, password, email, phonenumber, bloodtype, birthday, address;

    public User(String username, String password, String email, String phonenumber, String bloodtype, String birthday, String address)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.bloodtype = bloodtype;
        this.birthday = birthday;
        this.address = address;

    }
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.email = "";
        this.phonenumber = "";
        this.bloodtype = "";
        this.birthday = "";
        this.address = "";

    }










}
