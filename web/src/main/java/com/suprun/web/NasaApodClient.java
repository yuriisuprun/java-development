package com.suprun.web;

import java.io.IOException;
import java.util.Objects;

/**
 * Client for NASA's Astronomy Picture of the Day (APOD) API.
 *
 * @author Yurii_Suprun
 */
public final class NasaApodClient {

    static final String DEFAULT_BASE_URL = "https://api.nasa.gov/planetary/apod";
    static final String DEMO_API_KEY = "DEMO_KEY";

    private final HttpGetClient httpClient;
    private final String apiKey;
    private final String baseUrl;

    public NasaApodClient() {
        this(new HttpGetClient(), DEMO_API_KEY, DEFAULT_BASE_URL);
    }

    NasaApodClient(HttpGetClient httpClient, String apiKey, String baseUrl) {
        this.httpClient = Objects.requireNonNull(httpClient, "httpClient must not be null");
        this.apiKey = Objects.requireNonNull(apiKey, "apiKey must not be null");
        this.baseUrl = Objects.requireNonNull(baseUrl, "baseUrl must not be null");
    }

    /**
     * Fetches today's APOD entry from the NASA API.
     *
     * @return HTTP status code and JSON response body
     * @throws IOException          if the request fails
     * @throws InterruptedException if the request is interrupted
     */
    public HttpResult fetchApod() throws IOException, InterruptedException {
        return httpClient.get(baseUrl + "?api_key=" + apiKey);
    }
}
