package com.enel.permitting.config;


public final class ApplicationConstants {

	/** ############################# API CONSTANTS ####################################### */	
	public static final String X_API_KEY = "X-API-Key";
    
	public static final String API_OWNER = "Enel";
	public static final String API_OWNER_LICENSE_URL="";
	public static final String API_VERSION = "1.0";
	public static final String BASE_BUSINESS_PROCESS_URL = "/permitting";

	public static final String API_DOCUMENTATION_TITLE = "Grid Activity API";
	public static final String API_DOCUMENTATION_DESCRITPTION = "<h3>General</h3>"
            + "<p>Permitting component, thru a set of REST calls, "
            + "offers the chance to manage a set of \"Model-Entities\" intended to enrich Enel portfolio.</p>";
	public static final String API_DOCUMENTATION_TAGS = "Model-Entities";
	public static final String API_STANDARD_JSON_FORMAT = "application/json; charset=UTF-8";
    public static final String API_DOCUMENTATION_OPERATION = "Create a \"Model-Entity\" Resource";
    public static final String API_DOCUMENTATION_OPERATION_NOTES = "Returns the URL of the new resource in the Location header.";
	public static final String API_PACKAGE_DEFINITION = "com.enel.permitting.controller";
	
	/** ############################# LIST STORE PROCEDURES  ####################################### */
	
	public static final String STORE_PROCEDURE_1 = "ARDESIAI.PCK_GEST_FASCREAL.SALVA_FASCICOLO_REALE";
	public static final String STORE_PROCEDURE_2 = "COMMONSERVICES.P#ENVIRONMENT.STARTSESSION";
	public static final String STORE_PROCEDURE_3 = "COMMONSERVICES.P#ENVIRONMENT.ENDSESSION";
	
	/** ############################# ERROR MESSAGES ####################################### */	
	public static final String SUCCESS_MESSAGE = "Successful operation";
	public static final String BAD_REQUEST_MESSAGE = "Invalid data supplied";
	public static final String INTERNAL_ERROR_MESSAGE = "Internal server error";

	public static final String APPLICATION_SCHEMA_REFERENCE = "ARDESIA";
	public static final String APPLICATION_INIZIALIZATION = "ARDESIA_ONLINE";
	private ApplicationConstants() {
		// Constants
	}

}
