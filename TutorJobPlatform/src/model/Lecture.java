package model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a lecture in the system.
 * Each lecture has a name, abbreviation, desired number of tutors,
 * course information, assigned teacher, and a list of allowed study paths.
 */
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

    /**
     *Constructs a new lecture.
     *
     * @param name the name of the lecture
     * @param abbreviation the abbreviation of the lecture
     * @param numTutors the number of desired tutors
     * @param courseInfo information about the course
     * @param teacher the teacher assigned to the lecture
     * @param allowedStudyPaths the study paths allowed for the lecture
     */
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

    /**
     * @return the name of the lecture
     */
    public String getName() {
        return name;
    }

    /**
     * @return the abbreviation of the lecture
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * @return the number of open slots
     */
    public int getNumOfOpenSlots() {
        return numOfOpenSlots;
    }

    /**
     * @return the number of assigned tutors
     */
    public int getNumOfAssignedTutors() {
        return numOfAssignedTutors;
    }

    /**
     *Increment the number of assigned tutors by one.
     */
    public void incrementTutors() {
        numOfAssignedTutors++;
    }


    /**
     * @return the course information
     */
    public String getCourseInfo() {
        return courseInfo;
    }

    /**
     * @return the teacher assigned to the lecture.
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * @return the list of tutors assigned to the lecture.
     */
    public ArrayList<Student> getTutors() {
        return tutors;
    }

    /**
     * Adds a tutor to the lecture.
     * Decrement the number of open slots and increment
     * the number of assigned tutors
     */
    public void addTutor() {
        numOfOpenSlots --;
        numOfAssignedTutors++;
    }

    /**
     * Resets the tutor assignment.
     * Sets the number of open slots to the desired number of tutors
     * and resets the assigned tutors count to zero.
     */
    public void resetTutorAssignment() {
        numOfOpenSlots = numOfDesiredTutors;
        numOfAssignedTutors = 0;
    }

    /**
     * Decrements the number of open slots by one.
     */
    public void decrementSlot(){
        numOfOpenSlots--;
    }

    /**
     * @return the list of allowed study paths for the lecture
     */
    public ArrayList<String> getStudyPaths() {
        return studyPaths;
    }

    /**
     * @return a string representation of the lecture.
     */
    @Override
    public String toString() {
        return this.name + " bei " + this.teacher.toString();
    }
}
