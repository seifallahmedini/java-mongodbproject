package com.isamm.Galarie;

import java.util.Date;

public class Sculpture extends Oeuvre {
	private double volume;
	private double poids;
	private String matiere_utiliser;

	public Sculpture(String nom, String date_creation, String theme, double prix, boolean vendue, String createur,
			double volume, double poids, String matiere_utiliser) {
		super(nom, date_creation, theme, prix, vendue, createur);
		this.volume = volume;
		this.poids = poids;
		this.matiere_utiliser = matiere_utiliser;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public String getMatiere_utiliser() {
		return matiere_utiliser;
	}

	public void setMatiere_utiliser(String matiere_utiliser) {
		this.matiere_utiliser = matiere_utiliser;
	}

	@Override
	public String toString() {
		return super.toString() + " Sculpture [volume=" + volume + ", poids=" + poids + ", matiere_utiliser="
				+ matiere_utiliser + "]";
	}

}