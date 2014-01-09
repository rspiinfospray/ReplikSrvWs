package org.infospray.replik.beans;

import java.util.ArrayList;
import java.util.List;

import org.infospray.replik.db.DbReader;
import org.infospray.replik.db.ReplikDao;

public class Context {
	
	
	List<Game> listeGame = new ArrayList<Game>();

	

	public List<Game> getListeGame() {
		return listeGame;
	}

	public void setListeGame(List<Game> listeGame) {
		this.listeGame = listeGame;
	}


	
	

}
