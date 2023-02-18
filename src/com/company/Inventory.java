package com.company;

import java.util.ArrayList;

public class Inventory {
    private Database productDb;
    private Database transactionDb;

    private ArrayList<Product> products;
    private ArrayList<Transaction> transactions;

    /**
     * Used to instantiate the Inventory class.
     */
    public Inventory() {
        productDb = new Database("products.txt");
        transactionDb = new Database("transactions.txt");
        products = new ArrayList<>();
        transactions = new ArrayList<>();

        // retrieve the list of products from the file.
        productDb.retrieve().forEach(
            record ->
            products.add(new Product(
                record.get("name"),
                record.get("description"),
                Double.parseDouble(record.get("price")),
                Integer.parseInt(record.get("quantity"))
                )
            )
        );

        // retrieve the history of transactions from the file.
        transactionDb.retrieve().forEach(
            record ->
            transactions.add(new Transaction(
                record.get("date"),
                record.get("name"),
                Double.parseDouble(record.get("price")),
                Integer.parseInt(record.get("quantity")),
                Double.parseDouble(record.get("payment"))
                )
            )
        );
    }

    /**
     * Used to get the size of the inventory.
     * @return number of products in the inventory.
     */
    public int getSize() {
        return products.size();
    }

    /**
     * Used to show the list of products.
     * @param hasExit show the exit option.
     */
    public void showProducts(boolean hasExit) {
        // check if no product exists in the inventory.
        if(products.isEmpty()) {
            System.out.println("Sorry but the inventory is empty");
        }
        else {
            // iterate through the list of products.
            products.forEach(
                    // display each product and its details in the user's screen.
                    product ->
                            System.out.printf("[%d] %s - %s - %.2f - %d\n",
                                    products.indexOf(product),
                                    product.getName(),
                                    product.getDescription(),
                                    product.getPrice(),
                                    product.getQuantity()
                            )
            );
        }

        // show an exit option.
        if(hasExit) {
            System.out.printf("[%d] to exit...", products.size());
        }

        System.out.println();
    }

    /**
     * Used to add a new product to the list.
     * @param name name of the product.
     * @param description description of the product.
     * @param price price of the product.
     * @param quantity quantity of the product.
     */
    public void addProduct(String name, String description, double price, int quantity) {
        // iterate through the list of products
        for(Product product : products) {
            // check if the name of the product already exists.
            if(product.getName().equals(name)) {
                System.out.println("Product already exists!");
                return;
            }
        }

        // add a new object of the Product to the list of products.
        products.add(new Product(name, description, price, quantity));
        // update the file with the new list of products.
        productDb.update(products);
    }

    /**
     * Used to remove an existing product from the list.
     * @param index index of the product.
     */
    public void removeProduct(int index) {
        // remove the product on index n.
        products.remove(index);
        // update the file with the new list of products
        productDb.update(products);
    }

    /**
     * Used to modify an existing product
     * @param index index of the product.
     * @param field field name of the product.
     * @param value new value of the field.
     */
    public void editProduct(int index, ProductField field, String value) {
        // check the field that is going to be modified and replace the value.
        switch(field) {
            case NAME : products.get(index).setName(value); break;
            case DESCRIPTION : products.get(index).setDescription(value); break;
            case PRICE : products.get(index).setPrice(Double.parseDouble(value)); break;
            case QUANTITY : products.get(index).setQuantity(Integer.parseInt(value)); break;
        }
        // update the file with the new list of products.
        productDb.update(products);
    }

    /**
     * Used to get a specific product
     * @param index index of the product
     * @return product requested
     */
    public Product getProduct(int index) {
        return products.get(index);
    }

    /**
     * Used to display the transaction history of the system.
     */
    public void showTransactions() {
        // display the header of the table.
        System.out.println("Date\t\t\tUsername\t\tPrice\t\tQuantity\tPayment");
        // iterate to the history of transactions
        transactions.forEach(
                //  display each transaction to the screen of the user.
                transaction ->
                System.out.printf("%s\t\t%s\t\t%.2f\t\t%d\t\t\t%.2f\n",
                transaction.getDate(),
                transaction.getName(),
                transaction.getPrice(),
                transaction.getQuantity(),
                transaction.getPayment()
            )
        );
    }

    /**
     * Used to update the transaction history in the system.
     * @param name name of the transaction.
     * @param price price of the product.
     * @param quantity quantity of the product
     * @param payment payment of the user.
     */
    public void updateTransaction(String name, double price, int quantity, double payment) {
        // add a new object of Transaction to the records of transaction.
        transactions.add(new Transaction(name, price, quantity, payment));
        // update the file with the new transaction history.
        transactionDb.update(transactions);
    }

}
