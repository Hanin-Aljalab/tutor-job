package controller;

import model.*;

public class Login {
    private AppData data = AppData.data;

    public Login() {
    }

    //TODO:
    // a) Login nicht über Namen (Namen können gleich sein), sondern durch
    // eine eindeutige Eigenschaft, z.B. EMail oder Matrikelnummer.
    // b) separate Methode, die überprüft, ob der Nutzer existiert
    // c) separate MEthode, die das PAsswort auf Korrektheit überprüft
    // d) die MEthode sollte den jeweiligen Studenten oder Dozenten
    // zurückgeben, kein boolean
    public boolean loginUser(String username, String password, String profession) {
        boolean usernameExists = data.checkIfUsernameExists(username, profession);
        if (usernameExists) {
            User user = data.getUser(username, profession);
            if (user != null && user.getPassword().equals(password)) {
                //TODO hier Methodenaufruf validatePAssword()
                return true; //TODO: sollte kein boolean zurückgeben, sondern
                             // ein Objekt
            }
        }
        return false;
    }
}

