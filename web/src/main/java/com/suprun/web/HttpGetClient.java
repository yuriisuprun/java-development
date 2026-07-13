package com.suprun.web;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

/**
 * Default implementation of HTTP client for making GET requests using Java's built-in {@link HttpClient}.
 * This class implements the {@link HttpClientInterface} for dependency injection and testability.
 *
 * @author Yurii_Suprun
 */
public final class HttpGetClient implements HttpClientInterface {

    private final HttpClient httpClient;

    public HttpGetClient() {
        this(HttpClient.newHttpClient());
    }

    HttpGetClient(HttpClient httpClient) {
        this.httpClient = Objects.requireNonNull(httpClient, "httpClient must not be null");
    }

    /**
     * Sends a GET request to the given URL.
     *
     * @param url target URL
     * @return HTTP result containing status code and response body
     * @throws HttpClientException if the request fails
     */
    @Override
    public HttpResult get(String url) throws HttpClientException {
        Objects.requireNonNull(url, "url must not be null");

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return new HttpResult(response.statusCode(), response.body());
        } catch (IOException | InterruptedException e) {
            throw new HttpClientException("Failed to execute GET request to: " + url, e);
        }
    }
}
