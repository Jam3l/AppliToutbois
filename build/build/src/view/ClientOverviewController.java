package view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.MainApp;
import model.Client;

public class ClientOverviewController {		
	@FXML
	private TableView<Client>clientTable; 	
	@FXML
	private TableColumn<Client,String>numClientColumn; 
	@FXML
	private TableColumn<Client,String>enseigneColumn; 
	@FXML
	private TableColumn<Client,String>adresseColumn;
	@FXML
	private Label numClientLabel;
	@FXML
	private Label enseigneLabel;
	@FXML
	private Label adresseLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label telLabel;
	@FXML
	private Label siretLabel;
	@FXML
	private Label numRepLabel;
	@FXML
	private Label numComLabel;
	@FXML
	private TextField EnseigneRField;
	private MainApp mainApp;
	public static boolean presser;
    
    public ClientOverviewController() {
    }
    @FXML
    private void initialize() {
    	// initialise la table view 
        numClientColumn.setCellValueFactory(cellData -> cellData.getValue().numClientProperty());
        enseigneColumn.setCellValueFactory(cellData -> cellData.getValue().enseigneProperty());
        adresseColumn.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());      
        showClientDetails(null);
        // Listener
        clientTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClientDetails(newValue));
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Ajouter des données a liste observables
        clientTable.setItems(mainApp.getClientData());
    }
    //Details du client
    private void showClientDetails(Client client) {
        if (client != null) {
            numClientLabel.setText(client.getNumClient());
            enseigneLabel.setText(client.getEnseigne());
            adresseLabel.setText(client.getAdresse());            
            emailLabel.setText(client.getEmail());
            telLabel.setText(client.getTel());
            siretLabel.setText(client.getSiret());
            numRepLabel.setText(client.getNumRep());
            numComLabel.setText(Integer.toString(client.getNumCom()));
        } else {
            numClientLabel.setText("");
            enseigneLabel.setText("");
            adresseLabel.setText("");
            emailLabel.setText("");
            telLabel.setText("");
            siretLabel.setText("");
            numRepLabel.setText("");
            numComLabel.setText("");
        }
    }
    //Recherche d un client
    @FXML
    private void handleRechercheClient(){
    	String enseigneR = EnseigneRField.getText();
    	for(int i = 0;i < mainApp.getClientData().size();i++)
    		if(mainApp.getClientData().get(i).getEnseigne().toLowerCase().equals(enseigneR.toLowerCase())){
    			showClientDetails(mainApp.getClientData().get(i));
    			i = mainApp.getClientData().size();
    		}
    }
    // Suppression d un client
    @FXML
    private void handleDeleteClient() {
        int selectedIndex = clientTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            clientTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune séléction");
            alert.setHeaderText("Client non sélectionné");
            alert.setContentText("Veuillez séléctionner un client.");
            alert.showAndWait();
        }
    }
    // Création d un client
    @FXML 
    public void handleNewClient() {
        Client tempClient = new Client();
        presser = true;
        boolean okClicked = mainApp.showClientFormulaire(tempClient);
        if (okClicked) {
            mainApp.getClientData().add(tempClient);
        }
    }
    // Modification d un client
    @FXML
    private void handleEditPerson() {
        Client selectedPerson = clientTable.getSelectionModel().getSelectedItem();
        presser = false;
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showClientFormulaire(selectedPerson);
            if (okClicked) {
                showClientDetails(selectedPerson);
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Aucun client sélectionner");
            alert.setContentText("Veuillez sélectionner un client.");
            alert.showAndWait();
        }
    }
    // Retour menu
    @FXML
	public void handleMenu(){
    	File file = mainApp.getClientFilePath();
        if (file != null) {
            mainApp.saveClientDataToFile(file);}
		mainApp.showMenuPrincipale();
	}
    // methode pour savoir si l on a clique sur nouveau ou modifier
	public static boolean isPresser() {
		return presser;
	}
}
