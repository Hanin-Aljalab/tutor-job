package model;

import view.swing.components.PreferencePopup;

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable {
	private String studNumber;
	private String studyPath;
	private ArrayList<String> lecturePref;
	private ArrayList<String> teacherPref;
	private boolean choiceMade;

	private PreferencePopup preferencePopup;

	public Student(String name, String surname, String password, String studNumber, String studyPath) {
		super(name, surname, password, "Student*in");
		this.studNumber = studNumber;
		this.studyPath = studyPath;
        teacherPref = new ArrayList<>();
		this.lecturePref = new ArrayList<>();
		choiceMade = false;
	}

	public void deletePreferences() {
		lecturePref.clear();
		teacherPref.clear();
		choiceMade = false;
	}

	public String getStudNumber() {
		return studNumber;
	}

	public String getStudyPath() {
		return studyPath;
	}

	public ArrayList<String> getLecturePref() {
		return lecturePref;
	}
	public void setLecturePref(ArrayList<String> lecturePref) {
		this.lecturePref = lecturePref;
	}

	public ArrayList<String> getTeacherPref() {
		return teacherPref;
	}

	public void removePreference(String preference) {
		lecturePref.remove(preference);
		teacherPref.remove(preference);
	}

	public void addPreference(String preference, String prefCategory) {
		if (prefCategory.equals("Kurse")) {
			lecturePref.add(preference);
		} else if (prefCategory.equals("Dozent*innen")) {
			teacherPref.add(preference);
		}
	}

	public void setChoiceMade(boolean choiceMade) {
		this.choiceMade = choiceMade;
	}

	public boolean isChoiceMade() {
		return choiceMade;
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName() + " (" + getStudNumber() + ")";
	}
}
