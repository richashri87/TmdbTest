package io.tmdb.rest.test;


import io.restassured.response.Response;
import io.tmdb.rest.model.items.ListItemsResponse;
import io.tmdb.rest.model.items.MediaType;
import io.tmdb.rest.model.items.TMDBListItem;
import io.tmdb.rest.model.items.TMDBListItems;
import io.tmdb.rest.model.list.ListResponse;
import io.tmdb.rest.request.ListController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static io.tmdb.rest.common.CommonAssertions.*;
import static io.tmdb.rest.model.items.TMDBListItemsFixture.createTestDataForNewTMDBListItems;
import static io.tmdb.rest.model.list.TMDBListFixture.createTestDataFortNewTMDBList;
import static io.tmdb.rest.request.ListController.extractListItemResponse;
import static io.tmdb.rest.request.ListController.extractListResponse;

@DisplayName("API Tests for TMDB List Items")
@Tag("tmdb-list-items-tests")
public class TestTMDBListItems {
    ListController listController = new ListController();
    static ListResponse createdListResponse;
    static ListItemsResponse listItemsResponse;

    @ParameterizedTest
    @MethodSource("testDataFortNewTMDBList")
    public void GivenNewList_AddItemsInNewListOnTMDB_ThenAddedSuccessfully(TMDBListItems testDataForCreateTMDBListItems) {
        Response response = listController.createList(createTestDataFortNewTMDBList());
        assertStatusCode(response.getStatusCode(), 201);
        createdListResponse = extractListResponse(response);

        response = listController.addItemsByListId(createdListResponse.getId(), testDataForCreateTMDBListItems);
        assertStatusCode(response.getStatusCode(), 200);
        listItemsResponse = extractListItemResponse(response);
        assertStatusMessage(listItemsResponse.getStatus_message(), "Success");

        verifyEachItemAddedOrUpdatedOrDeleted(listItemsResponse);

        listController.removeListById(createdListResponse.getId());
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

    @ParameterizedTest
    @MethodSource("createAndUpdateProvider")
    public void GivenExistingList_UpdateItemsInListOnTMDB_ThenUpdatedSuccessfully(TMDBListItems testDataForCreateTMDBListItems, TMDBListItems testDataForUpdateTMDBListItems) {
        Response response = listController.createList(createTestDataFortNewTMDBList());
        assertStatusCode(response.getStatusCode(), 201);
        createdListResponse = extractListResponse(response);

        response = listController.addItemsByListId(createdListResponse.getId(), testDataForCreateTMDBListItems);
        assertStatusCode(response.getStatusCode(), 200);

        response = listController.updateItemsByListId(createdListResponse.getId(), testDataForUpdateTMDBListItems);
        assertStatusCode(response.getStatusCode(), 200);
        listItemsResponse = extractListItemResponse(response);
        assertStatusMessage(listItemsResponse.getStatus_message(), "Success");

        verifyEachItemAddedOrUpdatedOrDeleted(listItemsResponse);

        listController.removeListById(createdListResponse.getId());
    }

    @Test
    public void GivenExistingList_DeleteItemsInListOnTMDB_ThenDeletedSuccessfully() {
        Response response = listController.createList(createTestDataFortNewTMDBList());
        assertStatusCode(response.getStatusCode(), 201);
        createdListResponse = extractListResponse(response);

        TMDBListItems items = createTestDataForNewTMDBListItems();
        response = listController.addItemsByListId(createdListResponse.getId(), items);
        assertStatusCode(response.getStatusCode(), 200);

        response = listController.removeItemsByListId(createdListResponse.getId(), items);
        assertStatusCode(response.getStatusCode(), 200);
        listItemsResponse = extractListItemResponse(response);
        assertStatusMessage(listItemsResponse.getStatus_message(), "Success");

        verifyEachItemAddedOrUpdatedOrDeleted(listItemsResponse);

        listController.removeListById(createdListResponse.getId());
    }
}
