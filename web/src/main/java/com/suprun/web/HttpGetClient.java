package com.suprun.web;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

/**
 * Thin wrapper around {@link HttpClient} for GET requests.
 *
 * @author Yurii_Suprun
 */
public final class HttpGetClient {

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
     * @return status code and response body
     * @throws IOException          if the request fails
     * @throws InterruptedException if the request is interrupted
     */
    public HttpResult get(String url) throws IOException, InterruptedException {
        Objects.requireNonNull(url, "url must not be null");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new HttpResult(response.statusCode(), response.body());
    }
}
