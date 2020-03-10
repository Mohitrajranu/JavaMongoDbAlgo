package com.mkyong.core;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbCache {
 
	private final String uri = "mongodb://localhost:27017/?ssl=true";
    private  static MongoClientURI connectionString = null;
    private static  MongoClient mongo = null;
    private static volatile MongoDbCache instance = null;
	private MongoDbCache(){
		
		try{
		System.setProperty("javax.net.ssl.trustStore","/etc/ssl/firstTrustStore");
		System.setProperty("javax.net.ssl.trustStorePassword","bizlem123");
		System.setProperty ("javax.net.ssl.keyStore","/etc/ssl/MongoClientKeyCert.jks");
		System.setProperty ("javax.net.ssl.keyStorePassword","bizlem123");
		
		//String uri = "mongodb://localhost:27017";
		connectionString = new MongoClientURI(uri);
		mongo = new MongoClient(connectionString);
		}
		catch(Exception e){
			
		}
		
	}
	
	//MongoClient m = MongoDbCache.getInstance();
	
	
	// Utility method to get database instance
    private MongoDatabase getDB() {
        return mongo.getDatabase("test");
    }
 
    // Utility method to get user collection
    private MongoCollection<Document> getUserCollection() {
        return getDB().getCollection("user");
    }
 
	public static MongoDbCache getInstance() {
        // Double check locking principle.
        // If there is no instance available, create new one (i.e. lazy initialization).
        if (instance == null) {
 
            // To provide thread-safe implementation.
            synchronized(MongoDbCache.class) {
 
                // Check again as multiple threads can reach above step.
                if (instance == null) {
                    instance = new MongoDbCache();
                }
            }
        }
        return instance;
    }
    
	
}
