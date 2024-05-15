package model;
//Changed variable profession to role
//Deleted variable email 
//Changed firstname to name and lastname to surname
public class User {     
    private String name;
    private String surname;
    private String password;
    private String title;
    private String role;

    public User(String name, String surname, String password,
                String role) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.title = "";
        this.role = role;
    }

    public User(String name, String surname, String password, String title,
                String role) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.title = title;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTitle() {
        return title;
    }

    public String getPassword() {
        return password;
    }
    
    
    
}
