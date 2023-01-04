package com.example.semesterProject_2022;

import javafx.scene.control.MenuButton;

public class CustomMenuButton extends MenuButton {

    private String FullName,weight,Address,Email,Username,PackageType,PackagePrice;

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public CustomMenuButton(String s, String fullName, String weight, String address, String email, String username,String packagePrice) {
        super(s);
        FullName = fullName;
        this.weight = weight;
        Address = address;
        Email = email;
        Username = username;
        PackagePrice = packagePrice;
        if(Integer.parseInt(PackagePrice)==2000)
        {
            PackageType = "Beginner";
        } else if(Integer.parseInt(PackagePrice)==3000)
        {
            PackageType = "Starter";
        } else if(Integer.parseInt(PackagePrice)==4500)
        {
            PackageType = "Pro";
        } else
        {
            PackageType = "nil";
        }
    }
}
