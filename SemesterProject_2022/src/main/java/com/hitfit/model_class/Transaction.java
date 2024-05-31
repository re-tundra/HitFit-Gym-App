package com.hitfit.model_class;

import com.hitfit.controller.CustomMenuButton;
import com.hitfit.controller.GeneralFunctions;
import com.hitfit.database.DatabaseFunctions;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.sql.Date;

public class Transaction {

    private String paymentMethod;
    private int transactionId;
    private Date createdDate;
    private int amount;
    private String transactionNumber;
    private String bankName;
    private String accountOwnerName;
    private int fkCustomerId;
    private boolean status;
    private String StringStatus;

    private CustomMenuButton actionBtn;
    private MenuItem item = new MenuItem("Activate");
    // for TableView of Transactions
    public Transaction(boolean status,
                       int transactionId,
                       Date createdDate,
                       int amount,
                       String bankName,
                       String accountOwnerName,
                       CustomMenuButton button,
                       String paymentMethod) {
        this.status = status;
        this.transactionId = transactionId;
        this.createdDate = createdDate;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.bankName = bankName;
        this.accountOwnerName = accountOwnerName;

        if(!status)
        {
            StringStatus= "Inactive";
        } else {
        StringStatus = "Active";}

        if (button != null) {
            this.actionBtn = button;
            this.actionBtn.setStyle("-fx-background-color: #00C2FF; -fx-background-radius: 12px;");
            this.actionBtn.setTextFill(Paint.valueOf("White"));

            actionBtn.getItems().addAll(item);
        }


        item.setOnAction(event ->
        {
            DatabaseFunctions.updateTransactionStatus(actionBtn.getButtonId());

            // pop up message to display that status has been activated
            try {
                new GeneralFunctions().switchSceneModality("statusActive_popup.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });



    }

    public String getLowerCaseName()
    {
        return getAccountOwnerName().toLowerCase();
    }

    public Transaction(int transactionId,
                       Date createdDate,
                       int amount,
                       String transactionNumber,
                       String bankName,
                       String accountOwnerName,
                       String paymentMethod) {
        this.transactionId = transactionId;
        this.createdDate = createdDate;
        this.amount = amount;
        this.transactionNumber = transactionNumber;
        this.bankName = bankName;
        this.accountOwnerName = accountOwnerName;
        this.paymentMethod = paymentMethod;
    }

    public Transaction() {
    }

    public Transaction(int transactionId,
                       Date createdDate,
                       int amount,
                       String transactionNumber,
                       String bankName,
                       String accountOwnerName,
                       int fkCustomerId,
                       boolean status,
                       String paymentMethod) {
        this.transactionId = transactionId;
        this.createdDate = createdDate;
        this.amount = amount;
        this.transactionNumber = transactionNumber;
        this.paymentMethod = paymentMethod;
        this.bankName = bankName;
        this.accountOwnerName = accountOwnerName;
        this.fkCustomerId = fkCustomerId;
        this.status = status;
    }

    public String getStringStatus() {
        return StringStatus;
    }

    public void setStringStatus(String stringStatus) {
        StringStatus = stringStatus;
    }

    public CustomMenuButton getActionBtn() {
        return actionBtn;
    }

    public void setActionBtn(CustomMenuButton actionBtn) {
        this.actionBtn = actionBtn;
    }

    public int getFkCustomerId() {
        return fkCustomerId;
    }

    public void setFkCustomerId(int fkCustomerId) {
        this.fkCustomerId = fkCustomerId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
