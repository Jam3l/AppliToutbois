package view;

import java.io.File;
import java.time.LocalDate;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import main.MainApp;
import model.Client;
import model.Prospect;
import utilitaires.Calendrier;

public class ProspectOverviewController {
	
	@FXML
	private TableView<Prospect>prospectTable; 	
	@FXML
	private TableColumn<Prospect,LocalDate>dateVisiteColumn; 
	@FXML
	private TableColumn<Prospect,String>numRepProspectColumn;
	@FXML
	private TableColumn<Prospect,String>enseignePropectColumn;
	@FXML
	private TableColumn<Prospect,String>AdressePropectColumn;
	@FXML
	private Label dateVisiteLabel;
	@FXML
	private Label enseigneProspectLabel;
	@FXML
	private Label adressePropectLabel;
	@FXML
	private Label numRepProspectLabel;
	private MainApp mainApp;
	public static int devientClient = 0;
	public static Prospect selProspect;
	
	public ProspectOverviewController(){
	}
	
	@FXML
	private void initialize() {
		dateVisiteColumn.setCellValueFactory(cellData -> cellData.getValue().dateVisiteProperty());
		enseignePropectColumn.setCellValueFactory(cellData -> cellData.getValue().enseigneProperty());
	    numRepProspectColumn.setCellValueFactory(cellData -> cellData.getValue().numRepProperty());      
	    AdressePropectColumn.setCellValueFactory(cellData -> cellData.getValue().adresseProperty()); 
	    showProspectDetails(null);
	    // Affiche les détails de la ligne sélectionnée dans la fenêtre de détail.
        prospectTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProspectDetails(newValue));
	}
	
	private void showProspectDetails(Prospect prospect) {
        if (prospect != null) {
            // Remplit les labels avec les informations de l'objet représentants.
            dateVisiteLabel.setText(Calendrier.format(prospect.getDateVisite()));
            enseigneProspectLabel.setText(prospect.getEnseigne());
            adressePropectLabel.setText(prospect.getAdresse());
            numRepProspectLabel.setText(prospect.getNumRep());
        } else {
            // Si aucune ligne n'est sélectionnée, les labels sont vides.
        	dateVisiteLabel.setText("");
            enseigneProspectLabel.setText("");
            adressePropectLabel.setText("");
            numRepProspectLabel.setText("");
        }
    }
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        prospectTable.setItems(mainApp.getProspectData());
	}
	
	// Suppression d'un prospect
	@FXML
    private void handleDeleteProspect() {
        int selectedIndex = prospectTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            prospectTable.getItems().remove(selectedIndex);
        } else {
            // Si aucune ligne sélectionné, message d'erreur.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune séléction");
            alert.setHeaderText("Prospect non sélectionné");
            alert.setContentText("Veuillez séléctionner un prospect.");
            alert.showAndWait();
        }
    }
	
	// Création d'un nouveau prospect
    @FXML
    private void handleNewProspect() {
        Prospect tempProspect = new Prospect();
        boolean okClicked = mainApp.showProspectFormulaire(tempProspect);
        if (okClicked) {
            mainApp.getProspectData().add(tempProspect);
        }
    }
    
    // Bouton Modifier correspondant à la ligne sélectionnée
    @FXML
    private void handleEditProspect() {
        Prospect selectedProspect = prospectTable.getSelectionModel().getSelectedItem();
        if (selectedProspect != null) {
            boolean okClicked = mainApp.showProspectFormulaire(selectedProspect);
            if (okClicked) {
                showProspectDetails(selectedProspect);
            }
        } else {
        	// Si aucune ligne sélectionné, message d'erreur.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Aucun prospect sélectionner");
            alert.setContentText("Veuillez sélectionner un prospect.");
            alert.showAndWait();
        }
    }
	
    // Bouton Menu permettant de revenir sur la page principal
	@FXML
	public void handleMenu(){
		File file2 = mainApp.getProspectFilePath();
        if (file2 != null) {
        	
        }
        	Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Retour au menu principal");
        	alert.setHeaderText("Voulez-vous sauvegarder");        		        	
        	ButtonType buttonTypeOne = new ButtonType("OUI");
        	ButtonType buttonTypeTwo = new ButtonType("NON");
        	
        	alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        	Optional<ButtonType> result = alert.showAndWait();
        	
        	if (result.get() == buttonTypeOne){
        		mainApp.saveProspectDataToFile(file2);
        		mainApp.showMenuPrincipale();
        	    
        	} else if (result.get() == buttonTypeTwo) {
        		mainApp.showMenuPrincipale();
        	} 
	}
	
	// Permet de transformer un Prospect en Client
	@FXML
	public void handleDevientClient(){
		selProspect = prospectTable.getSelectionModel().getSelectedItem();
		if (selProspect != null) {
			devientClient = 1;
			ClientOverviewController.presser = true;
			MenuPrincipaleController.a = 1;
			
			Client tempClient = new Client();
			boolean okClicked = mainApp.showClientFormulaire(tempClient);
			
	        if (okClicked) {
	        	ProspectOverviewController.devientClient = 0;
	            mainApp.getProspectData().remove(selProspect);
	            File file2 = mainApp.getProspectFilePath();
	            if (file2 != null) {
	                mainApp.saveProspectDataToFile(file2);}
	            mainApp.showMenuClient();
	            mainApp.getClientData().add(tempClient);
	            Client.clientCompteur ++;
	            
	        }
		} else {
			// Si aucune ligne sélectionné, message d'erreur.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Aucun prospect sélectionner");
            alert.setContentText("Veuillez sélectionner un prospect.");
            alert.showAndWait();
        }
	}	    
}

