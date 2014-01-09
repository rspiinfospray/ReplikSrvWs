package org.infospray.replik.ws;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Answer")
public class Answer extends ResponseContext {
		
	private int 	classementReplik;
	
	private int 	classementGeneral;
	
	private int 	points;
	
	private int 	pointsReplik;
	
	private int		pointsBonusFiveChain;
	
	private int		pointsBonusTenChain;
	
	private int		pointsBonusFiveteenChain;

	private int		nbJoueurs;
	
	private long	tempsRestantAvantDebutManche;
	
	private int		secondEntreManche;
	
	private boolean gameReady;

	public int getClassementReplik() {
		return classementReplik;
	}

	public void setClassementReplik(int classementReplik) {
		this.classementReplik = classementReplik;
	}

	public int getClassementGeneral() {
		return classementGeneral;
	}

	public void setClassementGeneral(int classementGeneral) {
		this.classementGeneral = classementGeneral;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPointsReplik() {
		return pointsReplik;
	}

	public void setPointsReplik(int pointsReplik) {
		this.pointsReplik = pointsReplik;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public long getTempsRestantAvantDebutManche() {
		return tempsRestantAvantDebutManche;
	}

	public void setTempsRestantAvantDebutManche(long tempsRestantAvantDebutManche) {
		this.tempsRestantAvantDebutManche = tempsRestantAvantDebutManche;
	}

	public int getSecondEntreManche() {
		return secondEntreManche;
	}

	public void setSecondEntreManche(int secondEntreManche) {
		this.secondEntreManche = secondEntreManche;
	}

	public boolean isGameReady() {
		return gameReady;
	}

	public void setGameReady(boolean gameReady) {
		this.gameReady = gameReady;
	}

	public int getPointsBonusFiveChain() {
		return pointsBonusFiveChain;
	}

	public void setPointsBonusFiveChain(int pointsBonusFiveChain) {
		this.pointsBonusFiveChain = pointsBonusFiveChain;
	}

	public int getPointsBonusTenChain() {
		return pointsBonusTenChain;
	}

	public void setPointsBonusTenChain(int pointsBonusTenChain) {
		this.pointsBonusTenChain = pointsBonusTenChain;
	}

	public int getPointsBonusFiveteenChain() {
		return pointsBonusFiveteenChain;
	}

	public void setPointsBonusFiveteenChain(int pointsBonusFiveteenChain) {
		this.pointsBonusFiveteenChain = pointsBonusFiveteenChain;
	}

	
	
	
}
