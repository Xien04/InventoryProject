package com.company;

public class Transaction {
    private String date;
    private String name;
    private double price;
    private int quantity;
    private double payment;

    /**
     * Used to create an instance of the Transaction class
     * @param date date of the transaction.
     * @param name name of the user.
     * @param price price of the product.
     * @param quantity quantity of the product.
     * @param payment payment of the user.
     */
    public Transaction(String date, String name, double price, int quantity, double payment) {
        this.date = date;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.payment = payment;
    }

    /**
     * Used to create an instance of the Transaction class
     * @param name name of the user.
     * @param price price of the product.
     * @param quantity quantity of the product.
     * @param payment payment of the user.
     */
    public Transaction(String name, double price, int quantity, double payment) {
        this.date = String.valueOf(java.time.LocalDate.now());
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.payment = payment;
    }

    /**
     * Used to get the date of the transaction.
     * @return date of the transaction.
     */
    public String getDate() {
        return date;
    }

    /**
     * Used to get the name of the user.
     * @return name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Used to get the price of the product.
     * @return price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Used to get the quantity of the product.
     * @return quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Used to get the payment of the user.
     * @return payment of the user.
     */
    public double getPayment() {
        return payment;
    }

    /**
     * Used to create a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format(
            "date='%s', name='%s', price='%.2f', quantity='%d', payment='%.2f'",
            this.date,
            this.name,
            this.price,
            this.quantity,
            this.payment
        );
    }
}
