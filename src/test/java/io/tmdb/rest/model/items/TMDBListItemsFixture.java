package io.tmdb.rest.model.items;

import java.util.ArrayList;
import java.util.Arrays;

public class TMDBListItemsFixture {
    public static TMDBListItems getTestDataForNewTMDBListItems(){

        TMDBListItem item1= TMDBListItem.builder()
                .media_id("100")
                .media_type(MediaType.movie)
                .build();

        TMDBListItem item2= TMDBListItem.builder()
                .media_id("101")
                .media_type(MediaType.tv)
                .build();

        return TMDBListItems.builder()
                .items(new ArrayList<TMDBListItem>(Arrays.asList(item1,item2)))
                .build();
    }

    public static TMDBListItems getTestDataForUpdateTMDBListItems(){

        TMDBListItem item1= TMDBListItem.builder()
                .media_id("100")
                .media_type(MediaType.movie)
                .comment("updating comment for 100")
                .build();

        TMDBListItem item2= TMDBListItem.builder()
                .media_id("101")
                .media_type(MediaType.tv)
                .comment("updating comment for 101")
                .build();

        return TMDBListItems.builder()
                .items(new ArrayList<TMDBListItem>(Arrays.asList(item1,item2)))
                .build();
    }
}
