package io.tmdb.rest.test;


import io.restassured.response.Response;
import io.tmdb.rest.model.list.ListResponse;
import io.tmdb.rest.model.list.TMDBList;
import io.tmdb.rest.request.ListController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.tmdb.rest.common.CommonAssertions.assertStatusCode;
import static io.tmdb.rest.common.CommonAssertions.assertStatusMessage;
import static io.tmdb.rest.model.items.TMDBListItemsFixture.createTestDataForNewTMDBListItems;
import static io.tmdb.rest.request.ListController.extractListResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("API Tests for TMDB List")
@Tag("tmdb-list-tests")
public class TestTMDBList {
    ListController listController = new ListController();
    static ListResponse createdListResponse;
    static ListResponse updatedListResponse;


    @ParameterizedTest
    @MethodSource("io.tmdb.rest.model.list.TMDBListFixture#geTestDataFortNewTMDBList")
    public void GivenNewList_CreateNewListOnTMDB_ThenCreatedSuccessfully(TMDBList tmdbList) {
        Response response = listController.createList(tmdbList);

        assertStatusCode(response.getStatusCode(), 201);
        createdListResponse = extractListResponse(response);
        assertStatusMessage(createdListResponse.getStatus_message(), "create");
        assertThat(createdListResponse.isSuccess(), is(true));

        listController.removeListById(createdListResponse.getId());
    }

    @ParameterizedTest
    @MethodSource("io.tmdb.rest.model.list.TMDBListFixture#createAndUpdateProvider")
    public void GivenAList_UpdateDescOfListOnTMDB_ThenUpdatedSuccessfully(TMDBList testDataForCreateTMDBList, TMDBList testDataForUpdateTMDBList) {
        Response response = listController.createList(testDataForCreateTMDBList);

        assertStatusCode(response.getStatusCode(), 201);
        createdListResponse = extractListResponse(response);

        response = listController.updateListById(createdListResponse.getId(), testDataForUpdateTMDBList);
        assertStatusCode(response.getStatusCode(), 201);
        updatedListResponse = extractListResponse(response);
        assertStatusMessage(updatedListResponse.getStatus_message(), "update");
        assertThat(updatedListResponse.isSuccess(), is(true));

        listController.removeListById(createdListResponse.getId());
    }

    @ParameterizedTest
    @MethodSource("io.tmdb.rest.model.list.TMDBListFixture#geTestDataFortNewTMDBList")
    public void ClearListById(TMDBList tMDBList) {
        Response response = listController.createList(tMDBList);

        assertStatusCode(response.getStatusCode(), 201);
        createdListResponse = extractListResponse(response);

        response = listController.addItemsByListId(createdListResponse.getId(), createTestDataForNewTMDBListItems());
        assertStatusCode(response.getStatusCode(), 200);

        response = listController.clearListById(createdListResponse.getId());
        assertStatusCode(response.getStatusCode(), 200);
        assertStatusMessage(createdListResponse.getStatus_message(), "Success");

        listController.removeListById(createdListResponse.getId());
    }
}
