package com.suprun.comparablecomparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product class implementing Comparable interface.
 * Demonstrates natural ordering by price.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Comparable<Product> {
    private String name;
    private double price;
    private int quantity;

    /**
     * Natural ordering by price (ascending).
     *
     * @param other the Product to compare with
     * @return negative if this product is cheaper, 0 if same price, positive if more expensive
     */
    @Override
    public int compareTo(Product other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare with null");
        }
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
