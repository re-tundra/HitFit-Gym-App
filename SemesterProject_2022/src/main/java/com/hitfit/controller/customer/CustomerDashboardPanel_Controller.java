package com.hitfit.controller.customer;

import com.hitfit.database.DatabaseFunctions;
import com.hitfit.model_class.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerDashboardPanel_Controller implements Initializable {

    @FXML
    private Text BMIDescription;

    @FXML
    private Text BMIText;

    @FXML
    private Text BMIValue;

    @FXML
    private Text remainingDays;

    @FXML
    private Text totalTransactions;

    @FXML
    private Button CompletedButton;

    @FXML
    private AnchorPane CompletedPane;

    @FXML
    private Button PendingButton;

    @FXML
    private AnchorPane PendingPane;

    @FXML
    private StackPane QueriesStckPane;

    @FXML
    void CompletedBtn(ActionEvent event) {
//        QueriesStckPane.getChildren().get(0).setVisible(true);
//        QueriesStckPane.getChildren().get(1).setVisible(false);
//        new animatefx.animation.FadeIn(QueriesStckPane).play();
    }

    @FXML
    void PendingBtn(ActionEvent event) {
//        QueriesStckPane.getChildren().get(0).setVisible(false);
//        QueriesStckPane.getChildren().get(1).setVisible(true);
//        new animatefx.animation.FadeIn(QueriesStckPane).play();
    }

    @FXML
    void goToBMIView(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        QueriesStckPane.getChildren().get(0).setVisible(true);
//        QueriesStckPane.getChildren().get(1).setVisible(false);
        BMIText.setText("BMI: " + getBMICategory(CustomerPanel_Controller.Customer.getBmi()));
        BMIValue.setText(String.format("%.2f", CustomerPanel_Controller.Customer.getBmi()));
        remainingDays.setText(DatabaseFunctions.getRemainingDaysOfCustomer(CustomerPanel_Controller.Customer.getCustomerId()) + "");
        totalTransactions.setText(String.format("%.2f", DatabaseFunctions.getTotalTransactionsOfCustomer(CustomerPanel_Controller.Customer.getCustomerId())));
    }


    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal";
        } else if (bmi >= 25.0 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
