package view;


import main.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.Prospect;
import model.Representant;
import model.TypeVoie;

public class ProspectFormulaireController {
	@FXML
    private DatePicker datePicker;
    @FXML
    private TextField enseigneProspectField;
    @FXML
    private TextField numeroRueProspectField;
    @FXML
    private ComboBox<TypeVoie> voieBoxProspectField;
    @FXML
    private TextField nomRueProspectField;
    @FXML
    private TextField codePostalProspectField;
    @FXML
    private TextField villeProspectField;
    @FXML
    private TextField paysProspectField;	
    @FXML
    private ComboBox<Representant> numRepProspectField;
	@FXML
	private boolean okClicked = false; 
	private Stage dialogStageP;
	private Prospect prospect;
	private MainApp mainApp;
	@FXML
    private void initialize() {
        voieBoxProspectField.getItems().setAll(TypeVoie.values());
    }
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        
    }
    public void setDialogStage(Stage dialogStageP) {  
    	this.dialogStageP = dialogStageP;
    }
    public void setProspect(Prospect prospect) {
    	this.prospect = prospect;
        enseigneProspectField.setText(prospect.getEnseigneProspect());
        datePicker.setValue(prospect.getDateVisite());
        numeroRueProspectField.setText(prospect.getNumeroRueProspect());
        if (prospect.getVoieBoxProspect()!=null){ 
        voieBoxProspectField.setValue(TypeVoie.valueOf(prospect.getVoieBoxProspect()));
        }else{        
	        voieBoxProspectField.setValue(TypeVoie.rue);
        }
        nomRueProspectField.setText(prospect.getNomRueProspect());
        codePostalProspectField.setText(prospect.getCodePostalProspect());
        villeProspectField.setText(prospect.getVilleProspect());
        paysProspectField.setText(prospect.getPaysProspect());
        numRepProspectField.setValue(prospect.getRepCombo());
        numRepProspectField.setItems(mainApp.getRepresentantData()); 
    }
	public boolean isOkClicked() {
        return okClicked;
    }
   //action bouton OK
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	prospect.setDateVisite(datePicker.getValue());
        	prospect.setEnseigneProspect(enseigneProspectField.getText());
        	prospect.setNumeroRueProspect(numeroRueProspectField.getText());		           
        	prospect.setVoieBoxProspect(voieBoxProspectField.getValue().toString());
	        prospect.setNomRueProspect(nomRueProspectField.getText());
	        prospect.setCodePostalProspect(codePostalProspectField.getText());
	        prospect.setVilleProspect(villeProspectField.getText());
	        prospect.setPaysProspect(paysProspectField.getText());	            
	        prospect.setAdresseProspect(prospect.getNumeroRueProspect()+" "+voieBoxProspectField.getValue().toString()+" "+prospect.getNomRueProspect()+" "+prospect.getCodePostalProspect()+" "+prospect.getVilleProspect()+" "+prospect.getPaysProspect());
        	prospect.setNumRepProspect(numRepProspectField.getValue().getNumRepresentant().toString());
        	prospect.setRepCombo(numRepProspectField.getValue());
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
        if (numeroRueProspectField.getText() == null || numeroRueProspectField.getText().length() == 0) {
        	errorMessage += "Numero de rue invalide!\n";
	    }
	    if (voieBoxProspectField.getValue()==null){
	    	errorMessage += "Type de Voie invalide!\n";
	    }
	    if (nomRueProspectField.getText() == null || nomRueProspectField.getText().length() == 0) {
		    errorMessage += "Nom de rue invalide!\n";
		}
	    if (codePostalProspectField.getText() == null || codePostalProspectField.getText().length() == 0) {
	    	errorMessage += "Code Postal invalide!\n";
		}
	    if (villeProspectField.getText() == null || villeProspectField.getText().length() == 0) {
		    errorMessage += "Ville invalide!\n";
		}
	    if (paysProspectField.getText() == null || paysProspectField.getText().length() == 0) {
		    errorMessage += "Pays invalide!\n";
		}
        if (datePicker.getValue() == null || datePicker.getValue().toString().length() == 0) {
            errorMessage += "Date invalide!\n";
        }
        if (numRepProspectField.getValue() == null) {
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
