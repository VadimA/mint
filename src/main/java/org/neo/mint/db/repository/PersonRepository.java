package org.neo.mint.db.repository;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo.mint.db.domain.Person;
import org.neo.mint.utils.Serializer;
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
    private Serializer<Person> serializer;

    @Autowired
    public PersonRepository(Bucket bucket, ObjectMapper objectMapper){
        this.bucket = bucket;
        this.serializer = new Serializer<>(objectMapper, Person.class);
    }

    public Optional<Person> findById(String name){
        Optional<String> json = Optional.ofNullable(bucket.get(name))
                .map(JsonDocument::content)
                .map(JsonObject::toString);

        if (json.isPresent()) {
            return serializer.deserialize(json.get());
        }
        return Optional.empty();
    }

    public void save(Person person) {
        Optional<String> json = serializer.serialize(person);
        if(json.isPresent()) {
            bucket.upsert(RawJsonDocument.create(person.getName(), json.get()));
            LOGGER.info("Saved person: {}", person);
        } else {
            LOGGER.warn("Failed to save user {}", person);
        }
    }

    public void delete(String name) {
        bucket.remove(name);
        LOGGER.info("Deleted person: {}", name);
    }
}
