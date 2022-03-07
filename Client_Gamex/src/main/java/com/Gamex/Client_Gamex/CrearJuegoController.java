package com.Gamex.Client_Gamex;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import models.Game;
import models.requests.Request_CreateGame;
import models.requests.Request_Login;
import models.responses.Response_CreateGame;
import models.responses.Response_Login;

public class CrearJuegoController implements Runnable {
	@FXML
	public TextField nombre;
	@FXML
	public TextField precioCompra;
	
	public static Socket cliente = LoginController.cliente;
	static Response_CreateGame rc;


	public void initialize() {
		precioCompra.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*\\.")) {
					precioCompra.setText(newValue.replaceAll("[^\\d\\.]", ""));
				}
			}
		});

		

	}

	@FXML
	private void addGame() throws IOException {
		if (nombre.getText().matches("") || precioCompra.getText().matches("") ) {
			mostrarAlertCampos();
			return;
		}
		
		if(Float.valueOf(precioCompra.getText())<0.1f) {
			mostrarAlertCampos2();
			return;
		}
		
		
		Game juegonuevo = new Game(nombre.getText(), Float.valueOf(precioCompra.getText()));

		
		/**
		 * enviar
		 */
		if (cliente != null) {
			try {
				ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
				flujoSalida.writeObject(new Request_CreateGame(juegonuevo));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		/**
		 * esperar
		 */

		try {

			if (cliente != null) {
				ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
				rc = (Response_CreateGame) flujoEntrada.readObject();
				
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	

		nombre.setText("");
		precioCompra.setText("");
		
	}

	@FXML
	private void cancel() throws IOException {
		App.setRoot("login");
	}

	

	@FXML
	private void mostrarAlertCampos() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Se deben rellenar todos los campos");
		alert.showAndWait();
	}
	@FXML
	private void mostrarAlertCampos2() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("El precio de compra introducido debe ser mayor a 0");
		alert.showAndWait();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
