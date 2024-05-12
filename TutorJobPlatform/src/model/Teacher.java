package model;

import javax.swing.*;
import java.util.ArrayList;

public class Teacher extends User {
    JTextField LectureInput = new JTextField(); // TODO
    JTextField SlotInput = new JTextField();
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
    private void createLecture(){
       // TODO anpassen an refaktorisierte Klassen: Create lecture will have
       //  own popup
       // new Job(LectureInput.getText(),SlotInput.getColumns());
    }

}

