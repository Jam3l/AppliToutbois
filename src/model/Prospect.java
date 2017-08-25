package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prospect {
	private final StringProperty dateVisite;
	private final StringProperty enseigneProspect;
	private final StringProperty adresseProspect;
	private final StringProperty numRepProspect;
	
	public Prospect(){
		this.dateVisite = new SimpleStringProperty();
		this.enseigneProspect = new SimpleStringProperty();
		this.adresseProspect = new SimpleStringProperty();
		this.numRepProspect = new SimpleStringProperty();
	}
	
	public String getDateVisite() {return dateVisite.get();}
	public void setDateVisite(String dateVisite) {this.dateVisite.set(dateVisite);}            
	public StringProperty dateVisiteProperty() {return dateVisite;}
	
	public String getEnseigneProspect() {return enseigneProspect.get();}
	public void setEnseigneProspect(String enseigneProspect) {this.enseigneProspect.set(enseigneProspect);}            
	public StringProperty enseigneProspectProperty() {return enseigneProspect;}
	
	public String getAdresseProspect() {return adresseProspect.get();}
	public void setAdresseProspect(String adresseProspect) {this.adresseProspect.set(adresseProspect);}            
	public StringProperty adresseProspectProperty() {return adresseProspect;}
	
	public String getNumRepProspect() {return numRepProspect.get();}
	public void setNumRepProspect(String numRepProspect) {this.numRepProspect.set(numRepProspect);}            
	public StringProperty numRepProspectProperty() {return numRepProspect;}
	
}
