package com.suprun.enums;

/**
 * Day enum representing days of the week.
 * Each day has a display name that can be retrieved using {@link #getDisplayName()}.
 *
 * @author Yurii_Suprun
 */
public enum Day {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String displayName;

    /**
     * Private constructor for Day enum.
     *
     * @param displayName the display name for this day
     */
    Day(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name of the day.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }
}
