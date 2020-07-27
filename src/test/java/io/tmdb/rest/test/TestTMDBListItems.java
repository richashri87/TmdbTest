package io.tmdb.rest.test;


import io.restassured.response.Response;
import io.tmdb.rest.model.items.TMDBListItems;
import io.tmdb.rest.model.list.ListResponse;
import io.tmdb.rest.request.ListController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.tmdb.rest.common.CommonAssertions.assertStatusCode;
import static io.tmdb.rest.common.CommonAssertions.assertStatusMessage;
import static io.tmdb.rest.model.items.TMDBListItemsFixture.getTestDataForNewTMDBListItems;
import static io.tmdb.rest.model.items.TMDBListItemsFixture.getTestDataForUpdateTMDBListItems;
import static io.tmdb.rest.model.list.TMDBListFixture.geTestDataFortNewTMDBList;

@DisplayName("API Tests for TMDB List Items")
@Tag("tmdb-list-items-tests")
public class TestTMDBListItems
{
	ListController listController = new ListController();
	static ListResponse listResponse;

	@Test
	public void GivenNewList_AddItemsInNewListOnTMDB_ThenAddedSuccessfully() {

		Response response = listController.createList(geTestDataFortNewTMDBList());
		assertStatusCode(response.getStatusCode(), 201);
		listResponse = response.as(ListResponse.class);

		response = listController.addItemsByListId(listResponse.getId(), getTestDataForNewTMDBListItems());
		assertStatusCode(response.getStatusCode(), 200);
		assertStatusMessage(response.jsonPath().getString("status_message"), "Success");

		listController.removeListById(listResponse.getId());
	}

	@Test
	public void GivenExistingList_UpdateItemsInListOnTMDB_ThenUpdatedSuccessfully() {

		Response response = listController.createList(geTestDataFortNewTMDBList());
		assertStatusCode(response.getStatusCode(), 201);
		listResponse = response.as(ListResponse.class);

		response = listController.addItemsByListId(listResponse.getId(), getTestDataForNewTMDBListItems());
		assertStatusCode(response.getStatusCode(), 200);

		response = listController.updateItemsByListId(listResponse.getId(), getTestDataForUpdateTMDBListItems());
		assertStatusCode(response.getStatusCode(), 200);
		assertStatusMessage(response.jsonPath().getString(""), "Success");

		listController.removeListById(listResponse.getId());
	}

	@Test
	public void GivenExistingList_DeleteItemsInListOnTMDB_ThenDeletedSuccessfully() {

		Response response = listController.createList(geTestDataFortNewTMDBList());
		assertStatusCode(response.getStatusCode(), 201);
		listResponse = response.as(ListResponse.class);

		TMDBListItems items = getTestDataForNewTMDBListItems();
		response = listController.addItemsByListId(listResponse.getId(),items );
		assertStatusCode(response.getStatusCode(), 200);

		response = listController.removeItemsByListId(listResponse.getId(),items);
		assertStatusCode(response.getStatusCode(), 200);
		assertStatusMessage(listResponse.getStatus_message(), "Success");

		listController.removeListById(listResponse.getId());
	}
}
