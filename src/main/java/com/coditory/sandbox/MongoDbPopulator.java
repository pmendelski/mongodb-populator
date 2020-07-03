package com.coditory.sandbox;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDbPopulator {
    public static void main(String[] args) {
        if (args.length != 3) {
            error("Expected two arguments: <uri> <collection> <count>");
        }
        String uri = args[0];
        String collection = args[1];
        int count = Integer.parseInt(args[2]);
        new MongoDbPopulator().populate(uri, collection, count);
    }

    private final DocumentGenerator documentGenerator = new DocumentGenerator();

    void populate(String uri, String collectionName, int count) {
        MongoCollection<Document> collection = getDatabase(uri).getCollection(collectionName);
        long size = collection.countDocuments();
        if (size > 0) {
            exit("Collection already exists. Size: " + size);
        }
        int n = count;
        while (n > 0) {
            int chunk = Math.min(1000, n);
            n = n - chunk;
            List<Document> documents = createDocuments(chunk);
            try {
                collection.insertMany(documents);
                stdout("Inserted documents: " + (count - n));
            } catch (Exception e) {
                stdout("Could not insert batch. Cause: " + e.getMessage());
            }
        }
    }

    private MongoDatabase getDatabase(String uri) {
        ConnectionString connectionString = new ConnectionString(uri);
        PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry codecRegistry = fromRegistries(
                getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        MongoClient mongoClient = MongoClients.create(clientSettings);
        return mongoClient.getDatabase(connectionString.getDatabase());
    }

    private List<Document> createDocuments(int n) {
        List<Document> documents = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            documents.add(createDocument());
        }
        return documents;
    }

    private Document createDocument() {
        return documentGenerator.generate();
    }

    private static void exit(String message) {
        stdout(message);
        System.exit(0);
    }

    private static void error(String message) {
        stderr(message);
        System.exit(1);
    }

    private static void stdout(String message) {
        System.out.println(message);
    }

    private static void stderr(String message) {
        System.err.println(message);
    }
}
