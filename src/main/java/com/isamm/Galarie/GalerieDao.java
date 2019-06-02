package com.isamm.Galarie;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class GalerieDao {

	// Partie MongoDB
	// ... Create Galerie
	public Galerie createGalerieMongoDB(Galerie galerie) {
		try {

			// MongoDB connection.
			MongoClient mongo = new MongoClient("localhost", 27017);

			// Get the database.
			DB db = mongo.getDB("GalerieArt");
			DBCollection table = db.getCollection("galerie");

			// Ajouter une galerie dans la collection galerie
			BasicDBObject document = new BasicDBObject();
			document.put("nom_organisateur", galerie.getNom_organisateur());
			document.put("adresse", galerie.getAdresse());
			document.put("nb_oeuvre", galerie.getNb_oeuvre());

			// Une suite des instructions utilister pour serialize un table des owuvres afin
			// de les enregistre
			// ...dans la base mongodb
			// ... debut serialization
			List<Object> oeuvresDBList = new BasicDBList();
			for (Oeuvre oeuvre : galerie.getOeuvres()) {
				if (oeuvre != null) {
					DBObject oeuvreDBObject = new BasicDBObject();
					oeuvreDBObject.put("nom", oeuvre.getNom());
					oeuvreDBObject.put("date_creation", oeuvre.getDate_creation());
					oeuvreDBObject.put("theme", oeuvre.getTheme());
					oeuvreDBObject.put("prix", oeuvre.getPrix());
					oeuvreDBObject.put("vendu", oeuvre.isVendue());
					oeuvreDBObject.put("createur", oeuvre.getCreateur());
					if (oeuvre instanceof Peinture) {
						oeuvreDBObject.put("taille", ((Peinture) oeuvre).getTaille());
						oeuvreDBObject.put("type", "peinture");
					} else {
						oeuvreDBObject.put("volume", ((Sculpture) oeuvre).getVolume());
						oeuvreDBObject.put("poids", ((Sculpture) oeuvre).getPoids());
						oeuvreDBObject.put("matiere_utiliser", ((Sculpture) oeuvre).getMatiere_utiliser());
						oeuvreDBObject.put("type", "sculpture");
					}
					oeuvresDBList.add(oeuvreDBObject);

				}
			}
			document.put("oeuvers", oeuvresDBList);
			// ... fin serialization

			document.put("nb_max", galerie.getNb_max());
			table.insert(document);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return galerie;
	}

	// ... Update Galerie
	public void updateGalerieNomOrganisateurMongoDB(String nom_organisateur, String nom_organisateur_updated) {
		try {

			// MongoDB connection.
			MongoClient mongo = new MongoClient("localhost", 27017);

			// Get the database.
			DB db = mongo.getDB("GalerieArt");
			DBCollection table = db.getCollection("galerie");

			// Modifier une galerie dans la base mongodb dont le nom_organisateur est
			// "seifallahmedini1"
			// ... La requete
			BasicDBObject query = new BasicDBObject();
			query.put("nom_organisateur", nom_organisateur);

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("nom_organisateur", nom_organisateur_updated);

			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);

			table.update(query, updateObj);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ... Find Galerie
	public Galerie findGalerieNomOrganisateurMongoDB(Galerie galerie, String nom_organisateur) {
		try {

			// MongoDB connection.
			MongoClient mongo = new MongoClient("localhost", 27017);

			// Get the database.
			DB db = mongo.getDB("GalerieArt");
			DBCollection table = db.getCollection("galerie");

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom_organisateur", nom_organisateur);

			DBCursor cursor = table.find(searchQuery);

			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return galerie;
	}

	// ... Delete Galerie
	public void deleteGalerieNomOrganisateurMongoDB(String nom_organisateur) {
		try {

			// MongoDB connection.
			MongoClient mongo = new MongoClient("localhost", 27017);

			// Get the database.
			DB db = mongo.getDB("GalerieArt");
			DBCollection table = db.getCollection("galerie");

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom_organisateur", nom_organisateur);

			table.remove(searchQuery);
			System.out.println(
					"galerie dont le nom de l'organisateur est " + nom_organisateur + " est supprimer avec succes");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ... Find liste des peintures ou des sculptures
	public List<DBObject> findOeuvreTypeGalerieMongoDB(String nom_organisateur, String type) {
		List<DBObject> oeuvres = new ArrayList<DBObject>();
		try {

			// MongoDB connection.
			MongoClient mongo = new MongoClient("localhost", 27017);

			// Get the database.
			DB db = mongo.getDB("GalerieArt");

			DBCollection table = db.getCollection("galerie");

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom_organisateur", nom_organisateur);

			DBCursor cursor = table.find(searchQuery);

			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				List<DBObject> oeuv = (List<DBObject>) obj.get("oeuvers");
				for (DBObject o : oeuv) {
					if (o.get("type").equals(type)) {
						// System.out.println(o);
						oeuvres.add(o);
					}
				}
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oeuvres;
	}

	// ... Find liste des peintures ou des sculptures filtrage avec le nom
	public List<DBObject> findOeuvreNomGalerieMongoDB(String nomOeuvre, String nom_organisateur)
			throws NotExistException {
		List<DBObject> oeuvres = new ArrayList<DBObject>();
		try {

			// MongoDB connection.
			MongoClient mongo = new MongoClient("localhost", 27017);

			// Get the database.
			DB db = mongo.getDB("GalerieArt");

			DBCollection table = db.getCollection("galerie");

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom_organisateur", nom_organisateur);

			DBCursor cursor = table.find(searchQuery);

			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				List<DBObject> oeuv = (List<DBObject>) obj.get("oeuvers");
				for (DBObject o : oeuv) {
					if (o.get("nom").equals(nomOeuvre)) {
						// System.out.println(o);
						oeuvres.add(o);
					}
				}
			}
			if (oeuvres.size() == 0)
				throw new NotExistException();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oeuvres;
	}

	// ... Find liste des peintures ou des sculptures le prix
	public List<DBObject> findOeuvrePrixGalerieMongoDB(double p1, double p2, String nom_organisateur) {
		List<DBObject> oeuvres = new ArrayList<DBObject>();
		try {

			// MongoDB connection.
			MongoClient mongo = new MongoClient("localhost", 27017);

			// Get the database.
			DB db = mongo.getDB("GalerieArt");

			DBCollection table = db.getCollection("galerie");

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom_organisateur", nom_organisateur);

			DBCursor cursor = table.find(searchQuery);

			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				List<DBObject> oeuv = (List<DBObject>) obj.get("oeuvers");
				for (DBObject o : oeuv) {
					double p = (Double) o.get("prix");
					if (p > p1 && p < p2) {
						System.out.println(o);
						oeuvres.add(o);
					}
				}
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oeuvres;
	}
	
//	public synchronized void rechercheThread(String type,double min,double max,String nom_organisateur) {
//		List<DBObject> oeuvres = new ArrayList<DBObject>();
//		try {
//
//			// MongoDB connection.
//			MongoClient mongo = new MongoClient("localhost", 27017);
//
//			// Get the database.
//			DB db = mongo.getDB("GalerieArt");
//
//			DBCollection table = db.getCollection("galerie");
//
//			BasicDBObject searchQuery = new BasicDBObject();
//			searchQuery.put("nom_organisateur", nom_organisateur);
//
//			DBCursor cursor = table.find(searchQuery);
////			System.out.println("[");
//			while (cursor.hasNext()) {
//				DBObject obj = cursor.next();
//				List<DBObject> oeuv = (List<DBObject>) obj.get("oeuvers");
//
//				for (DBObject o : oeuv) {
//					double prix = Double.parseDouble(o.get("prix").toString());
//
//					if (prix >= min && prix <= max) {
//						String nom = o.get("nom").toString();
//						String date = o.get("date_creation").toString();
//						String theme = o.get("theme").toString();
//						String createur = o.get("createur").toString();
//						if (o.get("type").equals(type) && type.equals("sculpture")) {
//							double volume = Double.parseDouble(o.get("volume").toString());
//							double poids = Double.parseDouble(o.get("poids").toString());
//							String matiere = o.get("matiere_utiliser").toString();
//							System.out.println(" {");
//							System.out.println("  nom: "+nom);
//							System.out.println("  date_creation: "+date);
//							System.out.println("  theme: "+theme);
//							System.out.println("  prix: "+prix);
//							System.out.println("  vendu: "+false);
//							System.out.println("  createur: "+createur);
//							System.out.println("  volume: "+volume);
//							System.out.println("  poids: "+poids);
//							System.out.println("  matiere: "+matiere);
//							System.out.println(" },");
////							System.out.println(new Sculpture(nom, date, theme, prix, false,createur, volume, poids, matiere).toString());
//						} else if (o.get("type").equals(type) && type.equals("peinture")) {
//							double taille = Double.parseDouble(o.get("taille").toString());
//							System.out.println(" {");
//							System.out.println("  nom: "+nom);
//							System.out.println("  date_creation: "+date);
//							System.out.println("  theme: "+theme);
//							System.out.println("  prix: "+prix);
//							System.out.println("  vendu: "+false);
//							System.out.println("  createur: "+createur);
//							System.out.println("  taille: "+taille);
//							System.out.println(" },");
////							System.out.println(new Peinture(nom, date, theme, prix, false, createur, taille).toString());
//						}
//						
//
//					}
//				}
//				/*
//				 * if(oeuvres.size()==0) throw new NotExistException();
//				 */
//			}
////			System.out.println("]");
//
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
