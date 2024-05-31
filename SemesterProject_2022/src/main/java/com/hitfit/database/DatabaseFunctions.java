package com.hitfit.database;

import backend_functions.CustomDate;
import backend_functions.Login;
import com.hitfit.controller.customer.CustomerPanel_Controller;
import com.hitfit.controller.employees.EmployeesPanel_Controller;
import com.hitfit.model_class.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseFunctions {

//    private static final String dbUrl = "jdbc:mysql://localhost:3306/gym_database?useSSL=false";
    private static final String dbUrlFormat = "jdbc:sqlserver://localhost:1433;databaseName=%s;trustServerCertificate=true;encrypt=false";
    private static final String NAME_KEY = "db_name";
    private static final String USERNAME_KEY = "db_user";
    private static final String PASSWORD_KEY = "db_password";

    private static Connection dbConnection = null;

    public static int customersListCount;
    public static int employeesListCount;
    public static int totalList;

    public static void initFunction() {
        makeConnection();

    }

    public static boolean makeConnection() {
        try {
            File dbPropFile = new File("db.properties");

            if (dbPropFile.exists()) {
                Properties prop = new Properties();
                InputStream input = new FileInputStream("db.properties");
                prop.load(input);

                String dbUrl = String.format(dbUrlFormat, prop.getProperty(NAME_KEY));
                String dbUsername = prop.getProperty(USERNAME_KEY);
                String dbPassword = prop.getProperty(PASSWORD_KEY);

                dbConnection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
                return true;
            }

            throw new FileNotFoundException();
        } catch (SQLException e) {
            System.out.println("Error! Could not connect to Db: " + e);
        } catch (FileNotFoundException e) {
            System.out.println("Error! Could not connect to Db: No db.properties file found");
        } catch (IOException e) {
            System.out.println("Error! Could not connect to Db: Couldn't read db.properties file");
        }

        return false;
    }

    public static void saveToDb(Customer customer) {

        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    insert into customers (first_name, last_name, email, phone_number, password, username, gender, weight, height, dob,
                    membership, is_active, address)
                    values (?,?,?,?,?,?,?,?,?,?,?,?,?);""");

//            queryStatement.setInt(1, customer.getCustomerId());
            queryStatement.setString(1, customer.getFirstName());
            queryStatement.setString(2, customer.getLastName());
            queryStatement.setString(3, customer.getEmail());
            queryStatement.setString(4, customer.getPhoneNumber());
            queryStatement.setString(5, customer.getPassword());
            queryStatement.setString(6, customer.getUserName());
            queryStatement.setString(7, customer.getGender());
            queryStatement.setDouble(8, customer.getWeight());
            queryStatement.setDouble(9, customer.getHeight());
            queryStatement.setString(10, customer.getDob());
            queryStatement.setInt(11, customer.getMembership());
//            queryStatement.setString(12, customer.getNicNumber());
            queryStatement.setBoolean(12, false);
//            queryStatement.setString(14, customer.getPasswordSalt());
            queryStatement.setString(13, customer.getAddress());
            queryStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
        }

    }

    public static boolean saveToDb(Transaction transaction) {
        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    INSERT INTO transactions (created_date, amount, p_method, account_owner_name, fk_customer_id, status)
                    VALUES (?,?,?,?,?,?);""");

//            queryStatement.setInt(1, transaction.getTransactionId());
            queryStatement.setDate(1, CustomDate.getCurrentDate());
            queryStatement.setInt(2, transaction.getAmount());
//            queryStatement.setString(4, transaction.getTransactionNumber());
//            queryStatement.setString(5, transaction.getBankName());
            queryStatement.setString(3, transaction.getPaymentMethod());
            queryStatement.setString(4, transaction.getAccountOwnerName());
            queryStatement.setInt(5, transaction.getFkCustomerId());
            queryStatement.setBoolean(6, transaction.isStatus());

            queryStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
            return false;
        }
    }

    public static void saveToDb(Employee employee) {

        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    INSERT INTO employees (first_name, last_name, designation, nic_number, salary, gender, phone_number, joining_date, username, password, salt, access,email)
                    VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);""");

//            queryStatement.setInt(1, employee.getId());
            queryStatement.setString(1, employee.getFirstName());
            queryStatement.setString(2, employee.getLastName());
            queryStatement.setString(3, employee.getDesignation());
            queryStatement.setString(4, employee.getNicNumber());
            queryStatement.setInt(5, employee.getSalary());
            queryStatement.setString(6, employee.getGender());
            queryStatement.setString(7, employee.getPhoneNumber());
            queryStatement.setDate(8, CustomDate.getCurrentDate());
            queryStatement.setString(9, employee.getUserName());
            queryStatement.setString(10, employee.getPassword());
            queryStatement.setString(11, employee.getSalt());
            queryStatement.setInt(12, employee.getAccess());
            queryStatement.setString(13, employee.getEmail());

            queryStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
        }

    }

    public static boolean saveToDb(Expense expense, Integer fkEmployeeId) {

        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    INSERT INTO expenses (id, description,created_date, amount, month, year, fk_employee_id, selected_date)
                    VALUES (?,?,?,?,?,?,?,?)
                    """);

            queryStatement.setInt(1, expense.getId());
            queryStatement.setString(2, expense.getDescription());
            queryStatement.setDate(3, CustomDate.getCurrentDate());
            queryStatement.setInt(4, expense.getAmount());
            queryStatement.setString(5, expense.getMonth());
            queryStatement.setString(6, expense.getYear());
            queryStatement.setObject(7, fkEmployeeId);
            queryStatement.setDate(8, expense.getSelectedDate());

            queryStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return true;
    }

    public static boolean saveToDb(Queries query) {

        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    INSERT INTO queries (id, heading, email, description, created_date, username)
                    VALUES (?,?,?,?,?,?)""");

            queryStatement.setInt(1, query.getId());
            queryStatement.setString(2, query.getHeading());
            queryStatement.setString(3, query.getUsername());
            queryStatement.setString(4, query.getDescription());
            queryStatement.setDate(5, query.getCurrent_date());
            queryStatement.setString(6, query.getUsername());

            queryStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    public static boolean saveToDb(BMI bmi, int fk_customer_id) {

        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    INSERT INTO bmi (id, weight, recorded_date, fk_customer_id, recorded_month, height, bmi_value)
                    VALUES (?,?,?,?,?,?,?);
                    """);

            queryStatement.setInt(1, bmi.getId());
            queryStatement.setDouble(2, bmi.getWeight());
            queryStatement.setDate(3, bmi.getRecordedDate());
            queryStatement.setInt(4, fk_customer_id);
            queryStatement.setString(5, bmi.getRecordedMonth());
            queryStatement.setDouble(6, bmi.getHeight());
            queryStatement.setDouble(7, bmi.getBMI());

            queryStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }

        return true;
    }

    public static boolean saveToDb(String reply, int id) {

        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    UPDATE queries
                    SET reply = ? AND status = true
                    WHERE id = ?
                    """);
            queryStatement.setString(1, reply);
            queryStatement.setInt(2, id);
            queryStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    public static boolean saveUpdateToDb(Revenue revenue) {

        PreparedStatement queryStatement = null;
        ResultSet revenueRs = null;
        int amountNew = 0;

        try {

            queryStatement = dbConnection.prepareStatement("""
                    SELECT * FROM revenues WHERE for_month = ? AND for_year = ?;
                    """);

            queryStatement.setString(1, revenue.getForMonth());
            queryStatement.setString(2, revenue.getForYear());

            revenueRs = queryStatement.executeQuery();

            while (revenueRs.next()) {
                amountNew = revenueRs.getInt("amount");
            }

            if (amountNew == 0) {

                queryStatement = dbConnection.prepareStatement("""
                        INSERT INTO revenues (id, for_month, for_year, updated_date, amount)
                        VALUES (?,?,?,?,?);
                        """);
                queryStatement.setInt(1, revenue.getId());
                queryStatement.setString(2, revenue.getForMonth());
                queryStatement.setString(3, revenue.getForYear());
                queryStatement.setDate(4, CustomDate.getCurrentDate());
                queryStatement.setInt(5, revenue.getAmount());

                queryStatement.executeUpdate();

            } else {
                queryStatement = dbConnection.prepareStatement("""
                        UPDATE revenues
                        SET amount = ?
                        WHERE for_month = ? AND for_year = ?;
                        """);


                queryStatement.setInt(1, amountNew + revenue.getAmount());
                queryStatement.setString(2, revenue.getForMonth());
                queryStatement.setString(3, revenue.getForYear());
                queryStatement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return true;
    }

    public static void updateCustomerPassword(String email, String[] password) {
        PreparedStatement queryStatement = null;
        try {
            queryStatement = dbConnection.prepareStatement("UPDATE customers SET password = ?, salt = ? WHERE email = ?");
            queryStatement.setString(1, password[1]);
            queryStatement.setString(2, password[0]);
            queryStatement.setString(3, email);
            queryStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void updateEmployeePassword(String email, String[] password) {
        PreparedStatement queryStatement = null;
        try {
            queryStatement = dbConnection.prepareStatement("UPDATE employees SET password = ?, salt = ? WHERE email = ?");
            queryStatement.setString(1, password[1]);
            queryStatement.setString(2, password[0]);
            queryStatement.setString(3, email);
            queryStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void updateTransactionStatus(int transactionId) {

        PreparedStatement queryStatement;
        PreparedStatement queryStatement2;
        int fkCustomerId = 0;
//        int transactionAmount = 0;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    UPDATE transactions
                    SET status = 1
                    WHERE id = ?;
            """);
            queryStatement.setInt(1, transactionId);
            queryStatement.executeUpdate();

            try {
                PreparedStatement queryStatement3 = dbConnection.prepareStatement("SELECT fk_customer_id FROM transactions WHERE id = ?");
                queryStatement3.setInt(1, transactionId);
                ResultSet resultSet = queryStatement3.executeQuery();

                while (resultSet.next()) {
                    fkCustomerId = resultSet.getInt("fk_customer_id");
                }

            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }

            queryStatement2 = dbConnection.prepareStatement("""
                    UPDATE customers
                    SET is_active = 1
                    WHERE id = ?;
            """);

            queryStatement2.setInt(1, fkCustomerId);
            queryStatement2.executeUpdate();

//            queryStatement = dbConnection.prepareStatement("""
//                    SELECT amount FROM transactions
//                    WHERE fk_customer_id = ?;
//            """);
//            queryStatement.setInt(1, fkCustomerId);
//
//            ResultSet transactionAmountRs = queryStatement.executeQuery();
//
//            while (transactionAmountRs.next()) {
//                transactionAmount = transactionAmountRs.getInt(1);
//            }
//
//            Revenue revenue = new Revenue(DatabaseFunctions.generateId("revenues"), CustomDate.getCurrentMonth(), CustomDate.getCurrentYear(), transactionAmount);
//            DatabaseFunctions.saveUpdateToDb(revenue);

        } catch (SQLException e) {
            System.out.println("Error! Could not run query: " + e);
        }
    }

    public static boolean updateSalaryStatus(int employeeId, String email) {

        PreparedStatement queryStatement = null;
        ResultSet salaryRs = null;
        int employeeSalary = 0;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT salary FROM employees
                    WHERE id = ?
                    """);

            queryStatement.setInt(1, employeeId);

            salaryRs = queryStatement.executeQuery();

            while (salaryRs.next()) {
                employeeSalary = salaryRs.getInt("salary");
            }


            Revenue revenue = new Revenue(DatabaseFunctions.generateId("revenues"), CustomDate.getCurrentMonth(), CustomDate.getCurrentYear(), -employeeSalary);
            DatabaseFunctions.saveUpdateToDb(revenue);

        } catch (SQLException e) {
            System.out.println("error: " + e);
        }

        return true;

    }

    public static ResultSet getAllCustomers() {
        ResultSet allDataRs = null;
        PreparedStatement queryStatement;

        try {
            queryStatement = dbConnection.prepareStatement("SELECT * FROM customers");
            allDataRs = queryStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println("Error in getting customers: " + e);
        }

        return allDataRs;
    }

    public static ArrayList<Customer> getRecentCustomers() {
        ArrayList<Customer> result = new ArrayList<>();
        PreparedStatement queryStatement;

        try {
            queryStatement = dbConnection.prepareStatement("SELECT * FROM RecentCustomers");
            ResultSet allDataRs = queryStatement.executeQuery();

            while (allDataRs.next()) {
                result.add(
                    new Customer(
                        allDataRs.getString("first_name"),
                        allDataRs.getString("last_name"),
                        allDataRs.getString("email"),
                        allDataRs.getString("gender"),
                        allDataRs.getString("phoneNumber"),
                        allDataRs.getString("userName"),
                        allDataRs.getString("password"),
                        "",
                        allDataRs.getString("address"),
                        allDataRs.getString("dob"),
                        allDataRs.getDouble("weight"),
                        allDataRs.getDouble("height"),
                        allDataRs.getInt("membership"),
                        allDataRs.getInt("id"),
                        "",
                        0.0
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println("Error in getting customers: " + e);
        }

        return result;
    }

    public static ArrayList<Customer> getOnDueCustomers() {
        ArrayList<Customer> result = new ArrayList<>();
        PreparedStatement queryStatement;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT * FROM OnDueCustomers
                    WHERE remaining_days_to_next_month < 7 AND remaining_days_to_next_month > 0;
                    """);
            ResultSet allDataRs = queryStatement.executeQuery();

            while (allDataRs.next()) {
                result.add(
                        new Customer(
                                allDataRs.getString("first_name"),
                                allDataRs.getString("last_name"),
                                allDataRs.getString("email"),
                                allDataRs.getString("gender"),
                                allDataRs.getString("phoneNumber"),
                                allDataRs.getString("userName"),
                                allDataRs.getString("password"),
                                "",
                                allDataRs.getString("address"),
                                allDataRs.getString("dob"),
                                allDataRs.getDouble("weight"),
                                allDataRs.getDouble("height"),
                                allDataRs.getInt("membership"),
                                allDataRs.getInt("id"),
                                "",
                                0.0
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println("Error in getting customers: " + e);
        }

        return result;
    }

    public static ArrayList<Customer> getExpiredCustomers() {
        ArrayList<Customer> result = new ArrayList<>();
        PreparedStatement queryStatement;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT * FROM OnDueCustomers
                    WHERE remaining_days_to_next_month <= 0;
                    """);
            ResultSet allDataRs = queryStatement.executeQuery();

            while (allDataRs.next()) {
                result.add(
                        new Customer(
                                allDataRs.getString("first_name"),
                                allDataRs.getString("last_name"),
                                allDataRs.getString("email"),
                                allDataRs.getString("gender"),
                                allDataRs.getString("phoneNumber"),
                                allDataRs.getString("userName"),
                                allDataRs.getString("password"),
                                "",
                                allDataRs.getString("address"),
                                allDataRs.getString("dob"),
                                allDataRs.getDouble("weight"),
                                allDataRs.getDouble("height"),
                                allDataRs.getInt("membership"),
                                allDataRs.getInt("id"),
                                "",
                                0.0
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println("Error in getting customers: " + e);
        }

        return result;
    }

    public static int[] getNumberOfMemberships() {

        ResultSet resultSet = null;
        PreparedStatement queryStatement = null;
        ArrayList<Integer> tempArr = new ArrayList<>();
        int[] allMemberships = new int[3];

        Package1 package1 = new Package1();
        Package2 package2 = new Package2();
        Package3 package3 = new Package3();

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT membership
                    FROM customers
                    ORDER BY membership ASC;
                    """);

            resultSet = queryStatement.executeQuery();

            while (resultSet.next()) {

                tempArr.add(resultSet.getInt(1));

            }

            for (int i : tempArr) {
                if (i == package1.getAmount()) {
                    allMemberships[0] += 1;
                } else if (i == package2.getAmount()) {
                    allMemberships[1] += 1;
                } else if (i == package3.getAmount()) {
                    allMemberships[2] += 1;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error in getting memberships: " + e);
        }

        return allMemberships;
    }

    public static double getMonthlyRevenue(int month) {
        double revenue = 0;

        try {
            PreparedStatement queryStatement = dbConnection.prepareStatement("""
                    SELECT
                        SUM(amount) AS total_month_revenue
                    FROM transactions
                    WHERE MONTH(created_date) = ?
                      AND YEAR(created_date) = YEAR(GETDATE());
                    """);

            queryStatement.setInt(1, month);
            ResultSet resultSet = queryStatement.executeQuery();
            while (resultSet.next()) {
                revenue = resultSet.getDouble("total_month_revenue");
            }
        } catch (SQLException e) {
            System.out.println("Error in getting monthly revenue for month " + month + ": " + e);
        }


        return Math.max(0, revenue);
    }

    public static int getMonthlyNumberOfRegistrations(int month) {
        int registrations = 0;

        try {
            PreparedStatement queryStatement = dbConnection.prepareStatement("""
                    SELECT COUNT(DISTINCT(customer_id)) as total_monthly_registered
                    FROM LatestTransactionOfCustomers
                    WHERE MONTH(latest_transaction_date) = ?
                        AND YEAR(latest_transaction_date) = YEAR(GETDATE());
                    """);

            queryStatement.setInt(1, month);
            ResultSet resultSet = queryStatement.executeQuery();
            while (resultSet.next()) {
                registrations = resultSet.getInt("total_monthly_registered");
            }
        } catch (SQLException e) {
            System.out.println("Error in getting monthly registrations for month " + month + ": " + e);
        }


        return Math.max(0, registrations);
    }

    public static ResultSet getQuery(String username) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = dbConnection.prepareStatement("""
                    SELECT * FROM queries WHERE  username = ?
                    """);

            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            return resultSet;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return resultSet;
    }

    public static ResultSet getAllEmployees() {
        ResultSet allDataRs = null;
        PreparedStatement queryStatement;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT id, first_name, last_name, designation, nic_number, salary, gender, phone_number, joining_date, username, access, email FROM employees""");
            allDataRs = queryStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println("Error in getting employees: " + e);
        }
        return allDataRs;
    }

    public static ResultSet getAllExpenses() {

        PreparedStatement queryStatement = null;
        ResultSet expensesRs = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT id, description, amount, selected_date, month, year FROM expenses""");
            expensesRs = queryStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return expensesRs;
    }

    public static ResultSet getAllQueries() {

        PreparedStatement queryStatement = null;
        ResultSet expensesRs = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT * FROM queries;
                    """);
            expensesRs = queryStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return expensesRs;

    }

    public static ResultSet getAllTransactions() {

        PreparedStatement queryStatement = null;
        ResultSet expensesRs = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT * FROM transactions;
                    """);
            expensesRs = queryStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return expensesRs;

    }

    public static ResultSet getAllRevenues() {

        PreparedStatement queryStatement = null;
        ResultSet expensesRs = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT * FROM revenues;
                    """);
            expensesRs = queryStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }

        return expensesRs;
    }

    public static ResultSet getAllBmiInfo() {

        PreparedStatement queryStatement = null;
        ResultSet bmiRs = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT * FROM bmi;
                    """);
            bmiRs = queryStatement.executeQuery();

            while (bmiRs.next()) {
                System.out.println(bmiRs.getInt(1));
                System.out.println(bmiRs.getString(2));
                System.out.println(bmiRs.getString(3));
            }

        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }

        return bmiRs;

    }

    public static int getCurrentMonthExpense() {

        PreparedStatement queryStatement = null;
        ResultSet allExpensesRs = null;
        int totalMonthlyExpense = 0;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    SELECT amount FROM expenses
                    WHERE month = ?;""");

            queryStatement.setString(1, CustomDate.getCurrentMonth());
            allExpensesRs = queryStatement.executeQuery();

            while (allExpensesRs.next()) {

                totalMonthlyExpense += allExpensesRs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        System.out.println(totalMonthlyExpense);
        return totalMonthlyExpense;
    }

    public static String getAccountPassword(String customerUsernameEmail, String tableName) {

//        ArrayList<String> saltPassArray = new ArrayList<>();
        String result = null;

        switch (Login.queryOption) {
            case "username" -> {
                try {
                    PreparedStatement queryStatement = dbConnection.prepareStatement("SELECT * FROM " + tableName + " WHERE username = ?");
                    queryStatement.setString(1, customerUsernameEmail);
                    ResultSet resultSet = queryStatement.executeQuery();

                    while (resultSet.next()) {
                        result = resultSet.getString("password");
                    }

                } catch (SQLException e) {
                    System.out.println("Error in retrieving password: " + e);
                }
            }

            case "email" -> {
                try {
                    PreparedStatement queryStatement = dbConnection.prepareStatement("SELECT * FROM " + tableName + " WHERE email = ?");
                    queryStatement.setString(1, customerUsernameEmail);
                    ResultSet resultSet = queryStatement.executeQuery();

                    while (resultSet.next()) {
                        result = resultSet.getString("password");
                    }


                } catch (SQLException e) {
                    System.out.println("Error in retrieving password: " + e);
                }
            }
        }

        return result;

    }

//    public static String getEmployeePassword(String employeeUsernameEmail) {
//
//        ArrayList<String> saltPassArray = new ArrayList<>();
//
//        switch (Login.queryOption) {
//            case "username" -> {
//                try {
//                    PreparedStatement queryStatement = dbConnection.prepareStatement("SELECT * FROM employees WHERE username = ?");
//                    queryStatement.setString(1, employeeUsernameEmail);
//                    ResultSet resultSet = queryStatement.executeQuery();
//
//                    while (resultSet.next()) {
//                        saltPassArray.add(resultSet.getString("salt"));
//                        saltPassArray.add(resultSet.getString("password"));
//                    }
//
//                } catch (SQLException e) {
//                    System.out.println("Error in retrieving customer: " + e);
//                }
//            }
//
//            case "email" -> {
//                try {
//                    PreparedStatement queryStatement = dbConnection.prepareStatement("SELECT * FROM employees WHERE email = ?");
//                    queryStatement.setString(1, employeeUsernameEmail);
//                    ResultSet resultSet = queryStatement.executeQuery();
//
//                    while (resultSet.next()) {
//                        saltPassArray.add(resultSet.getString("salt"));
//                        saltPassArray.add(resultSet.getString("password"));
//                    }
//
//
//                } catch (SQLException e) {
//                    System.out.println("Error in retrieving customer: " + e);
//                }
//            }
//        }
//
//        return saltPassArray;
//
//    }

    public static ArrayList<String> getAllUsernames() {

        ResultSet allUsernamesRs = null;
        PreparedStatement queryStatement = null;
        ArrayList<String> allUsernames = new ArrayList<>();


        try {
            queryStatement = dbConnection.prepareStatement("SELECT username FROM customers");

            allUsernamesRs = queryStatement.executeQuery();

            while (allUsernamesRs.next()) {
                allUsernames.add(allUsernamesRs.getString(1));
                customersListCount++;
            }

        } catch (SQLException e) {
            System.out.println("Error in retrieving usernames: " + e);
        }

        try {
            queryStatement = dbConnection.prepareStatement("SELECT username FROM employees");

            allUsernamesRs = queryStatement.executeQuery();

            while (allUsernamesRs.next()) {
                allUsernames.add(allUsernamesRs.getString(1));
                employeesListCount++;
            }

        } catch (SQLException e) {
            System.out.println("Error in retrieving usernames: " + e);
        }

        return allUsernames;

    }

    public static ArrayList<String> getAllEmails() {

        ResultSet allEmailsRs = null;
        PreparedStatement queryStatement = null;
        ArrayList<String> allEmails = new ArrayList<>();


        try {
            queryStatement = dbConnection.prepareStatement("SELECT email FROM customers");

            allEmailsRs = queryStatement.executeQuery();

            while (allEmailsRs.next()) {
                allEmails.add(allEmailsRs.getString(1));
                customersListCount++;
            }


        } catch (SQLException e) {
            System.out.println("Error in retrieving usernames: " + e);
        }

        try {
            queryStatement = dbConnection.prepareStatement("SELECT email FROM employees");

            allEmailsRs = queryStatement.executeQuery();

            while (allEmailsRs.next()) {
                allEmails.add(allEmailsRs.getString(1));
                employeesListCount++;
            }


        } catch (SQLException e) {
            System.out.println("Error in retrieving usernames: " + e);
        }


        return allEmails;
    }

    public static int[] getNumberOfCustomers() {

        PreparedStatement queryStatement = null;
        int allCustomers = 0;
        int allActiveCustomers = 0;

        try {
            queryStatement = dbConnection.prepareStatement(
            """
                    SELECT
                        COUNT(c1.id) AS total_customers,
                        COUNT(c2.id) AS total_active_customers
                    FROM customers c1
                    LEFT JOIN customers c2 ON c1.id = c2.id AND c2.is_active = 1;
                """
            );

            ResultSet set = queryStatement.executeQuery();

            while (set.next()) {
                allCustomers = set.getInt(1);
                allActiveCustomers = set.getInt(2);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting number of customers: " + e);
        }

        return new int[]{allCustomers, allActiveCustomers};
    }

    public static double getCurrentMonthRevenue() {
        PreparedStatement queryStatement = null;
        double revenue = 0;

        try {
            queryStatement = dbConnection.prepareStatement(
            """
                    SELECT
                        SUM(amount) AS MonthlyRevenue
                    FROM transactions
                    WHERE created_date
                        BETWEEN DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0)
                        AND DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) + 1, 0);
                """
            );

            ResultSet set = queryStatement.executeQuery();

            while (set.next()) {
                revenue = set.getDouble("MonthlyRevenue");
            }

        } catch (SQLException e) {
            System.out.println("Error in getting monthly revenue: " + e);
        }

        return revenue;
    }

    public static int getTotalList() {

        totalList = customersListCount + employeesListCount;
        return totalList;

    }

    public static void getLoggedInCustomer(String usernameEmail) {

        ResultSet allDataRs = null;
        PreparedStatement queryStatement = null;

        if (Login.queryOption.equals("email")) {

            try {
                queryStatement = dbConnection.prepareStatement("""
                        SELECT * FROM CustomersWithBMI
                        WHERE email = ?;
                        """);
                queryStatement.setString(1, usernameEmail);
                allDataRs = queryStatement.executeQuery();

                while (allDataRs.next()) {

                    CustomerPanel_Controller.Customer.setFirstName(allDataRs.getString("first_name"));
                    CustomerPanel_Controller.Customer.setLastName(allDataRs.getString("last_name"));
                    CustomerPanel_Controller.Customer.setCustomerId(allDataRs.getInt("id"));
                    CustomerPanel_Controller.Customer.setEmail(allDataRs.getString("email"));
                    CustomerPanel_Controller.Customer.setPhoneNumber(allDataRs.getString("phone_number"));
                    CustomerPanel_Controller.Customer.setUserName(allDataRs.getString("username"));
                    CustomerPanel_Controller.Customer.setGender(allDataRs.getString("gender"));
                    CustomerPanel_Controller.Customer.setWeight(allDataRs.getDouble("weight"));
                    CustomerPanel_Controller.Customer.setHeight(allDataRs.getDouble("height"));
                    CustomerPanel_Controller.Customer.setDob(allDataRs.getString("dob"));
                    CustomerPanel_Controller.Customer.setMembership(allDataRs.getInt("membership"));
//                    CustomerPanel_Controller.Customer.setNicNumber(allDataRs.getString("nic"));
                    CustomerPanel_Controller.Customer.setAddress(allDataRs.getString("address"));
                    CustomerPanel_Controller.Customer.setBmi(allDataRs.getDouble("bmi"));
                    CustomerPanel_Controller.Customer.setPassword(allDataRs.getString("password"));

                }

            } catch (SQLException e) {
                System.out.println("Error in logged in customer: " + e);
            }

        } else if (Login.queryOption.equals("username")) {

            try {
                queryStatement = dbConnection.prepareStatement("""
                        SELECT * FROM CustomersWithBMI
                        WHERE username = ?;
                        """);
                queryStatement.setString(1, usernameEmail);
                allDataRs = queryStatement.executeQuery();

                while (allDataRs.next()) {

                    CustomerPanel_Controller.Customer.setFirstName(allDataRs.getString("first_name"));
                    CustomerPanel_Controller.Customer.setLastName(allDataRs.getString("last_name"));
                    CustomerPanel_Controller.Customer.setCustomerId(allDataRs.getInt("id"));
                    CustomerPanel_Controller.Customer.setEmail(allDataRs.getString("email"));
                    CustomerPanel_Controller.Customer.setPhoneNumber(allDataRs.getString("phone_number"));
                    CustomerPanel_Controller.Customer.setUserName(allDataRs.getString("username"));
                    CustomerPanel_Controller.Customer.setGender(allDataRs.getString("gender"));
                    CustomerPanel_Controller.Customer.setWeight(allDataRs.getDouble("weight"));
                    CustomerPanel_Controller.Customer.setHeight(allDataRs.getDouble("height"));
                    CustomerPanel_Controller.Customer.setDob(allDataRs.getString("dob"));
                    CustomerPanel_Controller.Customer.setMembership(allDataRs.getInt("membership"));
                    // CustomerPanel_Controller.Customer.setNicNumber(allDataRs.getString("nic"));
                    CustomerPanel_Controller.Customer.setAddress(allDataRs.getString("address"));
                    CustomerPanel_Controller.Customer.setBmi(allDataRs.getDouble("bmi"));
                    CustomerPanel_Controller.Customer.setPassword(allDataRs.getString("password"));

                }

            } catch (SQLException e) {
                System.out.println("Error in getting ids: " + e);
            }

        }
    }

    public static void getLoggedInEmployee(String usernameEmail) {

        ResultSet allDataRs = null;
        PreparedStatement queryStatement = null;

        if (Login.queryOption.equals("email")) {

            try {
                queryStatement = dbConnection.prepareStatement("""
                    SELECT * FROM employees
                    WHERE email = ?;""");

                queryStatement.setString(1, usernameEmail);
                allDataRs = queryStatement.executeQuery();

                while (allDataRs.next()) {

                    EmployeesPanel_Controller.employee.setId(allDataRs.getInt("id"));
                    EmployeesPanel_Controller.employee.setFirstName(allDataRs.getString("first_name"));
                    EmployeesPanel_Controller.employee.setLastName(allDataRs.getString("last_name"));
                    EmployeesPanel_Controller.employee.setDesignation(allDataRs.getString("designation"));
                    EmployeesPanel_Controller.employee.setNicNumber(allDataRs.getString("nic_number"));
                    EmployeesPanel_Controller.employee.setSalary(allDataRs.getInt("salary"));
                    EmployeesPanel_Controller.employee.setGender(allDataRs.getString("gender"));
                    EmployeesPanel_Controller.employee.setPhoneNumber(allDataRs.getString("phone_number"));
                    EmployeesPanel_Controller.employee.setUserName(allDataRs.getString("username"));
                    EmployeesPanel_Controller.employee.setEmail(allDataRs.getString("email"));

                }

            } catch (SQLException e) {
                System.out.println("Error in getting ids: " + e);
            }

        } else if (Login.queryOption.equals("username")) {

            try {
                queryStatement = dbConnection.prepareStatement("""
                        SELECT id, first_name, last_name, email, phone_number, username, gender, weight, dob, membership, nic, is_active, address FROM customers
                        WHERE username = ?;
                        """);
                queryStatement.setString(1, usernameEmail);
                allDataRs = queryStatement.executeQuery();

                while (allDataRs.next()) {

                    EmployeesPanel_Controller.employee.setId(allDataRs.getInt("id"));
                    EmployeesPanel_Controller.employee.setFirstName(allDataRs.getString("first_name"));
                    EmployeesPanel_Controller.employee.setLastName(allDataRs.getString("last_name"));
                    EmployeesPanel_Controller.employee.setDesignation(allDataRs.getString("designation"));
                    EmployeesPanel_Controller.employee.setNicNumber(allDataRs.getString("nic_number"));
                    EmployeesPanel_Controller.employee.setSalary(allDataRs.getInt("salary"));
                    EmployeesPanel_Controller.employee.setGender(allDataRs.getString("gender"));
                    EmployeesPanel_Controller.employee.setPhoneNumber(allDataRs.getString("phone_number"));
                    EmployeesPanel_Controller.employee.setUserName(allDataRs.getString("username"));
                    EmployeesPanel_Controller.employee.setEmail(allDataRs.getString("email"));

                }

            } catch (SQLException e) {
                System.out.println("Error in getting ids: " + e);
            }

        }
    }

    public static void deleteData(String tableName, int id) {

        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("""
                    CALL delete_data(?,?);""");

            queryStatement.setString(1, tableName);
            queryStatement.setInt(2, id);

            queryStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("error in deleting: " + e);
        }

    }

    public static int generateId(String choice) {

        ResultSet allIds = null;
        int lastId = 0;
        PreparedStatement queryStatement = null;

        try {
            queryStatement = dbConnection.prepareStatement("SELECT * FROM " + choice);
            allIds = queryStatement.executeQuery();
            while (allIds.next()) {
                lastId = allIds.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error in getting ids: " + e);
        }
        return lastId + 1;

    }

    public static int getRemainingDaysOfCustomer(int customerId) {
        int result = 0;

        try {
            PreparedStatement queryStatement = dbConnection.prepareStatement(
            """
                SELECT
                    DATEDIFF(
                        DAY,
                        GETDATE(),
                        DATEADD(MONTH, 1, l.latest_transaction_date)
                    ) AS remaining_days_to_next_month
                FROM LatestTransactionOfCustomers as l
                WHERE l.customer_id = ?;
                """
            );
            queryStatement.setInt(1, customerId);

            ResultSet set = queryStatement.executeQuery();
            while (set.next()) {
                result = set.getInt("remaining_days_to_next_month");
            }
        } catch (SQLException e) {
            System.out.println("Error in customer's remaining days: " + e);
        }

        return Math.max(0, result);
    }

    public static double getTotalTransactionsOfCustomer(int customerId) {
        double result = 0;

        try {
            PreparedStatement queryStatement = dbConnection.prepareStatement(
            """
                   SELECT SUM(amount) AS total_amount
                   FROM transactions
                   WHERE fk_customer_id = ?
                """
            );
            queryStatement.setInt(1, customerId);

            ResultSet set = queryStatement.executeQuery();
            while (set.next()) {
                result = set.getDouble("total_amount");
            }
        } catch (SQLException e) {
            System.out.println("Error in total sum transactions: " + e);
        }

        return Math.max(0.0, result);
    }

    public static void updateCustomer(
        int customerId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String password
    ) {
        try {
            PreparedStatement queryStatement = dbConnection.prepareStatement(
            """
                   UPDATE customers
                   SET
                        first_name = ?,
                        last_name = ?,
                        email = ?,
                        phone_number = ?,
                        password = ?
                   WHERE id = ?
                """
            );

            queryStatement.setString(1, firstName);
            queryStatement.setString(2, lastName);
            queryStatement.setString(3, email);
            queryStatement.setString(4, phoneNumber);
            queryStatement.setString(5, password);
            queryStatement.setInt(6, customerId);

            queryStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in total sum transactions: " + e);
        }
    }
}
