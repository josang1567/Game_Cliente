package com.Gamex.Client_Gamex;

import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {

   
    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("register");
    }
    @FXML
    private void switchToCompra() throws IOException {
        App.setRoot("compra");
    }
}
