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
import static io.tmdb.rest.request.ListController.extractListResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("API Tests for TMDB List Items")
@Tag("tmdb-list-tests")
public class TestTMDBList
{
	ListController listController = new ListController();
	static ListResponse createdListResponse;
	static ListResponse updatedListResponse;
	

	@Test
	public void GivenNewList_CreateNewListOnTMDB_ThenCreatedSuccessfully() {

		Response response = listController.createList(geTestDataFortNewTMDBList());

		assertStatusCode(response.getStatusCode(), 201);
		createdListResponse = extractListResponse(response);
		assertStatusMessage(createdListResponse.getStatus_message(), "create");
		assertThat(createdListResponse.isSuccess(),is(true));

		listController.removeListById(createdListResponse.getId());
	}

	@Test
	public void GivenAList_UpdateDescOfListOnTMDB_ThenUpdatedSuccessfully() {

		Response response = listController.createList(geTestDataFortNewTMDBList());

		assertStatusCode(response.getStatusCode(), 201);
		createdListResponse = extractListResponse(response);

		response = listController.updateListById(createdListResponse.getId(), getTestDataForUpdateTMDBList());
		assertStatusCode(response.getStatusCode(), 201);
		updatedListResponse = extractListResponse(response);
		assertStatusMessage(updatedListResponse.getStatus_message(), "update");
		assertThat(updatedListResponse.isSuccess(),is(true));

		listController.removeListById(createdListResponse.getId());
	}

	@Test
	public void ClearListById() {

		Response response = listController.createList(geTestDataFortNewTMDBList());

		assertStatusCode(response.getStatusCode(), 201);
		createdListResponse = extractListResponse(response);

		response = listController.addItemsByListId(createdListResponse.getId(), getTestDataForNewTMDBListItems());
		assertStatusCode(response.getStatusCode(), 200);

		response = listController.clearListById(createdListResponse.getId());
		assertStatusCode(response.getStatusCode(), 200);
		assertStatusMessage(createdListResponse.getStatus_message(), "Success");

		listController.removeListById(createdListResponse.getId());
	}

}
