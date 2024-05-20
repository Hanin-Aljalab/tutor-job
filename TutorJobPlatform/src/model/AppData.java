package model;

import java.util.ArrayList;
import exceptions.UserAlreadyExistsException;

import java.util.HashMap;
import java.util.List;

public class AppData {
	final public static AppData data = new AppData();
	private static List<Student> students;
	private static List<Teacher> teachers;
	private static List<Lecture> lectures;
	private static HashMap<Student, Lecture> matches;
    public static AppData data = new AppData();
    private static List<Student> students;
    private static List<Teacher> teachers;
    private static List<Lecture> lectures;

	public AppData() {
		students = new ArrayList<>();
		teachers = new ArrayList<>();
		lectures = new ArrayList<>();

		// TODO: These objects are dummies for testing purposes!
		Student stud1 = new Student("Max", "Mustermann", "12345", "1234567",
				"CSB");
		Student stud2 = new Student("Erna", "Musterfrau", "12345",
				"1111111",
				"IB");
		Student stud3 = new Student("Maja", "Biene", "12345", "2222222",
				"IMB");
		Student stud4 = new Student("Henry", "Hupfe", "12345",
				"3333333", "IMB");

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
		Lecture lec2 = new Lecture("Mathematik 2", "MA2", 2,
				"Vektorräume, Gruppen", tea1, new ArrayList<>(List.of(new String[]{"IMB", "IB",
				"CSB", "UIB"})));
		Lecture lec3 = new Lecture("Programmieren 1", "PR1", 5,
				"Java Basics", tea2, new ArrayList<>(List.of(new String[]{
						"IMB", "IB", "CSB"})));
		Lecture lec4 = new Lecture("Medizinische Informatik 1",
				"MI1",	2, "Terminologie, Biologie, Physik", tea3,
				new ArrayList<>(List.of(new String[]{"IMB"})));
		Lecture lec5 = new Lecture("Software Engineering 1", "SE1", 3,
				"Analyse, Design, Testen, Swing", tea2,
				new ArrayList<>(List.of(new String[]{"IMB", "IB", "CSB", "UIB"})));

		lectures.add(lec1);
		lectures.add(lec2);
		lectures.add(lec3);
		lectures.add(lec4);
		lectures.add(lec5);

		matches = new HashMap<>();
		matches.put(stud1, lec1);
		matches.put(stud2, lec2);
		matches.put(stud3, lec3);
	//	matches.put(stud4, lec4);
		matches.put(stud4, lec1);
	}
    public AppData() {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        lectures = new ArrayList<>();
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

    public static List<Teacher> getTeachers() {
        return teachers;
    }

    public static List<Lecture> getLectures() {
        return lectures;
    }

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public ArrayList<String> getTeacherNames() {
		ArrayList<String> teacherNames = new ArrayList<>();
		for (Teacher teacher : teachers) {
			teacherNames.add(teacher.toString());
		}
		return teacherNames;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public ArrayList<String> getLectureNames() {
		ArrayList<String> lectureNames = new ArrayList<>();
		for (Lecture lecture : lectures) {
			lectureNames.add(lecture.getAbbreviation());
		}
		return lectureNames;
	}

	public static HashMap<Student, Lecture> getMatches() {
		return matches;
	}
}
