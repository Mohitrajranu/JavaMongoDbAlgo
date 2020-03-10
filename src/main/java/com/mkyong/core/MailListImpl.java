package com.mkyong.core;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

public class MailListImpl implements MailListCheck{
	
	public boolean validMailBox(String username,String emailId) {

		MongoClientURI connectionString = null;
		MongoClient mongo = null;
		MongoDatabase db = null;
		MongoCollection<Document> table = null;
		MongoCursor<Document> cursor  =  null;
		MongoCursor<Document> subcursor  =  null;
		boolean existflag = false;
		try {
			System.setProperty("javax.net.ssl.trustStore","/etc/ssl/firstTrustStore");
			System.setProperty("javax.net.ssl.trustStorePassword","bizlem123");
			System.setProperty ("javax.net.ssl.keyStore","/etc/ssl/MongoClientKeyCert.jks");
			System.setProperty ("javax.net.ssl.keyStorePassword","bizlem123");
			String uri = "mongodb://localhost:27017/?ssl=true";
			//String uri = "mongodb://localhost:27017";
			connectionString = new MongoClientURI(uri);
			mongo = new MongoClient(connectionString);
		    db = mongo.getDatabase("mailcheckerlist");
			table = db.getCollection("validmaillist");
			String col_name = "username", srch_string = username;
			/*Bson filter =  Filters.eq(col_name, srch_string);
			Bson unwind = new Document("$unwind", "$emaillist");
		    Bson match = new Document("$match", new Document(
		            "emaillist.email", emailId));
		    List<Bson> pipeline = Arrays.asList(filter,unwind, match);
		    AggregateIterable<Document> output = table.aggregate(pipeline);
		   for(Document doc:output){
			   System.out.println(doc.get("email"));
		   }*/
			/*
			db.filterArray.aggregate({ $match:{_id:ObjectId("5c6d63f2734e98fc0a434aeb")}}, {
$unwind:'$L'}, {$match:{'L.N':{$gt:3}}},
{$group:{_id:'$_id',subDocument:{$push:'$L.N'}}}).pretty();
			*/
			
	       FindIterable<Document> fi = table.find(Filters.eq(col_name, srch_string)).limit(100);      
	        cursor = fi.iterator();
			
			while (cursor.hasNext()) {
				cursor.next().toJson();	
				Document statusQuery = new Document("email", emailId);
			    Document fields = new Document("$elemMatch", statusQuery);
			    Document query = new Document("emaillist",fields);
			    FindIterable<Document> subfi=   table.find(query);
			    subcursor = subfi.iterator();
			    while (subcursor.hasNext()) {
			    subcursor.next().toJson();	
				existflag = true;
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
         finally{
        	 if(null != cursor){
        	 cursor.close();
        	 }
        	 if(null != subcursor){
        		 subcursor.close();
            	 }
        	 if(null !=mongo){
        	 mongo.close();
        	 }
        }
		return existflag;
	}

	
	public boolean invalidMailBox(String username,String emailId) {

		MongoClientURI connectionString = null;
		MongoClient mongo = null;
		MongoDatabase db = null;
		MongoCollection<Document> table = null;
		MongoCursor<Document> cursor  =  null;
		MongoCursor<Document> subcursor  =  null;
		boolean existflag = false;
		try {

			
			/*System.setProperty("javax.net.ssl.trustStore","/etc/ssl/firstTrustStore");
			System.setProperty("javax.net.ssl.trustStorePassword","bizlem123");
			System.setProperty ("javax.net.ssl.keyStore","/etc/ssl/MongoClientKeyCert.jks");
			System.setProperty ("javax.net.ssl.keyStorePassword","bizlem123");
			String uri = "mongodb://localhost:27017/?ssl=true";*/
			String uri = "mongodb://localhost:27017";
			connectionString = new MongoClientURI(uri);
			mongo = new MongoClient(connectionString);
		    db = mongo.getDatabase("mailcheckerlist");
			table = db.getCollection("invalidmaillist");
			String col_name = "username", srch_string = username;
			
	       FindIterable<Document> fi = table.find(Filters.eq(col_name, srch_string));      
	        cursor = fi.iterator();
			
			while (cursor.hasNext()) {
				cursor.next().toJson();	
				Document statusQuery = new Document("email", emailId);
			    Document fields = new Document("$elemMatch", statusQuery);
			    Document query = new Document("emaillist",fields);
			    FindIterable<Document> subfi=   table.find(query);
			    subcursor = subfi.iterator();
			    while (subcursor.hasNext()) {
			    subcursor.next().toJson();	
				existflag = true;
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
         finally{
        	 if(null != cursor){
        	 cursor.close();
        	 }
        	 if(null != subcursor){
        		 subcursor.close();
            	 }
        	 if(null !=mongo){
        	 mongo.close();
        	 }
        }
		return existflag;
	}
	
	public void insermailList(String username, String emailId,String collection) {
		MongoClientURI connectionString = null;
		MongoClient mongo = null;
		MongoDatabase db = null;
		MongoCollection<Document> table = null;
		try {
			System.setProperty("javax.net.ssl.trustStore","/etc/ssl/firstTrustStore");
			System.setProperty("javax.net.ssl.trustStorePassword","bizlem123");
			System.setProperty ("javax.net.ssl.keyStore","/etc/ssl/MongoClientKeyCert.jks");
			System.setProperty ("javax.net.ssl.keyStorePassword","bizlem123");
			String uri = "mongodb://localhost:27017/?ssl=true";
			//String uri = "mongodb://localhost:27017";
			connectionString = new MongoClientURI(uri);
			mongo = new MongoClient(connectionString);
		    db = mongo.getDatabase("mailcheckerlist");
			table = db.getCollection(collection);
			Document emaillist = new Document().append("email", emailId);
			table.updateOne(Filters.eq("username", username),Updates.addToSet("emaillist", emaillist),new UpdateOptions().upsert(true));
	       
		} catch (Exception e) {
			e.printStackTrace();
		} 
         finally{
        	
        	 if(null !=mongo){
        	 mongo.close();
        	 }
        }
	}

	public static void main(String[] args) {
		try {
			MailListImpl mi = new MailListImpl();
			boolean flag = mi.invalidMailBox("ranu_raj", "mohitraj.ranu@outlook.com");
			System.out.println(flag);
			//mi.insermailList("mohit_raj", "mohitraj.ranu@outlook.com", "invalidmaillist");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
