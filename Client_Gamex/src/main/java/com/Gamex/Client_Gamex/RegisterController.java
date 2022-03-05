package com.Gamex.Client_Gamex;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Game;
import models.User;
import models.requests.Request_Login;
import models.requests.Request_Register;
import models.responses.Response_Login;

public class RegisterController implements Runnable {
	@FXML
	public TextField usuario;
	@FXML
	public TextField contraseña;
	@FXML
	public TextField saldo;
	static Response_Login rl= new Response_Login();
	public  Socket cliente = LoginController.cliente;
	

	public void initialize() {
		contraseña.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					contraseña.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		saldo.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*\\.")) {
					saldo.setText(newValue.replaceAll("[^\\d\\.]", ""));
				}
			}
		});

	}

	@FXML
	private void createUser() throws IOException {
		if (usuario.getText().equals("") || contraseña.getText().equals("") || saldo.getText().equals("")) {
			mostrarAlertCampos();
			return;
		}
		if (Float.valueOf(saldo.getText()) < 0.1f) {
			mostrarAlertCampos2();
			return;
		}
		User usuarionuevo = new User(usuario.getText(), Float.valueOf(saldo.getText()), contraseña.getText());
	
		/**
		 * enviar
		 */
		if (cliente != null) {
			try {
				ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
				flujoSalida.writeObject(new Request_Register(usuario.getText(),contraseña.getText(), Double.valueOf(saldo.getText()) ));
				System.out.println("enviado");
				
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
				rl = (Response_Login) flujoEntrada.readObject();
				
				System.out.println(rl.isAccepted());
				
				if(rl.isAccepted()==false) {
					AlreadyExistsAlert();
					return;
				}else {
					System.out.println(rl);
					RegisterAlert();
					App.setRoot("login");
				}
				
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
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
		alert.setContentText("El saldo introducido debe ser mayor a 0");
		alert.showAndWait();
	}
	@FXML
	private void RegisterAlert() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Usuario Registrado");
		alert.showAndWait();
	}
	@FXML
	private void AlreadyExistsAlert() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("EL usuario ya existe");
		alert.showAndWait();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}