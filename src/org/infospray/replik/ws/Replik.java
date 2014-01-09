package org.infospray.replik.ws;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Replik")
public class Replik {

	
	private int 	replikId;
	
	private String 	replikLbl;
	
	private int 	replikIdInList;
	
	private Proposition[] propositions;


	public Proposition[] getPropositions() {
		return propositions;
	}

	public void setPropositions(Proposition[] propositions) {
		this.propositions = propositions;
	}

	public int getReplikId() {
		return replikId;
	}

	public void setReplikId(int replikId) {
		this.replikId = replikId;
	}

	public int getReplikIdInList() {
		return replikIdInList;
	}

	public void setReplikIdInList(int replikIdInList) {
		this.replikIdInList = replikIdInList;
	}

	public String getReplikLbl() {
		return replikLbl;
	}

	public void setReplikLbl(String replikLbl) {
		this.replikLbl = replikLbl;
	}
	
	
	
}
