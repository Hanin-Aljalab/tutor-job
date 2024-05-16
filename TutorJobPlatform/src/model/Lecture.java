package model;
import java.util.ArrayList;

public class Lecture {

    private String name;
    private String abbreviation;
    private int numOpenTutorSlots;
    private String courseInfo;
    private Teacher teacher;
    private ArrayList<Student> tutors;
    private ArrayList<String> allowedStudyPaths;

    public Lecture(String name, String abbreviation, int numTutors,
                   String courseInfo, Teacher teacher, ArrayList<String> allowedStudyPaths) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.numOpenTutorSlots = numTutors;
        this.courseInfo = courseInfo;
        this.teacher = teacher;
        this.allowedStudyPaths = allowedStudyPaths;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int getNumOpenTutorSlots() {
        return numOpenTutorSlots;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ArrayList<Student> getTutors() {
        return tutors;
    }

    public void addTutor(Student student) {
        tutors.add(student);
    }

    public ArrayList<String> getAllowedStudyPaths() {
        return allowedStudyPaths;
    }

    public void allocateSlot(){
        numOpenTutorSlots--;
    }
}
