package io.tmdb.rest.model.items;

import io.tmdb.rest.model.items.TMDBListItem;
import io.tmdb.rest.model.items.TMDBListItems;
import io.tmdb.rest.model.list.MediaType;

import java.util.ArrayList;
import java.util.Arrays;

public class TMDBListItemsFixture {
    public static TMDBListItems getTestDataForNewTMDBListItems(){

        TMDBListItem item1= TMDBListItem.builder()
                .media_id("100")
                .media_type(MediaType.MOVIE)
                .build();

        TMDBListItem item2= TMDBListItem.builder()
                .media_id("101")
                .media_type(MediaType.MOVIE)
                .build();

        return TMDBListItems.builder()
                .items(new ArrayList<TMDBListItem>(Arrays.asList(item1,item2)))
                .build();
    }

    public static TMDBListItems getTestDataForUpdateTMDBListItems(){

        TMDBListItem item1= TMDBListItem.builder()
                .media_id("102")
                .media_type(MediaType.MOVIE)
                .build();

        TMDBListItem item2= TMDBListItem.builder()
                .media_id("103")
                .media_type(MediaType.TV)
                .build();

        return TMDBListItems.builder()
                .items(new ArrayList<TMDBListItem>(Arrays.asList(item1,item2)))
                .build();
    }
}
