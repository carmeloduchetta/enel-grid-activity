package com.enel.permitting.config;


public final class ApplicationConstants {

	/** ############################# API CONSTANTS ####################################### */	
	public static final String X_API_KEY = "X-API-Key";
    
	public static final String API_OWNER = "Enel";
	public static final String API_OWNER_LICENSE_URL="";
	public static final String API_VERSION = "1.0";
	public static final String BASE_BUSINESS_PROCESS_URL = "/ga";

	public static final String API_DOCUMENTATION_TITLE = "Grid Activity API";
	public static final String API_DOCUMENTATION_DESCRITPTION = "<h3>General</h3>"
            + "<p>Dossier component, thru a set of REST calls, "
            + "offers the chance to manage a set of \"Dossier \" intended to enrich Enel portfolio.</p>";
	public static final String API_DOCUMENTATION_TAGS = "Dossiers";
	public static final String API_STANDARD_JSON_FORMAT = "application/json; charset=UTF-8";
    public static final String API_DOCUMENTATION_OPERATION = "Create a \"Dossier\" Resource";
    public static final String API_DOCUMENTATION_OPERATION_NOTES = "Returns the URL of the new resource in the Location header.";
	public static final String API_PACKAGE_DEFINITION = "com.enel.virtualentity.controller";
	public static final String API_PAGE_NUMBER_DEFAULT = "0";
	public static final String API_PAGE_SIZE_DEFAULT = "10";
	
	/** ############################# DOSSIER DB SEQUENCE PARAMS ####################################### */
		
	public static final String DOSSIER_SEQUENCE_NAME = "dossier_sequence"; 
	public static final String DOSSIER_SEQUENCE_START = "1000000";
	public static final String DOSSIER_SEQUENCE_INCREMENT_SIZE = "1";

	/** ############################# LEGACY INIZIALIZATION PARAMS ####################################### */

	public static final String LEGACY_APPLICATION_INIZIALIZATION = "ARDESIA_ONLINE";
	
	/** ############################# ERROR MESSAGES ####################################### */	
	public static final String SUCCESS_MESSAGE = "Successful operation";
	public static final String BAD_REQUEST_MESSAGE = "Invalid data supplied";
	public static final String INTERNAL_ERROR_MESSAGE = "Internal server error";

    public static final String PL_SQL_PROCEDURE_INIZIALIZATION = "CONSTANT VALUE NEEDED TO INIZIALIZE SOME PL-SQL PROCEDURES"; //Example: "ARDESIA_ONLINE" 
	
	private ApplicationConstants() {

	}

}
