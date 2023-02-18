package com.company;

public class Product {
    private String name;
    private String description;
    private double price;
    private int quantity;

    public Product(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Used to get the name of the product.
     * @return name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Used to set the name of the product.
     * @param name name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Used to get the description of the product.
     * @return description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Used to set the description of the product.
     * @param description description of the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Used to get the price of the product.
     * @return price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Used to set the price of the product.
     * @param price of the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Used to get the quantity of the product.
     * @return quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Used to set the quantity of the product.
     * @param quantity quantity of the product.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Used to create a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format(
            "name='%s', description='%s', price='%.2f', quantity='%d'",
            this.name,
            this.description,
            this.price,
            this.quantity
        );
    }
}
