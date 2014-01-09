package org.infospray.replik.beans;

import java.util.ArrayList;
import java.util.List;


public class User {

	private int	id;
	
	private String pseudo;
	
	private String securityString;
	
	private int classementGeneral;
	
	private int point;
	
	// nombre de reponse correct succesive
	private int correctAnswerChain;
	
	private boolean inGame;
	
	
	private List<UserReplikStats> listUserReplikStats = new ArrayList<UserReplikStats>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getSecurityString() {
		return securityString;
	}

	public void setSecurityString(String securityString) {
		this.securityString = securityString;
	}



	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}



	public int getClassementGeneral() {
		return classementGeneral;
	}

	public void setClassementGeneral(int classementGeneral) {
		this.classementGeneral = classementGeneral;
	}


	public int getCorrectAnswerChain() {
		return correctAnswerChain;
	}

	public void setCorrectAnswerChain(int correctAnswerChain) {
		this.correctAnswerChain = correctAnswerChain;
	}

	public List<UserReplikStats> getListUserReplikStats() {
		return listUserReplikStats;
	}

	public void setListUserReplikStats(List<UserReplikStats> listUserReplikStats) {
		this.listUserReplikStats = listUserReplikStats;
	}
	
	
	
	
}
