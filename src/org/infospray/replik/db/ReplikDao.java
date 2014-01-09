package org.infospray.replik.db;

public class ReplikDao {

	private int		id;
	
	private String	replique;
	
	private String	film;
	
	private boolean actif;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReplique() {
		return replique;
	}

	public void setReplique(String replique) {
		this.replique = replique;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	@Override
	public String toString() {
		return "ReplikDao [id=" + id + ", replique=" + replique + ", film="
				+ film + ", actif=" + actif + "]";
	}
	
	
	
	
}
