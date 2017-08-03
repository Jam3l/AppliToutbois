package view;

import javafx.fxml.FXML;
import main.MainApp;

public class MenuPrincipaleController {
private MainApp mainApp;
	
	public MenuPrincipaleController() {
    }
	@FXML
	public void handleClient(){
		mainApp.showMenuClient();
	}
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
	@FXML
	public void initialize(){
		
	}
}
