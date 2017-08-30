package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Representant;

public class RepresentantFormulaireController {
	
	@FXML
    private TextField numRepresentantField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField tauxField;
    @FXML
    private TextField salaireField;
 
    private Stage dialogStage;
    private Representant representant;
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
        //decompte le numero de client qui a été créer si l'on ferme la fenetre
        dialogStage.setOnCloseRequest( event -> {if(RepresentantOverviewController.isPresser() == true){
    		representant.setRepresentantCompteur(representant.getRepresentantCompteur()-1);
    	}});
    }
    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setRepresentant(Representant representant) {
        this.representant = representant;
        numRepresentantField.setText(representant.getNumRepresentant());
        nomField.setText(representant.getNomRepresentant());
        prenomField.setText(representant.getPrenomRepresentant());
        tauxField.setText(representant.getTauxRepresentant());
        salaireField.setText(representant.getSalaireRepresentant());
 
    }
    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    //action bouton OK
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	representant.setNumRepresentant(numRepresentantField.getText());
        	representant.setNomRepresentant(nomField.getText());
        	representant.setPrenomRepresentant(prenomField.getText());
        	representant.setTauxRepresentant(tauxField.getText());
        	representant.setSalaireRepresentant(salaireField.getText());
    
            okClicked = true;
            dialogStage.close();
        }
    }
    //action bouton annuler
    @FXML
    private void handleCancel() {
    	if(RepresentantOverviewController.isPresser() == true){
    		representant.setRepresentantCompteur(representant.getRepresentantCompteur()-1);
    	}
        dialogStage.close();
    }
    
  //vérifie si les champs sont valide
    private boolean isInputValid() {
        String errorMessage = "";
        if (numRepresentantField.getText() == null || numRepresentantField.getText().length() == 0) {
            errorMessage += "Numéro Représentant invalide!\n";
        }
        if (nomField.getText() == null || nomField.getText().length() == 0) {
            errorMessage += "Nom invalide!\n";
        }
        if (prenomField.getText() == null || prenomField.getText().length() == 0) {
            errorMessage += "Prénom invalide!\n";
        }
        if (tauxField.getText() == null || tauxField.getText().length() == 0)  {
            errorMessage += "Taux de commission invalide!\n" ;
        } 
        if (salaireField.getText() == null || salaireField.getText().length() == 0) {
            errorMessage += "Salaire fixe Brut invalide!\n";
        } 


        if (errorMessage.length() == 0) {
            return true;
        } else {
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
