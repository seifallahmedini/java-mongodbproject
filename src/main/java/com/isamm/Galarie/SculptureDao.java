package com.isamm.Galarie;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class SculptureDao {
	// ... Create Sculpture
	public Sculpture createSculptureMongoDB(Sculpture sculpture) {
		try {

			// MongoDB connection.
			MongoClient mongo = new MongoClient("localhost", 27017);

			// Get the database.
			DB db = mongo.getDB("GalerieArt");
			DBCollection table = db.getCollection("sculpture");

			// Ajouter une sculpture dans la collection sculpture
			BasicDBObject document = new BasicDBObject();
			document.put("nom", sculpture.getNom());
			document.put("date_creation", sculpture.getDate_creation());
			document.put("prix", sculpture.getPrix());
			document.put("vendu", sculpture.isVendue());
			document.put("volume", sculpture.getVolume());
			document.put("poids", sculpture.getPoids());
			document.put("matiere_utiliser", sculpture.getMatiere_utiliser());
			table.insert(document);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sculpture;
	}

	// ... Delete Peinture
	public void deleteSculptureNomMongoDB(Sculpture sculpture, String nom) {
		try {

			// MongoDB connection.
			MongoClient mongo = new MongoClient("localhost", 27017);

			// Get the database.
			DB db = mongo.getDB("GalerieArt");
			DBCollection table = db.getCollection("sculpture");

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom", nom);

			table.remove(searchQuery);
			System.out.println("sculpture dont le nom est " + nom + " est supprimer avec succes");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
