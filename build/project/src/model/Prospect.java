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
	private final StringProperty numeroRueProspect;
	private final StringProperty voieBoxProspect;
	private final StringProperty nomRueProspect;
	private final StringProperty codePostalProspect;
	private final StringProperty villeProspect;
	private final StringProperty paysProspect;
	private final StringProperty numRepProspect;
	private final ObjectProperty<Representant> repCombo;
	//Constructeur
	public Prospect(){
		this.dateVisite = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		this.enseigneProspect = new SimpleStringProperty();
		this.adresseProspect = new SimpleStringProperty();
		this.numeroRueProspect = new SimpleStringProperty();
		this.voieBoxProspect = new SimpleStringProperty();
		this.nomRueProspect = new SimpleStringProperty();
		this.codePostalProspect = new SimpleStringProperty();
		this.villeProspect = new SimpleStringProperty();
		this.paysProspect = new SimpleStringProperty();
		this.numRepProspect = new SimpleStringProperty();
		this.repCombo = new SimpleObjectProperty<Representant>();
	}
	// Getters et Setters
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
	
	public String getNumeroRueProspect() {return numeroRueProspect.get();}
	public void setNumeroRueProspect(String numeroRueProspect) {this.numeroRueProspect.set(numeroRueProspect);}
	public StringProperty numeroRueProspectProperty() {return numeroRueProspect;}
	
	public String getVoieBoxProspect() {return voieBoxProspect.get();}
	public void setVoieBoxProspect(String voieBoxProspect) {this.voieBoxProspect.set(voieBoxProspect);}
	public StringProperty voieBoxProspectProperty() {return voieBoxProspect;}
	
	public String getNomRueProspect() {return nomRueProspect.get();}
	public void setNomRueProspect(String nomRueProspect) {this.nomRueProspect.set(nomRueProspect);}
	public StringProperty nomRueProspectProperty() {return nomRueProspect;}
	
	public String getCodePostalProspect() {return codePostalProspect.get();}
	public void setCodePostalProspect(String codePostalProspect) {this.codePostalProspect.set(codePostalProspect);}
	public StringProperty codePostalProspectProperty() {return codePostalProspect;}
	
	public String getVilleProspect() {return villeProspect.get();}
	public void setVilleProspect(String villeProspect) {this.villeProspect.set(villeProspect);}
	public StringProperty villeProspectProperty() {return villeProspect;}
	
	public String getPaysProspect() {return paysProspect.get();}
	public void setPaysProspect(String paysProspect) {this.paysProspect.set(paysProspect);}
	public StringProperty paysProspectProperty() {return paysProspect;}
	
	public String getNumRepProspect() {return numRepProspect.get();}
	public void setNumRepProspect(String representant) {this.numRepProspect.set(representant);}            
	public StringProperty numRepProspectProperty() {return numRepProspect;}
	
	public Representant getRepCombo() {return repCombo.get();}
	public void setRepCombo(Representant repCombo) {this.repCombo.set(repCombo);}            
	public ObjectProperty<Representant> repComboProperty() {return repCombo;}
}
