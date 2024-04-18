package com.suprun.enums;

/**
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

    private String displayName;

    // Private constructor
    private Day(String displayName){
        this.displayName = displayName;
    }

    // Getter method for displayName
    public String getDisplayName() {
        return displayName;
    }
}
