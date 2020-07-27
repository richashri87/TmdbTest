package io.tmdb.rest.model.list;

public class TMDBListFixture {
    public static TMDBList geTestDataFortNewTMDBList(){
        return TMDBList.builder()
                .name("My movie builder list")
                .iso_639_1("en")
                .build();
    }

    public static TMDBList getTestDataForUpdateTMDBList(){
        return TMDBList.builder()
                .description("This list is very awesome")
                .build();
    }

}
