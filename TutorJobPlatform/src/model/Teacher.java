//Added variable abbreviation(Dozentenkürzel)
package model;

import javax.swing.*;

public class Teacher extends User {
	private String abbreviation;

    JTextField LectureInput = new JTextField(); // TODO
    JTextField SlotInput = new JTextField();


    public Teacher(String name, String surname, String password, String title, String abbreviation) {
        super(name, surname, password, title, "Dozent*in");
		this.abbreviation = abbreviation;
    }



    /**
     * creates a new Job, depending on the input given in the JText fields
     */
    private void createLecture(){
       // TODO anpassen an refaktorisierte Klassen: Create lecture will have
       //  own popup
       // new Job(LectureInput.getText(),SlotInput.getColumns());
    }
    
    public String getAbbreviation() {
		return abbreviation;
	}

}

