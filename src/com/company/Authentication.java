package com.company;

import java.util.ArrayList;

public class Authentication {
    private Database accountDb;
    private ArrayList<Account> accounts;
    private Account activeAccount;

    /**
     * Used to instantiate the Authentication class.
     */
    public Authentication() {
        accountDb = new Database("accounts.txt");
        accounts = new ArrayList<>();

        // iterate through the retrieved contents from the file.
        accountDb.retrieve().forEach(
                // create a new object of Account using the contents.
                // add the created object to the list of Account objects.
                account -> accounts.add(new Account(
                account.get("username"),
                account.get("password"),
                AccountType.valueOf(account.get("type"))
                )
            )
        );
    }

    /**
     * Used to get the logged in account.
     * @return current active account.
     */
    public Account getActiveAccount() {
        return activeAccount;
    }

    /**
     * Used to give authentication based on the user's credential
     * @param username username of the user.
     * @param password password of the user.
     */
    public void login(String username, String password) {
        // iterate through the list of accounts.
        if(!accounts.isEmpty()) {
            for(Account account : accounts) {
                // check if the username exist and if the password matches.
                if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                    activeAccount = account;
                    return;
                }
                // check if the username is existing.
                else if (account.getUsername().equals(username)) {
                    System.out.println("Password does not match!\n");
                    return;
                }
            }
        }

        // if user does not exist
        System.out.println("User does not exist!\n");
    }

    /**
     * Used to create a new account.
     * @param username username of the user.
     * @param password password of the user.
     * @param type account type of the user.
     */
    public void register(String username, String password, String type) {
        AccountType accountType;

        // check if the username is unique.
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                System.out.println("User already exists!");
                return;
            }
        }

        // ensures the strength of the password entered by the user.
        if(password.length() < 8) {
            System.out.println("Password must be at least 8 characters long!");
            return;
        }
        else if(password.equals(password.toLowerCase())) {
            System.out.println("Password must contain uppercase characters!");
            return;
        }
        else if(password.equals(password.toUpperCase())) {
            System.out.println("Password must contain lowercase characters!");
            return;
        }
        else if(!password.matches(".*\\d.*")) {
            System.out.println("Password must contain digits!");
            return;
        }
        else if(!password.matches("[A-Za-z0-9]*")) {
            System.out.println("Password can only contain letters and digits!");
            return;
        }
        else {
            try {
                accountType = AccountType.valueOf(type.toUpperCase());
                // create the account and append it to the list of accounts.
                accounts.add(new Account(username, password, accountType));
                // update the file with the new list of accounts.
                accountDb.update(accounts);

                System.out.println("Successfully registered!");
            }
            catch(IllegalArgumentException illegalArgumentException) {
                System.out.println("Invalid account type! You can only be an ADMIN or CONSUMER.");
            }
        }
    }
}
