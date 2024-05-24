package view.swing.components.homescreens;

import controller.App;
import model.AppData;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentHomescreenTest {

    private AppData appData;
    private Student s1;
    private StudentHomescreen homescreen;
    private ArrayList<String> lecturePrefBefore;
    private ArrayList<String> teacherPrefBefore;
    private ArrayList<String> lecturePrefAfter;
    private ArrayList<String> teacherPrefAfter;

    @BeforeEach
    void setUp() {
        appData = new AppData();
        System.out.println("AppData initialized: " + true);

        s1 = new Student("Markus", "Winklhofer", "12345", "3008816", "IMB");
        s1.addPreference("MA1", "Kurse");
        s1.addPreference("Todorov", "Dozent*innen");
        s1.addPreference("PR2", "Kurse");
        appData.addUser(s1);
        System.out.println("Student initialized: " + s1);

        App.setData(appData);
        System.out.println("AppData set in App class: " + App.getData());

        homescreen = new StudentHomescreen(s1);
        System.out.println("StudentHomescreen initialized with student: " + App.getData().getStudents());

        lecturePrefBefore = new ArrayList<>(s1.getLecturePref());
        teacherPrefBefore = new ArrayList<>(s1.getTeacherPref());

    }

    @Test
    void removePreferences() {
        homescreen.removePreferences();
        lecturePrefAfter = new ArrayList<>(s1.getLecturePref());
        teacherPrefAfter = new ArrayList<>(s1.getTeacherPref());

        assertEquals(lecturePrefAfter, lecturePrefBefore);
        assertEquals(teacherPrefAfter, teacherPrefBefore);

    }
}