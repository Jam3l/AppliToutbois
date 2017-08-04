package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "clients")

public class ClientListeEnregistrement {
	 private List<Client> clients;

	 @XmlElement(name = "client")
	 public List<Client> getClients() {
		 return clients;
	 }
	 public void setClients(List<Client> clients) {
	     this.clients = clients;
	 }
}
	
	

