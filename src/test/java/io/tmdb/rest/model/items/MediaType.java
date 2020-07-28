package io.tmdb.rest.model.items;

public enum MediaType {
    movie("movie"),

    tv("tv");

    private String value;

    MediaType(String value) {
        this.value = value;
    }

    public String MediaType() {
        return value;
    }
}
