module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens be.ac.umons to javafx.fxml;
    exports be.ac.umons;
}