package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import main.MainApp;
import model.Prospect;

public class ProspectOverviewController {
	
	@FXML
	private TableView<Prospect>prospectTable; 	
	@FXML
	private TableColumn<Prospect,String>dateVisiteColumn; 
	@FXML
	private TableColumn<Prospect,String>numRepProspectColumn;
	@FXML
	private TableColumn<Prospect,String>enseignePropectColumn;
	@FXML
	private Label dateVisiteLabel;
	@FXML
	private Label enseigneProspectLabel;
	@FXML
	private Label adressePropectLabel;
	@FXML
	private Label numRepProspectLabel;
	private MainApp mainApp;
	
	public ProspectOverviewController(){
	}
	@FXML
	private void initialize() {
		dateVisiteColumn.setCellValueFactory(cellData -> cellData.getValue().dateVisiteProperty());
		enseignePropectColumn.setCellValueFactory(cellData -> cellData.getValue().enseigneProspectProperty());
	    numRepProspectColumn.setCellValueFactory(cellData -> cellData.getValue().numRepProspectProperty());      
	        showProspectDetails(null);
	        // Listen for selection changes and show the person details when changed.
	        prospectTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showProspectDetails(newValue));
	}
	private void showProspectDetails(Prospect prospect) {
        if (prospect != null) {
            // Fill the labels with info from the person object.
            dateVisiteLabel.setText(prospect.getDateVisite());
            enseigneProspectLabel.setText(prospect.getEnseigneProspect());
            adressePropectLabel.setText(prospect.getAdresseProspect());
            numRepProspectLabel.setText(prospect.getNumRepProspect());
        } else {
            // Person is null, remove all the text.
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
	@FXML
    private void handleDeleteProspect() {
        int selectedIndex = prospectTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            prospectTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune séléction");
            alert.setHeaderText("Prospect non sélectionné");
            alert.setContentText("Veuillez séléctionner un prospect.");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleNewProspect() {
        Prospect tempProspect = new Prospect();
        boolean okClicked = mainApp.showProspectFormulaire(tempProspect);
        if (okClicked) {
            mainApp.getProspectData().add(tempProspect);
            System.out.println("tempPropect "+tempProspect);
        }
    }
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditProspect() {
        Prospect selectedProspect = prospectTable.getSelectionModel().getSelectedItem();
        if (selectedProspect != null) {
            boolean okClicked = mainApp.showProspectFormulaire(selectedProspect);
            if (okClicked) {
                showProspectDetails(selectedProspect);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Aucun prospect sélectionner");
            alert.setContentText("Veuillez sélectionner un prospect.");
            alert.showAndWait();
        }
    }
	
	@FXML
	public void handleMenu(){
		mainApp.showMenuPrincipale();
	}
}
