package com.mkyong.core;

import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


/**
 * Java + MongoDB Hello world Example
 * 
 */
public class App {
	public static void main(String[] args) {
		MongoClientURI connectionString = null;
		MongoClient mongo = null;
		DB db = null;
		DBCollection table = null;
		DBCursor cursor =  null;
		DBCursor cursor2 =  null;
		try {

			/**** Connect to MongoDB ****/
			// Since 2.10.0, uses MongoClient  "jdk.tls.trustNameService
			System.setProperty("javax.net.ssl.trustStore","/etc/ssl/firstTrustStore");
			System.setProperty("javax.net.ssl.trustStorePassword","bizlem123");
			System.setProperty ("javax.net.ssl.keyStore","/etc/ssl/MongoClientKeyCert.jks");
			System.setProperty ("javax.net.ssl.keyStorePassword","bizlem123");
			String uri = "mongodb://localhost:27017/?ssl=true";
				connectionString = new MongoClientURI(uri);
				mongo = new MongoClient(connectionString);
			//MongoClient mongo = new MongoClient(new ServerAddress("localhost", 27017),sslOptions);

			
			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
		    db = mongo.getDB("testdb");

			/**** Get collection / table from 'testdb' ****/
			// if collection doesn't exists, MongoDB will create it for you
			table = db.getCollection("user");

			/**** Insert ****/
			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("name", "mkyong");
			document.put("age", 30);
			document.put("createdDate", new Date());
			table.insert(document);

			/**** Find and display ****/
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", "mkyong");

			cursor = table.find(searchQuery).limit(100);

			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

			/**** Update ****/
			// search document where name="mkyong" and update it with new values
			BasicDBObject query = new BasicDBObject();
			query.put("name", "mkyong");

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("name", "mkyong-updated");

			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);

			table.update(query, updateObj);

			/**** Find and display ****/
			BasicDBObject searchQuery2 
				= new BasicDBObject().append("name", "mkyong-updated");

			cursor2 = table.find(searchQuery2);

			while (cursor2.hasNext()) {
				System.out.println(cursor2.next());
			}

			/**** Done ****/
			System.out.println("Done");

			mongo.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
         finally{
        	 cursor.close();
        	 cursor2.close();
        	 mongo.close();
        }
	}
}
