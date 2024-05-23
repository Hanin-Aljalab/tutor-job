package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which creates static dummies of students, teachers and lectures for
 * demonstration purposes
 */
public class Dummies {
    private static ArrayList<Student> studentDummies;
    private static ArrayList<Teacher> teacherDummies;
    private static ArrayList<Lecture> lectureDummies;

    static {
        studentDummies = new ArrayList<>();
        teacherDummies = new ArrayList<>();
        lectureDummies = new ArrayList<>();
    }

    /**
     * Creates dummies for demonstration purposes
     */
    public static void createDummies() {
        createStudentDummies();
        createTeacherDummies();
        createLectureDummies();
    }

    private static void createStudentDummies() {
        Student stud1 = new Student("Max", "Mustermann", "12345", "1234567",
                "CSB");
        ArrayList<String> lecturePref1 = new ArrayList<>();
        lecturePref1.add("PR1");
        lecturePref1.add("SE1");
        lecturePref1.add("MI1");
        stud1.setLecturePref(lecturePref1);

        Student stud2 = new Student("Erna", "Musterfrau", "12345",
                "1111111",
                "IB");
        ArrayList<String> lecturePref2 = new ArrayList<>();
        lecturePref2.add("PR1");
        lecturePref2.add("SE1");
        lecturePref2.add("MA2");
        stud2.setLecturePref(lecturePref2);

        Student stud3 = new Student("Maja", "Biene", "12345", "2222222",
                "IMB");
        ArrayList<String> lecturePref3 = new ArrayList<>();
        lecturePref3.add("PR1");
        lecturePref3.add("SE1");
        lecturePref3.add("MA2");
        stud3.setLecturePref(lecturePref3);

        Student stud4 = new Student("Henry", "Hupfe", "12345",
                "3333333", "IMB");
        ArrayList<String> lecturePref4 = new ArrayList<>();
        lecturePref4.add("PR2");
        lecturePref4.add("MI1");
        lecturePref4.add("SE1");
        stud4.setLecturePref(lecturePref4);

        studentDummies.add(stud1);
        studentDummies.add(stud2);
        studentDummies.add(stud3);
        studentDummies.add(stud4);
    }

    private static void createTeacherDummies() {
        Teacher tea1 = new Teacher("Jens", "Baum", "12345", "Dr.", "BAJ");
        Teacher tea2 = new Teacher("Jolla", "Jammer", "12345", "Prof. Dr.",
                "JOJA");
        Teacher tea3 = new Teacher("Harry", "Potter", "12345", "",
                "POH");

        teacherDummies.add(tea1);
        teacherDummies.add(tea2);
        teacherDummies.add(tea3);
    }

    private static void createLectureDummies() {
        Lecture lec1 = new Lecture("Mathematik 1", "MA1", 3, "Aussagenlogik, " +
                "Folgen, Beweise", teacherDummies.get(0),
                new ArrayList<>(List.of(new String[]{"IMB", "IB",
                "CSB", "UIB"})));
        Lecture lec2 = new Lecture("Mathematik 2", "MA2", 5,
                "Vektorräume, Gruppen", teacherDummies.get(1),
                new ArrayList<>(List.of(new String[]{"IMB", "IB",
                "CSB", "UIB"})));
        Lecture lec3 = new Lecture("Programmieren 1", "PR1", 2,
                "Java Basics", teacherDummies.get(2),
                new ArrayList<>(List.of(new String[]{
                "IMB", "IB", "CSB"})));
        Lecture lec4 = new Lecture("Medizinische Informatik 1",
                "MI1",	1, "Terminologie, Biologie, Physik",
                teacherDummies.get(2),
                new ArrayList<>(List.of(new String[]{"IMB"})));
        Lecture lec5 = new Lecture("Software Engineering 1", "MA2", 1,
                "Analyse, Design, Testen, Swing", teacherDummies.get(1),
                new ArrayList<>(List.of(new String[]{"IMB", "IB", "CSB", "UIB"})));
        Lecture lec6 = new Lecture("Mathematik 1", "MA1", 3, "Aussagenlogik, " +
                "Folgen, Beweise", teacherDummies.get(1),
                new ArrayList<>(List.of(new String[]{"IMB", "IB",
                        "CSB", "UIB"})));

        lectureDummies.add(lec1);
        lectureDummies.add(lec2);
        lectureDummies.add(lec3);
        lectureDummies.add(lec4);
        lectureDummies.add(lec5);
        lectureDummies.add(lec6);
    }

    public static ArrayList<Student> getStudentDummies() {
        return studentDummies;
    }

    public static ArrayList<Teacher> getTeacherDummies() {
        return teacherDummies;
    }

    public static ArrayList<Lecture> getLectureDummies() {
        return lectureDummies;
    }
}
