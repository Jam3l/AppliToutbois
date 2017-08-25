package view;

import javafx.fxml.FXML;
import main.MainApp;

public class MenuPrincipaleController {
private MainApp mainApp;
	
	@FXML
	public void handleClient(){
		mainApp.showMenuClient();
	}
	
	@FXML
	public void handleRepresentant(){
		mainApp.showMenuRepresentant();
	}
	/*
	@FXML
	public void handlePros(){
		mainApp.showMenuPros();
	}
	 */
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
}
