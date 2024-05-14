package model;

import javax.swing.*;
import java.util.ArrayList;

public class Teacher extends User {
    JTextField LectureInput = new JTextField(); // TODO
    JTextField SlotInput = new JTextField();


    public Teacher(String firstName, String lastName, String password, String title) {
        super(firstName, lastName, password, title, "Dozent*in");
    }



    /**
     * creates a new Job, depending on the input given in the JText fields
     */
    public Lecture createLecture(String name, int anzahlTutoren, String anforderungen, ArrayList<String> selectedStudyPaths) {
        Lecture lecture = new Lecture(name, anzahlTutoren, anforderungen, this, selectedStudyPaths);
        return lecture;
    }

}

