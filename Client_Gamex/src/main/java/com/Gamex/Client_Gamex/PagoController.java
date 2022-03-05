package com.Gamex.Client_Gamex;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import models.Game;

public class PagoController implements Runnable{
	@FXML
	private TableView<Game> tablaGames;
	@FXML
	private TableColumn<Game, String> GameColumna;
	@FXML
	private TableColumn<Game, String> GameColumna2;
	@FXML
	private Label nombre;
	
	@FXML
	private Label precioVenta;
	@FXML
	private ImageView caratula;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println();
	}
	 @FXML
	    private void switchToInicio() throws IOException {
	    	
	    	mostrarAlert();	
			App.setRoot("compra");
	       
	    }
	 
	 @FXML
	    private void mostrarAlert() {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setHeaderText(null);
	        alert.setTitle("Info");
	        alert.setContentText("Pago realizado");
	        alert.showAndWait();
	    }
}
