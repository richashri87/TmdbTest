package io.tmdb.rest.test;


import io.restassured.response.Response;
import io.tmdb.rest.model.list.ListResponse;
import io.tmdb.rest.model.list.TMDBList;
import io.tmdb.rest.request.ListController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.tmdb.rest.common.CommonAssertions.assertStatusCode;
import static io.tmdb.rest.common.CommonAssertions.assertStatusMessage;
import static io.tmdb.rest.model.items.TMDBListItemsFixture.getTestDataForNewTMDBListItems;
import static io.tmdb.rest.request.ListController.extractListResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("API Tests for TMDB List")
@Tag("tmdb-list-tests")
public class TestTMDBList
{
	ListController listController = new ListController();
	static ListResponse createdListResponse;
	static ListResponse updatedListResponse;
	

	@ParameterizedTest
	@MethodSource("geTestDataFortNewTMDBList")
	public void GivenNewList_CreateNewListOnTMDB_ThenCreatedSuccessfully(TMDBList tmdbList) {

		Response response = listController.createList(tmdbList);

		assertStatusCode(response.getStatusCode(), 201);
		createdListResponse = extractListResponse(response);
		assertStatusMessage(createdListResponse.getStatus_message(), "create");
		assertThat(createdListResponse.isSuccess(),is(true));

		listController.removeListById(createdListResponse.getId());
	}

	@ParameterizedTest
	@MethodSource("createAndUpdateProvider")
	public void GivenAList_UpdateDescOfListOnTMDB_ThenUpdatedSuccessfully(TMDBList testDataForCreateTMDBList, TMDBList testDataForUpdateTMDBList) {

		Response response = listController.createList(testDataForCreateTMDBList);

		assertStatusCode(response.getStatusCode(), 201);
		createdListResponse = extractListResponse(response);

		response = listController.updateListById(createdListResponse.getId(), testDataForUpdateTMDBList);
		assertStatusCode(response.getStatusCode(), 201);
		updatedListResponse = extractListResponse(response);
		assertStatusMessage(updatedListResponse.getStatus_message(), "update");
		assertThat(updatedListResponse.isSuccess(),is(true));

		listController.removeListById(createdListResponse.getId());
	}

	@ParameterizedTest
	@MethodSource("geTestDataFortNewTMDBList")
	public void ClearListById(TMDBList tMDBList) {

		Response response = listController.createList(tMDBList);

		assertStatusCode(response.getStatusCode(), 201);
		createdListResponse = extractListResponse(response);

		response = listController.addItemsByListId(createdListResponse.getId(), getTestDataForNewTMDBListItems());
		assertStatusCode(response.getStatusCode(), 200);

		response = listController.clearListById(createdListResponse.getId());
		assertStatusCode(response.getStatusCode(), 200);
		assertStatusMessage(createdListResponse.getStatus_message(), "Success");

		listController.removeListById(createdListResponse.getId());
	}

	static TMDBList[] geTestDataFortNewTMDBList() {
		return new TMDBList[] {
				new TMDBList("en","first list name",0,"desc1"),
				new TMDBList("de"," second list name",0,"desc2"),
				new TMDBList("en"," third list name",0,"desc3")
		};
	}

	static Stream<Arguments> createAndUpdateProvider() {
		return Stream.of(Arguments.of(new TMDBList("en","first list name",0,"desc1"), new TMDBList("This list is pretty good for first list")),
				Arguments.of(new TMDBList("de"," second list name",0,"desc2"), new TMDBList("This list is pretty good for second list")),
				Arguments.of(new TMDBList("en"," third list name",0,"desc3"), new TMDBList("This list is pretty good for for third list"))
		);
	}
}
