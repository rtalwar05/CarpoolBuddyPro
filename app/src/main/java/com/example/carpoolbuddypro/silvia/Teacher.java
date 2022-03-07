package com.example.carpoolbuddypro.silvia;

import java.util.ArrayList;

public class Teacher extends User
{
    String inschooltitle;

    public Teacher ()
    {
    }

    public String getInschooltitle() {
        return inschooltitle;
    }

    public void setInschooltitle(String inschooltitle) {
        this.inschooltitle = inschooltitle;
    }

    public Teacher(String uid, String name, String email, String usertype, double pricemult, ArrayList<String> ownveh, double balance, int greenpoints, String inschooltitle) {
        super(uid, name, email, usertype, pricemult, ownveh, balance, greenpoints);
        this.inschooltitle = inschooltitle;
    }

    @Override
    public String toString() {
        return "teacher{" +
                "inschooltitle='" + inschooltitle + '\'' +
                '}';
    }
}
