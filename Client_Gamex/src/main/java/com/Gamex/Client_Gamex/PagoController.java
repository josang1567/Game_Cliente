package com.Gamex.Client_Gamex;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import models.Game;
import models.User;
import models.requests.Request_Buy;
import models.responses.Response_Buy;

public class PagoController implements Runnable {
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

	private User user = CarritoController.user;
	private ObservableList<Game> juegos = FXCollections.observableArrayList(user.getJuegos());
	public static Socket cliente = LoginController.cliente;
	static Response_Buy rb;


	@FXML
	private void mostrarAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Pago realizado");
		alert.showAndWait();
	}
	@FXML
	private void SaldoALert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("No tienes dinero suficiente para pagar todos los juegos");
		alert.showAndWait();
	}

	@FXML
	protected void initialize() throws UnknownHostException, IOException {
	
		
		configuraTabla();

		tablaGames.setItems(FXCollections.observableArrayList(juegos));

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
	private void switchToPago() throws IOException {

		try {
		
			ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
			List<Game> Lista=new ArrayList<Game>(juegos);
			flujoSalida.writeObject(new Request_Buy(Lista,LoginController.rl.getUser()));
			System.out.println(LoginController.rl.getUser());
			System.out.println("enviado pago");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			if (cliente != null) {
				ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
				rb = (Response_Buy) flujoEntrada.readObject();
				
				if(rb.isAccepted()==true) {
					mostrarAlert();
					LoginController.rl.getUser().setJuegos(new ArrayList<Game>());
					LoginController.rl.setUser(rb.getUser());
					
					
					
					App.setRoot("compra");
				}if(rb.isAccepted()==false) {
					SaldoALert();
					return;
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
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
