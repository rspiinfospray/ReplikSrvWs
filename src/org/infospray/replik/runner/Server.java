package org.infospray.replik.runner;

import java.util.List;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.infospray.replik.beans.Context;
import org.infospray.replik.beans.Game;
import org.infospray.replik.db.Credentials;
import org.infospray.replik.db.DbReader;
import org.infospray.replik.db.ReplikDao;
import org.infospray.replik.ws.ReplikWsImpl;




public class Server {

	static Logger logger = Logger.getLogger(Server.class);

	public Server() throws Exception {

		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("Starting Server");
		Context ctx = new Context();
		Game game = null;
		if(Credentials.isSourceReplikDb()){
			logger.info("Connextion à la DB");
			List<ReplikDao> listReplikDao = DbReader.getListReplik();
			if(listReplikDao.isEmpty()){
				System.exit(0);
			}
			game = new Game(listReplikDao);
		}else{
			logger.info("Recuperation de la liste des replik dans le properties");
			game = new Game();
		}

		game.setSecondParReplik(Integer.valueOf(Credentials.getSecondParReplik()));
		logger.info("Config : Seconde par replique : " + game.getSecondParReplik());
		game.setSecondBetweenGame(Integer.valueOf(Credentials.getSecondBetweenGame()));
		logger.info("Config : Seconde entre chaque manche : " + game.getSecondBetweenGame());
		game.setMaxReplik(Integer.valueOf(Credentials.getNbReplik()));
		logger.info("Config : Nombre total de repliques : " + game.getMaxReplik());
		game.setNbProposition(Integer.valueOf(Credentials.getNbPropositions()));
		logger.info("Config : Nombre de propositions de réponses par repliques : " + game.getNbProposition());
		ctx.getListeGame().add(game);
		game.start();


		ReplikWsImpl replik = new ReplikWsImpl(ctx);
		String address =  Credentials.getWebServiceUrl();
		System.out.println(address);
		Endpoint.publish(address, replik);


	}

}
