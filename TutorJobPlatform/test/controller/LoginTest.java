package controller;

import exceptions.*;
import model.AppData;
import model.Student;
import model.Teacher;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    private Login login;
    private AppData appData;
    private Registration registration;


    @BeforeEach
    void setUp() throws InvalidInputException, PasswordsNotIdenticalException, UserAlreadyExistsException, StudentNumberInvalidException, TeacherIdInvalidException {
        appData = new AppData();
        App.setData(appData);

        login = Login.login;
        registration = Registration.registration;

        registration.registerUser("Max", "Mustermann", "passwort22+!",
                "passwort22+!", "Student*in", "", "1000000", "", "IMB");
        registration.registerUser("Max", "Mustermann", "passwort22!!",
                "passwort22!!", "Dozent*in", "Prof. Dr.", "", "MM", "");
    }

    @Test
    void correctLoginStudent() throws InvalidInputException, IncorrectPasswordException, UserDoesNotExistException {
        User user = login.loginUser("Student*in", "1000000", "", "passwort22+!");
        assertEquals("1000000", ((Student) user).getStudNumber());
        assertEquals("passwort22+!", ((Student) user).getPassword());
    }

    @Test
    void correctLoginTeacher() throws InvalidInputException, IncorrectPasswordException, UserDoesNotExistException {
        User user = login.loginUser("Dozent*in", "", "MM", "passwort22!!");
        assertEquals("MM", ((Teacher) user).getTeacherId());
        assertEquals("passwort22!!", ((Teacher) user).getPassword());
    }

    @Test
    void studDoesNotExist() {
        assertThrows(UserDoesNotExistException.class, () -> login.loginUser("Student*in", "1122334",
                "", "passwort22+!"));
    }

    @Test
    void teacherDoesNotExist() {
        assertThrows(UserDoesNotExistException.class, () -> login.loginUser("Dozent*in", "",
                "ABC", "passwort22!!"));
    }

    @Test
    void incorrectPassword() {
        assertThrows(IncorrectPasswordException.class, () -> login.loginUser("Student*in", "1000000",
                "", "passwort22"));
    }
}