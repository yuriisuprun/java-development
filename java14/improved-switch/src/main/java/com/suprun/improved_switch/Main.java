package com.suprun.improved_switch;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        int stage = ThreadLocalRandom.current().nextInt(1, 6);
        System.out.println(SwitchExpressionExamples.seasonName(stage));

        Season season = Season.SPRING;
        System.out.println(SwitchExpressionExamples.weatherFor(season));
    }
}
