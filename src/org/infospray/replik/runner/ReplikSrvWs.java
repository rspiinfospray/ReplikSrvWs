package org.infospray.replik.runner;


public class ReplikSrvWs {

	

	public static void main(String[] args) {


		try {
			new Server();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Server ready...");


	}

	
}
