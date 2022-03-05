package com.Gamex.Client_Gamex;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.User;

public class RegisterController implements Runnable {
	@FXML
	public TextField usuario;
	@FXML
	public TextField contraseña;
	@FXML
	public TextField saldo;

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
		System.out.println(usuarionuevo);
		App.setRoot("compra");
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

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}