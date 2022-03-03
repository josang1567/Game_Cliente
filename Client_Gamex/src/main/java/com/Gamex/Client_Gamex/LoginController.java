package com.Gamex.Client_Gamex;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.Gamex.Model.User;
import com.Gamex.Requests.Request_Login;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements Runnable  {
	
	@FXML
	TextField usuarioTextField;
	@FXML
	TextField passwordTextField;
	
   Request_Login mensaje;
   public static Socket cliente= null;
   @FXML
   protected void initialize() throws UnknownHostException,IOException{
	   cliente= new Socket();
	   Thread miHilo= new Thread(this);
	   miHilo.start();
   }
   
    @FXML
    private void switchToRegister() throws IOException {
    	
    	
    	App.setRoot("register");
       
    }
    @FXML
    private void switchToInicio() throws IOException {
    	User usuario= new User();
    	
    	 if(usuarioTextField.getText().matches("") || passwordTextField.getText().matches("")) {
    		 mostrarAlertCampos();
    		 return;
    	 }
    		 
    	 usuario.setNombre(usuarioTextField.getText());
    	 usuario.setPassword(passwordTextField.getText());
    	 
    	mensaje = new Request_Login(usuarioTextField.getText(), passwordTextField.getText());
    	
    	
    	
	   if(usuario.getNombre().equals("9")&& usuario.getPassword().equals("9")) {
		   usuario.setAdmin(true);
	   }else {
		   usuario.setAdmin(false);
	   }
    	
    	
    	if(usuario.isAdmin()==false) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Información");
			alert.setContentText("Entrando como usuario");
			alert.showAndWait();
    		App.setRoot("compra");
    	}else if(usuario.isAdmin()==true) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Información");
			alert.setContentText("Entrando como admin");
			alert.showAndWait();
    		App.setRoot("CrearJuego");
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
        alert.setContentText("Usuario: "+usuarioTextField.getText()+
        		"\nPasword: "+passwordTextField.getText());
        alert.showAndWait();
    }
	@Override
	public void run() {
		
		
	}
}
