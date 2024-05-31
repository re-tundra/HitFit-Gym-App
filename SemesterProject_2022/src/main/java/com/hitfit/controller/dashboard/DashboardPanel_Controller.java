package com.hitfit.controller.dashboard;

import com.hitfit.database.DatabaseFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardPanel_Controller implements Initializable {

/*-Buttons-*/
    @FXML
    private Button PendingButton;
    @FXML
    private Button CompletedButton;
    @FXML
    private Button dueButton;
    @FXML
    private Button expiredButton;
    @FXML
    private Button recentButton;
    @FXML
    private Button HistoryButton;
    @FXML
    private Button RecentButtonExpenses;
    @FXML
    private Button InStockButton;
    @FXML
    private Button OutofStockButton;
/*--------*/

    @FXML
    private Text activeMembers;
    @FXML
    public ScrollPane scrollpanedashboard = new ScrollPane();
    @FXML
    private Text monthlyExpense;

    @FXML
    private Text monthlyMembers;


    @FXML
    private Text monthlyRevenue;

    @FXML
    private Text pendingPayments;

    @FXML
    private LineChart<String, Double> monthlyRevenueChart;

    @FXML
    private BarChart<String, Integer> monthlyRegistrationChart;
    @FXML
    private Text totalMembers;

    private void updateRecords() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        XYChart.Series<String, Double> revenueSeries = new XYChart.Series<>();
        XYChart.Series<String, Integer> registrationSeries = new XYChart.Series<>();
        registrationSeries.setName(currentYear + "'s Monthly Registrations");
        revenueSeries.setName(currentYear + "'s Monthly Revenue");

        monthlyRevenueChart.getData().clear();
        monthlyRegistrationChart.getData().clear();

        for (int i = 1; i <= 12; i++) {
            double revenue = DatabaseFunctions.getMonthlyRevenue(i);
            int registeredCustomers = DatabaseFunctions.getMonthlyNumberOfRegistrations(i);

            revenueSeries.getData().add(new XYChart.Data<>(String.valueOf(i), revenue));
            registrationSeries.getData().add(new XYChart.Data<>(String.valueOf(i), registeredCustomers));
        }

        monthlyRevenueChart.getData().add(revenueSeries);
        monthlyRegistrationChart.getData().add(registrationSeries);

        int noOfCustomers = 0, activeMembersCount = 0;
        double revenue = 0.0;

        try{
            int[] customersCount = DatabaseFunctions.getNumberOfCustomers();

            revenue = DatabaseFunctions.getCurrentMonthRevenue();
            noOfCustomers = customersCount[0];
            activeMembersCount = customersCount[1];
        }
        catch (Exception e){
            e.printStackTrace();
        }

        monthlyRevenue.setText(String.format("%.2f", revenue));
        totalMembers.setText(String.valueOf(noOfCustomers));
        activeMembers.setText(String.valueOf(activeMembersCount));
    }


    @FXML
    void refreshbtn(ActionEvent event) {
        updateRecords();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateRecords();
    }
}
