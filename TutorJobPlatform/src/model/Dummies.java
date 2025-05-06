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
        createTeacherDummies();
        createLectureDummies();
        createStudentDummies();

    }

    private static void createStudentDummies() {
        Student stud1 = new Student("Max", "Mustermann", "12345", "1234567",
                "CSB");
        ArrayList<String> lecturePref1 = new ArrayList<>();
        lecturePref1.add("SE1");
        lecturePref1.add("MI1");
        stud1.setLecturePref(lecturePref1);

        Student stud2 = new Student("Erna", "Musterfrau", "12345",
                "1111111",
                "IB");
        ArrayList<String> lecturePref2 = new ArrayList<>();
        lecturePref2.add("MA1");
        stud2.setLecturePref(lecturePref2);

        Student stud3 = new Student("Maja", "Biene", "12345", "2222222",
                "UIB");
        ArrayList<String> lecturePref3 = new ArrayList<>();
        lecturePref3.add("SE1");
        lecturePref3.add("MA2");
        stud3.setLecturePref(lecturePref3);

        Student stud4 = new Student("Henry", "Hupfe", "12345",
                "3333333", "UIB");
        ArrayList<String> lecturePref4 = new ArrayList<>();
        lecturePref4.add("MA1");
        stud4.setLecturePref(lecturePref4);
        ArrayList<String> teacherPref4 = new ArrayList<>();
        teacherPref4.add(teacherDummies.get(0).toString());
        stud4.setTeacherPref(teacherPref4);

        Student stud5 = new Student("Olaf", "Scholz", "12345",
                "7777777",
                "IMB");
        ArrayList<String> lecturePref5 = new ArrayList<>();
        lecturePref5.add("MI1");
        stud1.setLecturePref(lecturePref5);

        Student stud6 = new Student("Billie", "Eilish", "12345",
                "4444444", "IMB");
        ArrayList<String> teacherPref6 = new ArrayList<>();
        teacherPref6.add(teacherDummies.get(2).toString());
        stud4.setTeacherPref(teacherPref6);

        Student stud7 = new Student("Leo", "Tolstoi", "12345",
                "5555555", "CSB");
        ArrayList<String> teacherPref7 = new ArrayList<>();
        teacherPref7.add(teacherDummies.get(3).toString());
        stud7.setTeacherPref(teacherPref7);

        studentDummies.add(stud1);
        studentDummies.add(stud2);
        studentDummies.add(stud3);
        studentDummies.add(stud4);
        studentDummies.add(stud5);
        studentDummies.add(stud6);
        studentDummies.add(stud7);
    }

    private static void createTeacherDummies() {
        Teacher tea1 = new Teacher("Jens", "Baum", "12345", "Dr.", "BAJ");
        Teacher tea2 = new Teacher("Jolla", "Jammer", "12345", "Prof. Dr.",
                "JOJA");
        Teacher tea3 = new Teacher("Harry", "Potter", "12345", "",
                "POH");
        Teacher tea4 = new Teacher("Markus", "Winklhofer", "12345", "",
                "WKL");

        teacherDummies.add(tea1);
        teacherDummies.add(tea2);
        teacherDummies.add(tea3);
        teacherDummies.add(tea4);
    }

    private static void createLectureDummies() {
        Lecture lec1 = new Lecture("Mathematik 1", "MA1", 3, "Aussagenlogik, " +
                "Folgen, Beweise", teacherDummies.get(0),
                new ArrayList<>(List.of(new String[]{"IMB", "IB",
                "CSB", "UIB"})));
//        Lecture lec2 = new Lecture("Mathematik 2", "MA2", 2, "Aussagenlogik, " +
//                "Folgen, Beweise", teacherDummies.get(0),
//                new ArrayList<>(List.of(new String[]{"IMB", "IB",
//                        "CSB", "UIB"})));
        Lecture lec4 = new Lecture("Medizinische Informatik 1",
                "MI1",	1, "Terminologie, Biologie, Physik",
                teacherDummies.get(2),
                new ArrayList<>(List.of(new String[]{"IMB"})));
        Lecture lec5 = new Lecture("Software Engineering 1", "SE1", 2,
                "Analyse, Design, Testen, Swing", teacherDummies.get(1),
                new ArrayList<>(List.of(new String[]{"IMB", "IB", "CSB"})));
        Lecture lec6 = new Lecture("Mathematik 1", "MA1", 3, "Aussagenlogik, " +
                "Folgen, Beweise", teacherDummies.get(1),
                new ArrayList<>(List.of(new String[]{"IMB", "IB",
                        "CSB", "UIB"})));
        Lecture lec7 = new Lecture("Testen für Profis", "TEP", 3,
                "Serialisierung, Swing, Sortieren",
                teacherDummies.get(3), new ArrayList<>(List.of(new String[]{
                        "CSB", "UIB"})));

        lectureDummies.add(lec1);
      //  lectureDummies.add(lec2);
        lectureDummies.add(lec4);
        lectureDummies.add(lec5);
        lectureDummies.add(lec6);
        lectureDummies.add(lec7);
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
