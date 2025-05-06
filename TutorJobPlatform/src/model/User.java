package model;

import java.io.Serializable;

/**
 * Represents a user in the system.
 * Each user has a first name, last name, password, title and role.
 */
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String password;
    private String title;
    private String role;

    /**
     * Constructs a new user with the specified first name,
     * last name, password and role.
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param password the password of the user
     * @param role the role of the user
     */
    public User(String firstName, String lastName, String password,
                String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.title = "";
        this.role = role;
    }

    /**
     * Constructs a new User with the specified first name,
     * last name, password, title and role.
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param password the password of the user
     * @param title the title of the user
     * @param role the role of the user
     */
    public User(String firstName, String lastName, String password, String title,
                String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.title = title;
        this.role = role;
    }

    /**
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the title of the user
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the role of the user
     */
    public String getRole() {
        return role;
    }

}
