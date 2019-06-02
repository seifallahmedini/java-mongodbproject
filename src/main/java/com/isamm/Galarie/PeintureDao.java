package com.isamm.Galarie;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class PeintureDao {
	// ... Create Peinture
	public Peinture createPeintureMongoDB(Peinture peinture) {
		try {

			// MongoDB connection.
			MongoClient mongo = new MongoClient("localhost", 27017);

			// Get the database.
			DB db = mongo.getDB("GalerieArt");
			DBCollection table = db.getCollection("peinture");

			// Ajouter une galerie dans la collection galerie
			BasicDBObject document = new BasicDBObject();
			document.put("nom", peinture.getNom());
			document.put("date_creation", peinture.getDate_creation());
			document.put("prix", peinture.getPrix());
			document.put("vendu", peinture.isVendue());
			document.put("taille", peinture.getTaille());
			table.insert(document);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return peinture;
	}

	// ... Delete Peinture
	public void deletePeintureNomMongoDB(Peinture peinture, String nom) {
		try {

			// MongoDB connection.
			MongoClient mongo = new MongoClient("localhost", 27017);

			// Get the database.
			DB db = mongo.getDB("GalerieArt");
			DBCollection table = db.getCollection("peinture");

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom", nom);

			table.remove(searchQuery);
			System.out.println("peinture dont le nom est " + nom + " est supprimer avec succes");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
