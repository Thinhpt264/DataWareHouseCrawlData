package com.example.JavaWebCrawler.entities;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.UUID;

public class MongoClientConnectionExample {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://hackerbyhatake:phanthethinh2604@cluster0.17qau.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("datawarehouse");

            // Create collections and indexes
            createCollection(database, "baohanhdim", Arrays.asList("id", "name"));
            createCollection(database, "chungloaidim", Arrays.asList("id", "name"));
            createCollection(database, "config", Arrays.asList("id", "key_name", "value", "description", "created_at", "updated_at"));
            createCollection(database, "congnghelocdim", Arrays.asList("id", "name"));
            createCollection(database, "congsuatlocdim", Arrays.asList("id", "name"));
            createCollection(database, "dungtichbnhchuadim", Arrays.asList("id", "name"));
            createCollection(database, "loaimaylocnuocdim", Arrays.asList("id", "name"));
            createCollection(database, "log", Arrays.asList("id", "timestamp", "level", "message", "context"));
            createCollection(database, "loilocdim", Arrays.asList("id", "name"));
            createCollection(database, "product", Arrays.asList("Ma_san_pham", "name", "old_price", "new_price", "percent_discount",
                    "link", "image_url", "installment", "Thuong_hieu", "Tien_ich", "Loai_may_loc_nuoc", "Chung_loai",
                    "So_loi_loc", "Cong_suat_loc", "Dung_tich_binh_chua", "Cong_nghe_loc", "Tinh_nang_noi_bat",
                    "Vo_tu", "Cong_suat_tieu_thu", "Kich_thuoc", "Loi_loc", "Bao_hanh", "Xuat_xu", "update_at"));
            createCollection(database, "soloilocdim", Arrays.asList("id", "name"));
            createCollection(database, "thuonghieudim", Arrays.asList("id", "name"));
            createCollection(database, "tienichdim", Arrays.asList("id", "name"));
            createCollection(database, "votudim", Arrays.asList("id", "name"));
            createCollection(database, "xuatxudim", Arrays.asList("id", "name"));

            System.out.println("All collections created successfully.");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a MongoDB collection with specified fields.
     *
     * @param database the MongoDatabase instance
     * @param collectionName the name of the collection
     * @param fields the fields to include in the collection
     */
    private static void createCollection(MongoDatabase database, String collectionName, Iterable<String> fields) {
        try {
            // Check if the collection already exists
            boolean collectionExists = database.listCollectionNames()
                    .into(new java.util.ArrayList<>())
                    .contains(collectionName);
            if (!collectionExists) {
                database.createCollection(collectionName);
                System.out.println("Created collection: " + collectionName);

                // Add a sample document to define fields
                Document document = new Document();
                document.append("_id", UUID.randomUUID().toString()); // Generate a unique ID
                for (String field : fields) {
                    document.append(field, null); // Placeholder for field types
                }
                database.getCollection(collectionName).insertOne(document);
                System.out.println("Initialized collection: " + collectionName);
            } else {
                System.out.println("Collection already exists: " + collectionName);
            }
        } catch (Exception e) {
            System.err.println("Error creating collection: " + collectionName);
            e.printStackTrace();
        }
    }
}