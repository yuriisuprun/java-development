package com.suprun.web;

/**
 * Result of an HTTP GET request.
 *
 * @param statusCode HTTP status code
 * @param body       response body
 * @author Yurii_Suprun
 */
public record HttpResult(int statusCode, String body) {
}
