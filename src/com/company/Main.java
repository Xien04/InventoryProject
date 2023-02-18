package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while(true) {
            Authentication authentication = new Authentication();
            boolean flag = true;
            int exit = 0;

            while(authentication.getActiveAccount() == null) {
                try {
                    System.out.println("Running system...");
                    System.out.println("[1] Create an account");
                    System.out.println("[2] Log-in");
                    System.out.print(">> ");
                    int acc = sc.nextInt();
                    sc.nextLine();
                    switch (acc) {
                        case 1:
                            System.out.println("\nCreate an account:");
                            System.out.print("Username: ");
                            String username = sc.nextLine();
                            System.out.print("Password: ");
                            String password = sc.nextLine();
                            System.out.print("Select account type: ");
                            String type = sc.nextLine();
                            System.out.println();
                            authentication.register(username, password, type);
                            System.out.println();
                            break;
                        case 2:
                            System.out.println("\nAccount...");
                            System.out.print("Username: ");
                            username = sc.nextLine();
                            System.out.print("Password: ");
                            password = sc.nextLine();
                            System.out.println();
                            authentication.login(username, password);
                            break;
                        default:
                            System.out.println("Invalid input");
                    }
                }
                catch(InputMismatchException e) {
                    System.out.println();
                    System.out.println("An error occurred with your input. Please try again");
                    System.out.println();
                    sc.next();
                }
            }

            while(flag) {
                if(authentication.getActiveAccount().getType() == AccountType.CONSUMER) {
                    try {
                        int prod = 0;
                        System.out.println("Welcome " + authentication.getActiveAccount().getUsername());
                        while(prod != inventory.getSize()) {
                            System.out.println("Displaying products...");
                            inventory.showProducts(true);
                            System.out.print("\nWhat do you want to purchase? ");
                            prod = sc.nextInt();
                            System.out.println();
                            if(prod < 0 || prod > inventory.getSize()) {
                                System.out.println("Product does not exist!\n");
                            }
                            else if(prod == inventory.getSize()) {
                                flag = false;
                            }
                            else {
                                Product product = inventory.getProduct(prod);
                                System.out.print("Enter quantity: ");
                                int quantity = sc.nextInt();
                                sc.nextLine();
                                System.out.print("Enter payment: ");
                                double payment = sc.nextDouble();
                                sc.nextLine();

                                System.out.println();
                                if(quantity > product.getQuantity()) {
                                    System.out.println("Sorry but we're low on stock for this product.");
                                }
                                else if(quantity * product.getPrice() > payment) {
                                    System.out.println("Sorry but you don't have enough funds.");
                                }
                                else {
                                    inventory.editProduct(prod, ProductField.QUANTITY, String.valueOf(product.getQuantity() - quantity));
                                    inventory.updateTransaction(product.getName(), product.getPrice(), quantity, payment);
                                    System.out.println("Purchase successful, your change is " + (payment - product.getPrice() * quantity));
                                }
                                System.out.println();
                            }
                        }
                    }catch(InputMismatchException e) {
                        System.out.println();
                        System.out.println("The system has encountered an error");
                        System.out.println();
                        sc.next();
                    }
                }
                else if(authentication.getActiveAccount().getType() == AccountType.ADMIN) {
                    try {
                        System.out.println("Welcome " + authentication.getActiveAccount().getUsername());
                        int act = 0;
                        while (act != 3) {
                            System.out.println("[1] Maintenance.");
                            System.out.println("[2] Display Transactions.");
                            System.out.println("[3] Exit");
                            System.out.println();
                            System.out.print("Choose an action: ");
                            act = sc.nextInt();
                            String fill = sc.nextLine();
                            System.out.println();

                            try {
                                switch (act) {
                                    case 1:
                                        int action = 0;
                                        while (flag) {
                                            System.out.println("Maintenance is on going...");

                                            System.out.println("[1] Display Products ");
                                            System.out.println("[2] Add Product ");
                                            System.out.println("[3] Edit Product ");
                                            System.out.println("[4] Remove Product ");
                                            System.out.println("[5] Exit. ");
                                            System.out.println();

                                            System.out.print("Choose a maintenance feature: ");
                                            action = sc.nextInt();
                                            fill = sc.nextLine();

                                            switch (action) {
                                                case 1:
                                                    try {
                                                        System.out.println();
                                                        System.out.println("The Available Products are...");
                                                        inventory.showProducts(false);
                                                    } catch (InputMismatchException e) {
                                                        System.out.println();
                                                        System.out.println("Please input a valid integer value.");
                                                        System.out.println();
                                                        sc.next();
                                                    } catch (Exception e) {
                                                        System.out.println();
                                                        System.out.println("The system encountered an error.");
                                                        System.out.println();
                                                        sc.next();
                                                    }
                                                    break;
                                                case 2:
                                                    try {
                                                        System.out.println();
                                                        System.out.println("Adding Products...");
                                                        System.out.print("Enter product name: ");
                                                        String name = sc.nextLine();
                                                        System.out.print("Enter product description: ");
                                                        String desc = sc.nextLine();
                                                        System.out.print("Enter product price: ");
                                                        double price = sc.nextDouble();
                                                        System.out.print("Enter product quantity: ");
                                                        int qty = sc.nextInt();
                                                        System.out.println();
                                                        inventory.addProduct(name, desc, price, qty);
                                                        System.out.println("Product added successfully!");
                                                        System.out.println();

                                                    } catch (InputMismatchException e) {
                                                        System.out.println();
                                                        System.out.println("Please input a valid integer value. ");
                                                        System.out.println();
                                                        sc.next();
                                                    } catch (Exception e) {
                                                        System.out.println();
                                                        System.out.println("The system encountered an error.");
                                                        System.out.println();
                                                        sc.next();
                                                    }
                                                    break;
                                                case 3:
                                                    try {
                                                        System.out.println();
                                                        System.out.println("Editing Products...");
                                                        inventory.showProducts(false);
                                                        System.out.print("What would you like to edit? ");
                                                        int index = sc.nextInt();
                                                        System.out.println();
                                                        System.out.println("Displaying product fields...");
                                                        System.out.println("[1] Product Name ");
                                                        System.out.println("[2] Product Description ");
                                                        System.out.println("[3] Product Price ");
                                                        System.out.println("[4] Product Quantity ");
                                                        System.out.println("[5] Exit\n");
                                                        System.out.println("Which product field would you like to edit? ");
                                                        System.out.println();
                                                        int field = sc.nextInt();
                                                        fill = sc.nextLine();
                                                        switch (field) {
                                                            case 1:
                                                                System.out.print("Enter Product Name: ");
                                                                String name = sc.nextLine();
                                                                System.out.println();
                                                                System.out.println("Successfully edited Product!");
                                                                System.out.println();

                                                                inventory.editProduct(index, ProductField.NAME, name);
                                                                break;
                                                            case 2:
                                                                System.out.print("Enter Product Description: ");
                                                                String desc = sc.nextLine();
                                                                System.out.println();
                                                                System.out.println("Successfully edited Product!");
                                                                System.out.println();

                                                                inventory.editProduct(index, ProductField.DESCRIPTION, desc);
                                                                break;
                                                            case 3:
                                                                System.out.print("Enter Product Price: ");
                                                                String prc = sc.nextLine();
                                                                System.out.println();
                                                                System.out.println("Successfully edited Product!");
                                                                System.out.println();

                                                                inventory.editProduct(index, ProductField.PRICE, prc);
                                                                break;
                                                            case 4:
                                                                System.out.print("Enter Product Quantity: ");
                                                                String quanti = sc.nextLine();
                                                                System.out.println();
                                                                System.out.println("Successfully edited Product!");
                                                                System.out.println();

                                                                inventory.editProduct(index, ProductField.QUANTITY, quanti);
                                                                break;
                                                            default:
                                                                System.out.println("Please input a valid choice.");
                                                                System.out.println();
                                                                break;
                                                        }
                                                    } catch (InputMismatchException e) {
                                                        System.out.println();
                                                        System.out.println("Please input a valid integer value. ");
                                                        System.out.println();
                                                        sc.next();
                                                    } catch (IndexOutOfBoundsException e) {
                                                        System.out.println();
                                                        System.out.println("Please input an integer within the array range.");
                                                        System.out.println();
                                                        sc.next();
                                                    } catch (Exception e) {
                                                        System.out.println();
                                                        System.out.println("The system encounterd an error.");
                                                        System.out.println();
                                                        sc.next();
                                                    }
                                                    break;
                                                case 4:
                                                    try {
                                                        System.out.println();
                                                        System.out.println("Removing Products...");
                                                        inventory.showProducts(false);
                                                        System.out.print("Enter product index: ");
                                                        int index = sc.nextInt();
                                                        System.out.println();
                                                        System.out.println("Successfully removed product!");
                                                        System.out.println();

                                                        inventory.removeProduct(index);
                                                    } catch (InputMismatchException e) {
                                                        System.out.println();
                                                        System.out.println("Please input a valid integer value. ");
                                                        System.out.println();
                                                        sc.next();
                                                    } catch (IndexOutOfBoundsException e) {
                                                        System.out.println();
                                                        System.out.println("Please input an integer within the array range.");
                                                        System.out.println();
                                                        sc.next();
                                                    } catch (Exception e) {
                                                        System.out.println();
                                                        System.out.println("The system encountered an error.");
                                                        System.out.println();
                                                        sc.next();
                                                    }
                                                    break;
                                                case 5:
                                                    flag = false;
                                                    break;
                                                default:
                                                    System.out.println("Invalid input\n");
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Showing Transactions...");
                                        inventory.showTransactions();
                                        System.out.println();
                                        break;
                                    case 3:
                                        System.out.println("Thank you. Have a good day!\n");
                                        flag = false;
                                        break;
                                    default:
                                        System.out.println("Invalid input");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println();
                                System.out.println("Please input a valid integer value. ");
                                System.out.println();
                                sc.next();
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println();
                        System.out.println("Please input a valid integer value. ");
                        System.out.println();
                        sc.next();
                    }
                }
            }
        }
    }
}
