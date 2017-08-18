package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import main.MainApp;
import model.Client;

public class ClientOverviewController {
	@FXML
	private TableView<Client>clientTable; 	//Jamel Test Merge
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
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ClientOverviewController() {
    }
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        numClientColumn.setCellValueFactory(cellData -> cellData.getValue().numClientProperty());
        enseigneColumn.setCellValueFactory(cellData -> cellData.getValue().enseigneProperty());
        adresseColumn.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());      
        showClientDetails(null);
        // Listen for selection changes and show the person details when changed.
        clientTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClientDetails(newValue));
    }
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        clientTable.setItems(mainApp.getClientData());
    }
    private void showClientDetails(Client client) {
        if (client != null) {
            // Fill the labels with info from the person object.
            numClientLabel.setText(client.getNumClient());
            enseigneLabel.setText(client.getEnseigne());
            adresseLabel.setText(client.getAdresse());
            emailLabel.setText(client.getEmail());
            telLabel.setText(client.getTel());
            siretLabel.setText(client.getSiret());
            numRepLabel.setText(client.getNumRep());
            numComLabel.setText(Integer.toString(client.getNumCom()));
        } else {
            // Person is null, remove all the text.
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
    @FXML
    private void handleRechercheClient(){
    	String enseigneR = EnseigneRField.getText();
    	for(int i = 0;i < mainApp.getClientData().size();i++)
    		if(mainApp.getClientData().get(i).getEnseigne().toLowerCase().equals(enseigneR.toLowerCase())){
    			showClientDetails(mainApp.getClientData().get(i));
    			i = mainApp.getClientData().size();
    		}
    }
    @FXML
    private void handleDeleteClient() {
        int selectedIndex = clientTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            clientTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune séléction");
            alert.setHeaderText("Client non sélectionné");
            alert.setContentText("Veuillez séléctionner un client.");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleNewClient() {
        Client tempClient = new Client();
        presser = true;
        boolean okClicked = mainApp.showClientFormulaire(tempClient);
        if (okClicked) {
            mainApp.getClientData().add(tempClient);
        }
    }
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
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
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Aucun client sélectionner");
            alert.setContentText("Veuillez sélectionner un client.");
            alert.showAndWait();
        }
    }
    @FXML
	public void handleMenu(){
		mainApp.showMenuPrincipale();
	}
	public static boolean isPresser() {
		return presser;
	}
}
