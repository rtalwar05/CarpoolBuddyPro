package com.example.carpoolbuddypro.silvia;

import java.util.ArrayList;

public class Vehicle {

    String liscensePlate;
    String model;
    int capacity;
    ArrayList<String> ridersUID;
    boolean open;
    double basePrice;
    boolean isGreen;
    String energytype;
    String owner;

    public Vehicle (){}

    public Vehicle(String liscenseplate, String model, int capacity,
                   ArrayList<String> ridersUID, boolean open,
                   double basePrice, boolean isGreen, String energytype, String owner) {
        this.liscensePlate = liscenseplate;
        this.model = model;
        this.capacity = capacity;
        this.ridersUID = ridersUID;
        this.open = open;
        this.basePrice = basePrice;
        this.isGreen = isGreen;
        this.energytype = energytype;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEnergytype() {
        return energytype;
    }

    public void setEnergytype(String energytype) {
        this.energytype = energytype;
    }

    public String getLiscensePlate() {
        return liscensePlate;
    }

    public void setLiscensePlate(String liscenseplate) {
        this.liscensePlate = liscenseplate;
    }

    public boolean isGreen() {
        return isGreen;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
