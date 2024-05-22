package controller;

import model.*;
import exceptions.*;


public class Registration {
	public static Registration registration = new Registration();
	private AppData data = AppData.data;


	/**
     * Checks if the user already exists.
     * 
     * @param teacherId the teacher ID
     * @param studNumber the student number
     * @param role the role of the user (e.g., student, teacher)
     * @return true if the user already exists, false otherwise
     */
    private boolean checkIfUserAlreadyExists(String teacherId, String studNumber, String role) {
        return data.checkIfUserExists(teacherId, studNumber, role);

    }

    /**
     * Checks if the provided passwords match.
     * 
     * @param password the password
     * @param passwordRep the repeated password
     * @return true if the passwords match, false otherwise
     */
    private boolean checkIfPasswordsMatch(String password, String passwordRep) {
		return password.equals(passwordRep);
	}

    /**
     * Checks if any input fields are empty.
     * 
     * @param firstName the user's name
     * @param lastName the user's surname
     * @param title the user's title (if applicable)
     * @param password the user's password
     * @param passwordRep the repeated password
     * @param role the user's role
     * @param teacherId the teacher ID (if applicable)
     * @param studNumber the student number (if applicable)
     * @param studyPath the study path (if applicable)
     * @return true if any input fields are incomplete, false otherwise
     */	private boolean checkIfInputIsIncomplete(String firstName, String lastName, String title, String password,
			String passwordRep, String role, String teacherId, String studNumber, String studyPath) {
		if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || passwordRep.isEmpty() || role.isEmpty()
				|| (role.equals("Student*in") && studNumber.isEmpty()) || (role.equals("Dozent*in") && title.isEmpty())
				|| (role.equals("Dozent*in") && teacherId.isEmpty()) || (role.equals("Student*in") && studyPath.isEmpty())) {
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
      * @param firstName the user's name
      * @param lastName the user's surname
      * @param password the user's password
      * @param passwordRep the repeated password
      * @param role the user's role (e.g., student, teacher)
      * @param title the user's title (if applicable)
      * @param studNumber the student number (if applicable)
      * @param teacherId the teacher ID (if applicable)
      * @param studyPath the study path (if applicable)
      * @return true if the user is successfully registered, false otherwise
      * @throws UserAlreadyExistsException if the user already exists
      * @throws StudentNumberInvalidException if the student number is invalid
      * @throws PasswordsNotIdenticalException if the passwords do not match
      * @throws InvalidInputException if any input fields are incomplete
      * @throws TeacherIdInvalidException if the teacher ID is invalid
      */
     public boolean registerUser(String firstName, String lastName, String password, String passwordRep, String role,
			String title, String studNumber, String teacherId, String studyPath)
			throws UserAlreadyExistsException, StudentNumberInvalidException, PasswordsNotIdenticalException,
			InvalidInputException, TeacherIdInvalidException{

		// Überprüfe ob Eingaben leer sind
		if (checkIfInputIsIncomplete(firstName, lastName, title, password, passwordRep, role, teacherId, studNumber, studyPath)) {
			throw new InvalidInputException("Ein oder mehrere Felder sind leer.");
		}

		// Überprüfe ob Benutzer bereits existiert
		if (checkIfUserAlreadyExists(teacherId, studNumber, role)) {
			throw new UserAlreadyExistsException("Benutzer existiert schon!");
		}

		// Überprüfe ob das Passwort korrekt eingegeben wurde
		if (!checkIfPasswordsMatch(password, passwordRep)) {
			throw new PasswordsNotIdenticalException("Passwörter stimmen nicht überein!");
		}

		// Überprüfe ob die Matrikelnummer gültig ist, falls der Benutzer ein Student ist
		if (role.equals("Student*in") && !checkIfStudNumberIsCorrect(studNumber)) {
			throw new StudentNumberInvalidException("Ungültige Matrikelnummer!");
		}

		// Überprüfe ob das Dozentenkürzel gültig ist, falls der Benutzer ein Dozent ist
		if (role.equals("Dozent*in") && !checkIfTeacherIdIsCorrect(teacherId)) {
			throw new TeacherIdInvalidException("Ungültiges " +
					"Dozent*innenkürzel!");
		}

		// Neuen Benutzer erstellen und hinzufügen
		User user = null;
		if (role.equals("Dozent*in")) {
			user = new Teacher(firstName, lastName, password, title, teacherId);
		} else if (role.equals("Student*in")) {
			user = new Student(firstName, lastName, passwordRep, studNumber, studyPath);
		}
		data.addUser(user);

		return true;
	}
}