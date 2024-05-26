package controller;

import model.*;
import exceptions.*;

public class Registration {
	public static Registration registration = new Registration();

	/**
	 * Checks if the user already exists.
	 * 
	 * @param teacherId  the teacher ID
	 * @param studNumber the student number
	 * @param role       the role of the user (e.g., student, teacher)
	 * @return true if the user already exists, false otherwise
	 */
	private boolean checkIfUserAlreadyExists(String teacherId, String studNumber, String role) {
		return App.getData().checkIfUserExists(teacherId, studNumber, role);
	}

	/**
	 * Checks if the provided passwords match.
	 * 
	 * @param password    the password
	 * @param passwordRep the repeated password
	 * @return true if the passwords match, false otherwise
	 */
	private boolean checkIfPasswordsMatch(String password, String passwordRep) {
		return password.equals(passwordRep);
	}

	/**
	 * Checks if any input fields are empty.
	 * 
	 * @param firstName   the user's name
	 * @param lastName    the user's surname
	 * @param title       the user's title (if applicable)
	 * @param password    the user's password
	 * @param passwordRep the repeated password
	 * @param role        the user's role
	 * @param teacherId   the teacher ID (if applicable)
	 * @param studNumber  the student number (if applicable)
	 * @param studyPath   the study path (if applicable)
	 * @return true if any input fields are incomplete, false otherwise
	 */
	private boolean checkIfInputIsIncomplete(String firstName, String lastName, String title, String password,
			String passwordRep, String role, String teacherId, String studNumber, String studyPath) {
		if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || passwordRep.isEmpty() || role.isEmpty()
				|| (role.equals("Student*in") && studNumber.isEmpty()) || (role.equals("Dozent*in") && title.isEmpty())
				|| (role.equals("Dozent*in") && teacherId.isEmpty())
				|| (role.equals("Student*in") && studyPath.isEmpty())) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the firstname and lastname is valid (contains only letters).
	 * 
	 * @param name the firstname or the lastname of the User
	 * @return true if the teacher ID is valid, false otherwise
	 */
	public boolean checkIfNameIsCorrect(String name) {
		if (name.matches("[a-zA-Z]+")) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the student number is valid (contains only digits).
	 * 
	 * @param studNumber the student number
	 * @return true if the student number is valid, false otherwise
	 */
	public boolean checkIfStudNumberIsCorrect(String studNumber) {
		if (studNumber.matches("[0-9]+")) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the teacher ID is valid (contains only letters).
	 * 
	 * @param teacherId the teacher ID
	 * @return true if the teacher ID is valid, false otherwise
	 */
	public boolean checkIfTeacherIdIsCorrect(String teacherId) {
		if (teacherId.matches("[a-zA-Z]+")) {
			return true;
		}
		return false;
	}

	/**
	 * Registers a user with error handling.
	 * 
	 * @param firstName   the user's name
	 * @param lastName    the user's surname
	 * @param password    the user's password
	 * @param passwordRep the repeated password
	 * @param role        the user's role (e.g., student, teacher)
	 * @param title       the user's title (if applicable)
	 * @param studNumber  the student number (if applicable)
	 * @param teacherId   the teacher ID (if applicable)
	 * @param studyPath   the study path (if applicable)
	 * @return true if the user is successfully registered, false otherwise
	 * @throws UserAlreadyExistsException     if the user already exists
	 * @throws StudentNumberInvalidException  if the student number is invalid
	 * @throws PasswordsNotIdenticalException if the passwords do not match
	 * @throws InvalidInputException          if any input fields are incomplete
	 * @throws TeacherIdInvalidException      if the teacher ID is invalid
	 * @throws IncorrectNameException         if the first or last name is incorrect
	 */
	public boolean registerUser(String firstName, String lastName, String password, String passwordRep, String role,
			String title, String studNumber, String teacherId, String studyPath)
			throws UserAlreadyExistsException, StudentNumberInvalidException, PasswordsNotIdenticalException,
			InvalidInputException, TeacherIdInvalidException, IncorrectNameException {

		// Check if inputs are empty
		if (checkIfInputIsIncomplete(firstName, lastName, title, password, passwordRep, role, teacherId, studNumber,
				studyPath)) {
			throw new InvalidInputException("Ein oder mehrere Felder sind leer.");
		}

		// Check if user already exists
		if (checkIfUserAlreadyExists(teacherId, studNumber, role)) {
			throw new UserAlreadyExistsException("Benutzer existiert schon!");
		}

		// Check if the first name contains only letters
		if (!checkIfNameIsCorrect(firstName)) {
			throw new IncorrectNameException("Ungültiger Vorname!");
		}

		// Check if the last name contains only letters
		if (!checkIfNameIsCorrect(lastName)) {
			throw new IncorrectNameException("Ungültiger Nachname!");
		}

		// Check if the password is entered correctly
		if (!checkIfPasswordsMatch(password, passwordRep)) {
			throw new PasswordsNotIdenticalException("Passwörter stimmen nicht überein!");
		}

		// Check if the student number is valid if the user is a student
		if (role.equals("Student*in") && !checkIfStudNumberIsCorrect(studNumber)) {
			throw new StudentNumberInvalidException("Ungültige Matrikelnummer!");
		}

		// Check if the teacher ID is valid if the user is a teacher
		if (role.equals("Dozent*in") && !checkIfTeacherIdIsCorrect(teacherId)) {
			throw new TeacherIdInvalidException("Ungültiges " + "Dozent*innenkürzel!");
		}

		// Create and add new user
		User user = null;
		if (role.equals("Dozent*in")) {
			user = new Teacher(firstName, lastName, password, title, teacherId);
		} else if (role.equals("Student*in")) {
			user = new Student(firstName, lastName, passwordRep, studNumber, studyPath);
		}
		App.getData().addUser(user);

		return true;
	}
}
