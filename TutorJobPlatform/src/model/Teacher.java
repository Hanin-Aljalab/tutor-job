package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Represents the teacher in the system.
 * Each teacher has a teacher-ID and can manage the list of lectures
 */
public class Teacher extends User implements Serializable {
	private String teacherId;

	JTextField LectureInput = new JTextField();
	JTextField SlotInput = new JTextField();
	ArrayList<Lecture> lectures;

	/**
	 * Constructs a new teacher
	 * @param firstName the first name of the teacher
	 * @param lastName the last name of the teacher
	 * @param password the password of the teacher
	 * @param title the title of the teacher
	 * @param teacherId the unique ID of the teacher
	 */
	public Teacher(String firstName, String lastName, String password, String title, String teacherId) {
		super(firstName, lastName, password, title, "Dozent*in");
		this.teacherId = teacherId;
		lectures = new ArrayList<>();
	}

	/**
	 * @return the list of lectures manged by the teacher
	 */
	public ArrayList<Lecture> getLectures() {
		return lectures;
	}
	
	/**
	 * creates a new Job, depending on the input given in the JText fields
	 * @param firstName the name of the lecture
	 * @param abbreviation the abbreviation of the lecture
	 * @param numTutors the number of desired tutors
	 * @param courseInfo information about the course
	 * @param allowedStudyPaths the study paths allowed for this lecture
	 * @return the created lecture object
	 */
	public Lecture createLecture(String firstName, String abbreviation, int numTutors, String courseInfo,
			ArrayList<String> allowedStudyPaths) {
		Lecture lecture = new Lecture(firstName, abbreviation, numTutors, courseInfo, this, allowedStudyPaths);
		return lecture;
	}

	/**
	 * @return the teacher ID.
	 */
	public String getTeacherId() {
		return teacherId;
	}

	/**
	 * @return a string representation of the teacher.
	 */
	@Override
	public String toString() {
		if (getTitle().isEmpty()) {
			return getFirstName() + " " + getLastName();
		}
		return getTitle() + " " + getFirstName() + " " + getLastName();
	}
}
