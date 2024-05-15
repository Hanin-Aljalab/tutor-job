package controller;

import model.*;

import exceptions.IncorrectPasswordException;
import exceptions.UserDoesNotExistException;

public class Login {
	public static Login login = new Login();
	private AppData data = AppData.data;

	//Methode, die überprüft ob ein Benutzer registriert ist
	public boolean checkIfUserExists(User user) {
		if (user == null) {
			return false;
		}
		return true;
	}
	
	//Methode, die das Passwort auf Korrektheit überprüft
	public boolean validatePassword(User user, String passwort) {
		if (user.getPassword().equals(passwort)) {
			return true;
		}
		return false;
	}
	
	// Überprüfung, ob Benutzer existiert und Passwort korrekt ist
	public User loginUser(String role, String studNumber, String abbreviation, String password)
			throws IncorrectPasswordException, UserDoesNotExistException {
		User user = data.getUser(studNumber, abbreviation, role);

		// Überprüfung, ob der Benutzer existiert
		if (!checkIfUserExists(user)) {
			throw new UserDoesNotExistException("Benutzer nicht gefunden!");
		}
		
		if (!validatePassword(user, password)) {
            throw new IncorrectPasswordException("Falsches Passwort!");
		}

		return user;
	}
	
}
