package io.tmdb.rest.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public enum ObjectMapperHolder {

    INSTANCE;

    private final ObjectMapper mapper;

    private ObjectMapperHolder() {
        this.mapper = create();
    }

    public ObjectMapper get() {
        return this.mapper;
    }

    private static ObjectMapper create() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

}