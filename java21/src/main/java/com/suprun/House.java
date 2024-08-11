package com.suprun;

/**
 * @author Yurii_Suprun
 */
public record House(String address, int numberOfBedrooms, boolean hasBasement) implements Building {
}
