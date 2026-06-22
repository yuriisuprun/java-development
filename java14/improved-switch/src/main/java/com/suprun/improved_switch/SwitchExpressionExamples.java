package com.suprun.improved_switch;

public final class SwitchExpressionExamples {

    private SwitchExpressionExamples() {
    }

    public static String seasonName(int stage) {
        return switch (stage) {
            case 1 -> "Winter";
            case 2 -> "Spring";
            case 3 -> "Summer";
            case 4 -> "Autumn";
            default -> "Invalid stage";
        };
    }

    public static String weatherFor(Season season) {
        return switch (season) {
            case WINTER -> "Snowy";
            case SPRING -> "Warm";
            case SUMMER -> "Sunny";
            case FALL -> "Rainy";
        };
    }
}
