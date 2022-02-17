package com.Gamex.Client_Gamex;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class CrearJuegoController {
public TextField precioCompra;
public TextField precioVenta;

	public void initialize() {
		precioCompra.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	precioCompra.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		precioVenta.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	precioVenta.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	
	}
	
}
