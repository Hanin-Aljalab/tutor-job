package model;

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String title;
    private String profession;

    public User(String name, String surname, String password,
                String profession) {
        this.firstName = name;
        this.lastName = surname;
        this.password = password;
        this.title = "";
        this.profession = profession;
    }

    public User(String name, String surname, String password, String title,
                String profession) {
        this.firstName = name;
        this.lastName = surname;
        this.password = password;
        this.title = title;
        this.profession = profession;
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

    public String getProfession() {
        return profession;
    }
}
