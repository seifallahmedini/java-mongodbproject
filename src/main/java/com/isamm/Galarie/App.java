package com.isamm.Galarie;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		GalerieDao gestionGalerie = new GalerieDao();
		int choix;
		try {
			do {
				System.out.println(
						"*************************************    Fonctionnalités de base    *************************************");
				System.out.println("1- Crée une galerie");
				System.out.println("2- Afficher les peintures");
				System.out.println("3- Afficher les scluptures");
				System.out.println("4- Cherche un oeuvre");
				System.out.println("5- Cherche un oeuvre entre deux prix");
				System.out.println("0- Quitter");
				System.out.println(
						"*************************************    Fonctionnalités optionnels    *************************************");
				System.out.println("6- Modifier une galerie");
				System.out.println("7- Supprimer une galerie");
				System.out.println(
						"*************************************    Taper votre choix    *************************************");
				choix = sc.nextInt();
				switch (choix) {
				case 1: {
					System.out.println("Tapez le nom du galerie");
					String nom = sc.next();
					System.out.println("Tapez l'adresse du galerie");
					String adresse = sc.next();
					Galerie galiere = new Galerie(nom, adresse);
					int choix2;
					do {
						System.out.println("1- Ajouter Sclupture");
						System.out.println("2- Ajouter Peinture");
						System.out.println("3- Enregistrer la galerie");
						System.out.println("Tapez votre choix");
						choix2 = sc.nextInt();
						switch (choix2) {
						case 1: {
							Sculpture sculpture1 = new Sculpture("sculpture1", new Date().toString(), "histoire",
									2500.0, false, "ghaith", 16.2, 15.0, "bois");
							galiere.ajouterSculpture(sculpture1);
							break;
						}
						case 2: {
							Peinture peinture1 = new Peinture("peinture1", new Date().toString(), "histoire", 150.0,
									false, "seif", 16.2);
							galiere.ajouterPeinture(peinture1);
							break;
						}
						case 3: {
							gestionGalerie.createGalerieMongoDB(galiere);
							break;
						}
						}
					} while (choix2 != 3);
					break;
				}
				case 2: {
					System.out.println("Saisir le nom du galerie");
					String nom = sc.next();
					List<DBObject> oeuvres = gestionGalerie.findOeuvreTypeGalerieMongoDB(nom, "peinture");
					// ... Affichage des oeuvres
					System.out.println("[");
					for (DBObject oeuvre : oeuvres) {
						System.out.println(" {");
						System.out.println("  nom: " + oeuvre.get("nom"));
						System.out.println("  date_creation: " + oeuvre.get("date_creation"));
						System.out.println("  theme: " + oeuvre.get("theme"));
						System.out.println("  prix: " + oeuvre.get("prix"));
						System.out.println("  vendu: " + oeuvre.get("vendu"));
						System.out.println("  createur: " + oeuvre.get("createur"));
						if (oeuvre.get("type").equals("peinture")) {
							System.out.println("  taille: " + oeuvre.get("taille"));
							System.out.println("  type: " + oeuvre.get("type"));
						} else if (oeuvre.get("type").equals("sculpture")) {
							System.out.println("  volume: " + oeuvre.get("volume"));
							System.out.println("  poids: " + oeuvre.get("poids"));
							System.out.println("  matiere_utiliser: " + oeuvre.get("matiere_utiliser"));
							System.out.println("  type: " + oeuvre.get("type"));
						}
						System.out.println(" },");
					}
					// System.out.println(oeuvres);
					break;
				}
				case 3: {
					System.out.println("Saisir le nom du galerie");
					String nom = sc.next();
					List<DBObject> oeuvres = gestionGalerie.findOeuvreTypeGalerieMongoDB(nom, "sculpture");
					// ... Affichage des oeuvres
					System.out.println("[");
					for (DBObject oeuvre : oeuvres) {
						System.out.println(" {");
						System.out.println("  nom: " + oeuvre.get("nom"));
						System.out.println("  date_creation: " + oeuvre.get("date_creation"));
						System.out.println("  theme: " + oeuvre.get("theme"));
						System.out.println("  prix: " + oeuvre.get("prix"));
						System.out.println("  vendu: " + oeuvre.get("vendu"));
						System.out.println("  createur: " + oeuvre.get("createur"));
						if (oeuvre.get("type").equals("peinture")) {
							System.out.println("  taille: " + oeuvre.get("taille"));
							System.out.println("  type: " + oeuvre.get("type"));
						} else if (oeuvre.get("type").equals("sculpture")) {
							System.out.println("  volume: " + oeuvre.get("volume"));
							System.out.println("  poids: " + oeuvre.get("poids"));
							System.out.println("  matiere_utiliser: " + oeuvre.get("matiere_utiliser"));
							System.out.println("  type: " + oeuvre.get("type"));
						}
						System.out.println(" },");
					}
					System.out.println("]");
					// System.out.println(oeuvres);
					break;
				}
				case 4: {
					String nomOeuvre = "";
					try {
						System.out.println("Saisir le nom du galerie");
						String nomGalerie = sc.next();
						System.out.println("Saisir le nom d'ouevre");
						nomOeuvre = sc.next();
						List<DBObject> oeuvres = gestionGalerie.findOeuvreNomGalerieMongoDB(nomOeuvre, nomGalerie);
						// ... Affichage des oeuvres
						System.out.println("[");
						for (DBObject oeuvre : oeuvres) {
							System.out.println(" {");
							System.out.println("  nom: " + oeuvre.get("nom"));
							System.out.println("  date_creation: " + oeuvre.get("date_creation"));
							System.out.println("  theme: " + oeuvre.get("theme"));
							System.out.println("  prix: " + oeuvre.get("prix"));
							System.out.println("  vendu: " + oeuvre.get("vendu"));
							System.out.println("  createur: " + oeuvre.get("createur"));
							if (oeuvre.get("type").equals("peinture")) {
								System.out.println("  taille: " + oeuvre.get("taille"));
								System.out.println("  type: " + oeuvre.get("type"));
							} else if (oeuvre.get("type").equals("sculpture")) {
								System.out.println("  volume: " + oeuvre.get("volume"));
								System.out.println("  poids: " + oeuvre.get("poids"));
								System.out.println("  matiere_utiliser: " + oeuvre.get("matiere_utiliser"));
								System.out.println("  type: " + oeuvre.get("type"));
							}
							System.out.println(" },");
						}
						// System.out.println(oeuvres);
						/*
						 * RechercheThread t1 = new RechercheThread("peinture",nomOeuvre,nomGalerie);
						 * RechercheThread t2 = new RechercheThread("sculpture",nomOeuvre,nomGalerie);
						 * t1.start(); t2.start();
						 */
					} catch (Exception e) {
						System.out.println(e.getMessage() + nomOeuvre);
					}
					break;
				}
				case 5: {
					System.out.println("Saisir le nom du galerie");
					String nomGalerie = sc.next();
					System.out.println("Saisir le prix min");
					double min = sc.nextInt();
					System.out.println("Saisir le prix max");
					double max = sc.nextInt();
					/*
					 * List<DBObject> oeuvres= gestionGalerie.findOeuvrePrixGalerieMongoDB(min,max,
					 * nomGalerie); System.out.println(oeuvres);
					 */
					// ... Affichage des oeuvres avec un thread
					RechercheThread t1 = new RechercheThread("peinture", min, max, nomGalerie);
					RechercheThread t2 = new RechercheThread("sculpture", min, max, nomGalerie);
					t1.start();
					t2.start();

					break;
				}
				case 6: {
					System.out.println("Saisir le nom du galerie à modifié");
					String nomGalerie = sc.next();
					System.out.println("Saisir le nouveau nom");
					String nomGalerieNouveau = sc.next();
					gestionGalerie.updateGalerieNomOrganisateurMongoDB(nomGalerie, nomGalerieNouveau);
					System.out.println("Galerie modifié de " + nomGalerie + " à " + nomGalerieNouveau);
					break;
				}
				case 7: {
					System.out.println("Saisir le nom du galerie à supprimé");
					String nomGalerie = sc.next();
					System.out.println("Voulez vous vraiment supprimer la galerie : " + nomGalerie + " [Oui/Non]?");
					String rep = sc.next();
					if (rep.equals("Oui")) {
						gestionGalerie.deleteGalerieNomOrganisateurMongoDB(nomGalerie);
						System.out.println("Galerie " + nomGalerie + " supprimé");
					} else
						System.out.println("Supression annulé");
					break;
				}
				}
			} while (choix != 0);
		} catch (Exception ex) {
			System.out.println(ex.getClass().getName() + " : " + ex.getMessage());
		}
	}
}
