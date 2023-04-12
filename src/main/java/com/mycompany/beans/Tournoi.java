package com.mycompany.beans;

public class Tournoi {
	
	private Long id;
	private String nom;
	private String code;
	
	public Tournoi(Long id, String nom, String code) {
		super();
		this.id = id;
		this.nom = nom;
		this.code = code;
	}

	public Tournoi() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}





}
