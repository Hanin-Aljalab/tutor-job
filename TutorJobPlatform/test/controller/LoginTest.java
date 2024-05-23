package controller;

import exceptions.IncorrectPasswordException;
import exceptions.InvalidInputException;
import exceptions.UserDoesNotExistException;
import model.AppData;
import model.Student;
import model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LoginTest contains all tests for the Login class
 */
class LoginTest {
    private Student student;
    private Teacher teacher;

    private static final Login login = Login.login;

    /**
     * Resets data before each test
     */
    @BeforeEach
    void setUp() {
        App.setData(new AppData());
        student = new Student("John", "Doe", "password", "556677", "CSB");
        App.getData().addUser(student);
        teacher = new Teacher("John", "Doe", "password", "Test Dr.", "AABBCC");
        App.getData().addUser(teacher);
    }

    /**
     * Test for checkIfUserExists()
     */
    @Test
    void checkIfUserExists() {
        assertTrue(login.checkIfUserExists(student));
        assertTrue(login.checkIfUserExists(teacher));

        assertFalse(login.checkIfUserExists(null));
    }

    /**
     * Test for validatePassword()
     */
    @Test
    void validatePassword() {
        assertTrue(login.validatePassword(student, "password"));
        assertFalse(login.validatePassword(student, "passwort"));

        assertTrue(login.validatePassword(teacher, "password"));
        assertFalse(login.validatePassword(teacher, "passwort"));
    }

    /**
     * Test for loginUser()
     * @throws IncorrectPasswordException if the provided password is incorrect
     * @throws UserDoesNotExistException if the user does not exist
     * @throws InvalidInputException if input is not valid
     */
    @Test
    void loginUser() throws IncorrectPasswordException, UserDoesNotExistException, InvalidInputException {
        assertSame(student, login.loginUser("Student*in", "556677", "", "password"));
        assertSame(teacher, login.loginUser("Dozent*in", "", "AABBCC", "password"));

        assertThrows(InvalidInputException.class, () -> login.loginUser("Dozent*in", "", "", "password"));
        assertThrows(InvalidInputException.class, () -> login.loginUser("Dozent*in", "", "AABBCC", ""));

        assertThrows(UserDoesNotExistException.class, () -> login.loginUser("Student*in", "123", "", "password"));
        assertThrows(UserDoesNotExistException.class, () -> login.loginUser("Dozent*in", "", "ABC", "password"));

        assertThrows(IncorrectPasswordException.class, () -> login.loginUser("Student*in", "556677", "", "passwort"));
        assertThrows(IncorrectPasswordException.class, () -> login.loginUser("Dozent*in", "", "AABBCC", "passwort"));
    }
}