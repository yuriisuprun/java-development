package com.suprun.improved_switch;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SwitchExpressionExamplesTest {

    @Test
    void mapsStageToSeasonName() {
        assertThat(SwitchExpressionExamples.seasonName(1)).isEqualTo("Winter");
        assertThat(SwitchExpressionExamples.seasonName(2)).isEqualTo("Spring");
        assertThat(SwitchExpressionExamples.seasonName(3)).isEqualTo("Summer");
        assertThat(SwitchExpressionExamples.seasonName(4)).isEqualTo("Autumn");
    }

    @Test
    void returnsInvalidStageForUnknownStage() {
        assertThat(SwitchExpressionExamples.seasonName(0)).isEqualTo("Invalid stage");
        assertThat(SwitchExpressionExamples.seasonName(5)).isEqualTo("Invalid stage");
    }

    @Test
    void mapsSeasonToWeather() {
        assertThat(SwitchExpressionExamples.weatherFor(Season.WINTER)).isEqualTo("Snowy");
        assertThat(SwitchExpressionExamples.weatherFor(Season.SPRING)).isEqualTo("Warm");
        assertThat(SwitchExpressionExamples.weatherFor(Season.SUMMER)).isEqualTo("Sunny");
        assertThat(SwitchExpressionExamples.weatherFor(Season.FALL)).isEqualTo("Rainy");
    }
}
