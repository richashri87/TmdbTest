package io.tmdb.rest.request;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.tmdb.rest.utils.PropertiesHelper;

public class BaseRequest {
    private String baseURI;
    private String apiKey;
    private String authorization;
    private RequestSpecification spec;

    BaseRequest() {
        init();
    }

    void init() {
        PropertiesHelper propertiesHelper = new PropertiesHelper();
        baseURI = propertiesHelper.readEnvironmentPropertyFile().getProperty("baseURI");
        apiKey = propertiesHelper.readEnvironmentPropertyFile().getProperty("api_key");
        authorization = propertiesHelper.readEnvironmentPropertyFile().getProperty("write_access_token");
        spec = RestAssured.given()
                .baseUri(baseURI)
                .contentType("application/json;charset=utf-8")
                .header("authorization", authorization)
                .filter(new ResponseLoggingFilter())
                .filter(new RequestLoggingFilter());
    }

    public Response create(Object object, String endpoint) {
        return spec.basePath(endpoint)
                .body(object)
                .post();

    }

    public Response getById(int listId, String endpoint) {
        return spec.basePath(endpoint)
                .queryParam("api_key", apiKey)
                .queryParam("page", "1")
                .pathParam("id", listId).get("/{id}");
    }

    public Response deleteById(int listId, String endpoint) {
        return spec.basePath(endpoint)
                .pathParam("id", listId)
                .delete("/{id}");
    }

    public Response updateById(int id, Object object, String endpoint) {

        return spec
                .basePath(endpoint)
                .pathParam("id", id)
                .body(object)
                .put("/{id}");
    }


    public Response clearById(int id, String endpoint) {
        return spec
                .basePath(endpoint)
                .pathParam("id", id)
                .get("/{id}/clear");

    }

    public Response addItemToListByListId(int id, Object object, String endpoint) {
        return spec.basePath(endpoint)
                .pathParam("id", id)
                .body(object)
                .post("/{id}/items");
    }

    public Response updateItemsToListByListId(int id, Object object, String endpoint) {

        return spec
                .basePath(endpoint)
                .pathParam("id", id)
                .body(object)
                .put("/{id}/items");
    }

    public Response deleteItemsFromListByListId(int id, Object object, String endpoint) {
        return spec.basePath(endpoint)
                .pathParam("id", id)
                .body(object)
                .delete("/{id}/items");
    }
}
