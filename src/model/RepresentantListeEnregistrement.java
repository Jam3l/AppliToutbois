package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "representants")

public class RepresentantListeEnregistrement {
	 private List<Representant> representants;

	 @XmlElement(name = "representant")
	 public List<Representant> getRepresentants() {
		 return representants;
	 }
	 public void setRepresentants(List<Representant> representants) {
	     this.representants = representants;
	 }
}
	


