package io.tmdb.rest.test;


import io.restassured.response.Response;
import io.tmdb.rest.model.list.ListResponse;
import io.tmdb.rest.request.ListController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.tmdb.rest.common.CommonAssertions.assertStatusCode;
import static io.tmdb.rest.common.CommonAssertions.assertStatusMessage;
import static io.tmdb.rest.model.items.TMDBListItemsFixture.getTestDataForNewTMDBListItems;
import static io.tmdb.rest.model.list.TMDBListFixture.geTestDataFortNewTMDBList;
import static io.tmdb.rest.model.list.TMDBListFixture.getTestDataForUpdateTMDBList;

@DisplayName("API Tests for TMDB List Items")
@Tag("tmdb-list-tests")
public class TestTMDBList
{
	ListController listController = new ListController();
	static ListResponse listResponse;

	@Test
	public void GivenNewList_CreateNewListOnTMDB_ThenCreatedSuccessfully() {

		Response response = listController.createList(geTestDataFortNewTMDBList());

		assertStatusCode(response.getStatusCode(), 201);
		listResponse = response.as(ListResponse.class);
		assertStatusMessage(listResponse.getStatus_message(), "create");

		listController.removeListById(listResponse.getId());
	}

	@Test
	public void GivenAList_UpdateDescOfListOnTMDB_ThenUpdatedSuccessfully() {

		Response response = listController.createList(geTestDataFortNewTMDBList());

		assertStatusCode(response.getStatusCode(), 201);
		listResponse = response.as(ListResponse.class);

		response = listController.updateListById(listResponse.getId(), getTestDataForUpdateTMDBList());
		assertStatusCode(response.getStatusCode(), 201);
		assertStatusMessage(response.jsonPath().getString("status_message"), "update");

		listController.removeListById(listResponse.getId());
	}

	@Test
	public void ClearListById() {

		Response response = listController.createList(geTestDataFortNewTMDBList());

		assertStatusCode(response.getStatusCode(), 201);
		listResponse = response.as(ListResponse.class);

		response = listController.addItemsByListId(listResponse.getId(), getTestDataForNewTMDBListItems());
		assertStatusCode(response.getStatusCode(), 200);

		response = listController.clearListById(listResponse.getId());
		assertStatusCode(response.getStatusCode(), 200);
		assertStatusMessage(listResponse.getStatus_message(), "Success");

		listController.removeListById(listResponse.getId());
	}

}
