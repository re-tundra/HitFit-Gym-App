module com.hitfit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.codec;
    requires com.mailjet.api;
    requires AnimateFX;
    requires annotations;

    opens com.hitfit to javafx.fxml;
    opens com.hitfit.controller to javafx.fxml;
    opens com.hitfit.controller.bmi to javafx.fxml;
    opens com.hitfit.controller.customer to javafx.fxml;
    opens com.hitfit.controller.dashboard to javafx.fxml;
    opens com.hitfit.controller.employees to javafx.fxml;
    opens com.hitfit.controller.equipment to javafx.fxml;
    opens com.hitfit.controller.faq to javafx.fxml;
    opens com.hitfit.controller.initial_screen to javafx.fxml;
    opens com.hitfit.controller.members_only to javafx.fxml;
    opens com.hitfit.controller.queries to javafx.fxml;
    opens com.hitfit.controller.revenue to javafx.fxml;
    opens com.hitfit.controller.transactions to javafx.fxml;
    opens com.hitfit.model_class to javafx.fxml;

    exports com.hitfit;
    exports com.hitfit.controller;
    exports com.hitfit.controller.bmi;
    exports com.hitfit.controller.customer;
    exports com.hitfit.controller.dashboard;
    exports com.hitfit.controller.employees;
    exports com.hitfit.controller.equipment;
    exports com.hitfit.controller.faq;
    exports com.hitfit.controller.initial_screen;
    exports com.hitfit.controller.members_only;
    exports com.hitfit.controller.queries;
    exports com.hitfit.controller.revenue;
    exports com.hitfit.controller.transactions;
    exports com.hitfit.model_class;
}