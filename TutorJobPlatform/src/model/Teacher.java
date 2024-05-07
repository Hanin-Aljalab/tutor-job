package model;

import javax.swing.*;

public class Teacher extends User {
    JTextField LectureInput = new JTextField(); // TODO
    JTextField SlotInput = new JTextField();


    public Teacher(String firstName, String lastName, String password, String title) {
        super(firstName, lastName, password, title, "Dozent*in");
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

