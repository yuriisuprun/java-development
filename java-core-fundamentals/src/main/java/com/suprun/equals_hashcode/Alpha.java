package com.suprun.equals_hashcode;

import java.util.Objects;

/**
 * Alpha demonstrates proper implementation of equals() and hashCode() methods.
 * This class is used in hash-based collections like HashMap and HashSet.
 *
 * @author Yurii_Suprun
 */
public class Alpha {
    private final String property;

    /**
     * Constructs an Alpha instance with the specified property.
     *
     * @param property the property value
     */
    public Alpha(String property) {
        this.property = property;
    }

    /**
     * Compares this Alpha object with another object for equality.
     * Two Alpha objects are equal if they have the same property value.
     *
     * @param o the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alpha alpha = (Alpha) o;
        return Objects.equals(property, alpha.property);
    }

    /**
     * Returns the hash code for this Alpha object.
     * Objects that are equal must have the same hash code.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(property);
    }
}
