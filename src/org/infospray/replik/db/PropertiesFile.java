package org.infospray.replik.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {

	private static Properties dbProperties;
	private static Properties queryProperties;
	private static Properties listeReplikProperties;

	private PropertiesFile() {
	}

	public static Properties getDbCredentials() {
		if (null == dbProperties) {
			dbProperties = new Properties();
			try {
				FileInputStream file =  new  FileInputStream("replik.properties");
				dbProperties.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return dbProperties;
	}
	
	public static Properties getQueryProperties() {
		if (null == queryProperties) {
			queryProperties = new Properties();
			try {
				FileInputStream file =  new  FileInputStream("query.properties");
				queryProperties.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return queryProperties;
	}
	
	public static Properties getListReplikProperties() {
		if (null == listeReplikProperties) {
			listeReplikProperties = new Properties();
			try {
				FileInputStream file =  new  FileInputStream("listeReplik.properties");
				listeReplikProperties.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return listeReplikProperties;
	}

}