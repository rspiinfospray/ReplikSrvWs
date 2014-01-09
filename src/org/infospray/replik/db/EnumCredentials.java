package org.infospray.replik.db;

public enum EnumCredentials {

	
	DATA_SOURCE_NAME("source.DataSourceName"),
	SERVER_NAME("source.ServerName"),
	DATABASE_NAME("source.DatabaseName"),
	USER("source.User"),
	PASSWORD("source.Password"),
	MAX_CONNECTIONS("source.MaxConnections"),
	PORT_NUMBER("source.PortNumber"),
	SCHEMA("source.Schema"),
	WS_URL("ws.url"),
	NB_MAX_REPLIK("replik.maxReplik"),
	SECOND_PAR_REPLIK("replik.secondeParReplik"),
	SECOND_BETWEEN_GAME("replik.secondBetweenGame"),
	NB_PROPOSITON("replik.nbProposition"),
	NB_REPLIK("replik.nombreReplik"),
	NB_CONNEXION_MAX("replik.nbConnexionMax"),
	REPLIK_SOURCE_DB("replik.replikSourceDb"),
	SELECT_ALL_REPLIK("replik.getAllReplik");	
	
	private final String libelle;

	public String getLibelle() {
		return libelle;
	}

	private EnumCredentials(String libelle) {
		this.libelle = libelle;
	}

}
