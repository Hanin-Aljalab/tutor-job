package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppDataTest {
    private AppData appData;

    @BeforeEach
    void setUp() {
        appData = new AppData();
    }

    @Test
    void testRemoveUser() {
        Student student = appData.getStudents().get(0);
        appData.removeUser(student);
        assertFalse(appData.getStudents().contains(student));

        Teacher teacher = appData.getTeachers().get(0);
        appData.removeUser(teacher);
        assertFalse(appData.getTeachers().contains(teacher));
    }

    @Test
    void testCheckIfUserExists() {
        assertTrue(appData.checkIfUserExists("BAJ", null, "Dozent*in"));
        assertFalse(appData.checkIfUserExists("ZZZ", null, "Dozent*in"));

        assertTrue(appData.checkIfUserExists(null, "1234567", "Student*in"));
        assertFalse(appData.checkIfUserExists(null, "0000000", "Student*in"));
    }

    @Test
    void testAddUser() {
        Student student = new Student("Hanin", "Aljalab", "password", "7654321", "CSB");
        appData.addUser(student);
        assertTrue(appData.getStudents().contains(student));

        Teacher teacher = new Teacher("Jessica", "Steinberger", "password", "Prof. Dr.", "STG");
        appData.addUser(teacher);
        assertTrue(appData.getTeachers().contains(teacher));
    }

}