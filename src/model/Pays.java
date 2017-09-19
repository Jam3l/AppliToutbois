package model;

public enum Pays {
	FRANCE ("FRANCE"),
	BELGIQUE ("BELGIQUE"),
	ALLEMAGNE ("ALLEMAGNE"),
	ESPAGNE ("ESPAGNE"),
	PORTUGAL ("PORTUGAL"),
	ITALIE ("ITALIE"),
	ANGLETERRE ("ANGLETERRE"),
	HOLLANDE ("Pays-Bas"),
	LUXEMBOURG ("LUXEMBOURG"),
	SUISSE ("SUISSE"),
	MAROC ("MAROC"),
	ALGERIE ("ALGERIE"),
	TUNISIE ("TUNISIE");      
	
	private String name = "";
	   
	  //Constructeur
	  Pays(String name){
	    this.name = name;
	  }
	   
	  public String toString(){
	    return name;
	
	  }

}
