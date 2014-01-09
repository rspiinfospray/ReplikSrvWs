package org.infospray.replik.beans;

import java.util.HashSet;
import java.util.Set;

import org.infospray.replik.ws.Proposition;

public class Replik {

	private int idReplik;
	
	private String replik;
	
	private String film;
	
	private int idReplikInList;
	
	private Set<Proposition> listProposition = new HashSet<Proposition>();

	public int getIdReplik() {
		return idReplik;
	}

	public void setIdReplik(int idReplik) {
		this.idReplik = idReplik;
	}

	public String getReplik() {
		return replik;
	}

	public void setReplik(String replik) {
		this.replik = replik;
	}


	public Set<Proposition> getListProposition() {
		return listProposition;
	}

	public void setListProposition(Set<Proposition> listProposition) {
		this.listProposition = listProposition;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public int getIdReplikInList() {
		return idReplikInList;
	}

	public void setIdReplikInList(int idReplikInList) {
		this.idReplikInList = idReplikInList;
	}
	
	
	
	
}
