package com.poo.infraestructure;

import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigMongoClient {

    @Value("mongodb+srv://geovannacunha:Utft7V3ZEAYaDCDz@gestao-locadora.ih5iyzd.mongodb.net/?retryWrites=true&w=majority")
    private String connectionString;

    @Value("Gestao-Locadora")
    private String databaseName;

    private MongoClient mongoClient;

    public void connect() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(connectionString);
        }
    }

    /** 
     * @return MongoClient
     */
    public MongoClient getMongoClient() {
        if (mongoClient == null) {
            connect();
        }
        return mongoClient;
    }

    /** 
     * @param collectionName
     * @return MongoCollection<Document>
     */
    public MongoCollection<Document> getCollection(String collectionName) {
        MongoDatabase database = getMongoClient().getDatabase(databaseName);
        return database.getCollection(collectionName, Document.class);
    }
}
