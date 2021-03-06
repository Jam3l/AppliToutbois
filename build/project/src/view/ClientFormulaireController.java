package view;

import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainApp;
import model.Client;
import model.Representant;
import model.TypeVoie;

public class ClientFormulaireController {	
	    @FXML
	    private TextField numClientField;
	    @FXML
	    private TextField enseigneField;
	    @FXML
	    private TextField numeroRue;
	    @FXML
	    private ComboBox<TypeVoie> voieBox;
	    @FXML
	    private TextField nomRue;
	    @FXML
	    private TextField codePostal;
	    @FXML
	    private TextField ville;
	    @FXML
	    private TextField pays;	    
	    @FXML
	    private TextField emailField;
	    @FXML
	    private TextField telField;
	    @FXML
	    private TextField siretField;
	    @FXML
	    private ComboBox<Representant> numRepClientField;
	    @FXML
	    private TextField numComField;
	    private Stage dialogStage;
	    private Client client;
	    private boolean okClicked = false;
		private MainApp mainApp;
	    
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;
	    }
	    // initialise la combobox type voie
	    @FXML
	    private void initialize() {
	    	voieBox.getItems().setAll(TypeVoie.values());//on rempli la comboBox avec les valeurs de la classe enum "TypeVoie"
	    }
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	        //decompte le numero de client qui a �t� cr�er si l'on ferme la fenetre avec la croix
	        dialogStage.setOnCloseRequest( event -> {
	        	// Si on vient de prospect
	        	if(ProspectOverviewController.devientClient == 1){
	        		client.setClientCompteur(client.getClientCompteur()-1);
	        	}
	        	// Si on vient de client
	        	ProspectOverviewController.devientClient = 0;
	        	if(ClientOverviewController.isPresser() == true){
	    		client.setClientCompteur(client.getClientCompteur()-1);
	    	}});
	    }
	    // D�finit le client � �diter dans la bo�te de dialogue
	    public void setClient(Client client) {
	    	// Si on vient de prospect
	    	if(ProspectOverviewController.devientClient == 1){
	    		Client.clientCompteur ++;
	    		this.client = client;
	    		numClientField.setText(client.getNumClient());
		    	enseigneField.setText(ProspectOverviewController.selProspect.getEnseigneProspect()); 
		    	numeroRue.setText(ProspectOverviewController.selProspect.getNumeroRueProspect());
		    	voieBox.setValue(TypeVoie.valueOf(ProspectOverviewController.selProspect.getVoieBoxProspect()));
		    	nomRue.setText(ProspectOverviewController.selProspect.getNomRueProspect());
		        codePostal.setText(ProspectOverviewController.selProspect.getCodePostalProspect());
		        ville.setText(ProspectOverviewController.selProspect.getVilleProspect());
		        pays.setText(ProspectOverviewController.selProspect.getPaysProspect());
		        numRepClientField.setItems(mainApp.getRepresentantData()); 
		        numRepClientField.setValue(ProspectOverviewController.selProspect.getRepCombo());
		        numComField.setText("1");
		    // Si on vient de client    
	    	}else{
	        	this.client = client;
		        numClientField.setText(client.getNumClient());
		        enseigneField.setText(client.getEnseigne());
		        numeroRue.setText(client.getNumeroRue());
		        if (client.getVoieBox()!=null){        
		        	voieBox.setValue(TypeVoie.valueOf(client.getVoieBox()));
		        }else{        
			        voieBox.setValue(TypeVoie.rue);
		        }
		        nomRue.setText(client.getNomRue());
		        codePostal.setText(client.getCodePostal());
		        ville.setText(client.getVille());
		        pays.setText(client.getPays());
		        emailField.setText(client.getEmail());
		        telField.setText(client.getTel());
		        siretField.setText(client.getSiret());
		        numRepClientField.setValue(client.getRepComboC());
		        numRepClientField.setItems(mainApp.getRepresentantData()); 
		        numComField.setText(Integer.toString(client.getNumCom()));   
	    	}
	    }
	    // verifie si l on a cliquer sur ok
	    public boolean isOkClicked() {
	        return okClicked;
	    }
	    //action bouton OK
	    @FXML
	    private void handleOk() {	    	
	        if (isInputValid()) {//Si tous les champs sont valide enregistrement dans les variables de la classe client
	        	client.setNumClient(numClientField.getText());
	            client.setEnseigne(enseigneField.getText());
	            client.setNumeroRue(numeroRue.getText());		           
	            client.setVoieBox(voieBox.getValue().toString());
	            client.setNomRue(nomRue.getText());
	            client.setCodePostal(codePostal.getText());
	            client.setVille(ville.getText());
	            client.setPays(pays.getText());	            
	            client.setAdresse(client.getNumeroRue()+" "+voieBox.getValue().toString()+" "+client.getNomRue()+" "+client.getCodePostal()+" "+client.getVille()+" "+client.getPays());
	            client.setEmail(emailField.getText());
	            client.setTel(telField.getText());
	            client.setSiret(siretField.getText());
	            client.setNumRep(numRepClientField.getValue().getNumRepresentant().toString());
	        	client.setRepComboC(numRepClientField.getValue());
	            client.setNumCom(Integer.parseInt(numComField.getText()));
	            okClicked = true;
	            dialogStage.close();
	        }
	    }
	    //action bouton annuler
	    @FXML
	    private void handleCancel() {
	    	//Decompte du compteur client si on appuie sur annuler
	    	// Si l on vient de prospect
	    	if(ProspectOverviewController.devientClient == 1){
        		client.setClientCompteur(client.getClientCompteur()-1);
        	}
	    	ProspectOverviewController.devientClient = 0;
	    	//si l on vient de client
	    	if(ClientOverviewController.isPresser() == true){
	    		client.setClientCompteur(client.getClientCompteur()-1);
	    	}
	    	//fermeture de la fenetre
	        dialogStage.close();
	    }
	    //teste l'email si correct
	    public boolean isEmailValid(){
	    	if(Pattern.matches("^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+$",emailField.getText())){
	    		return true;
	    	}
	    	else{return false;}
	    }
	    //teste le num�ro de t�l
	    public boolean isTelValid(){
	    	if(Pattern.matches("^([.0-9]+)+$",telField.getText())){
	    		return true;
	    	}
	    	else{return false;}
	    }
	    //teste le code postal
	    public boolean isCPValid(){
	    	if(Pattern.matches("^([0-9]+)+$",codePostal.getText())){
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
	    //Teste le n� de siret
	    public boolean isSiretValid(){
	    	if(Pattern.matches("^([0-9]+)+$",siretField.getText())){
	    		return true;
	    	}
	    	else{return false;}
	    } 
	    //v�rifie si les champs sont valide
	    private boolean isInputValid() {
	        String errorMessage = "";
	        if (enseigneField.getText() == null || enseigneField.getText().length() == 0) {
	            errorMessage += "Enseigne invalide!\n---------------\n";
	        }
	        if (numeroRue.getText() == null || numeroRue.getText().length() == 0) {
	           errorMessage += "Numero de rue invalide!\n---------------\n";
	        }
	        if (voieBox.getValue()==null){
	        	errorMessage += "Type de Voie invalide!\n---------------\n";
	        }
	        if (nomRue.getText() == null || nomRue.getText().length() == 0) {
		           errorMessage += "Nom de rue invalide!\n---------------\n";
		        }
	        if (codePostal.getText() == null || codePostal.getText().length() == 0 || isCPValid() == false) {
		           errorMessage += "Code Postal invalide!\n---------------\n";
		        }
	        if (ville.getText() == null || ville.getText().length() == 0) {
		           errorMessage += "Ville invalide!\n---------------\n";
		        }
	        if (pays.getText() == null || pays.getText().length() == 0) {
		           errorMessage += "Pays invalide!\n---------------\n";
		        }
	        if (emailField.getText() == null || emailField.getText().length() == 0 || isEmailValid() == false) {
	            errorMessage += "Email invalide!\n---------------\n";
	        }
	        if (telField.getText() == null || telField.getText().length() == 0 || isTelValid() == false || nbTelValid() == false) {
	            errorMessage += "T�l�phone invalide!\nVeuillez utiliser ces formats:\n01.23.45.69.78\n0123456789\n---------------\n" ;
	        } 
	        if (siretField.getText() == null || siretField.getText().length() != 14 || isSiretValid() == false) {
	            errorMessage += "Siret invalide!(14 chiffres)\n---------------\n";
	        }
	        if (numRepClientField.getValue()==null) {
	            errorMessage += "Num�ro repr�sentant invalide!\n---------------\n";
	        }
	        if (numComField.getText() == null || numComField.getText().length() == 0) {
	            errorMessage += "Num�ro de commande invalide!\n";
	        }else {
	            try {
	                Integer.parseInt(numComField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "Nombres de commande incorrect!\n";
	            }
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
