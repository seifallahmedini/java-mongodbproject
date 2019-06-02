/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isamm.Galarie;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class RechercheThread extends Thread {

	private String type;
	private double min;
	private double max;
	private String nom_organisateur;

	public RechercheThread(String type, double min, double max, String nom_organisateur) {
		this.type = type;
		this.min = min;
		this.max = max;
		this.nom_organisateur = nom_organisateur;
	}

	@Override
	public void run() {
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
//			System.out.println("[");
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				List<DBObject> oeuv = (List<DBObject>) obj.get("oeuvers");

				for (DBObject o : oeuv) {
					double prix = Double.parseDouble(o.get("prix").toString());

					if (prix >= min && prix <= max) {
						String nom = o.get("nom").toString();
						String date = o.get("date_creation").toString();
						String theme = o.get("theme").toString();
						String createur = o.get("createur").toString();
					
					
						if (o.get("type").equals(type) && type.equals("sculpture")) {
							double volume = Double.parseDouble(o.get("volume").toString());
							double poids = Double.parseDouble(o.get("poids").toString());
							String matiere = o.get("matiere_utiliser").toString();
							System.out.println(" {"+"\n  nom: "+nom+"\n  date_creation: "+date+"\n  theme: "+theme+"\n  prix: "+prix+"\n  vendu: "+false+"\n  createur: "+createur+"\n  volume: "+volume+"\n  poids: "+poids+"\n  matiere: "+matiere+"\n },");
//							System.out.println(new Sculpture(nom, date, theme, prix, false,createur, volume, poids, matiere).toString());
						} else if (o.get("type").equals(type) && type.equals("peinture")) {
							double taille = Double.parseDouble(o.get("taille").toString());
							System.out.println(" {"+"\n  nom: "+nom+"\n  date_creation: "+date+"\n  theme: "+theme+"\n  prix: "+prix+"\n  vendu: "+false+"\n  createur: "+createur+"\n  taille: "+taille+"\n },");
//							System.out.println(new Peinture(nom, date, theme, prix, false, createur, taille).toString());
						}
						

					}
				}
				/*
				 * if(oeuvres.size()==0) throw new NotExistException();
				 */
			}
//			System.out.println("]");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
