package com.Gamex.Client_Gamex;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Game;
import models.User;
import models.requests.Request_Edit;
import models.responses.Response_Edit;


public class CarritoController {

	@FXML
	private TableView<Game> tablaGames;
	@FXML
	private TableColumn<Game, String> GameColumna;
	@FXML
	private TableColumn<Game, String> GameColumna2;

	@FXML
	private Menu saldo;
	@FXML
	private TextField cantidad;

	public static User user =CompraController.user;
	private ObservableList< Game> juegos=FXCollections.observableArrayList(user.getJuegos());
	public static Socket cliente = LoginController.cliente;

	static Response_Edit re;
	@FXML
	protected void initialize() {
		
		
		saldo.setText("Saldo: " + user.getSaldo());
		configuraTabla();

		tablaGames.setItems(FXCollections.observableArrayList(juegos));

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

	@FXML
	private void AddMoney() throws IOException {

		if (cantidad.getText().equals(""))
			return;
		double total = user.getSaldo() + Integer.parseInt(cantidad.getText());
		
		user.setSaldo(total);
		
		if (cliente != null) {
			try {
				ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
				flujoSalida.writeObject(new Request_Edit(user));
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
				re = (Response_Edit) flujoEntrada.readObject();
			
				System.out.println(re);
				App.setRoot("carrito");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	

		cantidad.setText("");

	}

	@FXML
	private void switchToPago() throws IOException {

		App.setRoot("pago");

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
