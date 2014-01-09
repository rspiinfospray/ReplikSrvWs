package org.infospray.replik.ws;

import javax.jws.WebService;

@WebService
public interface ReplikWs {
	
	public ValidateUser validateUserByPseudo(String pseudo, String securityString);
	
	public Classement getClassement(String pseudo, String securityString);
	
	public Play	play(String pseudo, String securityString);
	
	public Answer validateAnswer(String pseudo, String securityString, boolean goodAnswer, int currentReplikid,  long tempsReaction);
	
	public Sync	synchronisation(String pseudo, String securityString);

}
