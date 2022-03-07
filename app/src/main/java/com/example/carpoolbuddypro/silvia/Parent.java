package com.example.carpoolbuddypro.silvia;

import java.util.ArrayList;

public class Parent extends User{


        ArrayList<String> childrenUIDs;

        public Parent(){}

        public Parent(String uid,
                      String name,
                      String email,
                      String usertype,
                      double pricemult, double balance,
                      ArrayList<String> ownveh,int greenpoints,
                      ArrayList<String> children) {
            super(uid, name, email, usertype, pricemult, ownveh, balance, greenpoints);
            this.childrenUIDs = children;
        }

        public ArrayList<String> getChildrenUIDs() {
            return childrenUIDs;
        }

        public void setChildrenUIDs(ArrayList<String> childrenUIDs) {
            this.childrenUIDs = childrenUIDs;
        }





}
