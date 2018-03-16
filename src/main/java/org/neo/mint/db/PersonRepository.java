package org.neo.mint.db;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo.mint.db.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by vanosov on 16.03.2018.
 */
@Repository
public class PersonRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepository.class);

    private Bucket bucket;
    private ObjectMapper objectMapper;

    @Autowired
    public PersonRepository(Bucket bucket, ObjectMapper objectMapper){
        this.bucket = bucket;
        this.objectMapper = objectMapper;
    }

    public Optional<Person> findById(String name){
        Optional<String> json = Optional.ofNullable(bucket.get(name))
                .map(JsonDocument::content)
                .map(JsonObject::toString);
        if (json.isPresent()) {
            try {
                return Optional.ofNullable(objectMapper.readValue(json.get(), Person.class));
            } catch (IOException e) {
                LOGGER.warn("Failed to deserialize person from json {}", json.get());
            }
        }
        return Optional.empty();
    }

    public void save(Person person) {
        try {
            bucket.upsert(RawJsonDocument.create(person.getName(), objectMapper.writeValueAsString(person)));
            LOGGER.info("Saved person: {}", person);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Failed to save user {}", person);
        }
    }

    public void delete(String name) {
        bucket.remove(name);
        LOGGER.info("Deleted person: {}", name);
    }
}
