package org.infospray.replik.ws;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "InfosUser")
public class InfosUser {


	private String pseudo;
	
	private int classement;
	
	private int point;


	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public int getClassement() {
		return classement;
	}

	public void setClassement(int classement) {
		this.classement = classement;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
	
}
