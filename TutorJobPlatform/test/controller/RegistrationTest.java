package controller;

import exceptions.*;
import model.AppData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RegistrationTest contains all tests for the Registration class
 */
class RegistrationTest {

    private static final Registration registration = Registration.registration;

    @BeforeEach
    void setUp() {
        App.setData(new AppData());
    }

    @Test
    void checkIfStudNumberIsCorrect() {
        assertTrue(registration.checkIfStudNumberIsCorrect("1234567"));
        assertTrue(registration.checkIfStudNumberIsCorrect("5588667"));

        assertFalse(registration.checkIfStudNumberIsCorrect("123X56789"));
        assertFalse(registration.checkIfStudNumberIsCorrect("AlfredWerner"));
        assertFalse(registration.checkIfStudNumberIsCorrect("-2"));
    }

    @Test
    void checkIfTeacherIdIsCorrect() {
        assertTrue(registration.checkIfTeacherIdIsCorrect("AABBCC"));
        assertTrue(registration.checkIfTeacherIdIsCorrect("aabbcc"));
        assertTrue(registration.checkIfTeacherIdIsCorrect("AaBbCc"));

        assertFalse(registration.checkIfTeacherIdIsCorrect("Abc123"));
        assertFalse(registration.checkIfTeacherIdIsCorrect("A1B2C3"));
        assertFalse(registration.checkIfTeacherIdIsCorrect("123456"));
    }

    @Test // Testfall 1: Erfolgreiche Registrierung eines Studenten mit korrekten Daten.
    void correctRegisterStudent() throws InvalidInputException, PasswordsNotIdenticalException, UserAlreadyExistsException, StudentNumberInvalidException, TeacherIdInvalidException {

        assertTrue(registration.registerUser(
                "Max",
                "Mustermann",
                "passwort22",
                "passwort22",
                "Student*in",
                "",
                "1000000",
                "",
                "IMB")
        );
    }


    @Test // Testfall 2: Erfolgreiche Registrierung eines Dozenten mit korrekten Daten.
    void correctRegisterTeacher() throws InvalidInputException, PasswordsNotIdenticalException, UserAlreadyExistsException, StudentNumberInvalidException, TeacherIdInvalidException {

        assertTrue(registration.registerUser(
                "Max",
                "Mustermann",
                "passwort22+!",
                "passwort22+!",
                "Dozent*in",
                "Dr.",
                "",
                "MM",
                "")
        );
    }

//    @Test // Testfall 3: Vorname enthält Zahl, wirft InvalidInput Exception
//    void invalidFirstName() throws InvalidInputException {
//
//        assertThrows(InvalidInputException.class, () -> registration.registerUser(
//                "Max1",
//                "Mustermann",
//                "passwort",
//                "passwort",
//                "Student*in",
//                "",
//                "9999999",
//                "",
//                "CSB")
//        );
//    }

    @Test // Testfall 4: Nachname enthält Zahl, wirft InvalidInput Exception
    void invalidLastName() throws InvalidInputException {

        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max",
                "Mustermann1",
                "passwort",
                "passwort",
                "Dozent*in",
                "Prof. Dr.",
                "",
                "MM",
                "")
        );
    }

//    @Test // Testfall 5: Nachname Leerzeichen, wirft InvalidInput Exception
//    void lastNameOnlySpace() throws InvalidInputException {
//
//        assertThrows(InvalidInputException.class, () -> registration.registerUser(
//                "Max",
//                " ",
//                "passwort",
//                "passwort",
//                "Dozent*in",
//                "Prof. Dr.",
//                "",
//                "MM",
//                "")
//        );
//    }


    @Test // Testfall 6: Passwort fehlt, wirft InvalidInput Exception
    void passwordIsMissing() throws InvalidInputException {

        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "",
                "passwort",
                "Student*in",
                "",
                "4444444",
                "",
                "IB")
        );
    }

//    @Test // Testfall 7: Passwort Leerzeichen, wirft InvalidInput Exception
//    void passwordWithOnlySpaces() throws InvalidInputException {
//
//        assertThrows(InvalidInputException.class, () -> registration.registerUser(
//                "Max",
//                "Mustermann",
//                " ",
//                "passwort",
//                "Dozent*in",
//                "Dr.",
//                "",
//                "MM",
//                "")
//        );
//    }

    @Test // Testfall 8: Die Passwörter stimmen nicht überein, was zu einer PasswordsNotIdenticalException führt.
    void passwordsNotIdentical() throws PasswordsNotIdenticalException {

        assertThrows(PasswordsNotIdenticalException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "passwort",
                "passwor",
                "Student*in",
                "",
                "5555551",
                "",
                "IMB")
        );
    }

//    @Test // Testfall 9: Matrikelnummer zu lang, führt zu StudentNumberInvalidException
//    void studentNumberTooLong() throws StudentNumberInvalidException {
//
//        assertThrows(StudentNumberInvalidException.class, () -> registration.registerUser(
//                "Max",
//                "Mustermann",
//                "passwort",
//                "passwort",
//                "Student*in",
//                "",
//                "100000000",
//                "",
//                "IMB")
//        );
//    }


//    @Test // Testfall 10: Matrikelnummer ist zu kurz
//    void studNumberTooShort() throws StudentNumberInvalidException {
//
//        assertThrows(StudentNumberInvalidException.class, () -> registration.registerUser(
//                "Max",
//                "Mustermann",
//                "passwort",
//                "passwort",
//                "Student*in",
//                "",
//                "999999",
//                "",
//                "IB")
//        );
//    }


    @Test // Testfall 11: Ungültige Matrikelnummer, enthält nicht-numerische Zeichen, was zu einer StudentNumberInvalidException führt.
    void studentNumberNonNumericCharacters() throws StudentNumberInvalidException {

        assertThrows(StudentNumberInvalidException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "passwort",
                "passwort",
                "Student*in",
                "",
                "12345A",
                "",
                "CSB")
        );
    }

    @Test // Testfall 12: Ungültige Matrikelnummer, negative Zahl, was zu einer StudentNumberInvalidException führt.
    void StudentNumberWithNegativeNumbers() throws StudentNumberInvalidException {

        assertThrows(StudentNumberInvalidException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "passwort",
                "passwort",
                "Student*in",
                "",
                "-1000000",
                "",
                "IMB")
        );
    }

    @Test // Testfall 13: Ungültige Dozenten-ID, enthält nicht nur Buchstaben, was zu einer TeacherIdInvalidException führt.
    void teacherIdNonLetterCharacters() throws TeacherIdInvalidException {

        assertThrows(TeacherIdInvalidException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "passwort",
                "passwort",
                "Dozent*in",
                "Prof. Dr.",
                "",
                "1234",
                "")
        );
    }

    @Test // Testfall 14: Ungültige Dozenten-ID, enthält nur ein Leerzeichen, was zu einer TeacherIdInvalidException führt.
    void TeacherIdOnlySpaces() throws TeacherIdInvalidException {

        assertThrows(TeacherIdInvalidException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "passwort",
                "passwort",
                "Dozent*in",
                "Prof. Dr.",
                "",
                " ",
                "")
        );
    }


    @Test // Testfall 15: Dozent-ID existiert bereits, was zu einer UserAlreadyExistsException führt.
    void teacherIdAlreadyExists() throws UserAlreadyExistsException, InvalidInputException, PasswordsNotIdenticalException, StudentNumberInvalidException, TeacherIdInvalidException {

        registration.registerUser("Max", "Mustermann", "passwort", "passwort",
                "Dozent*in", "Prof. Dr.", "", "MM", "");
        assertThrows(UserAlreadyExistsException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "passwort",
                "passwort",
                "Dozent*in",
                "Prof. Dr.",
                "",
                "MM",
                "")
        );
    }

    @Test // Testfall 16: Matrikelnr. existiert bereits, was zu einer UserAlreadyExistsException führt.
    void StudentNumberAlreadyExists() throws UserAlreadyExistsException, InvalidInputException, PasswordsNotIdenticalException, StudentNumberInvalidException, TeacherIdInvalidException {

        registration.registerUser("Max", "Mustermann", "passwort", "passwort",
                "Student*in", "", "1000000", "", "UIB");
        assertThrows(UserAlreadyExistsException.class, () -> registration.registerUser(
                "Max",
                "Mustermann",
                "passwort",
                "passwort",
                "Student*in",
                "",
                "1000000",
                "",
                "UIB")
        );
    }


    @Test // Testfall 18: Wiederholung von Testfall 1 zur Validierung der Konsistenz des Systems.
    void validateCorrectRegisterStudent() throws InvalidInputException, PasswordsNotIdenticalException, UserAlreadyExistsException, StudentNumberInvalidException, TeacherIdInvalidException {

        App.getData().removeUser(App.getData().getUser("1000000", "", "Student*in"));
        assertTrue(registration.registerUser(
                "Max",
                "Mustermann",
                "passwort22",
                "passwort22",
                "Student*in",
                "",
                "1000000",
                "",
                "IMB")
        );
    }
}