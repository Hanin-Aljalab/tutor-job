package model;

import javax.swing.*;
import java.util.ArrayList;

public class Teacher extends User {
    JTextField LectureInput = new JTextField(); // TODO
    JTextField SlotInput = new JTextField(); // TODO
    ArrayList<Lecture> lectures;


    public Teacher(String firstName, String lastName, String password, String title) {
        super(firstName, lastName, password, title, "Dozent*in");
        lectures = new ArrayList<>();
    }


    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    /**
     * creates a new Job, depending on the input given in the JText fields
     */
    public Lecture createLecture(String name, String abbreviation,
                                 int numTutors,
                                 String courseInfo, ArrayList<String> allowedStudyPaths) {
        Lecture lecture = new Lecture(name, abbreviation, numTutors,
                courseInfo,this, allowedStudyPaths);
        return lecture;
    }

}

