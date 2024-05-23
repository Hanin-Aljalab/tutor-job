package view.swing.components;

import static org.junit.jupiter.api.Assertions.assertThrows;

import controller.App;
import controller.Registration;
import exceptions.*;
import model.AppData;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationPopupTest {
    private RegistrationPopup registrationPopup;
    private AppData data;

    @BeforeEach
    void setUp() {
        registrationPopup = new RegistrationPopup();
        data = new AppData();

        registrationPopup.setFirstNameField("Markus");
        registrationPopup.setLastNameField("Winklhofer");
        registrationPopup.setPasswordField("password");
        registrationPopup.setPasswordConfirmField("password");
        registrationPopup.setStudNumberField("1234567");
        registrationPopup.setStudyPathDropdown("IMB");
        registrationPopup.setRoleDropdown("Student*in");
    }

    @Test
    void testUserAlreadyExistsException() {
        Student existingStudent = new Student("Markus", "Winklhofer",
                "password", "1234567", "IMB");
        data.addUser(existingStudent);

        assertThrows(UserAlreadyExistsException.class, () -> {
            registrationPopup.transmitData();
        });
    }

    @Test
    void testStudentNumberInvalidException() {
        registrationPopup.setStudNumberField("abc");

        assertThrows(StudentNumberInvalidException.class, () -> {
            registrationPopup.transmitData();
        });
    }

    @Test
    void testPasswordsNotIdenticalException() {
        registrationPopup.setPasswordConfirmField("differentPassword");

        assertThrows(PasswordsNotIdenticalException.class, () -> {
            registrationPopup.transmitData();
        });
    }

    @Test
    void testInvalidInputException() {
        registrationPopup.setFirstNameField("");

        assertThrows(InvalidInputException.class, () -> {
            registrationPopup.transmitData();
        });
    }

    @Test
    void testTeacherIdInvalidException() {
        registrationPopup.setRoleDropdown("Dozent*in");
        registrationPopup.setTeacherIdField("WKL1");

        assertThrows(TeacherIdInvalidException.class, () -> {
            registrationPopup.transmitData();
        });
    }
}
