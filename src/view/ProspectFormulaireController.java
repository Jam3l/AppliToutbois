package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Prospect;

public class ProspectFormulaireController {
	@FXML
    private TextField dateVisiteField;
    @FXML
    private TextField enseigneProspectField;
    @FXML
    private TextField adresseProspectField;
    @FXML
    private TextField numRepProspectField;
	@FXML
	private boolean okClicked = false; 
	private Stage dialogStageP;
	private Prospect prospect;
	
	@FXML
    private void initialize() {
    }
    public void setDialogStage(Stage dialogStageP) {  
    	this.dialogStageP = dialogStageP;
    }
    public void setProspect(Prospect prospect) {
    	this.prospect = prospect;
        enseigneProspectField.setText(prospect.getEnseigneProspect());
        dateVisiteField.setText(prospect.getDateVisite());
        adresseProspectField.setText(prospect.getAdresseProspect());
        numRepProspectField.setText(prospect.getNumRepProspect()); 
    }
    public boolean isOkClicked() {
        return okClicked;
    }
   //action bouton OK
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	prospect.setDateVisite(dateVisiteField.getText());
        	prospect.setEnseigneProspect(enseigneProspectField.getText());
        	prospect.setAdresseProspect(adresseProspectField.getText());
        	prospect.setNumRepProspect(numRepProspectField.getText());
            okClicked = true;
            dialogStageP.close();
        }
    }
   //action bouton annuler
    @FXML
    private void handleCancel() {
        dialogStageP.close();
    }
    //vérifie si les champs sont valide
    private boolean isInputValid() {
        String errorMessage = "";
        if (enseigneProspectField.getText() == null || enseigneProspectField.getText().length() == 0) {
            errorMessage += "Enseigne invalide!\n";
        }
        if (adresseProspectField.getText() == null || adresseProspectField.getText().length() == 0) {
            errorMessage += "Adresse invalide!\n";
        }
        if (dateVisiteField.getText() == null || dateVisiteField.getText().length() == 0) {
            errorMessage += "Date invalide!\n";
        }
        if (numRepProspectField.getText() == null || numRepProspectField.getText().length() == 0) {
            errorMessage += "Numéro représentant invalide!\n" ;
        } 
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStageP);
            alert.setTitle("Champ invalide");
            alert.setHeaderText("Veuillez remplir correctement les champs");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    
    
    
    
    
    
}
