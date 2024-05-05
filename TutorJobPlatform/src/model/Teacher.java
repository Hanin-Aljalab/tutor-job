package model;

import javax.swing.*;

public class Teacher extends User {
    JTextField LectureInput = new JTextField();
    JTextField SlotInput = new JTextField();

    public Teacher(String name, String surname, String password) {
        super(name, surname, password);
    }



    /**
     * creates a new Job, depending on the input given in the JText fields
     */
    private void createLecture(){
       // TODO anpassen an refaktorisierte Klassen
       // new Job(LectureInput.getText(),SlotInput.getColumns());
    }

}

