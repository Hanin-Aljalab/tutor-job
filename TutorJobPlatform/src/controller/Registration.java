package controller;

import model.AppData;
import model.User;
import model.Teacher;
import model.Student;
import model.Lecture;
import exceptions.AbbreviationInvalidException;
import exceptions.InvalidInputException;
import exceptions.UserAlreadyExistsException;
import exceptions.StudentNumberInvalidException;
import exceptions.PasswordsNotIdenticalException;

public class Registration {
	public static Registration registration = new Registration();
	private AppData data = AppData.data;

	// Methode, die überprüft ob der Benutzer bereits existiert
	private boolean checkIfUserAlreadyExists(String abbreviation, String studNumber, String role) {
		return data.checkIfUserExists(abbreviation, studNumber, role);
	}

	// Methode, die überprüft ob die Passwörter identisch sind
	private boolean checkIfPasswordsMatch(String password, String passwordRep) {
		return password.equals(passwordRep);
	}

	// Methode, die überprüft ob Eingabefelder leer sind
	private boolean checkIfInputIsIncomplete(String name, String surname, String title, String password,
			String passwordRep, String role, String abbreviation, String studNumber) {
		if (name.isEmpty() || surname.isEmpty() || password.isEmpty() || passwordRep.isEmpty() || role.isEmpty()
				|| (role.equals("Student*in") && studNumber.isEmpty()) || (role.equals("Dozent*in") && title.isEmpty())
				|| (role.equals("Dozent*in") && abbreviation.isEmpty())) {
			return true;
		}
		return false;
	}

	// Methode, die überprüft ob die Matrikelnummer nur aus Zahlen besteht 
	public boolean checkIfStudNumberIsCorrect(String studNumber) {
		if (studNumber.matches("[0-9]+")) {
			return true;
		}
		return false;
	}

	// Methode, die üperprüft ob das Dozentenkürzel nur aus Buchstaben besteht
	public boolean checkIfAbbreviationIsCorrect(String abbreviation) {
		if (abbreviation.matches("[a-zA-Z]+")) {
			return true;
		}
		return false;
	}

	// Registrierungsmethode mit Fehlerbehandlung 
	public boolean registerUser(String name, String surname, String password, String passwordRep, String role,
			String title, String studNumber, String abbreviation)
			throws UserAlreadyExistsException, StudentNumberInvalidException, PasswordsNotIdenticalException,
			InvalidInputException, AbbreviationInvalidException {

		// Überprüfe ob Eingaben leer sind
		if (checkIfInputIsIncomplete(name, surname, title, password, passwordRep, role, abbreviation, studNumber)) {
			throw new InvalidInputException("Ein oder mehrere Felder sind leer.");
		}

		// Überprüfe ob Benutzer bereits existiert
		if (checkIfUserAlreadyExists(abbreviation, studNumber, role)) {
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
		if (role.equals("Dozent*in") && !checkIfAbbreviationIsCorrect(abbreviation)) {
			throw new AbbreviationInvalidException("Ungültiges Dozentenkürzel!");
		}

		// Neuen Benutzer erstellen und hinzufügen
		User user = null;
		if (role.equals("Dozent*in")) {
			user = new Teacher(name, surname, password, title, abbreviation);
		} else if (role.equals("Student*in")) {
			user = new Student(name, surname, password, studNumber);
		}
		data.addUser(user);

		return true;
	}
}