package model;

import view.swing.components.PreferencePopup;
import java.util.ArrayList;

public class Student extends User {
	private String studNumber;
	private String studyPath;
	private ArrayList<String> lecturePref;
	private ArrayList<String> teacherPref;
	private boolean choiceMade;

	private PreferencePopup preferencePopup;

	public Student(String firstName, String lastName, String password, String studNumber, String studyPath) {
		super(firstName, lastName, password, "Student*in");
		this.studNumber = studNumber;
		this.studyPath = studyPath;
		choiceMade = false;
	}

	public void setPreferences(ArrayList<String> lectures, ArrayList<String> teachers) {
		setLecturePref(lectures);
		setTeacherPref(teachers);
		choiceMade = true;
		System.out.println("Das steht in Student" + lectures);
		System.out.println("Das steht in Student" + teachers);
	}

	public void setLecturePref(ArrayList<String> lectures) {
		lecturePref = lectures;
	}

	public void setTeacherPref(ArrayList<String> teachers) {
		teacherPref = teachers;
	}

	public void deletePreferences() {
		lecturePref = null;
		teacherPref = null;
		choiceMade = false;
	}

	public String getStudNumber() {
		return studNumber;
	}

	public ArrayList<String> getLecturePref() {
		return lecturePref;
	}

	public ArrayList<String> getTeacherPref() {
		return teacherPref;
	}

	public void setChoiceMade(boolean choiceMade) {
		this.choiceMade = choiceMade;
	}

	public boolean isChoiceMade() {
		return choiceMade;
	}

}
