package model;
//Changed variable profession to role
//Deleted variable email 

public class User {     
    private String firstName;
    private String lastName;
    private String password;
    private String title;
    private String role;

    public User(String firstName, String lastName, String password,
                String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.title = "";
        this.role = role;
    }

    public User(String firstName, String lastName, String password, String title,
                String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.title = title;
        this.role = role;
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
    
    public String getRole() {
        return role;
    }
    
}
