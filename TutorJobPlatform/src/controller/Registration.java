package controller;

import model.*;

public class Registration {
    public static Registration registration = new Registration();
    private AppData data = AppData.data;

    // TODO: Die Registrierungsmethode sollte einen Fehler
    //  werfen, falls mit der Registrierung etwas nicht
    //  klappt. Dafür brauchen wir eigene Exceptions, z.B.
    //  "UserAlreadyExistsException", "StudentNumberInvalidException",
    //  "PasswordsNotIdenticalException", "EmailNotValidException",
    //  "InvalidInputException" (Felder sind leer)
    //  Die Passwortüberprüfung und Überprüfung, ob User schon existiert,
    //  sollten in separate Methoden ausgelagert werden.
    public boolean registerUser(String firstName, String lastName,
                                String password,
                                String passwordRep, String profession,
                                String title, String studNumber,
                                String studyPath) {
        User user;

        boolean usernameExists = data.checkIfUsernameExists(lastName,
                profession);
        if (usernameExists) {
            System.out.println("Username schon vergeben!");
            return false; // Username exists
        }

        if (!password.equals(passwordRep)) {
            System.out.println("Passwort nicht korrekt eingegeben!");
            return false;
        }

        if (profession.equals("Dozent*in")) {
            user = new Teacher(firstName, lastName, password, title);
        } else {
            int matNumber = Integer.parseInt(studNumber); //TODO try catch
            user = new Student(firstName, lastName, password, matNumber,
                    studyPath);

        }
        data.addUser(user);

        return true;
    }
}