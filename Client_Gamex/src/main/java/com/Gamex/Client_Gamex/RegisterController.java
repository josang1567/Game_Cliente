package com.Gamex.Client_Gamex;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController implements Runnable{

	public TextField contraseña;
	public TextField saldo;

		public void initialize() {
			contraseña.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, 
			        String newValue) {
			        if (!newValue.matches("\\d*")) {
			        	contraseña.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			
			saldo.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, 
			        String newValue) {
			        if (!newValue.matches("\\d*")) {
			        	saldo.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
		
		}
		
		 @FXML
		    private void switchToLogin() throws IOException {
		        App.setRoot("login");
		    }

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
}