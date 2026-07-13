package com.suprun.web;

/**
 * Entry point for web client demonstrations.
 *
 * @author Yurii_Suprun
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        try {
            HttpResult result = new NasaApodClient().fetchApod();
            System.out.println("Response Code: " + result.statusCode());
            System.out.println("Response Body: " + result.body());
        } catch (HttpClientException e) {
            System.err.println("Failed to fetch APOD: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
