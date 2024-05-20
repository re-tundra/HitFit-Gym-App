package com.hitfit.controller;

import javafx.scene.control.MenuButton;

public class CustomMenuButton extends MenuButton {

    private int ButtonId;
    private String FullName,Address,Email,Username,PackageType,PackagePrice,designation,gender,phone;
    private double salary, weight,cHeight;

    // *FOR TRANSACTIONS

    public CustomMenuButton(String s, int buttonId) {
        super(s);
        ButtonId = buttonId;
    }

    //
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // constructor for employees
    public CustomMenuButton(String s, int buttonId, String fullName, String email, String username, String designation, double salary,String gender,String phone) {
        super(s);
        this.phone = phone;
        this.gender = gender;
        ButtonId = buttonId;
        FullName = fullName;
        Email = email;
        Username = username;
        this.designation = designation;
        this.salary = salary;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    public int getButtonId() {
        return ButtonId;
    }

    public void setButtonId(int buttonId) {
        ButtonId = buttonId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getCHeight() {
        return cHeight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public void setCHeight(Double height) {
        this.cHeight = height;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPackageType() {
        return PackageType;
    }

    public void setPackageType(String packageType) {
        PackageType = packageType;
    }

    public String getPackagePrice() {
        return PackagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        PackagePrice = packagePrice;
    }

    // constructor for Members
    public CustomMenuButton(int ButtonId,
                            String s,
                            String fullName,
                            Double weight,
                            Double height,
                            String address,
                            String email,
                            String username,
                            String packagePrice) {
        super(s);
        FullName = fullName;
        this.ButtonId=ButtonId;
        this.weight = weight;
        this.cHeight = height;
        Address = address;
        Email = email;
        Username = username;
        PackagePrice = packagePrice;

        switch (Integer.parseInt(PackagePrice)) {
            case 2000 -> PackageType = "Beginner";
            case 3000 -> PackageType = "Starter";
            case 4500 -> PackageType = "Pro";
            default -> PackageType = "nil";
        }
    }
}
