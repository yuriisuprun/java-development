package com.suprun.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mock HTTP client for testing purposes.
 * Records all requests and allows configuring responses.
 *
 * @author Yurii_Suprun
 */
public class MockHttpClient implements HttpClientInterface {

    private final List<String> requestUrls = new ArrayList<>();
    private HttpResult response = new HttpResult(200, "{}");

    /**
     * Sets the response that will be returned by subsequent get() calls.
     *
     * @param response the HTTP result to return
     */
    public void setResponse(HttpResult response) {
        this.response = Objects.requireNonNull(response, "response must not be null");
    }

    /**
     * Gets the list of all URLs that were requested.
     *
     * @return list of request URLs
     */
    public List<String> getRequestUrls() {
        return new ArrayList<>(requestUrls);
    }

    /**
     * Clears the list of recorded requests.
     */
    public void clearRequests() {
        requestUrls.clear();
    }

    @Override
    public HttpResult get(String url) throws HttpClientException {
        Objects.requireNonNull(url, "url must not be null");
        requestUrls.add(url);
        return response;
    }
}
