package com.enel.permitting.exception;

public class NetworkActivityMessageException extends RuntimeException {

    /** Serial version unique identifier. */
    private static final long serialVersionUID = 1L;

    /** Default error code. */
    private static final int DEFAULT_ERROR_CODE = 520;

    /** Application error code. */
    private final int errorCode;


    public NetworkActivityMessageException(final String message) {
        super(message);
        errorCode = DEFAULT_ERROR_CODE;
    }

    
    public NetworkActivityMessageException(final String message, final Throwable cause) {
        super(message, cause);
        errorCode = DEFAULT_ERROR_CODE;
    }

    
    public NetworkActivityMessageException(final int anErrorCode, final String message,
                       final Throwable cause) {
        super(message, cause);
        errorCode = anErrorCode;
    }

    
    public int getErrorCode() {
        return errorCode;
    }
}
