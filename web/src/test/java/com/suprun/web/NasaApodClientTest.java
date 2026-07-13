package com.suprun.web;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

class NasaApodClientTest {

    @Test
    void fetchesApodWithDemoKey() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/planetary/apod", exchange -> {
            assertThat(exchange.getRequestURI().getQuery()).isEqualTo("api_key=" + NasaApodClient.DEMO_API_KEY);

            byte[] response = "{\"title\":\"Cosmic Cliffs\"}".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
            exchange.close();
        });
        server.start();

        try {
            int port = server.getAddress().getPort();
            String baseUrl = "http://localhost:" + port + "/planetary/apod";
            HttpResult result = new NasaApodClient(new HttpGetClient(), baseUrl, NasaApodClient.DEMO_API_KEY)
                    .fetchApod();

            assertThat(result.statusCode()).isEqualTo(200);
            assertThat(result.body()).contains("Cosmic Cliffs");
        } finally {
            server.stop(0);
        }
    }
}
