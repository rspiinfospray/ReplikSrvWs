package org.infospray.replik.beans;

public class UserReplikStats {

	private int 	idReplik;
	
	private boolean hasCorrectAnswer;
	
	private long 	timeToResponse;
	
	private int 	classementReplik;
	
	private int 	classementReplikTemporaire;
	
	private int		pointReplik;
	
	private int 	pointReplikTemporaire;
	
	private boolean hasAnswer;

	public int getIdReplik() {
		return idReplik;
	}

	public void setIdReplik(int idReplik) {
		this.idReplik = idReplik;
	}

	public boolean isHasCorrectAnswer() {
		return hasCorrectAnswer;
	}

	public void setHasCorrectAnswer(boolean hasCorrectAnswer) {
		this.hasCorrectAnswer = hasCorrectAnswer;
	}

	public long getTimeToResponse() {
		return timeToResponse;
	}

	public void setTimeToResponse(long timeToResponse) {
		this.timeToResponse = timeToResponse;
	}

	public int getClassementReplik() {
		return classementReplik;
	}

	public void setClassementReplik(int classementReplik) {
		this.classementReplik = classementReplik;
	}

	public boolean isHasAnswer() {
		return hasAnswer;
	}

	public void setHasAnswer(boolean hasAnswer) {
		this.hasAnswer = hasAnswer;
	}

	public int getClassementReplikTemporaire() {
		return classementReplikTemporaire;
	}

	public void setClassementReplikTemporaire(int classementReplikTemporaire) {
		this.classementReplikTemporaire = classementReplikTemporaire;
	}

	public int getPointReplik() {
		return pointReplik;
	}

	public void setPointReplik(int pointReplik) {
		this.pointReplik = pointReplik;
	}

	public int getPointReplikTemporaire() {
		return pointReplikTemporaire;
	}

	public void setPointReplikTemporaire(int pointReplikTemporaire) {
		this.pointReplikTemporaire = pointReplikTemporaire;
	}
	
	
	
	
	
	
}
