package io.tmdb.rest.test;


import io.restassured.response.Response;
import io.tmdb.rest.model.items.ListItemsResponse;
import io.tmdb.rest.model.items.TMDBListItems;
import io.tmdb.rest.model.list.ListResponse;
import io.tmdb.rest.request.ListController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.tmdb.rest.common.CommonAssertions.*;
import static io.tmdb.rest.model.items.TMDBListItemsFixture.getTestDataForNewTMDBListItems;
import static io.tmdb.rest.model.items.TMDBListItemsFixture.getTestDataForUpdateTMDBListItems;
import static io.tmdb.rest.model.list.TMDBListFixture.geTestDataFortNewTMDBList;
import static io.tmdb.rest.request.ListController.extractListItemResponse;
import static io.tmdb.rest.request.ListController.extractListResponse;

@DisplayName("API Tests for TMDB List Items")
@Tag("tmdb-list-items-tests")
public class TestTMDBListItems
{
	ListController listController = new ListController();
	static ListResponse createdListResponse;
	static  ListItemsResponse listItemsResponse;

	@Test
	public void GivenNewList_AddItemsInNewListOnTMDB_ThenAddedSuccessfully() {

		Response response = listController.createList(geTestDataFortNewTMDBList());
		assertStatusCode(response.getStatusCode(), 201);
		createdListResponse = extractListResponse(response);

		response = listController.addItemsByListId(createdListResponse.getId(), getTestDataForNewTMDBListItems());
		assertStatusCode(response.getStatusCode(), 200);
		listItemsResponse = extractListItemResponse(response);
		assertStatusMessage(listItemsResponse.getStatus_message(), "Success");

		verifyEachItemAddedOrUpdatedOrDeleted(listItemsResponse);

		listController.removeListById(createdListResponse.getId());
	}

	@Test
	public void GivenExistingList_UpdateItemsInListOnTMDB_ThenUpdatedSuccessfully() {

		Response response = listController.createList(geTestDataFortNewTMDBList());
		assertStatusCode(response.getStatusCode(), 201);
		createdListResponse = extractListResponse(response);

		response = listController.addItemsByListId(createdListResponse.getId(), getTestDataForNewTMDBListItems());
		assertStatusCode(response.getStatusCode(), 200);

		response = listController.updateItemsByListId(createdListResponse.getId(), getTestDataForUpdateTMDBListItems());
		assertStatusCode(response.getStatusCode(), 200);
		listItemsResponse = extractListItemResponse(response);
		assertStatusMessage(listItemsResponse.getStatus_message(), "Success");

		verifyEachItemAddedOrUpdatedOrDeleted(listItemsResponse);

		listController.removeListById(createdListResponse.getId());
	}

	@Test
	public void GivenExistingList_DeleteItemsInListOnTMDB_ThenDeletedSuccessfully() {

		Response response = listController.createList(geTestDataFortNewTMDBList());
		assertStatusCode(response.getStatusCode(), 201);
		createdListResponse = extractListResponse(response);

		TMDBListItems items = getTestDataForNewTMDBListItems();
		response = listController.addItemsByListId(createdListResponse.getId(),items );
		assertStatusCode(response.getStatusCode(), 200);

		response = listController.removeItemsByListId(createdListResponse.getId(),items);
		assertStatusCode(response.getStatusCode(), 200);
		listItemsResponse = extractListItemResponse(response);
		assertStatusMessage(listItemsResponse.getStatus_message(), "Success");

		verifyEachItemAddedOrUpdatedOrDeleted(listItemsResponse);

		listController.removeListById(createdListResponse.getId());
	}
}
