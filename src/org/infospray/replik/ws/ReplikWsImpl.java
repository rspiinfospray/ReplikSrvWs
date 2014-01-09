package org.infospray.replik.ws;

import java.util.List;
import java.util.Set;

import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.infospray.replik.beans.Context;
import org.infospray.replik.beans.Game;
import org.infospray.replik.beans.Replik;
import org.infospray.replik.beans.User;
import org.infospray.replik.beans.UserReplikStats;
import org.infospray.replik.db.Credentials;
import org.infospray.replik.utils.Utils;

@WebService(endpointInterface = "org.infospray.replik.ws.ReplikWs",serviceName = "ReplikWs")
public class ReplikWsImpl implements ReplikWs {
	
	static Logger logger = Logger.getLogger(ReplikWsImpl.class);
	
	private static int FIRST_ANSWER_PTS = 50;
	private static int SECOND_ANSWER_PTS = 40;
	private static int THIRD_ANSWER_PTS = 30;
	private static int OTHER_ANSWER_PTS = 10;
	private static int BAD_ANSWER_PTS = 0;
	private static int NO_GOOD_ANSWER_CHAIN_PTS = 0;
	private static int FIVE_GOOD_ANSWER_CHAIN_PTS = 20;
	private static int TEN_GOOD_ANSWER_CHAIN_PTS = 40;
	private static int FIVETEEN_GOOD_ANSWER_CHAIN_PTS = 80;
	private static int FIVE_GOOD_ANSWER_CHAIN = 5;
	private static int TEN_GOOD_ANSWER_CHAIN = 10;
	private static int FIVETEEN_GOOD_ANSWER_CHAIN = 15;
	
	private Context context;
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public ReplikWsImpl(Context context) {
	
		this.context =  context;
	}

	@Override
	public ValidateUser validateUserByPseudo(String pseudo, String securityString) {
		logger.info("Appel au webservice validateUserByPseudo : " + pseudo);

		ValidateUser validate = new ValidateUser();
		// nouveau pseudo premiere connexion
		if(!pseudo.isEmpty() && (securityString == null || securityString.isEmpty())){
			List<Game> listeGame =  this.context.getListeGame();

			for (Game game : listeGame) {

				Set<User> setUser = game.getListeUser();
				
				if(setUser.size() == Integer.valueOf(Credentials.getNbConnexionMax()).intValue()){
					validate.setCodeRetour(ConstantsError.MAX_CONNEXION_REACH_CODE);
					validate.setLibelleRetour(ConstantsError.MAX_CONNEXION_REACH_CODE_LBL);						
					return 	validate;
				}
				for (User user : setUser) {
					if(user.getPseudo().toUpperCase().equals(pseudo.toUpperCase())){

						validate.setCodeRetour(ConstantsError.USER_STILL_EXITS_CODE);
						validate.setLibelleRetour(ConstantsError.USER_STILL_EXITS_LBL);						
						return 	validate;
					}
				}
			}

			String md5 = Utils.generateMd5();
			validate.setSecurityString(md5);
			validate.setCodeRetour(ConstantsError.OK_CODE);
			validate.setLibelleRetour(ConstantsError.OK_LBL);


			User user = new User();
			this.context.getListeGame().get(0).setCurrentIdUser(this.context.getListeGame().get(0).getCurrentIdUser() + 1);
			user.setId(this.context.getListeGame().get(0).getCurrentIdUser());
			user.setPseudo(pseudo);
			user.setSecurityString(md5);
			this.context.getListeGame().get(0).getListeUser().add(user);

		}else{			
			if(pseudo.isEmpty()){
				validate.setCodeRetour(ConstantsError.USER_EMPTY_CODE);
				validate.setLibelleRetour(ConstantsError.USER_EMPTY_LBL);						
				return 	validate;
			}
			if(securityString != null && !securityString.isEmpty()){
				Game game = Utils.findUserByPseudoAndSecurity(pseudo, securityString, this.context.getListeGame());
				if(game == null){
					validate.setCodeRetour(ConstantsError.USER_NOT_EXIST_CODE);
					validate.setLibelleRetour(ConstantsError.USER_NOT_EXIST_LBL);
				}else{
					validate.setSecurityString(securityString);
					validate.setCodeRetour(ConstantsError.OK_CODE);
					validate.setLibelleRetour(ConstantsError.OK_LBL);
				}
			}

		}


		return 	validate;
	}

	@Override
	public Classement getClassement(String pseudo, String securityString) {
		
		logger.info("Appel au webservice match : " + pseudo);
		Game game = Utils.findUserByPseudoAndSecurity(pseudo, securityString, this.context.getListeGame());
		Classement match = new Classement();
		if(game == null){
			match.setCodeRetour(ConstantsError.USER_UNKNOWN_CODE);
			match.setLibelleRetour(ConstantsError.USER_UNKNOWN_LBL);
		}else{
			
			match =  Utils.gameToMatch(game);
			match.setCodeRetour(ConstantsError.OK_CODE);
			match.setLibelleRetour(ConstantsError.OK_LBL);

		}
		
		return match;
	}

	@Override
	public Play play(String pseudo, String securityString) {
		
		logger.info("Appel au webservice play : " + pseudo);
		Game game = Utils.findUserByPseudoAndSecurity(pseudo, securityString, this.context.getListeGame());
	
		Play play = new Play(); 
		if(game == null){			
			play.setCodeRetour(ConstantsError.USER_UNKNOWN_CODE);
			play.setLibelleRetour(ConstantsError.USER_UNKNOWN_LBL);
		}else{
			
			User user = Utils.findUserByPseudo(pseudo, game.getListeUser());
			
			if(!user.isInGame()){				
				user.setInGame(true);
			}
			if(game.isGameReady()){
				user.setClassementGeneral(Utils.getNumeroClassementGeneral(game.getListeUser(),user ));
				
				Set<Replik> newSetReplik = Utils.generateSetAdaptedReplik(game.getSetReplik(),game.getCurrentReplik());
				org.infospray.replik.ws.Replik[] tabReplik = new org.infospray.replik.ws.Replik[newSetReplik.size()];
				int i = 0;
				for (Replik currentReplik : newSetReplik) {											
					Proposition[] propositions = currentReplik.getListProposition().toArray(new Proposition[currentReplik.getListProposition().size()]);
					tabReplik[i] =  new org.infospray.replik.ws.Replik();
					tabReplik[i].setPropositions(propositions);
					tabReplik[i].setReplikLbl(currentReplik.getReplik());
					tabReplik[i].setReplikId(currentReplik.getIdReplik());
					tabReplik[i].setReplikIdInList(currentReplik.getIdReplikInList());
					i++;
				}
				
				play.setReplik(tabReplik);
				play.setTempsRestantPourReplik(game.getTimeBeforeReplikChange());
				play.setCurrentReplikId(game.getCurrentReplik());
				
				play.setClassementGeneral(user.getClassementGeneral());
				play.setNbJoueurs(Utils.getNbJoueursInGame(game.getListeUser()));
				play.setPoints(user.getPoint());
				play.setGameReady(true);
			}else{
				play.setGameReady(false);								
			}
			
			play.setTempsRestantAvantDebutManche(game.getTimeBeforeGameStart());
			play.setSecondParReplik(game.getSecondParReplik());
			play.setNbTotalReplik(game.getMaxReplik());
			play.setSecondEntreManche(game.getSecondBetweenGame());
			play.setCodeRetour(ConstantsError.OK_CODE);
			play.setLibelleRetour(ConstantsError.OK_LBL);
		}
		
		return play;
	}

	@Override
	public Answer validateAnswer(String pseudo, String securityString, boolean goodAnswer, int currentReplikId, long tempsReaction) {

		logger.info("Appel au webservice Answer : " + pseudo + " - A repondu en : " + String.valueOf(tempsReaction));
		Game game = Utils.findUserByPseudoAndSecurity(pseudo, securityString, this.context.getListeGame());
		Answer answer = new Answer();
		if(game == null){
			answer.setCodeRetour(ConstantsError.USER_UNKNOWN_CODE);
			answer.setLibelleRetour(ConstantsError.USER_UNKNOWN_LBL);
			return answer;
		}else{

			if(game.isGameReady()){
				User user = Utils.findUserByPseudo(pseudo, game.getListeUser());

				UserReplikStats userReplikStats = Utils.getUserReplikStatsById(currentReplikId, user.getListUserReplikStats());
				
				if(!userReplikStats.isHasAnswer()){
									
					int classementReplikTemporaire = 0;

					for (Replik currentReplik : game.getSetReplik()) {

						if(currentReplik.getIdReplikInList() == currentReplikId){

							if(goodAnswer){
								userReplikStats.setHasAnswer(true);
								userReplikStats.setHasCorrectAnswer(true);
								userReplikStats.setTimeToResponse(tempsReaction);

								classementReplikTemporaire = Utils.classementReplikTemporaire(game.getListeUser(),user, currentReplikId);
								userReplikStats.setClassementReplikTemporaire(classementReplikTemporaire);
								user.setCorrectAnswerChain(user.getCorrectAnswerChain() + 1);
								
								if(user.getCorrectAnswerChain() ==  FIVE_GOOD_ANSWER_CHAIN){
									answer.setPointsBonusFiveChain(FIVE_GOOD_ANSWER_CHAIN_PTS);
									userReplikStats.setPointReplikTemporaire(FIVE_GOOD_ANSWER_CHAIN_PTS);
								}else if(user.getCorrectAnswerChain() ==  TEN_GOOD_ANSWER_CHAIN){
									answer.setPointsBonusTenChain(TEN_GOOD_ANSWER_CHAIN_PTS);
									userReplikStats.setPointReplikTemporaire(TEN_GOOD_ANSWER_CHAIN_PTS);
								}else if(user.getCorrectAnswerChain() ==  FIVETEEN_GOOD_ANSWER_CHAIN){
									answer.setPointsBonusFiveteenChain(FIVETEEN_GOOD_ANSWER_CHAIN_PTS);
									userReplikStats.setPointReplikTemporaire(FIVETEEN_GOOD_ANSWER_CHAIN_PTS);
								}
								
								
								if(classementReplikTemporaire == 1){
									user.setPoint(user.getPoint() + FIRST_ANSWER_PTS);
									userReplikStats.setPointReplikTemporaire(userReplikStats.getPointReplikTemporaire() + FIRST_ANSWER_PTS);
									answer.setPointsReplik(FIRST_ANSWER_PTS);
								}
								else if(classementReplikTemporaire == 2){
									userReplikStats.setPointReplikTemporaire(userReplikStats.getPointReplikTemporaire() + SECOND_ANSWER_PTS);
									user.setPoint(user.getPoint() + SECOND_ANSWER_PTS);
									answer.setPointsReplik(SECOND_ANSWER_PTS);
								}
								else if(classementReplikTemporaire == 3){
									userReplikStats.setPointReplikTemporaire(userReplikStats.getPointReplikTemporaire() + THIRD_ANSWER_PTS);
									user.setPoint(user.getPoint() + THIRD_ANSWER_PTS);
									answer.setPointsReplik(THIRD_ANSWER_PTS);
								}
								else{
									userReplikStats.setPointReplikTemporaire(userReplikStats.getPointReplikTemporaire() + OTHER_ANSWER_PTS);
									user.setPoint(user.getPoint() + OTHER_ANSWER_PTS);
									answer.setPointsReplik(OTHER_ANSWER_PTS);
								}
								answer.setPoints(user.getPoint());
							}else{
								userReplikStats.setHasAnswer(true);
								userReplikStats.setHasCorrectAnswer(false);
								user.setCorrectAnswerChain(0);
								answer.setPoints(user.getPoint());
								answer.setPointsReplik(BAD_ANSWER_PTS);
								answer.setPointsBonusFiveChain(NO_GOOD_ANSWER_CHAIN_PTS);
								answer.setPointsBonusTenChain(NO_GOOD_ANSWER_CHAIN_PTS);
								answer.setPointsBonusFiveteenChain(NO_GOOD_ANSWER_CHAIN_PTS);
							}

						}

					}


					answer.setClassementReplik(classementReplik);

					int classementGeneral = Utils.getNumeroClassementGeneral(game.getListeUser(), user);
					user.setClassementGeneral(classementGeneral);
					answer.setClassementGeneral(classementGeneral);


				}else{
					answer.setCodeRetour(ConstantsError.ANSWER_STILL_SEND_CODE);
					answer.setLibelleRetour(ConstantsError.ANSWER_STILL_SEND_LBL);	
					return answer;
				}

				answer.setNbJoueurs(Utils.getNbJoueursInGame(game.getListeUser()));
				answer.setGameReady(true);
			}else{
				answer.setGameReady(false);
				answer.setSecondEntreManche(game.getSecondBetweenGame());
				answer.setTempsRestantAvantDebutManche(game.getTimeBeforeGameStart());
			}
		}

		
		answer.setCodeRetour(ConstantsError.OK_CODE);
		answer.setLibelleRetour(ConstantsError.OK_LBL);
		return answer;
	}

	@Override
	public Sync synchronisation(String pseudo, String securityString) {
		
		logger.info("Appel au webservice synchronisation : " + pseudo);
		Game game = Utils.findUserByPseudoAndSecurity(pseudo, securityString, this.context.getListeGame());
	
		Sync sync = new Sync();
		if(game == null){			
			sync.setCodeRetour(ConstantsError.USER_UNKNOWN_CODE);
			sync.setLibelleRetour(ConstantsError.USER_UNKNOWN_LBL);
		}else{
			sync.setCodeRetour(ConstantsError.OK_CODE);
			sync.setLibelleRetour(ConstantsError.OK_LBL);
			sync.setGameReady(game.isGameReady());
			sync.setTempsRestantAvantDebutManche(game.getTimeBeforeGameStart());			
		}
		
		
		return sync;
	}

}
