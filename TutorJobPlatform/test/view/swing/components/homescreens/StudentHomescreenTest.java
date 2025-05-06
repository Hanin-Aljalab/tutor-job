package view.swing.components.homescreens;

import controller.App;
import model.AppData;
import model.Lecture;
import model.Student;
import model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentHomescreenTest {

    private AppData appData;
    private Student s1;
    private StudentHomescreen homescreen;
    private ArrayList<String> lecturePrefBefore;
    private ArrayList<String> teacherPrefBefore;
    private ArrayList<String> lecturePrefAfter;
    private ArrayList<String> teacherPrefAfter;
    private static ArrayList<Student> studentDummies;
    private static ArrayList<Teacher> teacherDummies;
    private static ArrayList<Lecture> lectureDummies;

    @BeforeEach
    void setUp() {
        appData = new AppData();
        s1 = new Student("Markus", "Winklhofer", "12345", "3008816", "IMB");

        ArrayList<String> lecturePref = new ArrayList<>();
        lecturePref.add("MA1");
        lecturePref.add("MI1");

        ArrayList<String> teacherPref = new ArrayList<>();
        teacherPref.add("BAJ");
        teacherPref.add("POH");

        s1.setLecturePref(lecturePref);
        s1.setTeacherPref(teacherPref);

        lecturePrefBefore = new ArrayList<>(s1.getLecturePref());
        teacherPrefBefore = new ArrayList<>(s1.getTeacherPref());

        App.setData(appData);

        homescreen = new StudentHomescreen(s1);
    }

    @Test
    void removePreferences() {
        homescreen.removePreferences();

        lecturePrefAfter = new ArrayList<>(s1.getLecturePref());
        teacherPrefAfter = new ArrayList<>(s1.getTeacherPref());

        assertTrue(lecturePrefAfter.isEmpty());
        assertTrue(teacherPrefAfter.isEmpty());

    }
}