package com.suprun.web;

import java.util.Objects;

/**
 * Base class for REST API clients.
 * Provides common functionality for building and executing API requests.
 *
 * @author Yurii_Suprun
 */
public abstract class ApiClient {

    protected final HttpClientInterface httpClient;
    protected final String baseUrl;
    protected final String apiKey;

    /**
     * Constructs an ApiClient with the provided HTTP client, base URL, and API key.
     *
     * @param httpClient HTTP client implementation
     * @param baseUrl    base URL for the API
     * @param apiKey     API key for authentication
     */
    protected ApiClient(HttpClientInterface httpClient, String baseUrl, String apiKey) {
        this.httpClient = Objects.requireNonNull(httpClient, "httpClient must not be null");
        this.baseUrl = Objects.requireNonNull(baseUrl, "baseUrl must not be null");
        this.apiKey = Objects.requireNonNull(apiKey, "apiKey must not be null");
    }

    /**
     * Builds a URL with the given path and query parameters.
     *
     * @param path    API endpoint path
     * @param params  query parameters (key=value format)
     * @return complete URL
     */
    protected String buildUrl(String path, String... params) {
        StringBuilder url = new StringBuilder(baseUrl);
        if (path != null && !path.isEmpty()) {
            url.append(path);
        }
        if (params.length > 0) {
            url.append("?");
            for (int i = 0; i < params.length; i++) {
                if (i > 0) {
                    url.append("&");
                }
                url.append(params[i]);
            }
        }
        return url.toString();
    }

    /**
     * Executes a GET request to the specified URL.
     *
     * @param url target URL
     * @return HTTP result
     * @throws HttpClientException if the request fails
     */
    protected HttpResult executeGet(String url) throws HttpClientException {
        return httpClient.get(url);
    }
}
