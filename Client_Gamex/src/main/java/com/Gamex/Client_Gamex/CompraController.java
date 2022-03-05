package com.Gamex.Client_Gamex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Game;
import models.User;

public class CompraController {
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

	@FXML
	private Menu saldo;
	@FXML
	private TextField cantidad;
	private ObservableList< Game> juegos=FXCollections.observableArrayList(LoginController.rl.getShop().getGames());
	public static User user = LoginController.rl.getUser();

	@FXML
	protected void initialize() {
		
		muestraInfo(null);
		configuraTabla();
		
		saldo.setText("Saldo: " + user.getSaldo());
		tablaGames.setItems(FXCollections.observableArrayList(juegos));
		tablaGames.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			muestraInfo(newValue);
		});
		cantidad.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					cantidad.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

	private void configuraTabla() {

		GameColumna.setCellValueFactory(cadaGame -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(cadaGame.getValue().getNombre());
			return v;
		});
		GameColumna2.setCellValueFactory(cadaGame -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(String.valueOf(cadaGame.getValue().getPrecioCompra()));
			return v;
		});

	}

	private void muestraInfo(Game g) {
		if (g != null) {
			nombre.setText(g.getNombre());
			precioVenta.setText(String.valueOf(g.getPrecioCompra()));
			File f = new File("file:" + g.getCaratula());
			Image portada = new Image(f.getPath());
			caratula.setImage(portada);
		} else {
			nombre.setText("Desconocido");
			precioVenta.setText("Sin datos");
		}
	}

	@FXML
	private void switchToCarrito() throws IOException {

		App.setRoot("Carrito");

	}

	@FXML
	private void AddMoney() throws IOException {
		if (cantidad.getText().equals(""))
			return;

		double total = user.getSaldo() + Integer.parseInt(cantidad.getText());
		saldo.setText("Saldo:" + total);
		user.setSaldo(total);

		cantidad.setText("");

	}

	@FXML
	private void addToCart() throws IOException{
		user.getJuegos().add(tablaGames.getSelectionModel().getSelectedItem());
		
	}
	
	
	@FXML
	private void logOut() throws IOException {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("¿Estas seguro que quieres salir de la sesion?");
		alert.setContentText("Confirme su respuesta.");

		ButtonType yesButton = new ButtonType("Yes");
		ButtonType noButton = new ButtonType("No");

		alert.getButtonTypes().setAll(yesButton, noButton);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == yesButton) {

			App.setRoot("Login");
		} else if (result.get() == noButton) {
			return;
		}

	}
}