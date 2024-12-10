package com.example.JavaWebCrawler.entities;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class AutoIncrementListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();

        Arrays.stream(source.getClass().getDeclaredFields()).forEach(field -> {
            if (field.isAnnotationPresent(AutoIncrement.class)) {
                try {
                    field.setAccessible(true);
                    if (field.get(source) == null || (Integer) field.get(source) == 0) {
                        AutoIncrement autoIncrement = field.getAnnotation(AutoIncrement.class);
                        String collectionName = autoIncrement.collectionName();
                        int nextId = getNextSequence(collectionName);
                        field.set(source, nextId);
                    }
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Failed to set auto-increment ID for collection: " + source.getClass().getName(), e);
                }
            }
        });
    }

    public int getNextSequence(String collectionName) {
        Document counter = mongoTemplate.findAndModify(
                query(where("_id").is(collectionName)),
                new org.springframework.data.mongodb.core.query.Update().inc("seq", 1),
                Document.class,
                "counters"
        );

        if (counter != null && counter.containsKey("seq")) {
            return counter.getInteger("seq");
        } else {
            System.out.println("Counter not found for collection: " + collectionName + ". Initializing...");
            mongoTemplate.insert(new Document("_id", collectionName).append("seq", 1), "counters");
            return 1;
        }
    }
}
