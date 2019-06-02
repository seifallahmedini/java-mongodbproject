package com.isamm.Galarie;

public class Galerie {
	private String nom_organisateur;
	private String adresse;
	private int nb_oeuvre = 0;
	private Oeuvre[] oeuvres = new Oeuvre[100];
	private final int nb_max = 100;

    public Galerie() {
    }
        
        

	public Galerie(String nom_organisateur, String adresse) {
		this.nom_organisateur = nom_organisateur;
		this.adresse = adresse;
	}
	
	

	public String getNom_organisateur() {
		return nom_organisateur;
	}



	public void setNom_organisateur(String nom_organisateur) {
		this.nom_organisateur = nom_organisateur;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public int getNb_oeuvre() {
		return nb_oeuvre;
	}



	public void setNb_oeuvre(int nb_oeuvre) {
		this.nb_oeuvre = nb_oeuvre;
	}



	public Oeuvre[] getOeuvres() {
		return oeuvres;
	}



	public void setOeuvres(Oeuvre[] oeuvres) {
		this.oeuvres = oeuvres;
	}



	public int getNb_max() {
		return nb_max;
	}



	// Cette method permet d'ajouter une peinture 	
	void ajouterPeinture(Peinture peinture) {
		if (this.nb_oeuvre < this.nb_max) {
			this.oeuvres[this.nb_oeuvre] = peinture;
			this.nb_oeuvre++;
		} else {
			System.out.println("la galerie est saturee");
		}
	}

	// Cette method permet de retourne la liste de toutes les peintures	
	Peinture[] getPeintures() {
		int indice = 0;
		Peinture[] peintures = new Peinture[100];
		for (Oeuvre oeuvre : this.oeuvres) {
			if (oeuvre instanceof Peinture) {
				peintures[indice] = (Peinture) oeuvre;
				indice++;
			}
		}
		return peintures;
	}

	// Cette method permet d'ajouter une Scrulpture	
	void ajouterSculpture(Sculpture sculpture) {
		if (this.nb_oeuvre < this.nb_max) {
			this.oeuvres[this.nb_oeuvre] = sculpture;
			this.nb_oeuvre++;
		} else {
			System.out.println("la galerie est saturee");
		}
	}

	// Cette method permet de retourne la liste de toutes les Scrulptures	
	Sculpture[] getSculptures() {
		int indice = 0;
		Sculpture[] sculptures = new Sculpture[100];
		for (Oeuvre oeuvre : this.oeuvres) {
			if (oeuvre instanceof Sculpture) {
				sculptures[indice] = (Sculpture) oeuvre;
				indice++;
			}
		}
		return sculptures;
	}
	
	// Cette method permet de retourne un oeuvre qu'a un prix entre p1 et p2
	Oeuvre[] getOeuvresPrixEntre(double p1, double p2) {
		int indice = 0;
		Oeuvre[] oeuvres = new Oeuvre[100];
		for (Oeuvre oeuvre : this.oeuvres) {
			if (oeuvre.getPrix() >= p1 && oeuvre.getPrix() <= p2) {
				oeuvres[indice] = oeuvre;
				indice++;
			}
		}
		return oeuvres;
	}

	// gestion des exceptions
	Oeuvre[] chercherOeuvres(String motCle) throws NotExistException {
		int indice = 0;
		Oeuvre[] oeuvres = new Oeuvre[100];
		for (Oeuvre oeuvre : this.oeuvres) {
			if (oeuvre != null) {
				if (oeuvre.getNom().contains(motCle)) {
					oeuvres[indice] = oeuvre;
					indice++;
				}
			}

		}

		// Si le tableau est vide càd l'oeuvre chercher n'existe pas...
		if (indice == 0) {
			// ...On doit lever une exception
			throw new NotExistException();
		} else {
			return oeuvres;
		}

	}
	
	
	
}
