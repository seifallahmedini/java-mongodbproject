package com.isamm.Galarie;

import java.util.Date;

public class Oeuvre {
	private String nom;
	private String date_creation;
	private String theme;
	private double prix;
	private boolean vendue;
	private String createur;

	public Oeuvre(String nom, String date_creation, String theme, double prix, boolean vendue, String createur) {
		super();
		this.nom = nom;
		this.date_creation = date_creation;
		this.theme = theme;
		this.prix = prix;
		this.vendue = vendue;
		this.createur = createur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public boolean isVendue() {
		return vendue;
	}

	public void setVendue(boolean vendue) {
		this.vendue = vendue;
	}

	public String getCreateur() {
		return createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	@Override
	public String toString() {
		return "Oeuvre [nom=" + nom + ", date_creation=" + date_creation + ", theme=" + theme + ", prix=" + prix
				+ ", vendue=" + vendue + ", createur=" + createur + "]";
	}
}
