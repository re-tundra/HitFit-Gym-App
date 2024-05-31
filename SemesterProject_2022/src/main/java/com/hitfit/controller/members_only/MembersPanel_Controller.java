package com.hitfit.controller.members_only;

import com.hitfit.controller.CustomMenuButton;
import com.hitfit.controller.GeneralFunctions;
import com.hitfit.database.DatabaseFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.hitfit.model_class.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class MembersPanel_Controller implements Initializable {

    // Making the field public so, it can be accessible without getter setters
    public static int deletingId=-1;

    private final static int DataSize = 100;
    private final static int rowsPerPage = 10;
    @FXML
    private Pagination pagination;

    Stage membercardstage;

    Customer customer = null;

    private String FullName;
    @FXML
    private TableColumn<Customer, Integer> Id;
    @FXML
    private TableColumn<Customer, MenuButton> action;

    @FXML
    private TableColumn<Customer, String> email;

    @FXML
    public TableView<Customer> membersView;

    @FXML
    private TableColumn<Customer, String> FirstName;
    @FXML
    private TableColumn<Customer, String> LastName;

    @FXML
    private TableColumn<Customer, String> isActive;

    @FXML
    private TableColumn<Customer, String> phone;

    @FXML
    private TableColumn<Customer, Integer> plan;

    @FXML
    private TextField keyword;

    public static ObservableList<Customer> memberslist = FXCollections.observableArrayList();
    ResultSet resultSet = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*Button*/

        pagination.setPageFactory(this::createPage);

        try {
            loadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*------Searching With Keryword Logic----------*/
        FilteredList<Customer> filteredList = new FilteredList<>(memberslist,b -> true);

        keyword.textProperty().addListener((observable,oldvalue,newvalue) ->
        {

            filteredList.setPredicate(customer -> {
                if(newvalue.isEmpty() || newvalue.isBlank())
                {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();

                return customer.getFullname().toLowerCase().contains(searchkeyword) || customer.getFirstName().toLowerCase().contains(searchkeyword) || customer.getLastName().toLowerCase().contains(searchkeyword) || customer.getPhoneNumber().toLowerCase().contains(searchkeyword) || customer.getNicNumber().toLowerCase().contains(searchkeyword) || String.valueOf(customer.getId()).contains(searchkeyword) || (String.valueOf(customer.getMembership()).toLowerCase().contains(searchkeyword)) || customer.getEmail().toLowerCase().contains(searchkeyword);

            });


            SortedList<Customer> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(membersView.comparatorProperty());
            membersView.setItems(sortedList);
        });
        /*----Search with Keyword Logic Ends HERE---------*/

    }
    @FXML
    void sortbtn(ActionEvent event) {

        memberslist.sort(Comparator.comparing(Customer::tolowerfirstname, Comparator.naturalOrder()));
        membersView.setItems(memberslist);

    }
    private Node createPage(int pageIndex) {
        if(!memberslist.isEmpty() && memberslist.size()<=10) {
            pagination.setPageCount(1);
        } else if(memberslist.size()>10 && memberslist.size()<=20)
        {
            pagination.setPageCount(2);
        } else if(memberslist.size()>20 && memberslist.size()<=30)
        {
            pagination.setPageCount(3);
        } else if(memberslist.size()>30 && memberslist.size()<=40)
        {
            pagination.setPageCount(4);
        } else if(memberslist.size()>40 && memberslist.size()<=50)
        {
            pagination.setPageCount(5);
        } else if(memberslist.size()>50 && memberslist.size()<=60)
        {
            pagination.setPageCount(6);
        } else if(memberslist.size()>60 && memberslist.size()<=70)
        {
            pagination.setPageCount(7);
        }
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex+rowsPerPage, memberslist.size());
        try{
            membersView.setItems(FXCollections.observableList(memberslist.subList(fromIndex, toIndex)));
        }
        catch (Exception e){
            System.out.println("Not Enough Entries");
        }
        return membersView;
    }

    public void view() throws IOException {
        new GeneralFunctions().switchSceneModality("membersDetailCard.fxml");
    }
    public void loadData() throws SQLException {
        showrecords();
        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        isActive.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        plan.setCellValueFactory(new PropertyValueFactory<>("membershipCategory"));
        action.setCellValueFactory(new PropertyValueFactory<>("actionBtn"));
    }

     void showrecords() throws SQLException {
        memberslist.clear();
         try {
             resultSet = DatabaseFunctions.getAllCustomers();


             while (resultSet.next()) {
                 memberslist.add(new Customer(true,
                         resultSet.getInt("id"),
                         resultSet.getString("first_name"),
                         resultSet.getString("last_name"),
                         resultSet.getString("email"),
                         resultSet.getString("phone_number"),
                         "",
                         Integer.parseInt(resultSet.getString("membership")),
                         resultSet.getBoolean("is_active"),
                         new CustomMenuButton(resultSet.getInt("id"),
                                 "Action",
                                 resultSet.getString("first_name")+" "+resultSet.getString("last_name"),
                                 resultSet.getDouble("weight"),
                                 resultSet.getDouble("height"),
                                 resultSet.getString("address"),
                                 resultSet.getString("email"),
                                 resultSet.getString("username"),
                                 resultSet.getString("membership"))));

                 membersView.setItems(memberslist);
             }
         }

         catch (NullPointerException e){
                 System.out.println("Connection to Database Cannot Be Established");
             }
    }
    @FXML
    void sortbtn1(ActionEvent event) {
        memberslist.sort(Comparator.comparing(Customer::getId, Comparator.naturalOrder()));
        membersView.setItems(memberslist);

    }

    @FXML
    void refreshbtn(ActionEvent event) throws SQLException {

            keyword.setText("");
            showrecords();

    }

}
