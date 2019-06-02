package com.isamm.Galarie;

import java.util.Date;

public class Peinture extends Oeuvre {
	private double taille;

	public Peinture(String nom, String date_creation, String theme, double prix, boolean vendue, String createur,
			double taille) {
		super(nom, date_creation, theme, prix, vendue, createur);
		this.taille = taille;
	}

	public double getTaille() {
		return taille;
	}

	public void setTaille(double taille) {
		this.taille = taille;
	}

	@Override
	public String toString() {
		return super.toString() + "Peinture [taille=" + taille + "]";
	}

}
