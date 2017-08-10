package view;

import java.util.regex.Pattern;
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
	        //decompte le numero de client qui a été créer si l'on ferme la fenetre
	        dialogStage.setOnCloseRequest( event -> {if(ClientOverviewController.isPresser() == true){
	    		client.setClientCompteur(client.getClientCompteur()-1);
	    	}});
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
	    //action bouton OK
	    @FXML
	    private void handleOk() {
	        if (isInputValid()) {
	        	client.setNumClient(numClientField.getText());
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
	    //action bouton annuler
	    @FXML
	    private void handleCancel() {
	    	if(ClientOverviewController.isPresser() == true){
	    		client.setClientCompteur(client.getClientCompteur()-1);
	    	}
	        dialogStage.close();
	    }
	    //teste l'email si correct
	    public boolean isEmailValid(){
	    	if(Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$",emailField.getText())){
	    		return true;
	    	}
	    	else{return false;}
	    }
	    //teste le numéro de tél
	    public boolean isTelValid(){
	    	if(Pattern.matches("^([.0-9]+)+$",telField.getText())){
	    		return true;
	    	}
	    	else{return false;}
	    }
	    //teste si il y a bien 10 chiffres
	    public boolean nbTelValid(){
	    	int i,j,valid = 0;
	    	char[] nbTel,j3;
		    String j2="0123456789";
	    	nbTel = telField.getText().toCharArray();
	    	j3 = j2.toCharArray();
	    	for(j=0;j<10;j++){
	    		i = 0;
	    		for(i=0; i<telField.getText().length(); i++){
					if(nbTel[i] == j3[j]){
						valid = valid + 1;
					}	
				}
	    	}
			if(valid == 10){
				return true;
			}else{return false;}	
	    }
	    public boolean isSiretValid(){
	    	if(Pattern.matches("^([0-9]+)+$",siretField.getText())){
	    		return true;
	    	}
	    	else{return false;}
	    } 
	    //vérifie si les champs sont valide
	    private boolean isInputValid() {
	        String errorMessage = "";
	        if (enseigneField.getText() == null || enseigneField.getText().length() == 0) {
	            errorMessage += "Enseigne invalide!\n";
	        }
	        if (adresseField.getText() == null || adresseField.getText().length() == 0) {
	            errorMessage += "Adresse invalide!\n";
	        }
	        if (emailField.getText() == null || emailField.getText().length() == 0 || isEmailValid() == false) {
	            errorMessage += "Email invalide!\n";
	        }
	        if (telField.getText() == null || telField.getText().length() == 0 || isTelValid() == false || nbTelValid() == false) {
	            errorMessage += "Téléphone invalide!\n" ;
	        } 
	        if (siretField.getText() == null || siretField.getText().length() != 14 || isSiretValid() == false) {
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
	                errorMessage += "Nombres de commande incorrect!\n";
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
