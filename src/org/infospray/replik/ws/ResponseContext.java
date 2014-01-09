package org.infospray.replik.ws;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ResponseContext")
public class ResponseContext {

	String codeRetour;
	
	String libelleRetour;

	public String getCodeRetour() {
		return codeRetour;
	}

	public void setCodeRetour(String codeRetour) {
		this.codeRetour = codeRetour;
	}

	public String getLibelleRetour() {
		return libelleRetour;
	}

	public void setLibelleRetour(String libelleRetour) {
		this.libelleRetour = libelleRetour;
	}
	
	
	
}
