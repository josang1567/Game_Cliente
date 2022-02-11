module com.Gamex.Client_Gamex {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.Gamex.Client_Gamex to javafx.fxml;
    exports com.Gamex.Client_Gamex;
}
