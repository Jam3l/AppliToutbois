package model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prospects")
public class ProspectListeEnregistrement {
	private List<Prospect> prospects;

	 @XmlElement(name = "prospect")
	 public List<Prospect> getProspects() {
		 return prospects;
	 }
	 public void setProspects(List<Prospect> prospects) {
	     this.prospects = prospects;
	 }
}
