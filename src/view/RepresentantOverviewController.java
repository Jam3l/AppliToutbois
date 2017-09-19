package view;

import java.io.File;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
	private Label adresseRepresentantLabel;
	@FXML
	private Label emailRepresentantLabel;
	@FXML
	private Label telephoneRepresentantLabel;
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
        	adresseRepresentantLabel.setText(representant.getAdresse());
        	emailRepresentantLabel.setText(representant.getEmail());
        	telephoneRepresentantLabel.setText(representant.getTel());
        	tauxRepresentantLabel.setText(representant.getTauxRepresentant());
        	salaireRepresentantLabel.setText(representant.getSalaireRepresentant());
           
        } else {
            // Si aucune ligne n'est sélectionnée, les labels sont vides.
        	numRepresentantLabel.setText("");
        	nomRepresentantLabel.setText("");
        	prenomRepresentantLabel.setText("");
        	adresseRepresentantLabel.setText("");
        	emailRepresentantLabel.setText("");
        	telephoneRepresentantLabel.setText("");
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
    			representantTable.getSelectionModel().select(mainApp.getRepresentantData().get(i));		// Surligne la ligne de l'enseigne trouvé
    		}
    	RepresentantRField.setText("");
    }
    
    // Suppression d'un représentant
    @FXML
    private void handleDeleteRepresentant() {
        int selectedIndex = representantTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	
        	boolean repExistant=false; // Booleen passe true si client ou prospect attaché au représentant selectionné
        	for(int i = 0;i < mainApp.getClientData().size();i++){
        		if(mainApp.getClientData().get(i).getNumRep().equals(mainApp.getRepresentantData().get(selectedIndex).getNumRepresentant())){
        			repExistant=true;
        			Alert alert = new Alert(AlertType.WARNING);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("ATTENTION!");
                    alert.setHeaderText("Ce représentant est attaché au client: "+mainApp.getClientData().get(i).getEnseigne());
                    alert.setContentText("Il est interdit de supprimer ce Représentant");
                    alert.showAndWait();
        			
        		}
        	}
        	for(int i = 0;i < mainApp.getProspectData().size();i++){
        		if(mainApp.getProspectData().get(i).getNumRep().equals(mainApp.getRepresentantData().get(selectedIndex).getNumRepresentant())){
        			repExistant=true;
        			Alert alert = new Alert(AlertType.WARNING);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("ATTENTION!");
                    alert.setHeaderText("Ce représentant est attaché au prospect: "+mainApp.getProspectData().get(i).getEnseigne());
                    alert.setContentText("Il est interdit de supprimer ce Représentant");
                    alert.showAndWait();
        		}
        	}
        	if (repExistant==false){
    			representantTable.getItems().remove(selectedIndex);    // Suppression du Représentant car attaché à aucun client/prospect  	
        		
        	}
        		
        		
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
        	
        }
        	Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Retour au menu principal");
        	alert.setHeaderText("Voulez-vous sauvegarder");        		        	
        	ButtonType buttonTypeOne = new ButtonType("OUI");
        	ButtonType buttonTypeTwo = new ButtonType("NON");
        	
        	alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        	Optional<ButtonType> result = alert.showAndWait();
        	
        	if (result.get() == buttonTypeOne){
        		mainApp.saveRepresentantDataToFile(file3);
        		mainApp.showMenuPrincipale();
        	    
        	} else if (result.get() == buttonTypeTwo) {
        		mainApp.showMenuPrincipale();
        	} 
  	}
    
  	public static boolean isPresser() {
  		return presser;
  	}

}
