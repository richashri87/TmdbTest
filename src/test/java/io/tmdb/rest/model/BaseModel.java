package io.tmdb.rest.model;


import com.fasterxml.jackson.core.JsonProcessingException;

abstract public class BaseModel {

    @Override
    public String toString() {
        String str = "";
        try {
            str = ObjectMapperHolder.INSTANCE.get().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(str);
        return str;
    }
}