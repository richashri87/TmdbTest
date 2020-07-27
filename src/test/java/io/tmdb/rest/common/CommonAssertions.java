package io.tmdb.rest.common;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommonAssertions {
	private static String CREATE_STATUS_MESSAGE = "The item/record was created successfully.";
	private static String UPDATE_STATUS_MESSAGE = "The item/record was updated successfully.";
	private static String SUCCESS = "Success.";

	public static void assertStatusCode(Integer actual, Integer expected) {
		assertThat("Status code does not match", actual ,equalTo(expected));
	}
	public static void assertStatusMessage(String actual, String action) {
		if(action.equalsIgnoreCase("create"))
			assertThat("Status message does not match", actual ,equalTo(CREATE_STATUS_MESSAGE));
		else if(action.equalsIgnoreCase("update"))
			assertThat("Status message does not match", actual ,equalTo(UPDATE_STATUS_MESSAGE));
		else if(action.equalsIgnoreCase("Success"))
			assertThat("Status message does not match", actual ,equalTo(SUCCESS));
	}
}
