package io.tmdb.rest.model.items;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class TMDBListItemsFixture {
    public static TMDBListItems createTestDataForNewTMDBListItems() {
        TMDBListItem item1 = TMDBListItem.builder()
                .media_id("100")
                .media_type(MediaType.movie)
                .build();

        TMDBListItem item2 = TMDBListItem.builder()
                .media_id("101")
                .media_type(MediaType.tv)
                .build();

        return TMDBListItems.builder()
                .items(new ArrayList<>(Arrays.asList(item1, item2)))
                .build();
    }

    static ArrayList<TMDBListItems> testDataFortNewTMDBList() {
        TMDBListItem item1 = TMDBListItem.builder()
                .media_id("100")
                .media_type(MediaType.movie)
                .build();

        TMDBListItem item2 = TMDBListItem.builder()
                .media_id("101")
                .media_type(MediaType.tv)
                .build();

        TMDBListItem item3 = TMDBListItem.builder()
                .media_id("102")
                .media_type(MediaType.tv)
                .build();

        TMDBListItem item4 = TMDBListItem.builder()
                .media_id("103")
                .media_type(MediaType.tv)
                .build();

        return new ArrayList<>(Arrays.asList(TMDBListItems.builder()
                        .items(new ArrayList<>(Arrays.asList(item1, item2)))
                        .build(),
                TMDBListItems.builder()
                        .items(new ArrayList<>(Arrays.asList(item3, item4)))
                        .build()));
    }

    static ArrayList<TMDBListItems> testDataForUpdateTMDBList() {
        TMDBListItem item1 = TMDBListItem.builder()
                .media_id("100")
                .media_type(MediaType.movie)
                .comment("updating comment for 100")
                .build();

        TMDBListItem item2 = TMDBListItem.builder()
                .media_id("101")
                .media_type(MediaType.tv)
                .comment("updating comment for 101")
                .build();

        TMDBListItem item3 = TMDBListItem.builder()
                .media_id("102")
                .media_type(MediaType.tv)
                .comment("updating comment for 102")
                .build();

        TMDBListItem item4 = TMDBListItem.builder()
                .media_id("103")
                .media_type(MediaType.tv)
                .comment("updating comment for 103")
                .build();

        return new ArrayList<>(Arrays.asList(TMDBListItems.builder()
                        .items(new ArrayList<>(Arrays.asList(item1, item2)))
                        .build(),
                TMDBListItems.builder()
                        .items(new ArrayList<>(Arrays.asList(item3, item4)))
                        .build()));
    }

    static Stream<Arguments> createAndUpdateProvider() {
        return Stream.of(
                Arguments.of(testDataFortNewTMDBList().get(0), testDataForUpdateTMDBList().get(0)),
                Arguments.of(testDataFortNewTMDBList().get(1), testDataForUpdateTMDBList().get(1))
        );
    }

}
