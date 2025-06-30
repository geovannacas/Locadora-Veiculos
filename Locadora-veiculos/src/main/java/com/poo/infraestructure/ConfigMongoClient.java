package com.poo.infraestructure;

import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigMongoClient {

    @Value("${MONGODB_CONN}")
    private String connectionString;

    @Value("${MONGODB_DB_NAME}")
    private String databaseName;

    private MongoClient mongoClient;

    public void connect() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(connectionString);
        }
    }

    public MongoClient getMongoClient() {
        if (mongoClient == null) {
            connect();
        }
        return mongoClient;
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        MongoDatabase database = getMongoClient().getDatabase(databaseName);
        return database.getCollection(collectionName, Document.class);
    }
}
