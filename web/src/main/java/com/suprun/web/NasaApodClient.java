package com.suprun.web;

import java.util.Objects;

/**
 * Client for NASA's Astronomy Picture of the Day (APOD) API.
 * Extends {@link ApiClient} to provide specialized access to the APOD endpoint.
 *
 * @author Yurii_Suprun
 */
public final class NasaApodClient extends ApiClient {

    static final String DEFAULT_BASE_URL = "https://api.nasa.gov/planetary/apod";
    static final String DEMO_API_KEY = "DEMO_KEY";

    public NasaApodClient() {
        this(new HttpGetClient(), DEFAULT_BASE_URL, DEMO_API_KEY);
    }

    NasaApodClient(HttpClientInterface httpClient, String baseUrl, String apiKey) {
        super(httpClient, baseUrl, apiKey);
    }

    /**
     * Fetches today's APOD entry from the NASA API.
     *
     * @return HTTP result containing status code and JSON response body
     * @throws HttpClientException if the request fails
     */
    public HttpResult fetchApod() throws HttpClientException {
        String url = buildUrl("", "api_key=" + apiKey);
        return executeGet(url);
    }
}
