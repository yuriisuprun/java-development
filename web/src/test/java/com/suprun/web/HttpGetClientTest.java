package com.suprun.web;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HttpGetClientTest {

    @Test
    void returnsStatusCodeAndBody() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/resource", exchange -> {
            byte[] response = "{\"message\":\"hello\"}".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.close();
        });
        server.start();

        try {
            int port = server.getAddress().getPort();
            HttpResult result = new HttpGetClient().get("http://localhost:" + port + "/resource");

            assertThat(result.statusCode()).isEqualTo(200);
            assertThat(result.body()).contains("hello");
        } finally {
            server.stop(0);
        }
    }

    @Test
    void rejectsNullUrl() {
        assertThatThrownBy(() -> new HttpGetClient().get(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("url must not be null");
    }
}
