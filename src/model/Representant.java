package model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Representant extends Personne {
	
	private String numRep;	// numéro unique pour chaque représentants
	private final StringProperty numRepresentant;
	private final StringProperty nomRepresentant;
	private final StringProperty prenomRepresentant;
	private final StringProperty tauxRepresentant;
	private final StringProperty salaireRepresentant;
	private static int representantCompteur;
	
	// Constructeur
	public Representant() {
		super();
		
		representantCompteur ++;
		String numRepCompteur = Integer.toString(representantCompteur);
		numRep = "R" + numRepCompteur;
		this.numRepresentant = new SimpleStringProperty(numRep);
		this.nomRepresentant = new SimpleStringProperty();
		this.prenomRepresentant = new SimpleStringProperty();
		this.tauxRepresentant = new SimpleStringProperty();
		this.salaireRepresentant = new SimpleStringProperty();		
	}
	
	// Getters et setters
	public String getNumRepresentant() {return numRepresentant.get();}
	public void setNumRepresentant(String numRepresentant) {this.numRepresentant.set(numRepresentant);}            
	public StringProperty numRepresentantProperty() {return numRepresentant;}
	
	public String getNomRepresentant() {return nomRepresentant.get();}
	public void setNomRepresentant(String nomRepresentant) {this.nomRepresentant.set(nomRepresentant);}
	public StringProperty nomRepresentantProperty() {return nomRepresentant;}
	
	public String getPrenomRepresentant() {return prenomRepresentant.get();}
	public void setPrenomRepresentant(String prenomRepresentant) {this.prenomRepresentant.set(prenomRepresentant);}
	public StringProperty prenomRepresentantProperty() {return prenomRepresentant;}
	
	public String getTauxRepresentant() {return tauxRepresentant.get();}
	public void setTauxRepresentant(String tauxRepresentant) {this.tauxRepresentant.set(tauxRepresentant);}
	public StringProperty tauxRepresentantProperty() {return tauxRepresentant;}
	
	public String getSalaireRepresentant() {return salaireRepresentant.get();}
	public void setSalaireRepresentant(String salaireRepresentant) {this.salaireRepresentant.set(salaireRepresentant);}
	public StringProperty salaireRepresentantProperty() {return salaireRepresentant;}

	public int getRepresentantCompteur() {return representantCompteur;}	
	public void setRepresentantCompteur(int representantCompteur) {Representant.representantCompteur = representantCompteur;}

	@Override
	public String toString() {return this.getNumRepresentant() + " " + this.getNomRepresentant();}

}
