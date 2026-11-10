package com.suprun.comparablecomparator;

/**
 * Product class implementing Comparable interface.
 * Demonstrates natural ordering by price.
 */
public class Product implements Comparable<Product> {
    private String name;
    private double price;
    private int quantity;

    /**
     * No-argument constructor.
     */
    public Product() {
    }

    /**
     * All-argument constructor.
     *
     * @param name the product name
     * @param price the product price
     * @param quantity the product quantity
     */
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets the name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price.
     *
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the quantity.
     *
     * @return the product quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     *
     * @param quantity the product quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.price, price) != 0) return false;
        if (quantity != product.quantity) return false;
        return name != null ? name.equals(product.name) : product.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + quantity;
        return result;
    }
}
