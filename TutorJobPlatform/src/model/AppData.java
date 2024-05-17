package model;

import java.util.ArrayList;
import exceptions.UserAlreadyExistsException;

import java.util.List;

public class AppData {
	final public static AppData data = new AppData();
	private static List<Student> students;
	private static List<Teacher> teachers;

	public AppData() {
		students = new ArrayList<>();
		teachers = new ArrayList<>();
	}

	/**
	 * Checks if a user is already registered.
	 * 
	 * @param teacherId  the teacher ID
	 * @param studNumber the student number
	 * @param role       the role of the user (e.g., student, teacher)
	 * @return true if the user exists, false otherwise
	 */
	public boolean checkIfUserExists(String teacherId, String studNumber, String role) {
		if (role.equals("Dozent*in")) {
			for (Teacher teacher : teachers) {
				if (teacherId.equals(teacher.getTeacherId())) {
					return true;
				}
			}
		}

		if (role.equals("Student*in")) {
			for (Student student : students) {
				if (studNumber.equals(student.getStudNumber())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Registers a new user.
	 * 
	 * @param user the user to be added
	 * @throws UserAlreadyExistsException if the user already exists
	 */
	public void addUser(User user) {
		if (user instanceof Teacher) {
			Teacher teacher = (Teacher) user;
			teachers.add(teacher);
		}
		if (user instanceof Student) {
			Student student = (Student) user;
			students.add(student);
		}
	}

	/**
	 * Searches for and returns a user based on their student number, teacher ID,
	 * and role.
	 * 
	 * @param studNumber the student number
	 * @param teacherId  the teacher ID
	 * @param role       the role of the user (e.g., student, teacher)
	 * @return the user if found, otherwise null
	 */
	public User getUser(String studNumber, String teacherId, String role) {
		if (role.equals("Dozent*in")) {
			for (Teacher teacher : teachers) {
				if (teacher.getTeacherId().equals(teacherId)) {
					return teacher;
				}
			}
		}

		if (role.equals("Student*in")) {
			for (Student student : students) {
				if (student.getStudNumber().equals(studNumber)) {
					return student;
				}
			}
		}

		return null; // Username not found
	}

	/**
	 * Searches for and returns a user based on their student number, teacher ID,
	 * and role.
	 * 
	 * @param studNumber the student number
	 * @param teacherId  the teacher ID
	 * @param role       the role of the user (e.g., student, teacher)
	 * @return the user if found, otherwise null
	 */
	public void removeUser(User user) {
		if (user instanceof Teacher) {
			Teacher teacher = (Teacher) user;
			if (teachers.contains(teacher)) {
				teachers.remove(teacher);
			}
		}

		if (user instanceof Student) {
			Student student = (Student) user;
			if (students.contains(student)) {
				students.remove(student);
			}
		}
	}

	/**
	 * Returns the list of students.
	 * 
	 * @return the list of students
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * Returns the list of teachers.
	 * 
	 * @return the list of teachers
	 */
	public List<Teacher> getTeachers() {
		return teachers;
	}
}
