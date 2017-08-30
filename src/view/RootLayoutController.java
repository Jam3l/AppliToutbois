package view;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import main.MainApp;
import view.MenuPrincipaleController;

public class RootLayoutController {
	// Reference to the main application
    private MainApp mainApp;
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNouveau() {
    	
        mainApp.getClientData().clear();
        mainApp.setClientFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOuvrir() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
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

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
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

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSauvegarderSous() {
    	
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        if(MenuPrincipaleController.a == 1){
	        // Show save file dialog
	        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
	        if (file != null) {
	            // Make sure it has the correct extension
	            if (!file.getPath().endsWith(".xml")) {
	                file = new File(file.getPath() + ".xml");
	            }
	            mainApp.saveClientDataToFile(file);
            }
        }
        if(MenuPrincipaleController.a == 2){
        	// Show save file dialog
	        File file2 = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
	        if (file2 != null) {
                // Make sure it has the correct extension
                if (!file2.getPath().endsWith(".xml")) {
                    file2 = new File(file2.getPath() + ".xml");
                }
	        }
            mainApp.saveProspectDataToFile(file2);}
        if(MenuPrincipaleController.a == 3){
        	// Show save file dialog
	        File file3 = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
	        if (file3 != null) {
                // Make sure it has the correct extension
                if (!file3.getPath().endsWith(".xml")) {
                    file3 = new File(file3.getPath() + ".xml");
                }
            mainApp.saveRepresentantDataToFile(file3);
            }
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Toutbois");
        alert.setHeaderText("About");
        alert.setContentText("Auteur : Mika&Jamal Cie");

        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleQuitter() {
        System.exit(0);
    }
}
