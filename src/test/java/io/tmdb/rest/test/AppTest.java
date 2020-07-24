package io.tmdb.rest.test;


import io.restassured.response.Response;
import io.tmdb.rest.model.movielist.MovieListRequest;
import io.tmdb.rest.model.movielist.MovieListResponse;
import io.tmdb.rest.request.MovieListController;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.tmdb.rest.common.CommonAssertions.assertStatusCode;
import static io.tmdb.rest.common.CommonAssertions.assertStatusMessage;
import static io.tmdb.rest.model.movielist.MovieListFixture.validMovieInfo;

/**
 * Unit test for simple App.
 */
@TestMethodOrder(OrderAnnotation.class)
public class AppTest
{
	MovieListController movieListController = new MovieListController();
	static MovieListResponse movieListResponse;

	@Test
	public void CreateList() {

		MovieListRequest movieListRequest = validMovieInfo();
		Response response = movieListController.createList(movieListRequest);

		assertStatusCode(response.getStatusCode(), 201);
		movieListResponse = response.as(MovieListResponse.class);
		assertStatusMessage(movieListResponse.getStatus_message(), "create");

		movieListController.removeListById(movieListResponse.getId());
	}

	@Test
	public void UpdateList() {

		MovieListRequest movieListRequest = validMovieInfo();
		Response response = movieListController.createList(movieListRequest);

		assertStatusCode(response.getStatusCode(), 201);
		movieListResponse = response.as(MovieListResponse.class);

		response = movieListController.updateListById(movieListResponse.getId());
		assertStatusCode(response.getStatusCode(), 201);
		assertStatusMessage(response.jsonPath().getString("status_message"), "update");

		movieListController.removeListById(movieListResponse.getId());

	}

	@Test
	@Order(4)
	public void ClearListById() {
		Response response = movieListController.clearListById(7051105);
		assertStatusCode(response.getStatusCode(), 200);
	}

}
