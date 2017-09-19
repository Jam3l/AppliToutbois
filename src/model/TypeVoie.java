package model;
// Enum des types de voies pour la comboBox (voieBox)
public enum TypeVoie {
	rue ("rue"),
	avenue ("avenue"),
	chemin ("chemin"),
	allée ("allée"),
	cour ("cour"),
	impasse ("impasse"),
	square ("square"),
	place ("place"),
	centreCommercial ("Centre Commercial");
	
	 private String name = "";
	   
	  //Constructeur
	  TypeVoie(String name){
	    this.name = name;
	  }
	   
	  public String toString(){
	    return name;
	
	  }
}
