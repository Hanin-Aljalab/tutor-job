package controller;

import exceptions.*;
import model.*;

/**
 * Aggregates login functions
 */
public class Login {
	public static Login login = new Login();

	/**
	 * Checks if a user is registered.
	 * 
	 * @param user the user to check
	 * @return true if the user exists, false otherwise
	 */
	public boolean checkIfUserExists(User user) {
		if (user == null) {
			return false;
		}
		return true;
	}

	/**
	 * Validates if the provided password matches the user's password.
	 * 
	 * @param user     the user whose password needs to be validated
	 * @param passwort the password to validate
	 * @return true if the password is correct, false otherwise
	 */
	public boolean validatePassword(User user, String passwort) {
		if (user.getPassword().equals(passwort)) {
			return true;
		}
		return false;
	}

	/**
	 * Logs in a user by checking if the user exists and if the password is correct.
	 * 
	 * @param role       the role of the user (e.g., student, teacher)
	 * @param studNumber the student number of the user (if applicable)
	 * @param teacherId  the teacher ID of the user (if applicable)
	 * @param password   the password of the user
	 * @return the logged-in user
	 * @throws IncorrectPasswordException if the provided password is incorrect
	 * @throws UserDoesNotExistException  if the user does not exist
	 */
	public User loginUser(String role, String studNumber, String teacherId, String password)
			throws IncorrectPasswordException, UserDoesNotExistException,
			InvalidInputException {
		User user = App.getData().getUser(studNumber, teacherId, role);

		if (studNumber.isEmpty() && teacherId.isEmpty() || password.isEmpty()) {
			throw new InvalidInputException("Fehlende Eingabe!");
		}
		// Überprüfe ob der Benutzer existiert
		if (!checkIfUserExists(user)) {
			throw new UserDoesNotExistException("Benutzer nicht gefunden!");
		}
		// Überprüfe ob das Passwort richtig ist
		if (!validatePassword(user, password)) {
			throw new IncorrectPasswordException("Falsches Passwort!");
		}

		return user;
	}
}
