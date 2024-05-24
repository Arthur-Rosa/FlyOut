package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField usuario;

	@FXML
	private PasswordField senha;

	public Stage primaryStage;

	public void entrar() {
		System.out.println("Tentativa de login...");
		// logica de entrada
		if (usuario.getText().equals("admin") && senha.getText().equals("admin")) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/dashboard.fxml"));
				
				BorderPane root = loader.load();
				
				Scene scene = new Scene(root, 600, 400);
				
				Stage currentStage = (Stage) usuario.getScene().getWindow();
				currentStage.setScene(scene);
				currentStage.setTitle("Dashboard");
				currentStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
		} else {
			mensagemDeErro();
		}
	}

	public void mensagemDeErro() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Sua senha esta errada");
		alert.setContentText("Senha Incorreta!");
		alert.setHeaderText(null);
		alert.showAndWait();
	}

}
