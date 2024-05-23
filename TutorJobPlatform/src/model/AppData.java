package model;

import java.io.Serializable;
import java.util.*;
import exceptions.*;

public class AppData implements Serializable {
	private ArrayList<Student> students;
	private ArrayList<Teacher> teachers;
	private ArrayList<Lecture> lectures;
	private HashMap<Student, Lecture> matches;

	public AppData() {
		System.out.println("Ich erstelle ein neues Objekt.");
		students = new ArrayList<>();
		teachers = new ArrayList<>();
		lectures = new ArrayList<>();
		matches = new HashMap<>();
	}

	public void createDummies() {
		// TODO: These objects are dummies for testing purposes!
		Student stud1 = new Student("Max", "Mustermann", "12345", "1234567",
				"CSB");
		ArrayList<String> lecturePref1 = new ArrayList<>();
		lecturePref1.add("PR1");
		lecturePref1.add("SE1");
		lecturePref1.add("MI1");
		stud1.setLecturePref(lecturePref1);

		Student stud2 = new Student("Erna", "Musterfrau", "12345",
				"1111111",
				"IB");
		ArrayList<String> lecturePref2 = new ArrayList<>();
		lecturePref2.add("PR1");
		lecturePref2.add("SE1");
		lecturePref2.add("MA2");
		stud2.setLecturePref(lecturePref2);

		Student stud3 = new Student("Maja", "Biene", "12345", "2222222",
				"IMB");
		ArrayList<String> lecturePref3 = new ArrayList<>();
		lecturePref3.add("PR1");
		lecturePref3.add("SE1");
		lecturePref3.add("MA2");
		stud3.setLecturePref(lecturePref3);

		Student stud4 = new Student("Henry", "Hupfe", "12345",
				"3333333", "IMB");
		ArrayList<String> lecturePref4 = new ArrayList<>();
		lecturePref4.add("PR2");
		lecturePref4.add("MI1");
		lecturePref4.add("SE1");
		stud4.setLecturePref(lecturePref4);

		students.add(stud1);
		students.add(stud2);
		students.add(stud3);
		students.add(stud4);

		Teacher tea1 = new Teacher("Jens", "Baum", "12345", "Dr.", "BAJ");
		Teacher tea2 = new Teacher("Jolla", "Jammer", "12345", "Prof. Dr.",
				"JOJA");
		Teacher tea3 = new Teacher("Harry", "Potter", "12345", "",
				"POH");

		teachers.add(tea1);
		teachers.add(tea2);
		teachers.add(tea3);

		Lecture lec1 = new Lecture("Mathematik 1", "MA1", 3, "Aussagenlogik, " +
				"Folgen, Beweise", tea1, new ArrayList<>(List.of(new String[]{"IMB", "IB",
				"CSB", "UIB"})));
		Lecture lec2 = new Lecture("Mathematik 2", "MA2", 5,
				"Vektorräume, Gruppen", tea1, new ArrayList<>(List.of(new String[]{"IMB", "IB",
				"CSB", "UIB"})));
		Lecture lec3 = new Lecture("Programmieren 1", "PR1", 2,
				"Java Basics", tea2, new ArrayList<>(List.of(new String[]{
				"IMB", "IB", "CSB"})));
		Lecture lec4 = new Lecture("Medizinische Informatik 1",
				"MI1",	1, "Terminologie, Biologie, Physik", tea3,
				new ArrayList<>(List.of(new String[]{"IMB"})));
		Lecture lec5 = new Lecture("Software Engineering 1", "MA2", 1,
				"Analyse, Design, Testen, Swing", tea2,
				new ArrayList<>(List.of(new String[]{"IMB", "IB", "CSB", "UIB"})));
		Lecture lec6 = new Lecture("Mathematik 1", "MA1", 3, "Aussagenlogik, " +
				"Folgen, Beweise", tea2,
				new ArrayList<>(List.of(new String[]{"IMB", "IB",
						"CSB", "UIB"})));

		lectures.add(lec1);
		lectures.add(lec2);
		lectures.add(lec3);
		lectures.add(lec4);
		lectures.add(lec5);
		lectures.add(lec6);
	}

	/**
	 * Adds a new lecture to the list of lectures.
	 *
	 * @param lecture The lecture to add.
	 */
	public void addLecture(Lecture lecture) {
		lectures.add(lecture);
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

	// TODO JavaDoc
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
    public List<Lecture> getLectures() {
        return lectures;
    }

	public ArrayList<String> getTeacherNames() {
		ArrayList<String> teacherNames = new ArrayList<>();
		for (Teacher teacher : teachers) {
			teacherNames.add(teacher.toString());
		}
		return teacherNames;
	}

	public ArrayList<String> getLectureNamesWithoutDuplicates() {
		Set<String> lectureNameSet = new HashSet<>();
		for (Lecture lecture : lectures) {
			lectureNameSet.add(lecture.getAbbreviation());
		}
		ArrayList<String> lectureNames = new ArrayList<>(lectureNameSet);
		return lectureNames;
	}

	public void setMatches(HashMap<Student, Lecture> matches) {
		this.matches = matches;
	}

	public HashMap<Student, Lecture> getMatches() {
		return matches;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AppData)) return false;
		AppData appData = (AppData) o;
		return Objects.equals(students, appData.students) &&
				Objects.equals(teachers, appData.teachers) &&
				Objects.equals(lectures, appData.lectures);
	}

	@Override
	public int hashCode() {
		return Objects.hash(students, teachers, lectures);
	}

}
