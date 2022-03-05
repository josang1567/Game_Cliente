package com.Gamex.Client_Gamex;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import models.requests.Request;
import models.requests.Request_Login;
import models.responses.Response_Login;

public class LoginController implements Runnable {

	@FXML
	TextField usuarioTextField;
	@FXML
	TextField passwordTextField;

	Request_Login mensaje;
	static Response_Login rl;
	public static Socket cliente = null;

	@FXML
	protected void initialize() throws UnknownHostException, IOException {
		cliente = new Socket("192.168.18.235", 6666);
		Thread miHilo = new Thread(this);
		miHilo.start();
	}

	@Override
	public void run() {
		
	}

	@FXML
	private void switchToRegister() throws IOException {

		App.setRoot("register");

	}

	@FXML
	private void switchToInicio() throws IOException {

		/**
		 * enviar
		 */
		if (cliente != null) {
			try {
				ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
				flujoSalida.writeObject(new Request_Login(usuarioTextField.getText(), passwordTextField.getText()));
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
					System.out.println(rl);
					cambiar();
				}

			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
	private void cambiar() throws IOException {
		if (usuarioTextField.getText().matches("") || passwordTextField.getText().matches("")) {
		mostrarAlertCampos();
		return;
	}

	mensaje = new Request_Login(usuarioTextField.getText(), passwordTextField.getText());

	if (rl.isAccepted() == true) {
		if (rl.getUser().isAdmin() == false) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Información");
			alert.setContentText("Entrando como usuario");
			alert.showAndWait();
			App.setRoot("compra");

		} else if (rl.getUser().isAdmin() == true) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Información");
			alert.setContentText("Entrando como admin");
			alert.showAndWait();
			App.setRoot("CrearJuego");
		}
	}
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
	private void mostrarAlertInfo() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Usuario: " + usuarioTextField.getText() + "\nPasword: " + passwordTextField.getText());
		alert.showAndWait();
	}

}
