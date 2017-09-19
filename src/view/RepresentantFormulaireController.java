package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Representant;
import model.TypeVoie;

public class RepresentantFormulaireController {
	
	@FXML
    private TextField numRepresentantField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField numeroRueField;
    @FXML
    private ComboBox<TypeVoie> voieBox;
    @FXML
    private TextField nomRueField;
    @FXML
    private TextField codePostalField;
    @FXML
    private TextField villeField;
    @FXML
    private TextField paysField;	    
    @FXML
    private TextField emailField;
    @FXML
    private TextField telField;
    @FXML
    private TextField tauxField;
    @FXML
    private TextField salaireField;
 
    private Stage dialogStage;
    private Representant representant;
    private boolean okClicked = false;   
 
    
    @FXML
    private void initialize() {
    	voieBox.getItems().setAll(TypeVoie.values());//on rempli la comboBox avec les valeurs de la classe enum "TypeVoie"
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
        
        numeroRueField.setText(representant.getNumeroRue());
        if (representant.getVoieBox()!=null){        
        	voieBox.setValue(TypeVoie.valueOf(representant.getVoieBox()));
        }else{        
	        voieBox.setValue(TypeVoie.rue);
        }
        nomRueField.setText(representant.getNomRue());
        codePostalField.setText(representant.getCodePostal());
        villeField.setText(representant.getVille());
        paysField.setText(representant.getPays());
        emailField.setText(representant.getEmail());
        telField.setText(representant.getTel());
        
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
        	representant.setNumeroRue(numeroRueField.getText());		           
        	representant.setVoieBox(voieBox.getValue().toString());
        	representant.setNomRue(nomRueField.getText());
        	representant.setCodePostal(codePostalField.getText());
        	representant.setVille(villeField.getText());
        	representant.setPays(paysField.getText());
        	
        	representant.setAdresse(representant.getNumeroRue()+" "+voieBox.getValue().toString()+" "+representant.getNomRue()+" "
        							+representant.getCodePostal()+" "+representant.getVille()+" "+representant.getPays());
        	
        	representant.setEmail(emailField.getText());
        	representant.setTel(telField.getText());        	
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
