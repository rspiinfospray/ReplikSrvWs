package org.infospray.replik.ws;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Proposition")
public class Proposition {
	
	int idPropostion;
	
	String lblProposition;

	public int getIdPropostion() {
		return idPropostion;
	}

	public void setIdPropostion(int idPropostion) {
		this.idPropostion = idPropostion;
	}

	public String getLblProposition() {
		return lblProposition;
	}

	public void setLblProposition(String lblProposition) {
		this.lblProposition = lblProposition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lblProposition == null) ? 0 : lblProposition.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proposition other = (Proposition) obj;
		if (lblProposition == null) {
			if (other.lblProposition != null)
				return false;
		} else if (!lblProposition.equals(other.lblProposition))
			return false;
		return true;
	}


	
	
	
	

}
