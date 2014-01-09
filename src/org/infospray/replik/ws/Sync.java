package org.infospray.replik.ws;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Sync")
public class Sync extends ResponseContext {


	private long	tempsRestantAvantDebutManche;
	
	private boolean gameReady;
	
	
	public long getTempsRestantAvantDebutManche() {
		return tempsRestantAvantDebutManche;
	}

	public void setTempsRestantAvantDebutManche(long tempsRestantAvantDebutManche) {
		this.tempsRestantAvantDebutManche = tempsRestantAvantDebutManche;
	}

	public boolean isGameReady() {
		return gameReady;
	}

	public void setGameReady(boolean gameReady) {
		this.gameReady = gameReady;
	}
	
}
