package model;
import java.util.ArrayList;

public class Lecture {
    private String name;
    private Teacher teacher;
    private ArrayList<Student> tutors;
    private String[] studyPaths;

    public Lecture(String name, Teacher teacher, String[] studyPaths) {
        this.name = name;
        this.teacher = teacher;
        this.studyPaths = studyPaths;
        tutors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String[] getStudyPaths() {
        return studyPaths;
    }

    public void addTutor(Student student) {
        tutors.add(student);
    }

    public ArrayList<Student> getTutors() {
        return tutors;
    }
}
