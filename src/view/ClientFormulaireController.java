package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Client;

public class ClientFormulaireController {
	
	    @FXML
	    private TextField numClientField;
	    @FXML
	    private TextField enseigneField;
	    @FXML
	    private TextField adresseField;
	    @FXML
	    private TextField emailField;
	    @FXML
	    private TextField telField;
	    @FXML
	    private TextField siretField;
	    @FXML
	    private TextField numRepField;
	    @FXML
	    private TextField numComField;
	    private Stage dialogStage;
	    private Client client;
	    private boolean okClicked = false;

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    }

	    /**
	     * Sets the stage of this dialog.
	     *
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	    /**
	     * Sets the person to be edited in the dialog.
	     *
	     * @param person
	     */
	    public void setClient(Client client) {
	        this.client = client;

	        numClientField.setText(client.getNumClient());
	        enseigneField.setText(client.getEnseigne());
	        adresseField.setText(client.getAdresse());
	        emailField.setText(client.getEmail());
	        telField.setText(client.getTel());
	        siretField.setText(client.getSiret());
	        numRepField.setText(client.getNumRep());
	        numComField.setText(Integer.toString(client.getNumCom()));
	        
	    }

	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     *
	     * @return
	     */
	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    /**
	     * Called when the user clicks ok.
	     */
	    @FXML
	    private void handleOk() {
	        if (isInputValid()) {
	            client.setEnseigne(enseigneField.getText());
	            client.setAdresse(adresseField.getText());
	            client.setEmail(emailField.getText());
	            client.setTel(telField.getText());
	            client.setSiret(siretField.getText());
	            client.setNumRep(numRepField.getText());
	            client.setNumCom(Integer.parseInt(numComField.getText()));
	            
	            okClicked = true;
	            dialogStage.close();
	        }
	    }

	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

	    /**
	     * Validates the user input in the text fields.
	     *
	     * @return true if the input is valid
	     */
	    private boolean isInputValid() {
	        String errorMessage = "";

	        if (numClientField.getText() == null || numClientField.getText().length() == 0) {
	            errorMessage += "Numéro client invalide !\n";
	        }
	        if (enseigneField.getText() == null || enseigneField.getText().length() == 0) {
	            errorMessage += "Enseigne invalide!\n";
	        }
	        if (adresseField.getText() == null || adresseField.getText().length() == 0) {
	            errorMessage += "Adresse invalide!\n";
	        }
	        if (emailField.getText() == null || emailField.getText().length() == 0) {
	            errorMessage += "Email invalide!\n";
	        }
	        if (telField.getText() == null || telField.getText().length() == 0) {
	            errorMessage += "Téléphone invalide!\n";
	        } 
	        if (siretField.getText() == null || siretField.getText().length() == 0) {
	            errorMessage += "Siret invalide!\n";
	        }
	        if (numRepField.getText() == null || numRepField.getText().length() == 0) {
	            errorMessage += "Numéro représentant invalide!\n";
	        }
	        if (numComField.getText() == null || numComField.getText().length() == 0) {
	            errorMessage += "Numéro de commande invalide!\n";
	        }else {
	            // try to parse the postal code into an int.
	            try {
	                Integer.parseInt(numComField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "No valid postal code (must be an integer)!\n";
	            }
	        }
	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Champ invalide");
	            alert.setHeaderText("Veuillez remplir correctement les champs");
	            alert.setContentText(errorMessage);

	            alert.showAndWait();

	            return false;
	        }
	    }
	}
