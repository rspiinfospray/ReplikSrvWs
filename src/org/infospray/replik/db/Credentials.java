package org.infospray.replik.db;



public   class Credentials {
	
	
	private Credentials() {
		super();
	}


	public static String getDataSourceName() {
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.DATA_SOURCE_NAME.getLibelle());
	}
	
	public static String getServerName() {
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.SERVER_NAME.getLibelle());
	}
	
	public static String getDataBaseName() {
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.DATABASE_NAME.getLibelle());
	}

	public static String getUser() {
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.USER.getLibelle());
	}

	public static String getPassword() {
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.PASSWORD.getLibelle());
	}

	public static String getMaxConnections() {
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.MAX_CONNECTIONS.getLibelle());
	}
	
	public static String getPortNumber() {
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.PORT_NUMBER.getLibelle());
	}
	
	public static String getSchema() {
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.SCHEMA.getLibelle());
	}

	public static String getWebServiceUrl() {
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.WS_URL.getLibelle());
	}

	public static String getNbPropositions(){
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.NB_PROPOSITON.getLibelle());
	}
	
	public static String getNbReplik(){
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.NB_REPLIK.getLibelle());
	}
	
	public static String getSecondBetweenGame(){
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.SECOND_BETWEEN_GAME.getLibelle());
	}
	
	
	public static String getSecondParReplik(){
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.SECOND_PAR_REPLIK.getLibelle());
	}
	
	public static String getNbConnexionMax(){
		return PropertiesFile.getDbCredentials().getProperty(EnumCredentials.NB_CONNEXION_MAX.getLibelle());
	}
	
	public static boolean isSourceReplikDb(){
		String sourceDb =  PropertiesFile.getDbCredentials().getProperty(EnumCredentials.REPLIK_SOURCE_DB.getLibelle());
		if(sourceDb.equals("1")){
			return true;
		}
		
		return false;
	}
	
	
}
