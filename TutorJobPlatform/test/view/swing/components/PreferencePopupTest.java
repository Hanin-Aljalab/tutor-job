package view.swing.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import view.swing.components.*;
import model.Student;
import model.*;
import controller.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//TODO anpassen an neuste Version

public class PreferencePopupTest {

    private PreferencePopup preferencePopup;
    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student("Hanin", "Aljalab", "password", "123456", "MA1");
        preferencePopup = new PreferencePopup(student);
    }

//    @Test
//    public void testLecturePref() {
//        ArrayList<String> expectedLecturePref = new ArrayList<>();
//        expectedLecturePref.add("PR1");
//        expectedLecturePref.add("SE1");
//        expectedLecturePref.add("MA1");
//        expectedLecturePref.add("MA2");
//
//        preferencePopup.addPreference("PR1", preferencePopup.getLecturePref());
//        preferencePopup.addPreference("SE1", preferencePopup.getLecturePref());
//        preferencePopup.addPreference("MA1", preferencePopup.getLecturePref());
//        preferencePopup.addPreference("MA2", preferencePopup.getLecturePref());
//
//        assertEquals(expectedLecturePref, preferencePopup.getLecturePref());
//    }

    @Test
    public void testTeacherPref() {
        ArrayList<String> expectedTeacherPref = new ArrayList<>();
        expectedTeacherPref.add("J. Fischer");
        expectedTeacherPref.add("P. Knauber");
        expectedTeacherPref.add("Y. Todorov");
        expectedTeacherPref.add("Y. Todorov");

        preferencePopup.addPreference("J. Fischer", preferencePopup.getTeacherPref());
        preferencePopup.addPreference("P. Knauber", preferencePopup.getTeacherPref());
        preferencePopup.addPreference("Y. Todorov", preferencePopup.getTeacherPref());

        assertEquals(expectedTeacherPref, preferencePopup.getTeacherPref());
    }

    @Test
    public void testActionPerformed() {
        ArrayList<String> expectedLecturePref = new ArrayList<>();
        expectedLecturePref.add("PR1");
        expectedLecturePref.add("SE1");
        expectedLecturePref.add("MA1");
        expectedLecturePref.add("MA2");
        ArrayList<String> expectedTeacherPref = new ArrayList<>();
        expectedTeacherPref.add("J. Fischer");
        expectedTeacherPref.add("P. Knauber");
        expectedTeacherPref.add("Y. Todorov");

        preferencePopup.addPreference("PR1", preferencePopup.getLecturePref());
        preferencePopup.addPreference("SE1", preferencePopup.getLecturePref());
        preferencePopup.addPreference("MA1", preferencePopup.getLecturePref());
        preferencePopup.addPreference("MA2", preferencePopup.getLecturePref());
        preferencePopup.addPreference("J. Fischer", preferencePopup.getTeacherPref());
        preferencePopup.addPreference("P. Knauber", preferencePopup.getTeacherPref());
        preferencePopup.addPreference("Y. Todorov", preferencePopup.getTeacherPref());


        preferencePopup.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

        assertTrue(student.isChoiceMade());
        assertEquals(expectedLecturePref, student.getLecturePref());
        assertEquals(expectedTeacherPref, student.getTeacherPref());
    }

    @Test
    public void testAddAndRemovePreference() {
        ArrayList<String> lecturePref = new ArrayList<>();
        preferencePopup.addPreference("PR1", lecturePref);
        preferencePopup.addPreference("SE1", lecturePref);
        assertEquals(2, lecturePref.size());

        preferencePopup.removePreference("PR1", lecturePref);
        assertEquals(1, lecturePref.size());
        assertTrue(lecturePref.contains("SE1"));
    }

    @Test
    public void testDeletePreferences() {
        ArrayList<String> lecturePref = new ArrayList<>();
        lecturePref.add("PR1");
        lecturePref.add("SE1");
        lecturePref.add("MA1");
        lecturePref.add("MA2");
        ArrayList<String> teacherPref = new ArrayList<>();
        teacherPref.add("J. Fischer");
        teacherPref.add("P. Knauber");
        teacherPref.add("Y. Todorov");

        student.setLecturePref(lecturePref);
        student.setTeacherPref(teacherPref);
        student.setChoiceMade(true);

        preferencePopup.removePreference("PR1", lecturePref);


        assertFalse(student.getLecturePref().isEmpty());
        assertFalse(student.getTeacherPref().isEmpty());
        assertTrue(student.isChoiceMade());
    }
}