package com.suprun.web;

/**
 * Interface for HTTP GET clients. Allows multiple implementations and easier testing.
 *
 * @author Yurii_Suprun
 */
public interface HttpClientInterface {

    /**
     * Sends a GET request to the given URL.
     *
     * @param url target URL
     * @return HTTP result containing status code and response body
     * @throws HttpClientException if the request fails
     */
    HttpResult get(String url) throws HttpClientException;
}
