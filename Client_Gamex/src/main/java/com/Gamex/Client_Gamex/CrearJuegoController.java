package com.Gamex.Client_Gamex;

import java.io.File;
import java.io.IOException;

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

public class CrearJuegoController implements Runnable {
	@FXML
	public TextField nombre;
	@FXML
	public TextField precioCompra;
	

	@FXML
	private ImageView portadaimage;
	@FXML
	private TextField urltext;
	@FXML
	private Button verimagen;

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
		if (nombre.getText().matches("") || precioCompra.getText().matches("") 	|| urltext.getText().matches("")) {
			mostrarAlertCampos();
			return;
		}
		
		if(Float.valueOf(precioCompra.getText())<0.1f) {
			mostrarAlertCampos2();
			return;
		}
		Game juegonuevo = new Game(nombre.getText(), Float.valueOf(precioCompra.getText()), urltext.getText());

		System.out.println(juegonuevo);

		nombre.setText("");
		precioCompra.setText("");
		urltext.setText("");
		portadaimage.setImage(null);
	}

	@FXML
	private void cancel() throws IOException {
		App.setRoot("login");
	}

	@FXML
	private void selecImagen() {
		urltext.setText("");
		File file = null;
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Selecionar imagen...");
		try {
			file = filechooser.showOpenDialog(null);
			if (file != null && file.getPath().matches(".+\\.png") || file.getPath().matches(".+\\.jpg")) {
				Image img = new Image("file:\\" + file.getPath());
				portadaimage.setImage(img);
				urltext.setText(file.getPath());
			} else { // si la extension es incorrecta sale esta alerta
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Información");
				alert.setContentText("Formato incorrecto: Debe elegir un tipo de archivo jpg o png.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			// TODO: handle exception;
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
