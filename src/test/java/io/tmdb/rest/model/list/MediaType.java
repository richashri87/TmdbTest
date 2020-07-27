package io.tmdb.rest.model.list;

public enum MediaType {
    MOVIE("movie"),

    TV("tv");

    private String value;

    MediaType(String value) {
        this.value = value;
    }

    public String MediaType() {
        return value;
    }
}
