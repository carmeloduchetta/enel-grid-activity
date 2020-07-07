package com.enel.permitting.config;


public final class ApiConstants {

	public static final String X_API_KEY = "X-API-Key";

	public static final String API_VERSION = "1";
	private static final String BASE_URL = "/v" + API_VERSION;

	public static final String SUCCESS_MESSAGE = "Successful operation";
	public static final String BAD_REQUEST_MESSAGE = "Invalid data supplied";
	public static final String INTERNAL_ERROR_MESSAGE = "Internal server error";

	public static final String APPLICATION_INIZIALIZATION = "ARDESIA_ONLINE";
	private ApiConstants() {
		// Constants
	}

}
