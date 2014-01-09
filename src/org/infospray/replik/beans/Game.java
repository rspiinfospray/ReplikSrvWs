package org.infospray.replik.beans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.infospray.replik.db.Credentials;
import org.infospray.replik.db.ReplikDao;
import org.infospray.replik.utils.Utils;


public class Game extends Thread {

	private Set<User> listeUser = new HashSet<User>();
	
	private int currentIdUser;
	
	private int currentReplik;
	
	private int maxReplik;
	
	private int secondParReplik;
	
	private int secondBetweenGame;
	
	private long timeBeforeReplikChange;
	
	private long timeBeforeGameStart;
	
	private int nbProposition;
	
	// le set de replik couramenent joué
	private Set<Replik> setReplik;
	
	// la liste des replik servant de referentiel
	private List<ReplikDao> listReplikDao;
	
	private boolean gameReady = false;

	
	
	
	public Game(List<ReplikDao> listReplikDao) {
		this.listReplikDao = listReplikDao;
	}
	
	public Game() {

	}

	public Set<User> getListeUser() {
		return listeUser;
	}

	public void setListeUser(Set<User> listeUser) {
		this.listeUser = listeUser;
	}

	public int getCurrentIdUser() {
		return currentIdUser;
	}

	public void setCurrentIdUser(int currentId) {
		this.currentIdUser = currentId;
	}

	public  int getCurrentReplik() {
		return currentReplik;
	}

	public void setCurrentReplik(int currentReplik) {
		this.currentReplik = currentReplik;
	}

	public int getMaxReplik() {
		return maxReplik;
	}

	public void setMaxReplik(int maxReplik) {
		this.maxReplik = maxReplik;
	}

	public int getSecondParReplik() {
		return secondParReplik;
	}

	public void setSecondParReplik(int secondParReplik) {
		this.secondParReplik = secondParReplik;
	}
	
	
	public long getTimeBeforeReplikChange() {
		return timeBeforeReplikChange;
	}

	public void setTimeBeforeReplikChange(long timeBeforeReplikChange) {
		this.timeBeforeReplikChange = timeBeforeReplikChange;
	}
	


	public Set<Replik> getSetReplik() {
		return setReplik;
	}

	public void setSetReplik(Set<Replik> setReplik) {
		this.setReplik = setReplik;
	}

	
	
	public int getNbProposition() {
		return nbProposition;
	}

	public void setNbProposition(int nbProposition) {
		this.nbProposition = nbProposition;
	}
	
	
	

	public boolean isGameReady() {
		return gameReady;
	}

	public void setGameReady(boolean gameReady) {
		this.gameReady = gameReady;
	}
	
	

	public int getSecondBetweenGame() {
		return secondBetweenGame;
	}

	public void setSecondBetweenGame(int secondBetweenGame) {
		this.secondBetweenGame = secondBetweenGame;
	}
	
	

	public long getTimeBeforeGameStart() {
		return timeBeforeGameStart;
	}

	public void setTimeBeforeGameStart(long timeBeforeGameStart) {
		this.timeBeforeGameStart = timeBeforeGameStart;
	}
	
	public List<ReplikDao> getListReplikDao() {
		return listReplikDao;
	}

	public void setListReplikDao(List<ReplikDao> listReplikDao) {
		this.listReplikDao = listReplikDao;
	}


	
	public void run() {

		long start =  System.currentTimeMillis();
		long startReloadGame =  System.currentTimeMillis();
		long waittime = 1000 * this.secondParReplik;
		long waittimeReloadGame = 1000 * this.secondBetweenGame;
		if(Credentials.isSourceReplikDb()){
			this.setReplik = Utils.generateSetReplik(this.maxReplik, this.nbProposition, this.listReplikDao);
		}else{
			this.setReplik = Utils.generateSetReplik(this.maxReplik, this.nbProposition);
		}
		this.timeBeforeGameStart = waittimeReloadGame;
		long i = 1;
		
		
		//valable seulement la	 premiere fois que l on lance le serveur
		// on attend 10 seconde avant le demarage du jeu
		long currentSystemTimeReloadGame = startReloadGame;		
		while(currentSystemTimeReloadGame < ( startReloadGame + waittimeReloadGame )){
			currentSystemTimeReloadGame = System.currentTimeMillis();
			this.timeBeforeGameStart = startReloadGame + waittimeReloadGame - currentSystemTimeReloadGame;	
		}
		this.gameReady = true;
		
		while(true){
		
			
			
			// on atteind le nombre max de replik donc on relance une manche
			if(Long.valueOf(this.maxReplik +1).longValue()  == i){
				
				// on attend  x seconde entre chaque manche
				this.gameReady = false;
				if(Credentials.isSourceReplikDb()){
					this.setReplik = Utils.generateSetReplik(this.maxReplik, this.nbProposition, this.listReplikDao);
				}else{
					this.setReplik = Utils.generateSetReplik(this.maxReplik, this.nbProposition);
				}
				startReloadGame =  System.currentTimeMillis();
				currentSystemTimeReloadGame =  startReloadGame;
				while(currentSystemTimeReloadGame < ( startReloadGame + waittimeReloadGame )){
					currentSystemTimeReloadGame = System.currentTimeMillis();
					this.timeBeforeGameStart = startReloadGame + waittimeReloadGame - currentSystemTimeReloadGame;	
				}
				this.gameReady = true;
				

				
				i = 1l;
				Utils.resetClassementGeneral(this.listeUser);				
				start = System.currentTimeMillis();
			}
			
			// si le temps courant est inferieur au temps de lancement de la manche + le temps legale d attente
			long currentSystemTime = System.currentTimeMillis();
			if( currentSystemTime < ( start + waittime )){
				this.currentReplik = Long.valueOf(i).intValue();
				this.timeBeforeReplikChange = start + waittime - currentSystemTime;				
			}else{				
				i++;
				Utils.resetClassementReplik(this.listeUser);
				start = System.currentTimeMillis();
			}
			
		}

	}
	
	
	
	
	
}
