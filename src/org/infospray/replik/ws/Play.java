package org.infospray.replik.ws;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Play")
public class Play extends ResponseContext {
	

	private long	tempsRestantPourReplik;
	
	private long	tempsRestantAvantDebutManche;
	
	private int 	currentReplikId;
	
	private int		secondParReplik;
	
	private int		secondEntreManche;
	
	private	int		nbTotalReplik;
	
	private int 	classementGeneral;
	
	private int		nbJoueurs;
	
	private int		points;
	
	private boolean gameReady;
	
	private Replik[] replik;


	public Replik[] getReplik() {
		return replik;
	}

	public void setReplik(Replik[] replik) {
		this.replik = replik;
	}

	public long getTempsRestantPourReplik() {
		return tempsRestantPourReplik;
	}

	public void setTempsRestantPourReplik(long tempsRestantPourReplik) {
		this.tempsRestantPourReplik = tempsRestantPourReplik;
	}

	public int getCurrentReplikId() {
		return currentReplikId;
	}

	public void setCurrentReplikId(int currentReplikId) {
		this.currentReplikId = currentReplikId;
	}

	public int getSecondParReplik() {
		return secondParReplik;
	}

	public void setSecondParReplik(int secondParReplik) {
		this.secondParReplik = secondParReplik;
	}

	public int getNbTotalReplik() {
		return nbTotalReplik;
	}

	public void setNbTotalReplik(int nbTotalReplik) {
		this.nbTotalReplik = nbTotalReplik;
	}

	public int getClassementGeneral() {
		return classementGeneral;
	}

	public void setClassementGeneral(int classementGeneral) {
		this.classementGeneral = classementGeneral;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isGameReady() {
		return gameReady;
	}

	public void setGameReady(boolean gameReady) {
		this.gameReady = gameReady;
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

	
	

		
	
	

}
