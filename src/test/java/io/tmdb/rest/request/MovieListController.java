package io.tmdb.rest.request;


import io.restassured.response.Response;
import io.tmdb.rest.model.movielist.MovieListRequest;

public class MovieListController {
	
	private static final String LIST_ENDPOINT="/list";

	BaseRequest baseRequest = new BaseRequest();

	public Response getListById(int id) {
		return baseRequest.getById(id,LIST_ENDPOINT);
	}

	public Response removeListById(int id) {
		return baseRequest.deleteById(id,LIST_ENDPOINT);
	}

	public Response createList(MovieListRequest movieListRequest) {

		return baseRequest.create(movieListRequest,LIST_ENDPOINT);
	}
	public Response updateListById(int id) {
		return baseRequest.updateById(id,LIST_ENDPOINT);
	}
	public Response clearListById(int id) {
		return baseRequest.clearById(id,LIST_ENDPOINT);
	}

	/*public Response addItem() {
		return baseRequest.create(LIST_ENDPOINT);
	}*/
	public Response updateItem(int id) {
		return baseRequest.updateById(id,LIST_ENDPOINT);
	}
	public Response removeItem(int id) {
		return baseRequest.clearById(id,LIST_ENDPOINT);
	}
}
