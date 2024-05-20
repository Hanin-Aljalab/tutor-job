package view.swing.components;

import static org.junit.jupiter.api.Assertions.assertThrows;

import controller.Registration;
import exceptions.*;
import model.AppData;
import model.Student;
import model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;

public class RegistrationPopupTest {
    private RegistrationPopup registrationPopup;
    private AppData data;
    private Registration registration;

    @BeforeEach
    void setUp() {
        data = AppData.data;
        registration = new Registration();
        Registration.registration.data = data;

        registrationPopup = new RegistrationPopup();

        data.getStudents().clear();
        data.getTeachers().clear();

        registrationPopup.firstNameField.setText("Markus");
        registrationPopup.lastNameField.setText("Winklhofer");
        registrationPopup.passwordField.setText("password");
        registrationPopup.passwordConfirmField.setText("password");
        registrationPopup.studNumberField.setText("1234567");
        registrationPopup.roleDropdown.setSelectedItem("Student*in");
    }

    @Test
    void testUserAlreadyExistsException() {
        Student existingStudent = new Student("Markus", "Winklhofer", "password", "1234567", "IMB");
        data.addUser(existingStudent);

        assertThrows(UserAlreadyExistsException.class, () -> {
            registrationPopup.transmitData();
        });
    }

    @Test
    void testStudentNumberInvalidException() {
        registrationPopup.studNumberField.setText("invalid");

        assertThrows(StudentNumberInvalidException.class, () -> {
            registrationPopup.transmitData();
        });
    }

    @Test
    void testPasswordsNotIdenticalException() {
        registrationPopup.passwordConfirmField.setText("differentPassword");

        assertThrows(PasswordsNotIdenticalException.class, () -> {
            registrationPopup.transmitData();
        });
    }

    @Test
    void testInvalidInputException() {
        registrationPopup.firstNameField.setText("");

        assertThrows(InvalidInputException.class, () -> {
            registrationPopup.transmitData();
        });
    }

    @Test
    void testTeacherIdInvalidException() {
        registrationPopup.roleDropdown.setSelectedItem("Dozent*in");

        registrationPopup.firstNameField.setText("Markus");
        registrationPopup.lastNameField.setText("Winklhofer");
        registrationPopup.passwordField.setText("password");
        registrationPopup.passwordConfirmField.setText("password");
        registrationPopup.teacherIdField.setText("invalid");

        assertThrows(TeacherIdInvalidException.class, () -> {
            registrationPopup.transmitData();
        });
    }
}
