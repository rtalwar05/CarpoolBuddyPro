package com.example.carpoolbuddypro.silvia;

import java.util.ArrayList;

public class Vehicle {

    String owner;
    String model;
    String vehicleID;
    int capacity;
    ArrayList<String> ridersUID;
    boolean open;
    String vehicleType;
    double basePrice;
    boolean isGreen;

    public Vehicle (){}

    public Vehicle(String owner, String model, String vID, int capacity,
                   ArrayList<String> ridersUID, boolean open, String type,
                   double basePrice, boolean isGreen) {
        this.owner = owner;
        this.model = model;
        this.vehicleID = vID;
        this.capacity = capacity;
        this.ridersUID = ridersUID;
        this.open = open;
        this.vehicleType = type;
        this.basePrice = basePrice;


    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<String> getRidersUID() {
        return ridersUID;
    }

    public void setRidersUID(ArrayList<String> ridersUID) {
        this.ridersUID = ridersUID;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public boolean getIsGreen() {
        return isGreen;
    }

    public void setGreen(boolean green) {
        this.isGreen = green;
    }


}
