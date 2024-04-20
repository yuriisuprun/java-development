package com.suprun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        // URL to make the request to
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";

        // Create a URL object
        URL apiUrl = new URL(url);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Read the response body
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Print the response body
        System.out.println("Response Body: " + response.toString());

        // Disconnect the connection
        connection.disconnect();
    }
}
