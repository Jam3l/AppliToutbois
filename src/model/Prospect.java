package model;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import utilitaires.LocalDateAdapter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prospect extends Personne{
	private final ObjectProperty<LocalDate> dateVisite;

	//Constructeur
	public Prospect(){
		super();
		this.dateVisite = new SimpleObjectProperty<LocalDate>(LocalDate.now());

	}
	
	// Getters et Setters
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getDateVisite() {return dateVisite.get();}
	public void setDateVisite(LocalDate dateVisite) {this.dateVisite.set(dateVisite);}            
	public ObjectProperty<LocalDate> dateVisiteProperty() {return dateVisite;}
	
	
}
