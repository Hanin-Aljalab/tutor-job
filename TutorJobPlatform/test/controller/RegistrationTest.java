package controller;

import exceptions.*;
import model.AppData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RegistrationTest contains all tests for the Registration class
 */
class RegistrationTest {

    private static final Registration registration = Registration.registration;

    /**
     * Test for checkIfStudNumberIsCorrect()
     */
    @Test
    void checkIfStudNumberIsCorrect() {
        assertTrue(registration.checkIfStudNumberIsCorrect("123456789"));
        assertTrue(registration.checkIfStudNumberIsCorrect("558866715"));
        assertTrue(registration.checkIfStudNumberIsCorrect("1"));

        assertFalse(registration.checkIfStudNumberIsCorrect("123X56789"));
        assertFalse(registration.checkIfStudNumberIsCorrect("AlfredWerner"));
        assertFalse(registration.checkIfStudNumberIsCorrect("-2"));
    }

    /**
     * Test for checkIfTeacherIdIsCorrect()
     */
    @Test
    void checkIfTeacherIdIsCorrect() {
        assertTrue(registration.checkIfTeacherIdIsCorrect("AABBCC"));
        assertTrue(registration.checkIfTeacherIdIsCorrect("aabbcc"));
        assertTrue(registration.checkIfTeacherIdIsCorrect("AaBbCc"));

        assertFalse(registration.checkIfTeacherIdIsCorrect("Abc123"));
        assertFalse(registration.checkIfTeacherIdIsCorrect("A1B2C3"));
        assertFalse(registration.checkIfTeacherIdIsCorrect("123456"));
    }

    /**
     * Test for registerUser()
     * @throws UserAlreadyExistsException if the user already exists
     * @throws StudentNumberInvalidException if the student number is invalid
     * @throws PasswordsNotIdenticalException if the passwords do not match
     * @throws InvalidInputException if any input fields are incomplete
     * @throws TeacherIdInvalidException if the teacher ID is invalid
     */
    @Test
    void registerUser() throws InvalidInputException, PasswordsNotIdenticalException, UserAlreadyExistsException, StudentNumberInvalidException, TeacherIdInvalidException {
        // Testfall 1: Erfolgreiche Registrierung eines Studenten mit korrekten Daten.
        assertTrue(registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Student*in",
                "",
                "123456",
                "",
                "Informatik")
        );

        // Testfall 2: Fehlende Eingabe für den Vornamen, was zu einer InvalidInputException führt.
        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "",
                "Mustermann",
                "pass123",
                "pass123",
                "Student*in",
                "",
                "123456",
                "",
                "Informatik")
        );

        // Testfall 3: Fehlende Eingabe für den Nachnamen, was zu einer InvalidInputException führt.
        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max",
                "",
                "pass123",
                "pass123",
                "Student*in",
                "",
                "123456",
                "",
                "Informatik")
        );

        // Testfall 4: Die Passwörter stimmen nicht überein, was zu einer PasswordsNotIdenticalException führt.
        assertThrows(PasswordsNotIdenticalException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass1234",
                "Student*in",
                "",
                "654321",
                "",
                "Informatik")
        );

        // Testfall 5: Ungültige Matrikelnummer, enthält nicht-numerische Zeichen, was zu einer StudentNumberInvalidException führt.
        assertThrows(StudentNumberInvalidException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Student*in",
                "",
                "12345A",
                "",
                "Informatik")
        );

        // Testfall 6: Ungültige Dozenten-ID, enthält nicht nur Buchstaben, was zu einer TeacherIdInvalidException führt.
        assertThrows(TeacherIdInvalidException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Dozent*in",
                "Prof.",
                "",
                "1234",
                "")
        );

        // Testfall 7: Erfolgreiche Registrierung eines Dozenten mit korrekten Daten.
        assertTrue(registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Dozent*in",
                "Prof.",
                "",
                "ABCD",
                "")
        );

        // Testfall 8: Der Dozent-ID ist vorhanden.
        assertThrows(UserAlreadyExistsException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Dozent*in",
                "Prof.",
                "",
                "ABCD",
                "")
        );

        // Testfall 9: Der Dozent ist bereits registriert.
        assertThrows(UserAlreadyExistsException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Dozent*in",
                "Prof.",
                "",
                "ABCD",
                "")
        );

        // Testfall 10: Fehlender Studiengang für einen Studenten, was zu einer InvalidInputException führt.
        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Student*in",
                "",
                "123456",
                "",
                "")
        );

        // Testfall 11: Benutzer existiert bereits, was zu einer UserAlreadyExistsException führt (angenommen, die Datenbank gibt diese Information zurück).
        assertThrows(UserAlreadyExistsException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Student*in",
                "",
                "123456",
                "",
                "Informatik")
        );

        // Testfall 12: Fehlender Titel für einen Dozenten, was zu einer InvalidInputException führt.
        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Dozent*in",
                "",
                "",
                "ABCD",
                "")
        );

        // Testfall 13: Benutzer existiert bereits, was zu einer UserAlreadyExistsException führt (angenommen, die Datenbank gibt diese Information zurück).
        assertThrows(UserAlreadyExistsException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Dozent*in",
                "Prof.",
                "",
                "ABCD",
                "Informatik")
        );

        // Testfall 14: Passwort fehlt, soll zu InvalidInputException führen.
        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "",
                "pass123",
                "Student*in",
                "",
                "123456",
                "",
                "Informatik")
        );

        // Testfall 15: Passwort-Wiederholung fehlt, soll zu InvalidInputException führen.
        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "",
                "Student*in",
                "",
                "123456",
                "",
                "Informatik")
        );

        // Testfall 16: Passwort und Passwort-Wiederholung fehlen, soll zu InvalidInputException führen.
        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "",
                "",
                "Student*in",
                "",
                "123456",
                "",
                "Informatik")
        );

        // Testfall 17: Rolle ist leer, soll zu InvalidInputException führen.
        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "",
                "",
                "123456",
                "",
                "Informatik")
        );

        // Testfall 18: StudNumber ist leer, soll zu InvalidInputException führen.
        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Student*in",
                "",
                "",
                "",
                "Informatik")
        );

        // Testfall 19: TeacherID ist leer, soll zu InvalidInputException führen.
        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Dozent*in",
                "Prof.",
                "",
                "",
                "Informatik")
        );

        // Testfall 20: Nicht-existierende Rolle für einen Benutzer soll nicht zu Exception führen.
        assertDoesNotThrow(() -> registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "User*in",
                "",
                "123456",
                "",
                "Informatik"
        ));

        // Testfall 21: Wiederholung von Testfall 1 zur Validierung der Konsistenz des Systems.
        AppData.data.removeUser(AppData.data.getUser("123456", "", "Student*in"));
        assertTrue(registration.registerUser(
                "Max",
                "Mustermann",
                "pass123",
                "pass123",
                "Student*in",
                "",
                "123456",
                "",
                "Informatik")
        );
    }
}