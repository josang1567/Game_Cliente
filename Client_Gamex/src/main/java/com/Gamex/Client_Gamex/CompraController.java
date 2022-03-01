package com.Gamex.Client_Gamex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.Gamex.Model.Game;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	private Menu saldo;
	@FXML
	private Label precioVenta;
	@FXML
	private ImageView caratula;
	
	
	@FXML
	protected void initialize() {
		List<Game> todas = new ArrayList<Game>();
		muestraInfo(null);
		configuraTabla();
		
		tablaGames.setItems(FXCollections.observableArrayList(todas));
		tablaGames.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			muestraInfo(newValue);
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
		if(g != null) {
			nombre.setText(g.getNombre());
			precioVenta.setText(String.valueOf(g.getPrecioCompra()));
			File f = new File("file:" + g.getCaratula());
			Image portada = new Image(f.getPath());
			caratula.setImage(portada);
		}else {
			nombre.setText("Desconocido");
			precioVenta.setText("Sin datos");
		}
	}
	
	 @FXML
	    private void switchToCarrito() throws IOException {
	    	
	    	
	    	App.setRoot("Carrito");
	       
	    }
}