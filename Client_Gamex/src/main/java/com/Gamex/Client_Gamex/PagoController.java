package com.Gamex.Client_Gamex;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class PagoController implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println();
	}
	 @FXML
	    private void switchToInicio() throws IOException {
	    	
	    	mostrarPagoAler();
			

			App.setRoot("compra");
	       
	    }
	 
	 @FXML
	    private void mostrarPagoAler() {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setHeaderText(null);
	        alert.setTitle("Info");
	        alert.setContentText("Pago realizado");
	        alert.showAndWait();
	    }
}
