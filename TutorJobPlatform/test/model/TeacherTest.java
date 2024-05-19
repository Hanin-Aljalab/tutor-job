package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class TeacherTest {
    private Teacher teacher;
    private ArrayList<String> studyPaths;

    @BeforeEach
    void setUp() {
        teacher = new Teacher("Jessica", "Steinberger", "password", "Prof. Dr.", "STG123");
        studyPaths = new ArrayList<>(List.of("IMB", "IB", "CSB"));
    }

    @Test
    void testCreateLecture() {
        Lecture lecture = teacher.createLecture("Programmierung 2", "PR2", 2, "Course Info", studyPaths);
        assertNotNull(lecture);
        assertEquals("Programmierung 2", lecture.getName());
        assertEquals("PR2", lecture.getAbbreviation());
        assertEquals(2, lecture.getNumOfTutors());
        assertEquals("Course Info", lecture.getCourseInfo());
        assertEquals(teacher, lecture.getTeacher());
        assertEquals(studyPaths, lecture.getStudyPaths());
    }


    //andere --------------------------------------------------------------------------- (was brauchen wir?)

    @Test
    void testGetTeacherId() {
        assertEquals("STG123", teacher.getTeacherId());
    }

    @Test
    void testGetLectures() {
        assertNotNull(teacher.getLectures());
        assertTrue(teacher.getLectures().isEmpty());
    }

    @Test
    void testToString() {
        assertEquals("Prof. Dr. Jessica Steinberger", teacher.toString());
    }

}