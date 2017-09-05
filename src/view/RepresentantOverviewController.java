package view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import main.MainApp;

import model.Representant;


public class RepresentantOverviewController {
	
	@FXML
	private TableView<Representant>representantTable; 	
	@FXML
	private TableColumn<Representant,String>numRepresentantColumn;
	@FXML
	private TableColumn<Representant,String>nomRepresentantColumn;
	@FXML
	private TableColumn<Representant,String>prenomRepresentantColumn;
	@FXML
	private TableColumn<Representant,String>tauxRepresentantColumn;
	@FXML
	private TableColumn<Representant,String>salaireRepresentantColumn;
	@FXML
	private Label numRepresentantLabel;
	@FXML
	private Label nomRepresentantLabel;
	@FXML
	private Label prenomRepresentantLabel;
	@FXML
	private Label tauxRepresentantLabel;
	@FXML
	private Label salaireRepresentantLabel;
	
	@FXML
	private TextField RepresentantRField;		// Textfield utilisé pour la barre de recherche
	private MainApp mainApp;
	public static boolean presser;				// Pour différencer de l'action "Nouveau" ou "Modifier"
	
	
    public RepresentantOverviewController() {
    	
    }
 
    @FXML
    private void initialize() {
    	// Chargement des 3 colonnes de la tableView
    	numRepresentantColumn.setCellValueFactory(cellData -> cellData.getValue().numRepresentantProperty());
    	nomRepresentantColumn.setCellValueFactory(cellData -> cellData.getValue().nomRepresentantProperty());
    	prenomRepresentantColumn.setCellValueFactory(cellData -> cellData.getValue().prenomRepresentantProperty());   

     
        showRepresentantDetails(null);
        // Affiche les détails de la ligne sélectionnée dans la fenêtre de détail.
       representantTable.getSelectionModel().selectedItemProperty().addListener(
             (observable, oldValue, newValue) -> showRepresentantDetails(newValue));    	
    }
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        representantTable.setItems(mainApp.getRepresentantData());
    }
    private void showRepresentantDetails(Representant representant) {
        if (representant != null) {
            // Remplit les labels avec les informations de l'objet représentants.
        	numRepresentantLabel.setText(representant.getNumRepresentant());
        	nomRepresentantLabel.setText(representant.getNomRepresentant());
        	prenomRepresentantLabel.setText(representant.getPrenomRepresentant());
        	tauxRepresentantLabel.setText(representant.getTauxRepresentant());
        	salaireRepresentantLabel.setText(representant.getSalaireRepresentant());
           
        } else {
            // Si aucune ligne n'est sélectionnée, les labels sont vides.
        	numRepresentantLabel.setText("");
        	nomRepresentantLabel.setText("");
        	prenomRepresentantLabel.setText("");
        	tauxRepresentantLabel.setText("");
        	salaireRepresentantLabel.setText("");

        }
    }
    
    // Recherche d'un représentant
    @FXML
    private void handleRechercheRepresentant(){
    	String RepresentantR = RepresentantRField.getText();
    	for(int i = 0;i < mainApp.getRepresentantData().size();i++)
    		if(mainApp.getRepresentantData().get(i).getNomRepresentant().toLowerCase().equals(RepresentantR.toLowerCase())){
    			showRepresentantDetails(mainApp.getRepresentantData().get(i));
    			i = mainApp.getRepresentantData().size();
    		}
    }
    
    // Suppression d'un représentant
    @FXML
    private void handleDeleteRepresentant() {
        int selectedIndex = representantTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            representantTable.getItems().remove(selectedIndex);
        } else {
            // Si aucune ligne sélectionné, message d'erreur.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune séléction");
            alert.setHeaderText("Representant non sélectionné");
            alert.setContentText("Veuillez séléctionner un representant.");
            alert.showAndWait();
        }
    }
    
    // Création d'un nouveau représentant
    @FXML
    private void handleNewRepresentant() {
        Representant tempRepresentant = new Representant();
        presser = true;
        boolean okClicked = mainApp.showRepresentantFormulaire(tempRepresentant);
        if (okClicked) {
            mainApp.getRepresentantData().add(tempRepresentant);
        }
    }

    // Bouton Modifier correspondant à la ligne sélectionnée
    @FXML
    private void handleEditPerson() {
        Representant selectedPerson = representantTable.getSelectionModel().getSelectedItem();
        presser = false;
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showRepresentantFormulaire(selectedPerson);
            if (okClicked) {
                showRepresentantDetails(selectedPerson);
            }
        } else {
            // Si aucune ligne sélectionné, message d'erreur.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Aucun client sélectionner");
            alert.setContentText("Veuillez sélectionner un client.");
            alert.showAndWait();
        }
    }  
    
    // Bouton Menu permettant de revenir sur la page principal
    @FXML
  	public void handleMenu(){
    	File file3 = mainApp.getRepresentantFilePath();
        if (file3 != null) {
            mainApp.saveRepresentantDataToFile(file3);}
  		mainApp.showMenuPrincipale();
  	}
  	public static boolean isPresser() {
  		return presser;
  	}

}
