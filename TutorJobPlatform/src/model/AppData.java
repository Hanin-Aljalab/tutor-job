package model;

import java.util.ArrayList;
import exceptions.UserAlreadyExistsException;

import java.util.List;

public class AppData {
	public static AppData data = new AppData();
	private static List<Student> students;
	private static List<Teacher> teachers;

	public AppData() {
		students = new ArrayList<>();
		teachers = new ArrayList<>();
	}

	// Methode, die überprüft ob ein Benutzer registriert ist
	public boolean checkIfUserExists(String abbreviation, String studNumber, String role) {
		if (role.equals("Dozent*in")) {
			for (Teacher teacher : teachers) {
				if (abbreviation.equals(teacher.getAbbreviation())) {
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

	// Methode, die einen Benutzer registriert
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

	// TODO: Auch eine Überlegung: in ArrayLists gibt es MEthoden, die direkt
	// einen gewünschten Wert zurückgeben, z.B. etwas wie find() - dann
	// müsste man es nicht mit einer komplizierten for-Schleife lösen
	
	//Methode, die einen bestimmten Benutzer sucht und zurückgibt
	public User getUser(String studNumber, String abbreviation, String role) {
		if (role.equals("Dozent*in")) {
			for (Teacher teacher : teachers) {
				if (teacher.getAbbreviation().equals(abbreviation)) {
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

	// Methode, die einen Benutzer löscht
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

	public List<Student> getStudents() {
		return students;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}
}
