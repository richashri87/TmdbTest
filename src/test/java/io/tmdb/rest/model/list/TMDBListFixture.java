package io.tmdb.rest.model.list;

public class TMDBListFixture {
    public static TMDBList geTestDataFortNewTMDBList(){
        return TMDBList.builder()
                .name("My movie builder list")
                .iso_639_1("en")
                .build();
    }

}
