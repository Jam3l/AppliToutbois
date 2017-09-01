package model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import utilitaires.LocalDateAdapter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prospect {
	private final ObjectProperty<LocalDate> dateVisite;
	private final StringProperty enseigneProspect;
	private final StringProperty adresseProspect;
	private final StringProperty numRepProspect;
	
	public Prospect(){
		this.dateVisite = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		this.enseigneProspect = new SimpleStringProperty();
		this.adresseProspect = new SimpleStringProperty();
		this.numRepProspect = new SimpleStringProperty();
	}
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getDateVisite() {return dateVisite.get();}
	public void setDateVisite(LocalDate dateVisite) {this.dateVisite.set(dateVisite);}            
	public ObjectProperty<LocalDate> dateVisiteProperty() {return dateVisite;}
	
	public String getEnseigneProspect() {return enseigneProspect.get();}
	public void setEnseigneProspect(String enseigneProspect) {this.enseigneProspect.set(enseigneProspect);}            
	public StringProperty enseigneProspectProperty() {return enseigneProspect;}
	
	public String getAdresseProspect() {return adresseProspect.get();}
	public void setAdresseProspect(String adresseProspect) {this.adresseProspect.set(adresseProspect);}            
	public StringProperty adresseProspectProperty() {return adresseProspect;}
	
	public String getNumRepProspect() {return numRepProspect.get();}
	public void setNumRepProspect(String representant) {this.numRepProspect.set(representant);}            
	public StringProperty numRepProspectProperty() {return numRepProspect;}
	
}
