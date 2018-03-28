package org.neo.mint.db.repository;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo.mint.db.domain.WorkUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by vanosov on 20.03.2018.
 */
@Repository
public class WorkUnitRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkUnitRepository.class);

    @Autowired
    @Qualifier(value = "workUnitBucket")
    private Bucket bucket;

    @Autowired
    ObjectMapper objectMapper;

    public Optional<WorkUnit> findById(String name){
        Optional<String> json = Optional.ofNullable(bucket.get(name))
                .map(JsonDocument::content)
                .map(JsonObject::toString);
        if (json.isPresent()) {
            try {
                return Optional.ofNullable(objectMapper.readValue(json.get(), WorkUnit.class));
            } catch (IOException e) {
                LOGGER.warn("Failed to deserialize workUnit from json {}", json.get());
            }
        }
        return Optional.empty();
    }

    public void save(WorkUnit workUnit) {
        try {
            bucket.upsert(RawJsonDocument.create(workUnit.getName(), objectMapper.writeValueAsString(workUnit)));
            LOGGER.info("Saved workUnit: {}", workUnit);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Failed to save workUnit {}", workUnit);
        }
    }

    public void delete(String name) {
        bucket.remove(name);
        LOGGER.info("Deleted workUnit: {}", name);
    }
}
