package org.neo.mint.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by vanosov on 23.03.2018.
 */
public class Serializer<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Serializer.class);

    private final ObjectMapper mapper;

    private final Class<T> typeClass;

    public Serializer(ObjectMapper objectMapper, Class<T> typeClass){
        this.mapper = objectMapper;
        this.typeClass = typeClass;
    }

    public Optional<String> serialize(Object value){
        try{
            return Optional.ofNullable(mapper.writeValueAsString(value));
        } catch (JsonProcessingException ex){
            LOGGER.info("Failed to serialize object {}", ex);
            return Optional.empty();
        }
    }

    public Optional<T> deserialize(String json){
        try{
            return Optional.ofNullable(mapper.readValue(json, typeClass));
        } catch (IOException ex){
            LOGGER.info("Failed to deserialize json {}", json, ex);
            return Optional.empty();
        }
    }
}
