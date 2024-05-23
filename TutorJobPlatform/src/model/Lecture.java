package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Lecture implements Serializable {
  
    private String name;
    private String abbreviation;
    private int numOfDesiredTutors;
    private int numOfOpenSlots;
    private int numOfAssignedTutors;
    private String courseInfo;
    private Teacher teacher;
    private ArrayList<Student> tutors;
    private ArrayList<String> studyPaths;

    public Lecture(String name, String abbreviation, int numTutors,
                   String courseInfo, Teacher teacher, ArrayList<String> allowedStudyPaths) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.numOfDesiredTutors = numTutors;
        this.numOfOpenSlots = numTutors;
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

    public int getNumOfOpenSlots() {
        return numOfOpenSlots;
    }

    public int getNumOfAssignedTutors() {
        return numOfAssignedTutors;
    }

    public void incrementTutors() {
        numOfAssignedTutors++;
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
    
    public void addTutor() {
        numOfOpenSlots --;
        numOfAssignedTutors++;
    }

    public void resetTutorAssignment() {
        numOfOpenSlots = numOfDesiredTutors;
        numOfAssignedTutors = 0;
    }

    public void decrementSlot(){
        numOfOpenSlots--;
    }

    public ArrayList<String> getStudyPaths() {
        return studyPaths;
    }

    @Override
    public String toString() {
        return this.name + " bei " + this.teacher.toString();
    }
}
