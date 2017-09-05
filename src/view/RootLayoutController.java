package view;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import main.MainApp;
import view.MenuPrincipaleController;

public class RootLayoutController {
	
	// Fait référence au MainApp
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    
     
    // Permet de créer une nouvelle fenêtre vierge.
    @FXML
    private void handleNouveau() {
    	
        mainApp.getClientData().clear();
        mainApp.setClientFilePath(null);
    }


    // Ouvrir un nouveau fichier.
    @FXML
    private void handleOuvrir() {
        FileChooser fileChooser = new FileChooser();

        // Définit l'extension de fichier
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Affiche la boîte de dialogue d'enregistrement de fichiers
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
        	if(MenuPrincipaleController.a == 1){
        		mainApp.loadClientDataFromFile(file);}
        	if(MenuPrincipaleController.a == 2){
                mainApp.loadProspectDataFromFile(file);}
        	if(MenuPrincipaleController.a == 3){
                mainApp.loadRepresentantDataFromFile(file);}
        }
        
    }


    // Sauvegarder le fichier en cours.
    // Si aucun fichier est ouvert, la méthode SauvegarderSous est lancée.
    @FXML
    private void handleSauvegarder() {
    	if(MenuPrincipaleController.a == 1){
	    	File file = mainApp.getClientFilePath();
	        if (file != null) {
	            mainApp.saveClientDataToFile(file);
	        } else {
	            handleSauvegarderSous();
	        }
    	}
    	if(MenuPrincipaleController.a == 2){
        	File file2 = mainApp.getProspectFilePath();
            if (file2 != null) {
                mainApp.saveProspectDataToFile(file2);
            } else {
                handleSauvegarderSous();
            }
        }
    	if(MenuPrincipaleController.a == 3){
        	File file3 = mainApp.getRepresentantFilePath();
            if (file3 != null) {
                mainApp.saveRepresentantDataToFile(file3);
            } else {
                handleSauvegarderSous();
            }
        }
    	
    }


    // Sauvegarder les données sous un nouveau fichier.
    @FXML
    private void handleSauvegarderSous() {
    	
        FileChooser fileChooser = new FileChooser();

        // Définit l'extension de fichier
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        if(MenuPrincipaleController.a == 1){
	        // Affiche la boîte de dialogue d'enregistrement de fichiers
	        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
	        if (file != null) {
	            // Vérification de la bonne extension .xml
	            if (!file.getPath().endsWith(".xml")) {
	                file = new File(file.getPath() + ".xml");
	            }
	            mainApp.saveClientDataToFile(file);
            }
        }
        if(MenuPrincipaleController.a == 2){
        	// Définit l'extension de fichier
	        File file2 = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
	        if (file2 != null) {
                // Vérification de la bonne extension .xml
                if (!file2.getPath().endsWith(".xml")) {
                    file2 = new File(file2.getPath() + ".xml");
                }
	        }
            mainApp.saveProspectDataToFile(file2);}
        if(MenuPrincipaleController.a == 3){
        	//  Définit l'extension de fichier
	        File file3 = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
	        if (file3 != null) {
                // Vérification de la bonne extension .xml
                if (!file3.getPath().endsWith(".xml")) {
                    file3 = new File(file3.getPath() + ".xml");
                }
            mainApp.saveRepresentantDataToFile(file3);
            }
        }
    }


     // Ouvre la boite de dialogue "Help/About".
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Toutbois");
        alert.setHeaderText("About");
        alert.setContentText("Auteur : Mika&Jamal Cie");

        alert.showAndWait();
    }

  
    // Quitte l'application.
    @FXML
    private void handleQuitter() {
        System.exit(0);
    }
}
