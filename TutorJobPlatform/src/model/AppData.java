package model;

import java.io.Serializable;
import java.util.*;

/**
 * Objects of this class centrally handle the data (students, teachers,
 * lectures, preferences, matchmaking status, matches) of the app.
 */
public class AppData implements Serializable {
    private boolean matchingDone;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Lecture> lectures;
    private Map<Student, Lecture> matches;

    public AppData() {
        matchingDone = false;
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        lectures = new ArrayList<>();
        matches = new HashMap<>();

        insertDummies();
    }

    /**
     * Creates and copies dummies for demonstration purposes
     */
    private void insertDummies() {
        Dummies.createDummies();
        students.addAll(Dummies.getStudentDummies());
        teachers.addAll(Dummies.getTeacherDummies());
        lectures.addAll(Dummies.getLectureDummies());
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
     * Registers a new user by adding them to data.
     *
     * @param user user to be added
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
	 * Adds a new lecture to the list of lectures.
	 *
	 * @param lecture The lecture to add.
	 */
	public void addLecture(Lecture lecture) {
		lectures.add(lecture);
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
	 * Removes user from list.
	 * @param user
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
	 * Provides complete names from all teachers in data
	 * @return list of teacher names consisting of title, first name, last name
	 */
    public ArrayList<String> getTeacherNames() {
        ArrayList<String> teacherNames = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherNames.add(teacher.toString());
        }
        return teacherNames;
    }

	/**
	 * Provides abbreviations of all lectures in data without duplicates
	 * @return list of lecture abbreviations without duplicates
	 */
    public ArrayList<String> getLectureNamesWithoutDuplicates() {
        Set<String> lectureNameSet = new HashSet<>();
        for (Lecture lecture : lectures) {
            lectureNameSet.add(lecture.getAbbreviation());
        }
        ArrayList<String> lectureNames = new ArrayList<>(lectureNameSet);
        return lectureNames;
    }

	/**
	 * Copies matches (students with lectures) into data
	 * @param matches hasp map of student/lecture combinations
	 */
    public void setMatches(HashMap<Student, Lecture> matches) {
        this.matches.putAll(matches);
    }

    public void setMatchingDone(boolean matchingDone) {
        this.matchingDone = matchingDone;
    }

    public boolean isMatchingDone() {
        return matchingDone;
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

	public Map<Student, Lecture> getMatches() {
		return matches;
	}
}
