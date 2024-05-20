package controller;
import java.util.*;

import model.Student;
import model.AppData;
import model.Teacher;
import model.Lecture;



public class Matchmaker {

    private int matNummer;
    private String teacher;
    private Lecture;
    private int numOpenTutorSlots;
    private List<Student> allStudents;
    private List<Student> copyAllStudents = new ArrayList<>(allStudents);
    private List<Lecture> allLectures;
    private List<Teacher> teachers;
    private Map<Student, Lecture> match;
    private boolean isAssigned;


    //TODO: Methode, welche je nach Fülle des Kurses/Fülle der Kurse des Teachers, die der
    // Studierende als Pref angegeben hat, den Studierenden zum weniger besetzten Kurs assigned.
    // Sorgt dafür, !!dass möglichst alle Kurse besetzt sind!!, und niemand etwas bekommt was er/sie gar nicht möchte.
    // yes, i know this is incredibly ugly
    public int balanceTutors() {
        if (student.getLecturePref().lecture.getNumOpenTutorSlots() >
                student.getTeacherPref.lecture.getNumOpenTutorSlots()) {
            //TODO student gets assigned to lecture of their choice
            else{
                //TODO student gets assigned to profs course of their choice
            }
        }
    }

    public Matchmaker() {
        allStudents = data.getStudents();
        allLectures = data.getLectures();
        match = new HashMap<>();
    }

    private void matchStudent(Student student) {

        isAssigned = false;
        for (String pref : student.getLecturePref()) {
            for (Lecture lecture : allLectures) {
                if (lecture.getNumOpenTutorSlots() > 0
                        && pref.equals(lecture.getAbbreviation())) {
                    match.put(student, lecture);
                    lecture.allocateSlot();
                    isAssigned = true;
                    gotAssigned(student);
                }
            }
        }

        //TODO not yet funktionsfähig!!
        // wenn teacher course hat, welcher slot > 0 hat und "stud.TeacherPref().equals(teacher)" ...
        isAssigned = false;
        for (String teachpref : student.getTeacherPref()) {
            for (Teacher teacher : teachers) {
                for (Lecture lecture : teacher.getLectures()) {
                    if (lecture.getNumOpenTutorSlots() > 0
                            && teacher.getTeacher().equals(teachpref)) {
                        match.put(student, teacher.getName());
                        lecture.allocateSlot();
                        isAssigned = true;
                        gotAssigned(student);
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
            matchStudent(nextStudent);
        }
    }

    public void gotAssigned(Student student) {
        if (isAssigned) {
            copyAllStudents.remove(student);
        }
    }

    public static void main(String[] args) {
        Matchmaker make = new Matchmaker();
    }
}

