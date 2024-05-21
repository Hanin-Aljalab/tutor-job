package model;
import java.util.ArrayList;

public class Lecture {
  
    private String name;
    private String abbreviation;
    private int numOfTutors;
    private String courseInfo;
    private Teacher teacher;
    private ArrayList<Student> tutors;
    private ArrayList<String> studyPaths;

    public Lecture(String name, String abbreviation, int numTutors,
                   String courseInfo, Teacher teacher, ArrayList<String> allowedStudyPaths) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.numOfTutors = numTutors;
        this.courseInfo = courseInfo;
        this.teacher = teacher;
        this.studyPaths = allowedStudyPaths;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int getNumOfTutors() {
        return numOfTutors;
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

    public void decrementSlot(){
        numOfTutors--;
    }

    public ArrayList<String> getStudyPaths() {
        return studyPaths;
    }

    @Override
    public String toString() {
        return this.name + " bei " + this.teacher.toString();
    }
}
