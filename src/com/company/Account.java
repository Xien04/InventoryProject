package com.company;

public class Account {
    private String id;
    private String username;
    private String password;
    private AccountType type;

    /**
     * Used to create an instance of the Account class.
     * @param username username of the user.
     * @param password password of the user.
     * @param type account type of the user.
     */
    public Account(String username, String password, AccountType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    /**
     * Used to get the username of the user.
     * @return username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Used to get the password of the user.
     * @return password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Used to get the type of the user.
     * @return type of the user.
     */
    public AccountType getType() {
        return type;
    }

    /**
     * Used to return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format(
                "username='%s', password='%s', type='%s'",
                this.username,
                this.password,
                this.type
        );
    }
}
