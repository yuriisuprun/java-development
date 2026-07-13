package com.suprun.web;

/**
 * Exception thrown when an HTTP client operation fails.
 * This exception wraps underlying I/O and interruption exceptions.
 *
 * @author Yurii_Suprun
 */
public class HttpClientException extends Exception {

    /**
     * Constructs an HttpClientException with the given message and cause.
     *
     * @param message descriptive error message
     * @param cause   the underlying exception
     */
    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an HttpClientException with the given message.
     *
     * @param message descriptive error message
     */
    public HttpClientException(String message) {
        super(message);
    }
}
