package com.hitfit.controller.initial_screen;

import backend_functions.Login;
import com.hitfit.controller.GeneralFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class LogIn_Form_Controller {

    private final String adminCreds = "admin";

    @FXML
    private TextField EmailField;

    @FXML
    private Button LogInButton;

    @FXML
    private PasswordField PassField;

    @FXML
    private AnchorPane anchorpane_login;
    @FXML
    private Button exit;
    @FXML
    private Label epValidation;
    @FXML
    private Label passwordValidation;

    /*---------Local Variable------------*/
    private String email;
    private String password;

    @FXML
    private CheckBox response;
    private double x = 0;
    private double y = 0;
    /*------*/
    /*---------Loading Screen object----------*/
    LoadingScreen_Controller obj = new LoadingScreen_Controller();
    /*For Resolution relative to every screen we will get the dimensions of the screen*/
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    String errorStyle = "-fx-border-color: #ff0000; -fx-border-width: 3px; -fx-border-radius:12px";
    String resetStyle = "-fx-border-color: transparent; -fx-border-radius:12px";

    @FXML
    void loginBtn(MouseEvent e) throws IOException {
        Login newLogin = new Login();

        email = EmailField.getText();
        password = PassField.getText();
        newLogin.setEmailUsername(email);
        newLogin.setPassword(password);



        if (email.isEmpty() || email.isBlank()) {
            epValidation.setText("! Email cannot be empty");
            EmailField.setStyle(errorStyle);
            EmailField.setText("");
        }

        else if (newLogin.checkUsernameEmail()) {
            epValidation.setText("! Invalid email or username");
            EmailField.setStyle(errorStyle);
            EmailField.setText("");
        }

        if (password.isEmpty() || password.isBlank()) {
            passwordValidation.setText("! Password cannot be empty");
            PassField.setStyle(errorStyle);
            PassField.setText("");
        }

        else if (!newLogin.userLoggedInStatus()) {
            passwordValidation.setText("! Password Incorrect");
            PassField.setStyle(errorStyle);
            PassField.setText("");
        }

        else if (!newLogin.checkUsernameEmail() && newLogin.userLoggedInStatus() && epValidation.getText().isEmpty() && passwordValidation.getText().isEmpty()) {
            if(response.isSelected()){
                Properties properties = new Properties();
                properties.setProperty("Username",email);
                properties.setProperty("password",password);

                try(OutputStream outputStream = new FileOutputStream("credentials.properties")){
                    properties.store(outputStream, null);
                }
                catch (Exception error){
                    System.out.println(error.getLocalizedMessage());
                }
            }
            new GeneralFunctions().switchSceneFXHelper(e, "CustomerPanel.fxml");

        }
    }
    public void adminLogin(ActionEvent e) throws IOException {
        email = EmailField.getText();
        password = PassField.getText();

        if (email.isEmpty() || email.isBlank()) {
            epValidation.setText("! Email cannot be empty");
            EmailField.setStyle(errorStyle);
            EmailField.setText("");
            return;
        }
        else if (!email.equals(adminCreds)) {
            epValidation.setText("! Invalid email or username");
            EmailField.setStyle(errorStyle);
            EmailField.setText("");
            return;
        }

        if (password.isEmpty() || password.isBlank()) {
            passwordValidation.setText("! Password cannot be empty");
            PassField.setStyle(errorStyle);
            PassField.setText("");
            return;
        } else if (!password.equals(adminCreds)) {
            passwordValidation.setText("! Password Incorrect");
            PassField.setStyle(errorStyle);
            PassField.setText("");
            return;
        }

        if (epValidation.getText().isEmpty() && passwordValidation.getText().isEmpty()) {
            new GeneralFunctions().switchSceneFXHelper(e, "AdminPanel.fxml");

        }
    }

    public void login(String userName, String Password) throws IOException {
        Login newLogin = new Login();
        newLogin.setEmailUsername(userName);
        newLogin.setPassword(Password);
        if(!newLogin.checkUsernameEmail() && newLogin.userLoggedInStatus()){
            new GeneralFunctions().switchScene("CustomerPanel.fxml");
        }
    }
    public void resetPassword() throws IOException {
        new GeneralFunctions().switchSceneModality("SendCodeToEmail.fxml");
    }
    public void clear(){
        epValidation.setText("");
        EmailField.setStyle(resetStyle);
        passwordValidation.setText("");
        PassField.setStyle(resetStyle);
    }

    @FXML
    void dragged(MouseEvent event) {
        obj.stage = (Stage) anchorpane_login.getScene().getWindow();
        obj.stage.setX(event.getScreenX() - x);
        obj.stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }


    public void close() {
        new GeneralFunctions().close(exit);
    }
    @FXML
    void GoBackLogIn(ActionEvent e) throws IOException {
        new GeneralFunctions().switchScene(e, "LoginSignUp.fxml");
    }
}
