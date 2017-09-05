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
 
    
    @FXML
    private void initialize() {
    }
    
    // Affiche la fen�tre formulaire
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        //D�compte le numero de client qui a �t� cr�er si l'on ferme la fenetre
        dialogStage.setOnCloseRequest( event -> {if(RepresentantOverviewController.isPresser() == true){
    		representant.setRepresentantCompteur(representant.getRepresentantCompteur()-1);
    	}});
    }
   
    // R�cup�re les donn�es du formulaire pour les inscrire dans la Table Repr�sentant
    public void setRepresentant(Representant representant) {
        this.representant = representant;
        numRepresentantField.setText(representant.getNumRepresentant());
        nomField.setText(representant.getNomRepresentant());
        prenomField.setText(representant.getPrenomRepresentant());
        tauxField.setText(representant.getTauxRepresentant());
        salaireField.setText(representant.getSalaireRepresentant());
 
    }
   
    // Retourne true si l'utilisateur a cliqu� sur OK
    public boolean isOkClicked() {
        return okClicked;
    }
    
    //Bouton OK
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
    //Bouton annuler
    @FXML
    private void handleCancel() {
    	if(RepresentantOverviewController.isPresser() == true){
    		representant.setRepresentantCompteur(representant.getRepresentantCompteur()-1);
    	}
        dialogStage.close();
    }
    
  //V�rifie si les champs sont valides et non vide
    private boolean isInputValid() {
        String errorMessage = "";
        if (numRepresentantField.getText() == null || numRepresentantField.getText().length() == 0) {
            errorMessage += "Num�ro Repr�sentant invalide!\n";
        }
        if (nomField.getText() == null || nomField.getText().length() == 0) {
            errorMessage += "Nom invalide!\n";
        }
        if (prenomField.getText() == null || prenomField.getText().length() == 0) {
            errorMessage += "Pr�nom invalide!\n";
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
