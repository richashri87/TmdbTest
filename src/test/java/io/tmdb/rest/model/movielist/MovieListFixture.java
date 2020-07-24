package io.tmdb.rest.model.movielist;

public class MovieListFixture {
    public static MovieListRequest validMovieInfo(){
        return MovieListRequest.builder()
                .name("My movie builder list")
                .iso_639_1("en")
                .build();
    }

}
