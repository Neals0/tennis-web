package com.mycompany.beans;

public class Epreuve {

	private Long id;
	private String joueur;
	private String type_epreuve;
	private int annee;
	private String nom_tournoi;
	private String statut;
	
	public Epreuve() {
		super();
	}

	public Epreuve(Long id, String joueur, String type_epreuve, int annee, String nom_tournoi,
			String statut) {
		super();
		this.id = id;
		this.joueur = joueur;
		this.type_epreuve = type_epreuve;
		this.annee = annee;
		this.nom_tournoi = nom_tournoi;
		this.statut = statut;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJoueur() {
		return joueur;
	}

	public void setJoueur(String joueur) {
		this.joueur = joueur;
	}

	public String getType_epreuve() {
		return type_epreuve;
	}

	public void setType_epreuve(String type_epreuve) {
		this.type_epreuve = type_epreuve;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public String getNom_tournoi() {
		return nom_tournoi;
	}

	public void setNom_tournoi(String nom_tournoi) {
		this.nom_tournoi = nom_tournoi;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}



	
	
}
