package com.example.benodonnell.a3rdyearproject;

/**
 * Created by benodonnell on 12/10/2017.
 */

public class User {

    private  String name;

    private String password;

    public User (String name, String password){
        this.name = name;
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
