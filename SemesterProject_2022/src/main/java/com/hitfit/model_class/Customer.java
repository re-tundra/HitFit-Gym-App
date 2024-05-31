package com.hitfit.model_class;

import com.hitfit.controller.members_only.MembersDetailCard_Controller;
import com.hitfit.controller.members_only.MembersPanel_Controller;
import com.hitfit.controller.CustomMenuButton;
import com.hitfit.database.DatabaseFunctions;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class Customer extends Person implements Comparable {


    private String address;
    private String dob;
    private Double weight;
    private Double height;
    private Double bmi;
    private String bmiCategory;
    private int monthlyPlan;
    private Package monthlyPackage;
    private int customerId;
    private String isActive;
    private String passwordSalt;
    private BMI CustomerBMI;
    private Boolean AdminAcces;
    private int Id;

    public String getFullname() {
        return getFirstName() + " " + getLastName();
    }

    /*--------*/
    private CustomMenuButton actionBtn;

    private MenuItem item1 = new MenuItem("View");
    private MenuItem item2 = new MenuItem("Remove");
    /**/


    public CustomMenuButton getActionBtn() {
        return actionBtn;
    }

    public void setActionBtn(CustomMenuButton actionBtn) {
        this.actionBtn = actionBtn;
    }

    public Customer(Boolean AdminAccess,
                    int Id,
                    String firstName,
                    String lastName,
                    String email,
                    String phoneNumber,
                    String nicNumber,
                    int monthlyPlan,
                    boolean isActive,
                    CustomMenuButton customMenuButton) {
        super(firstName, lastName, email, "gender", phoneNumber, "userName", "password", nicNumber);
        this.Id = Id;
        this.monthlyPlan = monthlyPlan;
        this.AdminAcces = AdminAccess;
        this.isActive = isActive ? "Activated" : "Not Activated";


        /*Action Button Stuff*/
        this.actionBtn = customMenuButton;
        this.actionBtn.setStyle("-fx-background-color: #00C2FF; -fx-background-radius: 12px;");
        this.actionBtn.setTextFill(Paint.valueOf("White"));
        if (this.AdminAcces) {
            actionBtn.getItems().addAll(item1, item2);
        } else {
            actionBtn.getItems().addAll(item1);
        }

        item1.setOnAction(event ->
        {
            MembersDetailCard_Controller.FullName = actionBtn.getFullName();
            MembersDetailCard_Controller.Weight = actionBtn.getWeight();
            MembersDetailCard_Controller.Height = actionBtn.getHeight();
            MembersDetailCard_Controller.Address = actionBtn.getAddress();
            MembersDetailCard_Controller.Emails = actionBtn.getEmail();
            MembersDetailCard_Controller.Username = actionBtn.getUsername();
            MembersDetailCard_Controller.PackagePrice = actionBtn.getPackagePrice();
            MembersDetailCard_Controller.PackageType = actionBtn.getPackageType();

            try {
                new MembersPanel_Controller().view();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        item2.setOnAction(event ->
        {

            MembersPanel_Controller.deletingId = actionBtn.getButtonId();
            DatabaseFunctions.deleteData("customers", MembersPanel_Controller.deletingId);
        });


    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Customer(String firstName,
                    String lastName,
                    String email,
                    String gender,
                    String phoneNumber,
                    String userName,
                    String password,
                    String nicNumber,
                    String address,
                    String dob,
                    Double weight,
                    Double height,
                    int monthlyPlan,
                    int customerId,
                    String passwordSalt,
                    Double bmi) {
        super(firstName, lastName, email, gender, phoneNumber, userName, password, nicNumber);
        this.address = address;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
        this.monthlyPlan = monthlyPlan;
        this.customerId = customerId;
        this.passwordSalt = passwordSalt;

        this.bmi = bmi;
    }

    public Package getMonthlyPackage() {
        return monthlyPackage;
    }

    public void setMonthlyPackage(Package monthlyPackage) {
        this.monthlyPackage = monthlyPackage;
    }

    public Customer() {
        super();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public int getMembership() {
        return monthlyPlan;
    }

    public String getMembershipCategory() {
        String PackageType;
        switch (monthlyPlan) {
            case 2000 -> PackageType = "Beginner";
            case 3000 -> PackageType = "Starter";
            case 4500 -> PackageType = "Pro";
            default -> PackageType = "";
        }

        return PackageType;
    }

    public void setMembership(int monthlyPlan) {
        this.monthlyPlan = monthlyPlan;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getIsActive() {
        return isActive;
    }
//
//    public void setActive(boolean active) {
//        isActive = active;
//    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String tolowerfirstname() {
        return getFirstName().toLowerCase();
    }

    @Override
    public String toString() {
        return super.toString() + "Customer{" +
                "address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", weight='" + weight + '\'' +
                ", monthlyPlan=" + monthlyPlan +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
