package org.infospray.replik.ws;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Classement")
public class Classement extends ResponseContext {

	
	private InfosUser[] listeUser;
	
	private int totalReplik;
	
	private int currentReplik;
	
	private int	secondParReplik;
	
	private long timeBeforeReplikChange;
	
	private int	nbJoueurs;
	
	private boolean gameReady;
	
	private long	tempsRestantAvantDebutManche;
	
	private int		secondEntreManche;
	

	public int getSecondParReplik() {
		return secondParReplik;
	}

	public void setSecondParReplik(int secondParReplik) {
		this.secondParReplik = secondParReplik;
	}

	public int getTotalReplik() {
		return totalReplik;
	}

	public void setTotalReplik(int totalReplik) {
		this.totalReplik = totalReplik;
	}

	public int getCurrentReplik() {
		return currentReplik;
	}

	public void setCurrentReplik(int currentReplik) {
		this.currentReplik = currentReplik;
	}

	public InfosUser[] getListeUser() {
		return listeUser;
	}

	public void setListeUser(InfosUser[] listeUser) {
		this.listeUser = listeUser;
	}

	public long getTimeBeforeReplikChange() {
		return timeBeforeReplikChange;
	}

	public void setTimeBeforeReplikChange(long timeBeforeReplikChange) {
		this.timeBeforeReplikChange = timeBeforeReplikChange;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
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
