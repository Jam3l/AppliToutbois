package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
	
	private String numCli;
	private final StringProperty numClient;
	private final StringProperty enseigne;
	private final StringProperty adresse;
	private final StringProperty numeroRue;
	private final StringProperty voieBox;
	private final StringProperty nomRue;
	private final StringProperty codePostal;
	private final StringProperty ville;
	private final StringProperty pays;
	private final StringProperty email;
	private final StringProperty tel;
	private final StringProperty siret;
	private final StringProperty numRep; 
	private final IntegerProperty numCom;
	private static int clientCompteur;
	
	public Client() {
		clientCompteur ++;
		String numCompteur = Integer.toString(clientCompteur);
		numCli = "C" + numCompteur;
		this.numClient = new SimpleStringProperty(numCli);
		this.enseigne = new SimpleStringProperty();
		this.adresse = new SimpleStringProperty();
		this.numeroRue = new SimpleStringProperty();
		this.voieBox = new SimpleStringProperty();
		this.nomRue = new SimpleStringProperty();
		this.codePostal = new SimpleStringProperty();
		this.ville = new SimpleStringProperty();
		this.pays = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.tel = new SimpleStringProperty();
		this.siret = new SimpleStringProperty();
		this.numRep = new SimpleStringProperty();
		this.numCom = new SimpleIntegerProperty();
	}
	
	public String getNumClient() {return numClient.get();}
	public void setNumClient(String numClient) {this.numClient.set(numClient);}            
	public StringProperty numClientProperty() {return numClient;}
	
	public String getEnseigne() {return enseigne.get();}
	public void setEnseigne(String enseigne) {this.enseigne.set(enseigne);}
	public StringProperty enseigneProperty() {return enseigne;}
	
	public String getAdresse() {return adresse.get();}
	public void setAdresse(String adresse) {this.adresse.set(adresse);}
	public StringProperty adresseProperty() {return adresse;}
	
	public String getNumeroRue() {return numeroRue.get();}
	public void setNumeroRue(String numeroRue) {this.numeroRue.set(numeroRue);}
	public StringProperty numeroRueProperty() {return numeroRue;}
	
	public String getVoieBox() {return voieBox.get();}
	public void setVoieBox(String voieBox) {this.voieBox.set(voieBox);}
	public StringProperty voieBoxProperty() {return voieBox;}
	
	public String getNomRue() {return nomRue.get();}
	public void setNomRue(String nomRue) {this.nomRue.set(nomRue);}
	public StringProperty nomRueProperty() {return nomRue;}
	
	public String getCodePostal() {return codePostal.get();}
	public void setCodePostal(String codePostal) {this.codePostal.set(codePostal);}
	public StringProperty codePostalProperty() {return codePostal;}
	
	public String getVille() {return ville.get();}
	public void setVille(String ville) {this.ville.set(ville);}
	public StringProperty villeProperty() {return ville;}
	
	public String getPays() {return pays.get();}
	public void setPays(String pays) {this.pays.set(pays);}
	public StringProperty paysProperty() {return pays;}
	
	public String getEmail() {return email.get();}
	public void setEmail(String email) {this.email.set(email);}
	public StringProperty emailProperty() {return email;}
	
	public String getTel() {return tel.get();}
	public void setTel(String tel) {this.tel.set(tel);}
	public StringProperty telPrroperty() {return tel;}
	
	public String getSiret() {return siret.get();}
	public void setSiret(String siret) {this.siret.set(siret);}
	public StringProperty siretProperty() {return siret;}
	
	public String getNumRep() {return numRep.get();}
	public void setNumRep(String numRep) {this.numRep.set(numRep);}
	public StringProperty numRepProperty() {return numRep;}
	
	public int getNumCom() {return numCom.get();}
	public void setNumCom(int numCom) {this.numCom.set(numCom);}
	public IntegerProperty numComProperty() {return numCom;}

	public int getClientCompteur() {
		return clientCompteur;
	}

	public void setClientCompteur(int clientCompteur) {
		Client.clientCompteur = clientCompteur;
	}

	
	
	
	
	
}
