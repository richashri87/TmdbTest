package io.tmdb.rest.request;


import io.restassured.response.Response;
import io.tmdb.rest.model.items.ListItemsResponse;
import io.tmdb.rest.model.items.TMDBListItems;
import io.tmdb.rest.model.list.ListResponse;
import io.tmdb.rest.model.list.TMDBList;

public class ListController {

    private static final String LIST_ENDPOINT = "/list";

    BaseRequest baseRequest = new BaseRequest();

    public Response createList(TMDBList TMDBListRequest) {
        return baseRequest.create(TMDBListRequest, LIST_ENDPOINT);
    }

    public Response getListById(int id) {
        return baseRequest.getById(id, LIST_ENDPOINT);
    }

    public Response updateListById(int id, TMDBList updatedTMDBList) {
        return baseRequest.updateById(id, updatedTMDBList, LIST_ENDPOINT);
    }

    public Response clearListById(int id) {
        return baseRequest.clearById(id, LIST_ENDPOINT);
    }

    public Response removeListById(int id) {
        return baseRequest.deleteById(id, LIST_ENDPOINT);
    }

    public Response addItemsByListId(int id, TMDBListItems items) {
        return baseRequest.addItemToListByListId(id, items, LIST_ENDPOINT);
    }

    public Response updateItemsByListId(int id, TMDBListItems items) {
        return baseRequest.updateItemsToListByListId(id, items, LIST_ENDPOINT);
    }

    public Response removeItemsByListId(int id, TMDBListItems items) {
        return baseRequest.deleteItemsFromListByListId(id, items, LIST_ENDPOINT);
    }

    public static ListResponse extractListResponse(Response response) {
        return response.then().extract().as(ListResponse.class);
    }

    public static ListItemsResponse extractListItemResponse(Response response) {
        return response.then().extract().as(ListItemsResponse.class);
    }
}
