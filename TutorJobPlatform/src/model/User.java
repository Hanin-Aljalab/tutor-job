package model;

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String title;

    public User(String name, String surname, String password) {
        this.firstName = name;
        this.lastName = surname;
        this.password = password;
        this.title = "";
    }

    public User(String name, String surname, String password, String title) {
        this.firstName = name;
        this.lastName = surname;
        this.password = password;
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getPassword() {
        return password;
    }
}
