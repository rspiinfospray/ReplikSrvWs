package org.infospray.replik.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;


public class DbReader {

	static Logger logger = Logger.getLogger(DbReader.class);

	private DbReader(){};


	public static List<ReplikDao> getListReplik() {
		Connection cnx = DbConnection.getConnection();

		Properties properties = PropertiesFile.getQueryProperties();

		List<ReplikDao> listReplikDao =  new ArrayList<ReplikDao>();
		ReplikDao replikDao =  null;

		String query = (String)properties.get(EnumCredentials.SELECT_ALL_REPLIK.getLibelle());
		logger.info("Execution de la requete : "+query);
		Statement req;
		try {
			req = cnx.createStatement();

			logger.info("Recuperation de la liste des répliques");
			ResultSet rs = req.executeQuery(query);
			if(rs != null){
				while (rs.next()) {

					replikDao = new ReplikDao();
					replikDao.setActif(rs.getBoolean("actif"));
					replikDao.setFilm(rs.getString("film"));
					replikDao.setId(rs.getInt("id"));
					replikDao.setReplique(rs.getString("replique"));
					listReplikDao.add(replikDao);
					logger.info(replikDao);
				}

				rs.close();
			}else{
				logger.error("Result set vide");
			}
			req.close();
		} catch (SQLException e) {
			logger.error("Echec de la récupération de la liste des répliques : " + e.getMessage());
		}catch(Exception e){
			logger.error("Echec de la récupération de la liste des répliques : " + e.getMessage());
		}
		

		return listReplikDao;
	}


}
