package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
	
	private final StringProperty numClient;
	private final StringProperty enseigne;
	private final StringProperty adresse;
	private final StringProperty email;
	private final StringProperty tel;
	private final StringProperty siret;
	private final StringProperty numRep; 
	private final IntegerProperty numCom;
	
	

	public Client() {
		this(null,null, null);
	}
	public Client(String numClient, String enseigne, String adresse) {
		this.numClient = new SimpleStringProperty(numClient);
		this.enseigne = new SimpleStringProperty(enseigne);
		this.adresse = new SimpleStringProperty(adresse);
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
	
	
	
	
}
