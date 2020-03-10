package com.mkyong.core;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;

public class MongoArrayInsert {

	public static void main(String[] args) {
  // insertMongo();
	updateMongo();
	}
	
	public Map<String,JSONArray> editFunnel(String destinationFunnel,String category) {
		MongoClient mongoClient = null;
		 MongoDatabase database  = null;
		 MongoCollection<Document> collection = null;
		 MongoCursor<Document> cursor  =  null;
		 MongoClientURI connectionString = null;
		 Map<String,JSONArray> respJson=null;
		 String uri = null;
		 JSONArray datajson = null;
		 try {
				System.setProperty("javax.net.ssl.trustStore","/etc/ssl/firstTrustStore");
				System.setProperty("javax.net.ssl.trustStorePassword","bizlem123");
				System.setProperty ("javax.net.ssl.keyStore","/etc/ssl/MongoClientKeyCert.jks");
				System.setProperty ("javax.net.ssl.keyStorePassword","bizlem123");
				uri = "mongodb://localhost:27017/?ssl=true";
			   connectionString = new MongoClientURI(uri);
			   mongoClient = new MongoClient(connectionString);
			   database = mongoClient.getDatabase("salesautoconvert");
			   collection=database.getCollection("FirstCategoryMails");
			   respJson = new WeakHashMap<String, JSONArray>();
			   datajson = new JSONArray();
			   Bson query = and(eq("Parentfunnel", destinationFunnel), eq("Category", category));
			   FindIterable<Document> fi = collection.find(query);      
		        cursor = fi.iterator();
				while (cursor.hasNext()) {
					JSONObject obj=new JSONObject(cursor.next().toJson());	
					datajson.put(obj);
					}
				
				respJson.put(destinationFunnel, datajson);
			   
		} catch (Exception e) {
			if(null!=respJson){
			respJson.clear();
			}
		}
		finally{
			if(null!=cursor){
				cursor.close();
				cursor = null;
				}
			if(null!=mongoClient){
			mongoClient.close();
			mongoClient = null;
			}
		}
		
		return respJson;
	}
	
	
	public String moveContacts(String sourceFunnel,String destinationFunnel,String dataArr) {
		MongoClient mongoClient = null;
		 MongoDatabase database  = null;
		 MongoCollection<Document> collection = null;
		// MongoCursor<Document> cursor  =  null;
		 MongoClientURI connectionString = null;
		 String uri = null;
		 String res=null;
		 try {
				System.setProperty("javax.net.ssl.trustStore","/etc/ssl/firstTrustStore");
				System.setProperty("javax.net.ssl.trustStorePassword","bizlem123");
				System.setProperty ("javax.net.ssl.keyStore","/etc/ssl/MongoClientKeyCert.jks");
				System.setProperty ("javax.net.ssl.keyStorePassword","bizlem123");
				uri = "mongodb://localhost:27017/?ssl=true";
			   connectionString = new MongoClientURI(uri);
			   mongoClient = new MongoClient(connectionString);
			   database = mongoClient.getDatabase("salesautoconvert");
			   collection=database.getCollection("FirstCategoryMails");
			  /* Bson condition = new Document("$eq", sourceFunnel);
			  
				Bson filter = new Document("funnelName", condition).append("Category", "Explore");
				
				FindIterable<Document> fi = collection.find(filter);      
		        cursor = fi.iterator();*/
		        //Bson conditionMove = new Document("$eq", destinationFunnel);
			   Bson query = and(eq("funnelName", destinationFunnel), eq("Category", "Explore"));
				//Bson query = new Document("funnelName", destinationFunnel).append("Category", "Explore");
			/*	while (cursor.hasNext()) {*/
				//	JSONArray arrdata= new JSONArray();
					/*JSONObject obj=new JSONObject(cursor.next().toJson());	*/
				JSONObject obj=new JSONObject(dataArr);
					if(obj.has("Contacts")){
						JSONArray arr=	obj.getJSONArray("Contacts");
						//scheduleTime
						 
						for(int k=0;k<arr.length();k++){
							JSONObject contact= arr.getJSONObject(k);
							Document newContact = Document.parse(contact.toString());
						/*Document newAddress = new Document().append("type", "secondaryAddress")
								.append("street", "#24 niceton")
								.append("city","Nice");*/

						collection.updateOne(query,Updates.addToSet("Contacts", newContact));
						
						}
						Document newDocument = new Document();
						newDocument.put("updateflag", "1");

						Document updateObj = new Document();
						updateObj.put("$set", newDocument);

						collection.updateOne(query, updateObj);
					}
					
					
				/*	}*/
				
				//collection.deleteOne(filter);
				res = "Success";
			   
		} catch (Exception e) {
			res = "Fail";
		}
		finally{
			/*if(null!=cursor){
				cursor.close();
				cursor = null;
				}*/
			if(null!=mongoClient){
			mongoClient.close();
			mongoClient = null;
			}
		}
		
		return res;
	}
	
	private static void insertMongo() {
		MongoClientURI connectionString = null;
		MongoClient mongo = null;
		MongoDatabase db = null;
		MongoCollection<Document> table = null;
		Document testObject = new Document();
		try{
			String uri = "mongodb://localhost:27017";
			connectionString = new MongoClientURI(uri);
			mongo = new MongoClient(connectionString);
			db = mongo.getDatabase("arraydata");
			table = db.getCollection("datalist");
			testObject.put("ID", "12");
			testObject.put("scheduleTime", "3:18");
			testObject.put("updateFlag", "1");
			testObject.put("testname", "test1");         
			List<Document> ObjData = new ArrayList<Document>();
			Document doc1 = new Document();
			doc1.put("Order", "2333");
			doc1.put("OrderName", "Ord1");
			doc1.put("OrderType", "sales");
			ObjData.add(doc1);
			Document doc2 = new Document();
			doc2.put("Order", "3333");
			doc2.put("OrderName", "Ord2");
			doc2.put("OrderType", "marketing");
			ObjData.add(doc2);
			Document doc3 = new Document();
			doc3.put("Order", "4333");
			doc3.put("OrderName", "Ord3");
			doc3.put("OrderType", "it");
			ObjData.add(doc3);
			Document doc4 = new Document();
			doc4.put("Order", "5333");
			doc4.put("OrderName", "Ord4");
			doc4.put("OrderType", "hr");
			ObjData.add(doc4);
			testObject.put("ObjData", ObjData);
			//testObject.put("tempObjData", ObjData);
			table.insertOne(testObject);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
       	 
       	 if(null !=mongo){
       	 mongo.close();
       	 }
       }
	}
	private static void getMailSentCount(String CreatedBy, String funnelName,String subCategory,String mailFlag){
		MongoClientURI connectionString = null;
		MongoClient mongo = null;
		MongoDatabase db = null;
		MongoCollection<Document> table = null;
		MongoCursor<Document> cursor  =  null;
		
		try{
			System.setProperty("javax.net.ssl.trustStore","/etc/ssl/firstTrustStore");
			System.setProperty("javax.net.ssl.trustStorePassword","bizlem123");
			System.setProperty ("javax.net.ssl.keyStore","/etc/ssl/MongoClientKeyCert.jks");
			System.setProperty ("javax.net.ssl.keyStorePassword","bizlem123");
			String uri = "mongodb://localhost:27017/?ssl=true";
			//String uri = "mongodb://localhost:27017";
			connectionString = new MongoClientURI(uri);
			mongo = new MongoClient(connectionString);
			db = mongo.getDatabase("salesautoconvert");
			table = db.getCollection("FirstCategoryMails");
			/*
			db.FirstCategoryMails.aggregate({$match:{"funnelName" : "PersonalContacts","updateflag" : "2",
			"Category" : "Explore"}},{ $unwind: "$Datasource" },
			{ $group: { id: "", count: { $sum: 1 } } },{ $project: { id: 0, count: 1 } });
			*/
			BasicDBObject unwind = new BasicDBObject("$unwind", "$Datasource");
			BasicDBObject match = new BasicDBObject("$match", new BasicDBObject(
					"CreatedBy", CreatedBy).append("funnelName", funnelName).append("Category", subCategory).append("updateflag", mailFlag));
			BasicDBObject project = new BasicDBObject("$project", new BasicDBObject(
		            "_id", 0).append("count", 1));
		    List<BasicDBObject> pipeline = Arrays.asList(unwind, match, project);
		    AggregationOutput output = (AggregationOutput) table.aggregate(pipeline);
		    Iterable<DBObject> results = output.results();
		    for (DBObject result : results) {
		        System.out.println(result.get("count").toString());
		    }
			
		}catch(Exception e){
		//	e.printStackTrace();
		}
		finally{
			 if(null != cursor){
	        	 cursor.close();
	        	 }
       	 if(null !=mongo){
       	 mongo.close();
       	 }
       }
		
	
		
	}
	
	private static void unsubscribeList(String mailTemplate,String unSubscribeEmail){
		MongoClientURI connectionString = null;
		MongoClient mongo = null;
		MongoDatabase db = null;
		MongoCollection<Document> table = null;
		MongoCursor<Document> cursor  =  null;
		try{
			System.setProperty("javax.net.ssl.trustStore","/etc/ssl/firstTrustStore");
			System.setProperty("javax.net.ssl.trustStorePassword","bizlem123");
			System.setProperty ("javax.net.ssl.keyStore","/etc/ssl/MongoClientKeyCert.jks");
			System.setProperty ("javax.net.ssl.keyStorePassword","bizlem123");
			String uri = "mongodb://localhost:27017/?ssl=true";
			//String uri = "mongodb://localhost:27017";
			connectionString = new MongoClientURI(uri);
			mongo = new MongoClient(connectionString);
			db = mongo.getDatabase("salesautoconvert");
			table = db.getCollection("FirstCategoryMails");
			String col_name = "Campaign_id", srch_string = mailTemplate;
		       FindIterable<Document> fi = table.find(Filters.eq(col_name, srch_string));      
		        cursor = fi.iterator();
				while (cursor.hasNext()) {
				//	JSONArray arrdata= new JSONArray();
					JSONObject obj=new JSONObject(cursor.next().toJson());	
					//JSONArray arr=	obj.getJSONArray("Datasource");
					//scheduleTime
					BasicDBObject query = new BasicDBObject();
					query.put("CreatedBy", obj.getString("CreatedBy"));
					query.put("funnelName", obj.getString("funnelName"));
					query.put("group", obj.getString("group"));
					query.put(col_name, mailTemplate);
				    BasicDBObject fields = new BasicDBObject("Datasource", 
				        new BasicDBObject( "email", unSubscribeEmail));
				    BasicDBObject update = new BasicDBObject("$pull",fields);
				    table.updateOne(query, update);
					}
				//table.findOne(Filters.eq(col_name, srch_string)).;
				//db.FirstCategoryMails.findOne({  "funnelName" :"PersonalContacts"}).Datasource.length;		
			
		}catch(Exception e){
		//	e.printStackTrace();
		}
		finally{
			 if(null != cursor){
	        	 cursor.close();
	        	 }
       	 if(null !=mongo){
       	 mongo.close();
       	 }
       }
		
	}
	
	
	private static void updateMongo() {
		MongoClientURI connectionString = null;
		MongoClient mongo = null;
		MongoDatabase db = null;
		MongoCollection<Document> table = null;
		MongoCursor<Document> cursor  =  null;
		BasicDBList productList = null;
		try{
			String uri = "mongodb://localhost:27017";
			connectionString = new MongoClientURI(uri);
			mongo = new MongoClient(connectionString);
			db = mongo.getDatabase("arraydata");
			table = db.getCollection("datalist");
			String col_name = "ID", srch_string = "12";
			
		       FindIterable<Document> fi = table.find(Filters.eq(col_name, srch_string));      
		        cursor = fi.iterator();
				
				while (cursor.hasNext()) {
					JSONArray arrdata= new JSONArray();
					JSONObject obj=new JSONObject(cursor.next().toJson());	
					JSONArray arr=	obj.getJSONArray("ObjData");
					productList = null;
					//scheduleTime
					
					productList = new BasicDBList();
					for(int i=0;i<arr.length();i++){
						JSONObject sub = arr.getJSONObject(i);
						
						if(i<3){
							arrdata.put(sub);
							BasicDBObject dbObject = (BasicDBObject) JSON
									.parse(sub.toString());
							productList.add(dbObject);
							// for the provided template name get created by, Funnel name, Group and fetch the primary key 
							BasicDBObject query = new BasicDBObject("ID", "12");
						    BasicDBObject fields = new BasicDBObject("ObjData", 
						        new BasicDBObject( "Order", sub.get("Order")));
						    BasicDBObject update = new BasicDBObject("$pull",fields);
						    /*
						    BasicDBObject listItem = new BasicDBObject("scores", new BasicDBObject("type","quiz").append("score",99));
						    BasicDBObject updateQuery = new BasicDBObject("$push", listItem);
						    table.updateOne(query, updateQuery);
						    Bson query = new Document("ID", "12");
						Bson update =  new Document("$pull",
						                new Document("ObjData.Order", sub.get("Order"))
						        
						);
						*/
						    table.updateOne(query, update);
						    if(arr.length()==1){
						    	StringBuilder cuurTime = updateScheduleTime(obj);
								
								BasicDBObject newDocument = new BasicDBObject();
								newDocument.put("scheduleTime", cuurTime.toString());

								BasicDBObject updateObj = new BasicDBObject();
								updateObj.put("$set", newDocument);

								table.updateOne(query, updateObj);
						    }
						   
						   
						//db.collection.update( { field: <query> }, { $pull: { field: <query> } } );
						}
						else {
							System.out.println("Eligible datasource "+arrdata.toString());
							//update schedule time increasing it to one hour.
							StringBuilder cuurTime = updateScheduleTime(obj);
							BasicDBObject query = new BasicDBObject();
							query.put("ID", "12");

							BasicDBObject newDocument = new BasicDBObject();
							newDocument.put("scheduleTime", cuurTime.toString());

							BasicDBObject updateObj = new BasicDBObject();
							updateObj.put("$set", newDocument);

							table.updateOne(query, updateObj);

							break;
						}
						
						
					}
					if(!(arrdata.isEmpty())){
					BasicDBObject query = new BasicDBObject("ID", "12");
					// BasicDBObject listItem = new BasicDBObject("tempObjData",arrdata);new BasicDBObject("simulatedProduct", productList)
					   BasicDBObject updateQuery = new BasicDBObject("$push", new BasicDBObject("tempObjData", productList));
					    table.updateOne(query, updateQuery,new UpdateOptions().upsert(true));
					}
					System.out.println("Moved datasource "+arrdata.toString());
					if(arr.length()==1){
						System.out.println("No data found");
						//update flag to 2
						
						BasicDBObject query = new BasicDBObject("ID", "12");

						BasicDBObject newDocument = new BasicDBObject();
						newDocument.put("updateFlag", "2");

						BasicDBObject updateObj = new BasicDBObject();
						updateObj.put("$set", newDocument);

						table.updateOne(query, updateObj);
						break;
					}
					
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if(null != cursor){
	        	 cursor.close();
	        	 }
       	 if(null !=mongo){
       	 mongo.close();
       	 }
       }
	}


	private static StringBuilder updateScheduleTime(JSONObject obj) {
		StringBuilder schtime=new StringBuilder(obj.getString("scheduleTime"));
		String[] split = schtime.toString().split(":", -1);
		StringBuilder cuurTime= new StringBuilder();
		
		int currScheduletime = Integer.parseInt(split[0]) + 1;
		if(currScheduletime >= 24){
			currScheduletime =1;
		}
		cuurTime.append(currScheduletime);
		cuurTime.append(":");
		cuurTime.append(split[1]);
		System.out.println("Schedule Time is "+cuurTime.toString());
		return cuurTime;
	}
	
}
