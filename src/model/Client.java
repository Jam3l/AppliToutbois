package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client extends Personne{
	
	private String numCli;
	private final StringProperty numClient;	 
	private final IntegerProperty numCom;	
	public static int clientCompteur;
	
	// Constructeur client
	public Client() {
		super();
		
		clientCompteur ++;// Compteur pour l'incrementation du numero de client
		String numCompteur = Integer.toString(clientCompteur);// parse de int en string
		numCli = "C" + numCompteur; // concatenation du numero avec une lettre
		this.numClient = new SimpleStringProperty(numCli);
		this.numCom = new SimpleIntegerProperty();
		
	}
	// Getters et Setters	
	public String getNumClient() {return numClient.get();}
	public void setNumClient(String numClient) {this.numClient.set(numClient);}            
	public StringProperty numClientProperty() {return numClient;}
	
	
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
