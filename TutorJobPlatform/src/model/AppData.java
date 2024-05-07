package model;

import java.util.ArrayList;

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
    public boolean checkIfUsernameExists(String username, String profession) {
        if (profession.equals("Dozent*in")) {
            for (Teacher teacher : teachers) {
                if (teacher.getLastName().equals(username)) {
                    return true;
                }
            }
        }

        if (profession.equals("Student*in")) {
            for (Student student : students) {
                if (student.getLastName().equals(username)) {
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
    public User getUser(String username, String profession) {
        if (profession.equals("Dozent*in")) {
            for (Teacher teacher : teachers) {
                if (teacher.getLastName().equals(username)) {
                    return teacher;
                }
            }
        }

        if (profession.equals("Student*in")) {
            for (Student student : students) {
                if (student.getLastName().equals(username)) {
                    return student;
                }
            }
        }

        return null; // Username not found
    }

    // TODO removeUser();

    public static List<Student> getStudents() {
        return students;
    }

    public static List<Teacher> getTeachers() {
        return teachers;
    }
}
