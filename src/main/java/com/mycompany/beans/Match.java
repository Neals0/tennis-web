package com.mycompany.beans;

public class Match {

	private int id; // changer en long
	private String vainqueur;
	private String finaliste;
	private String sexe;
	
	public Match() {
		super();
	}

	public Match(int id, String vainqueur, String finaliste, String sexe) {
		super();
		this.id = id;
		this.vainqueur = vainqueur;
		this.finaliste = finaliste;
		this.sexe = sexe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVainqueur() {
		return vainqueur;
	}

	public void setVainqueur(String vainqueur) {
		this.vainqueur = vainqueur;
	}

	public String getFinaliste() {
		return finaliste;
	}

	public void setFinaliste(String finaliste) {
		this.finaliste = finaliste;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	
	
	
}
