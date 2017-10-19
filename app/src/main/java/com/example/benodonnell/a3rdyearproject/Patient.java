package com.example.benodonnell.a3rdyearproject;

/**
 * Created by chris on 15/10/2017.
 */

public class Patient {
    private String name;
    private int age;
    private double lattitude;
    private double longitude;

    public Patient(String name, int age, double lattitude, double longitude){
        this.name = name;
        this.age = age;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public void setName (String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setLocation(double longitude, double lattitude){
        this.longitude = longitude;
        this.lattitude = lattitude;
    }
}
