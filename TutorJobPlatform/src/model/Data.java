package model;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Student> students;
    private static ArrayList<Teacher> teachers;

    public Data() {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
    }

    public static void addUser (User user) {
        if (user.getClass() == Student.class) {
            students.add((Student) user);
        }
        if (user.getClass() == Teacher.class) {
            teachers.add((Teacher) user);
        }
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static ArrayList<Teacher> getTeachers() {
        return teachers;
    }
}
