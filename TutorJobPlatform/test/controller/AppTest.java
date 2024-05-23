package controller;

import model.AppData;
import model.Lecture;
import model.Student;
import model.Teacher;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private static final String TEST_FILE_PATH = "./test/controller/testfile.ser";
    private static AppData data;
    private static AppData deData;
    private ArrayList<String> paths;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Lecture> lectures;

    @BeforeEach
    void setUp(){
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        lectures = new ArrayList<>();

        paths = new ArrayList<>(List.of("IMB", "CSB", "IB"));
        Student s1 = new Student("Markus", "W", "1234", "3008816", "IMB");
        students.add(s1);
        Teacher t1 = new Teacher("Angela", "Merkel", "1234", "Dr.", "MKL");
        teachers.add(t1);
        Lecture l1 = t1.createLecture("Politik", "PO", 3, "smth", paths);
        lectures.add(l1);

        data = new AppData();
        data.addUser(s1);
        data.addUser(t1);
        data.addLecture(l1);

        App.setData(data);
    }

        @Test
        public void testSerializeAndDeserializeObjects() {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TEST_FILE_PATH))) {
                out.writeObject(data);
                System.out.println(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(TEST_FILE_PATH))) {
                deData = (AppData) in.readObject();
                System.out.println(deData);
                assertEquals(data, data);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
