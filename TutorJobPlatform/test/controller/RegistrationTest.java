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

    /**
     * Test for checkIfStudNumberIsCorrect()
     */
    @Test
    void checkIfStudNumberIsCorrect() {
        assertTrue(registration.checkIfStudNumberIsCorrect("1234567"));
        assertTrue(registration.checkIfStudNumberIsCorrect("5588667"));

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
     * Test for correctRegisterStudent()
     *
     * @throws UserAlreadyExistsException     if the user already exists
     * @throws StudentNumberInvalidException  if the student number is invalid
     * @throws PasswordsNotIdenticalException if the passwords do not match
     * @throws InvalidInputException          if any input fields are incomplete
     * @throws TeacherIdInvalidException      if the teacher ID is invalid
     */
    @Test
        void correctRegisterStudent() throws InvalidInputException, PasswordsNotIdenticalException, UserAlreadyExistsException, StudentNumberInvalidException, TeacherIdInvalidException {
        // Testfall 1: Erfolgreiche Registrierung eines Studenten mit korrekten Daten.
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

    /**
     * Test for correctRegisterTeacher()
     *
     * @throws UserAlreadyExistsException     if the user already exists
     * @throws StudentNumberInvalidException  if the student number is invalid
     * @throws PasswordsNotIdenticalException if the passwords do not match
     * @throws InvalidInputException          if any input fields are incomplete
     * @throws TeacherIdInvalidException      if the teacher ID is invalid
     */
    @Test
    void correctRegisterTeacher() throws InvalidInputException, PasswordsNotIdenticalException, UserAlreadyExistsException, StudentNumberInvalidException, TeacherIdInvalidException {
        // Testfall 2: Erfolgreiche Registrierung eines Dozenten mit korrekten Daten.
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

    /**
     * Test for invalidFirstName()
     *
     * @throws InvalidInputException if any input fields are incomplete
     */
    @Test
    void invalidFirstName() throws InvalidInputException {
        // Testfall 3: Vorname enthält Zahl, wirft InvalidInput Exception
        assertThrows(InvalidInputException.class, () -> registration.registerUser(
                "Max1",
                "Mustermann",
                "passwort",
                "passwort",
                "Student*in",
                "",
                "9999999",
                "",
                "CSB")
        );
    }

    /**
     * Test for invalidLastName()
     *
     * @throws InvalidInputException if any input fields are incomplete
     */
    @Test
    void invalidLastName() throws InvalidInputException {
        // Testfall 4: Nachname enthält Zahl, wirft InvalidInput Exception
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

//    /**
//     * Test for lastNameOnlySpace()
//     *
//     * @throws InvalidInputException if any input fields are incomplete
//     */
//    @Test
//    void lastNameOnlySpace() throws InvalidInputException {
//        // Testfall 5: Nachname Leerzeichen, wirft InvalidInput Exception
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

    /**
     * Test for passwordIsMissing()
     *
     * @throws InvalidInputException if any input fields are incomplete
     */
    @Test
    void passwordIsMissing() throws InvalidInputException {
        // Testfall 6: Passwort fehlt, wirft InvalidInput Exception
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

//    /**
//     * Test for passwordWithOnlySpaces()
//     *
//     * @throws InvalidInputException if any input fields are incomplete
//     */
//    @Test
//    void passwordWithOnlySpaces() throws InvalidInputException {
//        // Testfall 7: Passwort Leerzeichen, wirft InvalidInput Exception
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

    /**
     * Test for passwordsNotIdentical()
     *
     * @throws PasswordsNotIdenticalException if the passwords do not match
     */
    @Test
    void passwordsNotIdentical() throws PasswordsNotIdenticalException {
        // Testfall 8: Die Passwörter stimmen nicht überein, was zu einer PasswordsNotIdenticalException führt.
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

//    /**
//     * Test for studentNumberTooLong()
//     *
//     * @throws StudentNumberInvalidException if the student number is invalid
//     */
//    @Test
//    void studentNumberTooLong() throws StudentNumberInvalidException {
//        // Testfall 9: Matrikelnummer zu lang, führt zu StudentNumberInvalidException
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

//    /**
//     * Test for teacherWithStudentNumber()
//     *
//     * @throws StudentNumberInvalidException if the student number is invalid
//     */
//    @Test
//    void studNumberTooShort() throws StudentNumberInvalidException {
//        // Testfall 10: Matrikelnummer ist zu kurz
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

    /**
     * studentNumberNonNumericCharacters
     *
     * @throws StudentNumberInvalidException if the student number is invalid
     */
    @Test
    void studentNumberNonNumericCharacters() throws StudentNumberInvalidException {
        // Testfall 11: Ungültige Matrikelnummer, enthält nicht-numerische Zeichen, was zu einer StudentNumberInvalidException führt.
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

    /**
     * Test for invalidStudentNumberWithNegativeNumbers()
     *
     * @throws StudentNumberInvalidException if the student number is invalid
     */
    @Test
    void StudentNumberWithNegativeNumbers() throws StudentNumberInvalidException {
        // Testfall 12: Ungültige Matrikelnummer, negative Zahl, was zu einer StudentNumberInvalidException führt.
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

    /**
     * Test for teacherIdNonLetterCharacters()
     *
     * @throws TeacherIdInvalidException if any input fields are incomplete
     */
    @Test
    void teacherIdNonLetterCharacters() throws TeacherIdInvalidException {
        // Testfall 13: Ungültige Dozenten-ID, enthält nicht nur Buchstaben, was zu einer TeacherIdInvalidException führt.
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

    /**
     * Test for TeacherIdOnlySpaces()
     *
     * @throws TeacherIdInvalidException if any input fields are incomplete
     */
    @Test
    void TeacherIdOnlySpaces() throws TeacherIdInvalidException {
        // Testfall 14: Ungültige Dozenten-ID, enthält nur ein Leerzeichen, was zu einer TeacherIdInvalidException führt.
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


    /**
     * Test for teacherIdAlreadyExists()
     *
     * @throws UserAlreadyExistsException     if the user already exists
     * @throws StudentNumberInvalidException  if the student number is invalid
     * @throws PasswordsNotIdenticalException if the passwords do not match
     * @throws InvalidInputException          if any input fields are incomplete
     * @throws TeacherIdInvalidException      if the teacher ID is invalid
     */
    @Test
    void teacherIdAlreadyExists() throws UserAlreadyExistsException, InvalidInputException, PasswordsNotIdenticalException, StudentNumberInvalidException, TeacherIdInvalidException {
        //  Testfall 15: Dozent-ID existiert bereits, was zu einer UserAlreadyExistsException führt.
        registration.registerUser("Max","Mustermann","passwort","passwort",
                "Dozent*in","Prof. Dr.","","MM","");
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


    /**
     * Test for StudentNumberAlreadyExists()
     *
     * @throws UserAlreadyExistsException     if the user already exists
     * @throws StudentNumberInvalidException  if the student number is invalid
     * @throws PasswordsNotIdenticalException if the passwords do not match
     * @throws InvalidInputException          if any input fields are incomplete
     * @throws TeacherIdInvalidException      if the teacher ID is invalid
     */
    @Test
    void StudentNumberAlreadyExists() throws UserAlreadyExistsException, InvalidInputException, PasswordsNotIdenticalException, StudentNumberInvalidException, TeacherIdInvalidException {
        //  Testfall 16: Matrikelnr. existiert bereits, was zu einer UserAlreadyExistsException führt.
        registration.registerUser("Max","Mustermann","passwort", "passwort",
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


    /**
     * Second Test for correctRegisterStudent()
     *
     * @throws UserAlreadyExistsException     if the user already exists
     * @throws StudentNumberInvalidException  if the student number is invalid
     * @throws PasswordsNotIdenticalException if the passwords do not match
     * @throws InvalidInputException          if any input fields are incomplete
     * @throws TeacherIdInvalidException      if the teacher ID is invalid
     */
    @Test
    void validateCorrectRegisterStudent() throws InvalidInputException, PasswordsNotIdenticalException, UserAlreadyExistsException, StudentNumberInvalidException, TeacherIdInvalidException {
        // Testfall 18: Wiederholung von Testfall 1 zur Validierung der Konsistenz des Systems.
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