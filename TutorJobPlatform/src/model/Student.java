package model;

import view.swing.components.PreferencePopup;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the student in the system.
 * Each student has a student number, study path, lecture and
 * teacher preferences and a flag indicating if their choice has been made.
 */
public class Student extends User implements Serializable {
	private String studNumber;
	private String studyPath;
	private ArrayList<String> lecturePref;
	private ArrayList<String> teacherPref;
	private boolean choiceMade;

	private PreferencePopup preferencePopup;

	/**
	 * Constructs a new Student
	 * @param name the first name of the student
	 * @param surname the last name of the student
	 * @param password the password of the student
	 * @param studNumber the student number
	 * @param studyPath the study path
	 */
	public Student(String name, String surname, String password, String studNumber, String studyPath) {
		super(name, surname, password, "Student*in");
		this.studNumber = studNumber;
		this.studyPath = studyPath;
        teacherPref = new ArrayList<>();
		this.lecturePref = new ArrayList<>();
		choiceMade = false;
	}

	/**
	 * Deletes all preferences (both lecture and teacher)
	 * and resets the choice flag
	 */
	public void deletePreferences() {
		lecturePref.clear();
		teacherPref.clear();
		choiceMade = false;
	}

	/**
	 * @return the student number
	 */
	public String getStudNumber() {
		return studNumber;
	}

	/**
	 * @return the study path
	 */
	public String getStudyPath() {
		return studyPath;
	}

	/**
	 * @return the list of lecture preferences
	 */
	public ArrayList<String> getLecturePref() {
		return lecturePref;
	}

	/**
	 * Sets the list of lecture preferences
	 * @param lecturePref the list of lecture preferences
	 */
	public void setLecturePref(ArrayList<String> lecturePref) {
		this.lecturePref = lecturePref;
	}

	/**
	 * Sets the teacher preferences
	 * @param teacherPref the list of teacher preferences
	 */
	public void setTeacherPref(ArrayList<String> teacherPref) {
		this.teacherPref = teacherPref;
	}

	/**
	 * @return the list of teacher preferences
	 */
	public ArrayList<String> getTeacherPref() {
		return teacherPref;
	}

	/**
	 * Removes a preferences from both lecture and teacher preferences list
	 * @param preference the preference to be removed
	 */
	public void removePreference(String preference) {
		lecturePref.remove(preference);
		teacherPref.remove(preference);
	}

	/**
	 * Adds a preference to the appropriate list based on the category
	 * @param preference the preference to be added
	 * @param prefCategory the category of the preference ("Kursse" oder "Dozent*innen)
	 */
	public void addPreference(String preference, String prefCategory) {
		if (prefCategory.equals("Kurse")) {
			lecturePref.add(preference);
		} else if (prefCategory.equals("Dozent*innen")) {
			teacherPref.add(preference);
		}
	}

	/**
	 *Sets a flag indicating whether the choice has been made.
	 * @param choiceMade true if the choice has been made
	 *                   false otherwise
	 */
	public void setChoiceMade(boolean choiceMade) {
		this.choiceMade = choiceMade;
	}

	/**
	 * @return true if the choice has been made,
	 * 		   false otherwise
	 */
	public boolean isChoiceMade() {
		return choiceMade;
	}

	/**
	 * @return a string representation of the student
	 */
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName() + " (" + getStudNumber() + ")";
	}
}
