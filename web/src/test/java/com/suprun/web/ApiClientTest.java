package com.suprun.web;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiClientTest {

    @Test
    void buildsUrlWithPathAndParams() {
        TestApiClient client = new TestApiClient();

        String url = client.buildUrl("", "api_key=demo");
        assertThat(url).isEqualTo("https://api.example.com?api_key=demo");
    }

    @Test
    void buildsUrlWithMultipleParams() {
        TestApiClient client = new TestApiClient();

        String url = client.buildUrl("", "key=value", "foo=bar");
        assertThat(url).isEqualTo("https://api.example.com?key=value&foo=bar");
    }

    @Test
    void buildsUrlWithPath() {
        TestApiClient client = new TestApiClient();

        String url = client.buildUrl("/endpoint", "api_key=demo");
        assertThat(url).isEqualTo("https://api.example.com/endpoint?api_key=demo");
    }

    /**
     * Minimal test implementation of ApiClient for testing URL building.
     */
    private static class TestApiClient extends ApiClient {
        TestApiClient() {
            super(new MockHttpClient(), "https://api.example.com", "test-key");
        }
    }

    /**
     * Mock HTTP client for testing.
     */
    private static class MockHttpClient implements HttpClientInterface {
        @Override
        public HttpResult get(String url) throws HttpClientException {
            return new HttpResult(200, "{}");
        }
    }
}
