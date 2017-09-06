package view;

import javafx.fxml.FXML;
import main.MainApp;

public class MenuPrincipaleController {
private MainApp mainApp;
public static int a;	// Pour savoir quel menu a ete selectionne (client,prospect ou representant)
	//Choix client
	@FXML
	public void handleClient(){
		mainApp.showMenuClient();
		 a=1;
	}
	// Choix representant
	@FXML
	public void handleRepresentant(){
		mainApp.showMenuRepresentant();
		a=3;
	}
	// Choix prospect
	@FXML
	public void handlePros(){
		mainApp.showMenuProspect();
		a=2;
	}
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;   
	}
	// Getters
	public int getA() {
		return a;
	}
	
}
