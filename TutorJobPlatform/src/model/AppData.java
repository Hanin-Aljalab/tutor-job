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

    //  TODO: Es sollte auch nicht anhand von dem Namen überprüft werden
    //   (Namen können gleich sein), sondern entweder mit Matrikelnummer oder
    //   Emailadresse. Wenn kein Student/Dozent gefunden wird, sollte eine
    //   Fehlermeldung (Exception) herausgegeben werden.
    
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
    //  einen gewünschten Wert zurückgeben, z.B. etwas wie find() - dann
    //  müsste man es nicht mit einer komplizierten for-Schleife lösen
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

    // TODO removeUser();

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}
