package model;

import java.util.ArrayList;

import javax.swing.*;

public class Teacher extends User {
    private String teacherId;

    JTextField LectureInput = new JTextField(); // TODO
    JTextField SlotInput = new JTextField(); // TODO
    ArrayList<Lecture> lectures;

    public Teacher(String firstName, String lastName, String password, String title, String teacherId) {
        super(firstName, lastName, password, title, "Dozent*in");
        this.teacherId = teacherId;
        lectures = new ArrayList<>();
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }
    
    /**
     * creates a new Job, depending on the input given in the JText fields
     */
    public Lecture createLecture(String firstName, String abbreviation, int numTutors, String courseInfo,
            ArrayList<String> allowedStudyPaths) {
        Lecture lecture = new Lecture(firstName, abbreviation, numTutors, courseInfo, this, allowedStudyPaths);
        return lecture;
    }
    
    public String getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        if (getTitle().isEmpty()) {
            return getFirstName() + " " + getLastName();
        }
        return getTitle() + " " + getFirstName() + " " + getLastName();
    }
}


