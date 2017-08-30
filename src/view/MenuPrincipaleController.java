package view;

import javafx.fxml.FXML;
import main.MainApp;

public class MenuPrincipaleController {
private MainApp mainApp;
public static int a;	
	@FXML
	public void handleClient(){
		mainApp.showMenuClient();
		 a=1;
	}
	
	@FXML
	public void handleRepresentant(){
		mainApp.showMenuRepresentant();
		a=3;
	}
	@FXML
	public void handlePros(){
		mainApp.showMenuProspect();
		a=2;
	}
	 
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
       
	}

	public int getA() {
		return a;
	}
	
}
