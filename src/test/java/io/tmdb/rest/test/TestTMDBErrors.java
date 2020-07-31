package io.tmdb.rest.test;

import io.restassured.response.Response;
import io.tmdb.rest.model.list.ListResponse;
import io.tmdb.rest.model.list.TMDBList;
import io.tmdb.rest.request.ListController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.tmdb.rest.common.CommonAssertions.assertStatusCode;
import static io.tmdb.rest.request.ListController.extractListResponse;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("API Tests for API Errors")
@Tag("tmdb-error-tests")
public class TestTMDBErrors {
    ListController listController = new ListController();
    static ListResponse createdListResponse;

    @DisplayName("Negative testing – invalid input")
    @Test
    public void GivenMissingRequiredParameter_CreateListOnTMDB_ThenThrowUnprocessableEntity() {
        TMDBList tMDBList = TMDBList.builder()
                .name("My movie builder list")
                .build();

        Response response = listController.createList(tMDBList);
        assertStatusCode(response.getStatusCode(), 422);
        assertThat("Not an appropriate error message", response.jsonPath().getString("errors").contains("iso_639_1 must be provided"));
    }

    @DisplayName("Negative testing – valid input")
    @Test
    public void GivenUnauthorizedListId_DeleteListOnTMDB_ThenThrowUnauthorized() {
        Response response = listController.removeListById(576768);
        assertStatusCode(response.getStatusCode(), 401);
        createdListResponse = extractListResponse(response);
        assertThat("Not an appropriate error message", createdListResponse.getStatus_message().contains("You don't have permission to edit this resource"));
    }
}
