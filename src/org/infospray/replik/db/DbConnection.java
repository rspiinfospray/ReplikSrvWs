package org.infospray.replik.db;


import java.sql.Connection;
import java.sql.SQLException;


import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

public final class DbConnection {


	private static Jdbc3PoolingDataSource source;



	private DbConnection() {

	}




	public static Connection getConnection(){


		if (null == source) {
			source = new Jdbc3PoolingDataSource();
			source.setDataSourceName(Credentials.getDataSourceName());
			source.setServerName(Credentials.getServerName());
			source.setDatabaseName(Credentials.getDataBaseName());
			source.setUser(Credentials.getUser());
			source.setPassword(Credentials.getPassword());
			source.setMaxConnections(Integer.valueOf(Credentials.getMaxConnections()));
			source.setPortNumber(Integer.valueOf(Credentials.getPortNumber()));
			
		}


		Connection con = null;
		try {
			con = source.getConnection();
		} catch (SQLException e) {
			System.out.println("SQL ERROR : " + e.getMessage());
		}

		return con;
	}

}
