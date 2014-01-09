package org.infospray.replik.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.infospray.replik.beans.Game;
import org.infospray.replik.beans.Replik;
import org.infospray.replik.beans.User;
import org.infospray.replik.beans.UserReplikStats;
import org.infospray.replik.db.PropertiesFile;
import org.infospray.replik.db.ReplikDao;
import org.infospray.replik.ws.InfosUser;
import org.infospray.replik.ws.Classement;
import org.infospray.replik.ws.Proposition;

public class Utils {

	
	public static String generateMd5(){
		
		Date date = new Date();
		String hash = "default";
		String superchaine = "SuperChaineAHacher0604" +  String.valueOf(date.getTime());
		
	     byte[] toHash = superchaine.getBytes();
         byte[] MD5Digest = null;
         StringBuilder hashString = new StringBuilder();
        
         //Calculer le MD5
         MessageDigest algo;
		try {
			algo = MessageDigest.getInstance("MD5");
			   algo.reset();
		         algo.update(toHash);
		         MD5Digest = algo.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        
         //Créer un représentation hexadecimal du Digest
         for (int i = 0; i < MD5Digest.length; i++) {
             String hex = Integer.toHexString(MD5Digest[i]);
             if (hex.length() == 1) {
                 hashString.append('0');
                 hashString.append(hex.charAt(hex.length() - 1));
             } else {
                 hashString.append(hex.substring(hex.length() - 2));
             }
         }
         return hashString.toString();        
		
		
	}
	
	
	public static Game findUserByPseudoAndSecurity(String pseudo, String securityString, List<Game> listeGame){
		
		for (Game game : listeGame) {
			
			Set<User> setUser = game.getListeUser();
			
			for (User user : setUser) {
				if(pseudo.equals(user.getPseudo()) && securityString.equals(user.getSecurityString())){
					return game;
				}
			}
		}
		
		return null;
	}
	
	
	public static User findUserByPseudo(String pseudo, Set<User> setUser){

		for (User user : setUser) {
			if(pseudo.equals(user.getPseudo())){
				return user;
			}
		}
		
		return null;
	}
	
	
	public static InfosUser[] UserToInfosUser(Set<User> setUser){

		InfosUser[] tab = new InfosUser[setUser.size()];

		int i = 0;
		for (User user : setUser) {
			if(user.isInGame()){
				tab[i] = new InfosUser();
				tab[i].setClassement(Utils.getNumeroClassementGeneral(setUser, user));
				tab[i].setPoint(user.getPoint());
				tab[i].setPseudo(user.getPseudo());
				i++;
			}
		}


		return tab;
	}
	
	

	
	public static Classement gameToMatch(Game game){
		
		Classement match =  new Classement();
		if(game.isGameReady()){
			match.setCurrentReplik(game.getCurrentReplik());
		
			//match.setSecondParReplik(game.getSecondParReplik());
			//match.setTotalReplik(game.getMaxReplik());
			match.setTimeBeforeReplikChange(game.getTimeBeforeReplikChange());
		}else{
			match.setTempsRestantAvantDebutManche(game.getTimeBeforeGameStart());
		
		}
		
		match.setSecondParReplik(game.getSecondParReplik());
		match.setGameReady(game.isGameReady());
		match.setNbJoueurs(Utils.getNbJoueursInGame(game.getListeUser()));
		match.setListeUser(Utils.UserToInfosUser(game.getListeUser()));
		match.setSecondEntreManche(game.getSecondBetweenGame());
		match.setTotalReplik(game.getMaxReplik());
		return match;
	}
	
	
	/**
	 * retourne un objet replik contenant une replike sa reponse et une liste de proposition
	 * @return
	 */
	public static Replik generateReplik(long rand, int nbProposition){
		
		Replik replik = new Replik();
		List<Proposition> listPropositon = new ArrayList<Proposition>();
		
		Properties properties = PropertiesFile.getListReplikProperties();
		Date date = new Date();
		int randomNumber = new Random(date.getTime() + rand).nextInt(21) +  1 ;
		
		String libelle =  (String)properties.get("replik.lbl."+randomNumber);
		String film = (String)properties.get("replik.film."+randomNumber);
		
		
		replik.setFilm(film);
		replik.setIdReplik(randomNumber);
		replik.setReplik(libelle);
	
		Proposition proposition = new Proposition();
		proposition.setIdPropostion(randomNumber);
		proposition.setLblProposition(film);
		replik.getListProposition().add(proposition);
		
		int cpt = 1;
		while(replik.getListProposition().size() != nbProposition){
			Date date2 = new Date();
			int randomNumber2 = new Random(date2.getTime() + cpt).nextInt(21) + 1;
			String film2 = (String)properties.get("replik.film."+randomNumber2);			
			Proposition proposition2 = new Proposition();
			proposition2.setIdPropostion(randomNumber2);
			proposition2.setLblProposition(film2);			
			replik.getListProposition().add(proposition2);
			cpt++;
		}
		
		listPropositon.addAll(replik.getListProposition());
		
		Collections.shuffle(listPropositon, new Random(date.getTime()));
		
		replik.getListProposition().clear();
		replik.getListProposition().addAll(listPropositon);
		
		listPropositon = null;
		
		return replik;
		
	}
	
	/**
	 *  retourne un objet replik contenant une replike sa reponse et une liste de proposition avec comme pour source la DB
	 * @param rand
	 * @param nbProposition
	 * @param listReplikDao
	 * @return
	 */
	public static Replik generateReplik(long rand, int nbProposition, List<ReplikDao> listReplikDao){
		
		Replik replik = new Replik();
		List<Proposition> listPropositon = new ArrayList<Proposition>();
	
		Date date = new Date();
		int randomNumber = new Random(date.getTime() + rand).nextInt(listReplikDao.size()) +  1 ;
		
		
		ReplikDao replikDao = listReplikDao.get(randomNumber);
		
		
		replik.setFilm(replikDao.getFilm());
		replik.setIdReplik(randomNumber);
		replik.setReplik(replikDao.getReplique());
	
		Proposition proposition = new Proposition();
		proposition.setIdPropostion(randomNumber);
		proposition.setLblProposition(replikDao.getFilm());
		replik.getListProposition().add(proposition);
		
		int cpt = 1;
		while(replik.getListProposition().size() != nbProposition){
			Date date2 = new Date();
			int randomNumber2 = new Random(date2.getTime() + cpt).nextInt(listReplikDao.size()) + 1;
			ReplikDao replikDao2 = listReplikDao.get(randomNumber2);			
			Proposition proposition2 = new Proposition();
			proposition2.setIdPropostion(randomNumber2);
			proposition2.setLblProposition(replikDao2.getFilm());			
			replik.getListProposition().add(proposition2);
			cpt++;
		}
		
		listPropositon.addAll(replik.getListProposition());
		
		Collections.shuffle(listPropositon, new Random(date.getTime()));
		
		replik.getListProposition().clear();
		replik.getListProposition().addAll(listPropositon);
		
		listPropositon = null;
		
		return replik;
		
	}
	
	
	/**
	 * genere le nombre de replik passe en parametre
	 * @param nbReplik
	 * @return
	 */
	public static Set<Replik> generateSetReplik(int nbReplik, int nbProposition,  List<ReplikDao> listReplikDao){
		
		Set<Replik> setReplik = new HashSet<Replik>();
		
		int cpt = 1;
		while(setReplik.size() < nbReplik){
			Replik replik = Utils.generateReplik(cpt, nbProposition, listReplikDao);
			replik.setIdReplikInList(cpt);
			setReplik.add(replik);
			cpt++;
		}
		
		return setReplik;
	}
	
	
	/**
	 * genere le nombre de replik passe en parametre
	 * @param nbReplik
	 * @return
	 */
	public static Set<Replik> generateSetReplik(int nbReplik, int nbProposition){
		
		Set<Replik> setReplik = new HashSet<Replik>();
		
		int cpt = 1;
		while(setReplik.size() < nbReplik){
			Replik replik = Utils.generateReplik(cpt, nbProposition);
			replik.setIdReplikInList(cpt);
			setReplik.add(replik);
			cpt++;
		}
		
		return setReplik;
	}
	
	
	/**
	 * donne le classement du joueur pour la replik courante
	 * @param setUser
	 * @param user
	 * @return
	 */
	public static int classementReplikTemporaire(Set<User> setUser,User user, int currentIdReplik){

		
		UserReplikStats userReplikStats = Utils.getUserReplikStatsById(currentIdReplik, user.getListUserReplikStats());
		
		int cpt = 1;
		for (User currentUser : setUser) {
			if(!currentUser.getPseudo().equals(user.getPseudo())){
				
				UserReplikStats currentUserReplikStats =  Utils.getUserReplikStatsById(currentIdReplik, currentUser.getListUserReplikStats());
				if(currentUserReplikStats.isHasAnswer() && currentUserReplikStats.isHasCorrectAnswer() && currentUserReplikStats.getTimeToResponse() < userReplikStats.getTimeToResponse()){
					cpt++;
				}
			}
		}

		return cpt;
	}
	
	
	public static void resetClassementReplik(Set<User> setUser){
		
		for (User user : setUser) {
			user.getListUserReplikStats().clear();
		/*	user.setClassementReplik(0);
			user.setHasAnswer(false);
			user.setHasCorrectAnswer(false);*/
		}
		
	}
	
	public static void resetClassementGeneral(Set<User> setUser){
		
		for (User user : setUser) {
			//user.setClassementReplik(0);
			user.setClassementGeneral(0);
			//user.setHasAnswer(false);
			//user.setHasCorrectAnswer(false);
			user.setPoint(0);
		}
		
	}
	
	
	public static Set<Replik> generateSetAdaptedReplik(Set<Replik> setReplik,int currentReplik){
		
		Set<Replik> newsetReplik = new HashSet<Replik>();
		
		for (Replik replik : setReplik) {
			if(replik.getIdReplikInList() >= currentReplik){
				newsetReplik.add(replik);
			}
		}
		
		return newsetReplik;
	}
	
	
	public static int getNbJoueursInGame(Set<User> setUser){
		
		int cpt = 0;
		for (User user : setUser) {
		
			if(user.isInGame()){
				cpt++;
			}
		}
		
		return cpt;
		
	}
	
	
	public static int getNumeroClassementGeneral(Set<User> setUser, User user){
		
		/*int nbJoueurInGame = Utils.getNbJoueursInGame(setUser);
		int nbJoueursWithNoPts = Utils.getNbJoueursWithNoPoints(setUser);
		
		if(nbJoueurInGame == 1){
			return 1;
		}*/
		
		int cpt = 1;
		
		for (User currentUser : setUser) {
			if(currentUser.isInGame() && currentUser.getPoint() > user.getPoint()){
				cpt++;
			}
		}
		
		return cpt;
		
		/*if(cpt != 0){
			return cpt + 1;
		}else{
			
			
			
			if(cpt == 0){
				return 1;
			}
			return (nbJoueurInGame - nbJoueursWithNoPts) + 1;*/
		//}
		
	}
	
	public static int getNbJoueursWithSameNumberPoints(Set<User> setUser, User user){
		
		int cpt = 0;
		for (User currentUser : setUser) {
			if(!currentUser.getPseudo().equals(user.getPseudo())){
				if(currentUser.isInGame() && currentUser.getPoint() == user.getPoint()){
					cpt++;
				}
			}
		}
		
		return cpt;
	}
	
	public static int getNbJoueursWithNoPoints(Set<User> setUser){

		int cpt = 0;
		for (User currentUser : setUser) {

			if(currentUser.isInGame() && currentUser.getPoint() == 0){
				cpt++;
			}
		}

		return cpt;
	}
	
	
	public static UserReplikStats getUserReplikStatsById(int idReplik, List<UserReplikStats> listUserReplikStats){
		
		for (UserReplikStats userReplikStats : listUserReplikStats) {
			if(userReplikStats.getIdReplik() ==  idReplik){
				return userReplikStats;
			}
		}
		
		return null;
	}
	
	public void sendPushMessage(){
		

		Sender sender = new Sender(
				"API_KEY");

		ArrayList<String> devicesList = new ArrayList<String>();
		devicesList.add("DEVICE-TOKEN");
		// Use this line to send message without payload data
		// Message message = new Message.Builder().build();

		// use this line to send message with payload data
		Message message = new Message.Builder()
				.collapseKey("1")
				.timeToLive(3)
				.delayWhileIdle(true)
				.addData("message",
						"this text will be seen in notification bar !!")
				.build();

		// Use this code to send to a single device
		// Result result = sender
		// .send(message,
		// "APA91bGiRaramjyohc2lKjAgFGpzBwtEmI8tJC30O89C2b3IjP1CuMeU1h9LMjKhmWuZwcXZjy1eqC4cE0tWBNt61Kx_SuMF6awzIt8WNq_4AfwflaVPHQ0wYHG_UX3snjp_U-5kJkmysdRlN6T8xChB1n3DtIq98w",
		// 1);

		// Use this for multicast messages
		MulticastResult result = sender.send(message, devicesList, 1);
		sender.send(message, devicesList, 1);

		System.out.println(result.toString());
		if (result.getResults() != null) {
			int canonicalRegId = result.getCanonicalIds();
			if (canonicalRegId != 0) {
			}
		} else {
			int error = result.getFailure();
			System.out.println(error);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	
}
