package com.suprun.equals_hashcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Yurii_Suprun
 */
public class Alpha {
    private final String property;

    public Alpha(String property) {
        this.property = property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alpha alpha = (Alpha) o;
        return Objects.equals(property, alpha.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(property);
    }
}

class Main {

    public static void main(String[] args) {
        Map<Alpha, Integer> map = new HashMap<>();
        var alpha = new Alpha("x");
        map.put(alpha, 1);
    }
}
