package controller;

import model.*;

import java.util.*;
import java.util.HashMap;

import static model.AppData.data;


public class Matcher {
    //private String teacher;
    //private int numOpenTutorSlots;
    private List<Student> allStudents;
    private List<Student> hasNoPref;
    private List<Student> copyAllStudents = new ArrayList<>(allStudents);
    private List<Lecture> allLectures;
    private List<Teacher> teachers;
    private boolean isAssigned;
    private boolean noPrefIsAssigned;
    private static HashMap<Student, Lecture> matches;

    public static HashMap<Student, Lecture> getMatches() {
        return AppData.getMatches();
    }


    public Matcher() {
        allStudents = data.getStudents();
        allLectures = data.getLectures();
        //teachers = data.getTeachers();
        hasNoPref = new ArrayList<>();
        copyAllStudents = new ArrayList<>();
    }


    /* Methode, welche je nach Fülle des Kurses/Fülle der Kurse des Teachers, die der
    / Studierende als Pref angegeben hat, den Studierenden zum weniger besetzten Kurs zuordnet.
    */
    public void balance(Student student) {
        List<String> lecturePrefs = student.getLecturePref();
        List<String> teacherPrefs = student.getTeacherPref();
        int numSlotsPrefCourse = 0;
        int numSlotsPrefTeacher = 0;

        /*
       Students who have no preference at all, will be added
       only after every student with a preference got assigned.
        */
        if (student.getLecturePref().isEmpty() || student.getTeacherPref().isEmpty()) {
            hasNoPref.add(student);
            copyAllStudents.remove(student);
            return;
        }

        for (String lectPref : student.getLecturePref()) {
            for (Lecture lecture : allLectures) {
                if (lectPref.equals(lecture.getAbbreviation())) {
                    numSlotsPrefCourse += lecture.getNumOfTutors();
                }
            }
        }

        for (String teachPref : student.getTeacherPref()) {
            for (Teacher teacher : teachers) {
                if (teachPref.equals(teacher.getTeacherId())) {
                    for (Lecture lecture : teacher.getLectures()) {
                        numSlotsPrefTeacher += lecture.getNumOfTutors();
                    }
                }
            }
        }

        if (numSlotsPrefCourse > numSlotsPrefTeacher) {
            matchToCourse(student);
        } else {
            matchToTeacher(student);
        }
    }

    private void matchToCourse(Student student) {

        isAssigned = false;
        for (String pref : student.getLecturePref()) {
            for (Lecture lecture : allLectures) {
                if (lecture.getNumOfTutors() > 0
                        && pref.equals(lecture.getAbbreviation())
                        && student.getStudyPath().equals(lecture.getStudyPaths())) {
                    matches.put(student, lecture);
                    lecture.decrementSlot();
                    isAssigned = true;
                    gotAssigned(student);
                }
            }
            if (isAssigned) break;
        }
    }

    public void matchToTeacher(Student student) {

        isAssigned = false;
        for (String teachpref : student.getTeacherPref()) {
            for (Teacher teacher : teachers) {
                for (Lecture lecture : teacher.getLectures()) {
                    if (lecture.getNumOfTutors() > 0
                            && lecture.getTeacher().equals(teachpref)
                            && student.getStudyPath().equals(lecture.getStudyPaths())) {
                        matches.put(student, lecture);
                        lecture.decrementSlot();
                        isAssigned = true;
                        gotAssigned(student);
                    }
                }
            }
            if (isAssigned) break;
        }
    }

    public void noPrefStudents() {
        //TODO decision tree course > teacher.course?
        noPrefIsAssigned = false;
        if (copyAllStudents.isEmpty()) {
            for (Student student : hasNoPref) {
                for (Lecture lecture : allLectures) {
                    if ((lecture.getNumOfTutors() > 0
                            && student.getStudyPath().equals(lecture.getStudyPaths()))) {
                        matches.put(student, lecture);
                        noPrefIsAssigned = true;
                        hasNoPref.remove(student);
                        lecture.decrementSlot();
                        break;
                    }
                }
                if (!noPrefIsAssigned) {
                    for (Teacher teacher : teachers) {
                        for (Lecture lecture : teacher.getLectures()) {
                            if ((lecture.getNumOfTutors() > 0 && student.getStudyPath().equals(lecture.getStudyPaths()))) {
                                matches.put(student, lecture);
                                noPrefIsAssigned = true;
                                hasNoPref.remove(student);
                                lecture.decrementSlot();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private Student pickRandomStudent(List<Student> copyAllStudents) {
        Random random = new Random();
        int index = random.nextInt(copyAllStudents.size());
        return copyAllStudents.get(index);
    }

    public void allocateStudents() { //pick random students while thr List is full
        while (!copyAllStudents.isEmpty()) {
            Student nextStudent = pickRandomStudent(copyAllStudents);
            balance(nextStudent);
        }
        noPrefStudents();
    }

    public void gotAssigned(Student student) {
        if (isAssigned) {
            copyAllStudents.remove(student);
        }
    }

    public static void main(String[] args) {
        Matcher make = new Matcher();
        make.allocateStudents();
    }
}










