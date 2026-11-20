package com.revature.database.util;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MongoConnectionManager {

    /*
     * The MongoClient Managed as a singleton -- created once and
     * reused throughout the application. This differs from JDBC
     * Connection objects, which are single-socket connections.
     *
     * MongoClient acts as a connection pool (it manages multiple
     * connections).
     */
    private static MongoClient mongoClient;

    /*
     * This method will attempt to read an environment variable (MONGODB_URI)
     * for the credentials (URI) needed to connect to my MongoDB
     *
     * If that Environment variable doesn't exist, we will call the 'loadUriFromPropertiesFile'
     * method to get the credentials from the properties file instead.
     */
    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            String connectionUri = System.getenv("MONGODB_URI");

            if (connectionUri == null || connectionUri.isEmpty()) {
                connectionUri = loadUriFromPropertiesFile();
            }

            if (connectionUri == null || connectionUri.isEmpty()) {
                throw new IllegalStateException(
                        "MongoDB connection URI not found in environment variables or properties file");
            }

            // This line creates the actual connection to our Mongo Cluster
            mongoClient = MongoClients.create(connectionUri);

            // Proactively verify the connection, since MongoClient creation
            // alone does NOT guarantee the server is reachable
            verifyConnection(mongoClient);
        }

        return mongoClient;
    }

    private static String loadUriFromPropertiesFile() {
        Properties properties = new Properties();

        // try-with-resources ensures the InputStream is closed automatically
        try (InputStream input = MongoConnectionManager.class
                .getClassLoader()
                .getResourceAsStream("mongo.properties")) {

            if (input == null) {
                System.out.println("mongo.properties file not found on classpath.");
                return null;
            }

            properties.load(input);
            return properties.getProperty("MONGODB_URI");

        } catch (IOException e) {
            System.out.println("Failed to read mongo.properties: " + e.getMessage());
            return null;
        }
    }

    // The following methods showcases how we can perform our sanity check against
    // our Mongo database
    private static void verifyConnection(MongoClient client) {
        try {
            // Running a lightweight "ping" command confirms the server is
            // reachable and credentials are valid
            MongoDatabase adminDatabase = client.getDatabase("admin");
            adminDatabase.runCommand(new Document("ping", 1));
            System.out.println("Successfully connected to MongoDB.");

        } catch (MongoException e) {
            // This is where connection issues actually surface -- NOT at
            // MongoClients.create() -- e.g. bad credentials, unreachable host,
            // or an IP not whitelisted in Atlas Network Access settings
            System.out.println("Failed to connect to MongoDB: " + e.getMessage());
            throw e;
        }
    }

    public static void closeConnection() {
        // Should be called once, at application shutdown
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
