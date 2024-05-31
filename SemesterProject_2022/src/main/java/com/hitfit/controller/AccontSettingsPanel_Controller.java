package com.hitfit.controller;

import com.hitfit.controller.customer.CustomerPanel_Controller;
import com.hitfit.database.DatabaseFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AccontSettingsPanel_Controller implements Initializable {

    @FXML
    private Button editEmailBtn;
    @FXML
    private Button editNameBtn;
    @FXML
    private Button editPhoneBtn;
    @FXML
    private Button updatePasswordButton;

    @FXML
    private TextField editEmail;

    @FXML
    private TextField editFirstName;
    @FXML
    private TextField editLastName;

    @FXML
    private TextField editPassword;

    @FXML
    private TextField editPhoneNumber;

    @FXML
    private Text emailField;

    @FXML
    private Text nameField;

    @FXML
    private Text phoneField;


    @FXML
    private Button save;

    String newName, newPhoneNumber, newEmail, newPassword;

    @FXML
    void editEmailButton(ActionEvent event) {
        editEmail.setVisible(true);
        editEmailBtn.setVisible(false);
        emailField.setVisible(false);
    }

    @FXML
    public void setData(KeyEvent e){
        newEmail = editEmail.getText();
        newName = editFirstName.getText() + " " + editLastName.getText();
        newPhoneNumber = editPhoneNumber.getText();
        newPassword = editPassword.getText();

        if(e.getCode() == KeyCode.ENTER){
            if(!newName.isEmpty() && !newName.isBlank()) {
                nameField.setText(newName);
                nameField.setVisible(true);
                editFirstName.setVisible(false);
                editLastName.setVisible(false);
            }

            if(!newEmail.isBlank() && !newEmail.isEmpty()) {
                emailField.setText(newEmail);
                emailField.setVisible(true);
                editEmail.setVisible(false);
            }

            if(!newPhoneNumber.isBlank() && !newPhoneNumber.isEmpty()) {
                phoneField.setText(newPhoneNumber);
                phoneField.setVisible(true);
                editPhoneNumber.setVisible(false);
            }

            editPassword.setVisible(false);
        }
    }

    @FXML
    void editNameButton(ActionEvent event) {
        editFirstName.setVisible(true);
        editLastName.setVisible(true);
        editNameBtn.setVisible(false);
        nameField.setVisible(false);
    }

    @FXML
    void editPhoneButton(ActionEvent event) {
        editPhoneNumber.setVisible(true);
        editPhoneBtn.setVisible(false);
        phoneField.setVisible(false);
    }

    @FXML
    void updatePasswordButton(ActionEvent event) {
        updatePasswordButton.setVisible(false);
        editPassword.setVisible(true);
    }
    @FXML
    void Save() {
        newEmail = editEmail.getText();
        newName = editFirstName.getText() + " " + editLastName.getText();
        newPhoneNumber = editPhoneNumber.getText();
        newPassword = editPassword.getText();

        nameField.setText(newName);
        emailField.setText(newEmail);
        phoneField.setText(newPhoneNumber);

        editPassword.setText("");
        editEmail.setVisible(false);
        editFirstName.setVisible(false);
        editLastName.setVisible(false);
        editPhoneNumber.setVisible(false);
        editPassword.setVisible(false);

        editEmailBtn.setVisible(true);
        editNameBtn.setVisible(true);
        editPhoneBtn.setVisible(true);
        updatePasswordButton.setVisible(true);

        emailField.setVisible(true);
        nameField.setVisible(true);
        phoneField.setVisible(true);

        DatabaseFunctions.updateCustomer(
            CustomerPanel_Controller.Customer.getCustomerId(),
            editFirstName.getText(),
            editLastName.getText(),
            newEmail,
            newPhoneNumber,
            newPassword.isEmpty() ? CustomerPanel_Controller.Customer.getPassword() : newPassword
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newName = CustomerPanel_Controller.Customer.getFullname();
        newEmail = CustomerPanel_Controller.Customer.getEmail();
        newPhoneNumber = CustomerPanel_Controller.Customer.getPhoneNumber();

        nameField.setText(newName);
        emailField.setText(newEmail);
        phoneField.setText(newPhoneNumber);

        editFirstName.setText(CustomerPanel_Controller.Customer.getFirstName());
        editLastName.setText(CustomerPanel_Controller.Customer.getLastName());
        editEmail.setText(newEmail);
        editPhoneNumber.setText(newPhoneNumber);
    }
}
