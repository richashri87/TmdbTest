package io.tmdb.rest.test;


import io.restassured.response.Response;
import io.tmdb.rest.model.items.ListItemsResponse;
import io.tmdb.rest.model.items.TMDBListItems;
import io.tmdb.rest.model.list.ListResponse;
import io.tmdb.rest.request.ListController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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
    @MethodSource("io.tmdb.rest.model.items.TMDBListItemsFixture#testDataFortNewTMDBList")
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

    @ParameterizedTest
    @MethodSource("io.tmdb.rest.model.items.TMDBListItemsFixture#createAndUpdateProvider")
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
