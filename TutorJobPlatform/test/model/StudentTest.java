package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("John", "Doe", "password", "1234567", "CSB");
    }

    @Test
    void deletePreferences() {
        //TODO
    }

    @Test
    void testRemovePreference() {
        student.addPreference("Mathematics", "Kurse");
        student.removePreference("Mathematics");
        assertFalse(student.getLecturePref().contains("Mathematics"));

        student.addPreference("Prof. Smith", "Dozent*innen");
        student.removePreference("Prof. Smith");
        assertFalse(student.getTeacherPref().contains("Prof. Smith"));
    }

    @Test
    void testAddPreference() {
        student.addPreference("Mathematics", "Kurse");
        assertTrue(student.getLecturePref().contains("Mathematics"));

        student.addPreference("Prof. Smith", "Dozent*innen");
        assertTrue(student.getTeacherPref().contains("Prof. Smith"));
    }

    //andere --------------------------------------------------------------------------- (was brauchen wir?)
    @Test
    void testGetStudNumber() {
        assertEquals("1234567", student.getStudNumber());
    }

    @Test
    void testGetLecturePref() {
        assertNotNull(student.getLecturePref());
    }

    @Test
    void testGetTeacherPref() {
        assertNotNull(student.getTeacherPref());
    }

    @Test
    void testSetChoiceMade() {
        student.setChoiceMade(true);
        assertTrue(student.isChoiceMade());

        student.setChoiceMade(false);
        assertFalse(student.isChoiceMade());
    }

    @Test
    void testToString() {
        assertEquals("John Doe (1234567)", student.toString());
    }

}