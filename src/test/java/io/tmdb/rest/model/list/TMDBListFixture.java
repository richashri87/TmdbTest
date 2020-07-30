package io.tmdb.rest.model.list;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TMDBListFixture {
    public static TMDBList createTestDataFortNewTMDBList() {
        return TMDBList.builder()
                .name("My movie builder list")
                .iso_639_1("en")
                .build();
    }

    static TMDBList[] geTestDataFortNewTMDBList() {
        return new TMDBList[]{
                new TMDBList("en", "first list name", 0, "desc for first list"),
                new TMDBList("de", " second list name", 0, "desc for second list"),
                new TMDBList("en", " third list name", 0, "desc for third list")
        };
    }

    static Stream<Arguments> createAndUpdateProvider() {
        return Stream.of(Arguments.of(new TMDBList("en", "first list name", 0, "desc1"), new TMDBList("updated description for first list")),
                Arguments.of(new TMDBList("de", " second list name", 0, "desc2"), new TMDBList("updated description for second list")),
                Arguments.of(new TMDBList("en", " third list name", 0, "desc3"), new TMDBList("updated description for third list"))
        );
    }

}
