package com.Gamex.Client_Gamex;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.Gamex.Model.User;
import com.Gamex.Requests.Request_Login;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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
    	usuario.setAdmin(false);
    	 if(usuarioTextField.getText().matches("") || passwordTextField.getText().matches("")) 
    		 return;
    	 
    	mensaje = new Request_Login(usuarioTextField.getText(), passwordTextField.getText());
    	
    	mostrarAlertInfo();
    	if(usuario.isAdmin()==false) {
    		App.setRoot("compra");
    	}else if(usuario.isAdmin()==true) {
    		App.setRoot("CrearJuego");
    	}
    	
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
