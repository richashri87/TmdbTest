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
	private RequestSpecification httpRequest;

	BaseRequest() {
		init();
	}

	void init() {
		PropertiesHelper propertiesHelper = new PropertiesHelper();
		baseURI = propertiesHelper.readEnvironmentPropertyFile().getProperty("baseURI");
		apiKey = propertiesHelper.readEnvironmentPropertyFile().getProperty("api_key");
		authorization = propertiesHelper.readEnvironmentPropertyFile().getProperty("write_access_token");
		httpRequest = RestAssured.given()
				.baseUri(baseURI)
				.contentType("application/json;charset=utf-8")
				.header("authorization", authorization)
				.filter(new ResponseLoggingFilter())
				.filter(new RequestLoggingFilter());
	}

	public Response create(Object object, String endpoint) {
		return httpRequest.basePath(endpoint).body(object).post();
		
	}

	public Response getById(int listId,String endpoint) {
		return httpRequest.basePath(endpoint)
				.queryParam("api_key", apiKey)
				.queryParam("page", "1")
		         .pathParam("id", listId).get("/{id}");
	}

	public Response deleteById(int listId,String endpoint) {
		return httpRequest.basePath(endpoint)
				.pathParam("id", listId).delete("/{id}");
	}

	public Response updateById(int id,String endpoint) {
		return httpRequest
				.basePath(endpoint)
				.pathParam("id", id)
				.body("{\"description\":\"This list is updated by automation.\"}")
				.put("/{id}");
		
	}

	public Response clearById(int id,String endpoint) {
		return httpRequest
				.basePath(endpoint)
				.pathParam("id", id)
				.get("/{id}/clear");

	}

	public Response addItem(int id, String endpoint) {
		return httpRequest.basePath(endpoint)
				.pathParam("id", id)
				.body("{\"items\":[{\"media_type\":\"movie\",\"media_id\":550},{\"media_type\":\"movie\",\"media_id\":244786},{\"media_type\":\"tv\",\"media_id\":1396}]}")
				.post();

	}
	public Response updateItem(int id, String endpoint) {
		return httpRequest.basePath(endpoint)
				.pathParam("id", id)
				.body("{\"items\":[{\"media_type\":\"movie\",\"media_id\":194662,\"comment\":\"Amazing movie!\"},{\"media_type\":\"movie\",\"media_id\":76203,\"comment\":\"Wow.\"}]}")
				.put();

	}
	public Response removeItem(int id, String endpoint) {
		return httpRequest.basePath(endpoint)
				.pathParam("id", id)
				.body("{\"items\":[{\"media_type\":\"movie\",\"media_id\":194662},{\"media_type\":\"movie\",\"media_id\":76203},{\"media_type\":\"movie\",\"media_id\":74643}]}")
				.delete();

	}
}
