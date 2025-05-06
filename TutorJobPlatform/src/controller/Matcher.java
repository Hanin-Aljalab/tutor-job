package controller;

import model.*;

import java.util.*;
import java.util.HashMap;


public class Matcher {
    private List<Student> allStudents;
    private List<Student> hasNoPref;
    private List<Lecture> allLectures;
    private List<Teacher> allTeachers;
    private boolean noPrefIsAssigned;
    private HashMap<Student, Lecture> matches;

    public HashMap<Student, Lecture> getMatches() {
        return matches;
    }

    public Matcher() {
        matches = new HashMap<>();
        hasNoPref = new ArrayList<>();

        allStudents = new ArrayList<>();
        allLectures = new ArrayList<>();
        allTeachers = new ArrayList<>();
    }

    /* Methode, welche je nach Fülle des Kurses/Fülle der Kurse des Teachers, die der
    / Studierende als Pref angegeben hat, den Studierenden zum weniger besetzten Kurs zuordnet.
    */
    public void balance(Student student) {
        /*
       Students who have no preference at all, will be added
       only after every student with a preference got assigned.
        */
        System.out.println("Die Prefs sind: " + student.getLecturePref());
        if (student.getLecturePref().isEmpty() && student.getTeacherPref().isEmpty()) {
            hasNoPref.add(student);
            allStudents.remove(student);
        } else if (student.getTeacherPref().isEmpty()) {
            matchToCourse(student);
        } else if (student.getLecturePref().isEmpty()) {
            matchToTeacher(student);
        } else {
            complexMatching(student);
        }
    }

    private void complexMatching(Student student) {
        int numSlotsPrefCourse = 0;
        int numSlotsPrefTeacher = 0;
        for (String lectPref : student.getLecturePref()) {
            for (Lecture lecture : allLectures) {
                if (lectPref.equals(lecture.getAbbreviation())) {
                    numSlotsPrefCourse += lecture.getNumOfOpenSlots();
                }
            }
        }

        for (String teachPref : student.getTeacherPref()) {
            for (Teacher teacher : allTeachers) {
                if (teachPref.equals(teacher.getTeacherId())) {
                    for (Lecture lecture : teacher.getLectures()) {
                        numSlotsPrefTeacher += lecture.getNumOfOpenSlots();
                    }
                }
            }
        }
        System.out.println("Nummer der Pref Course slots" + numSlotsPrefCourse);
        System.out.println("Nummer der Teach Course slots" + numSlotsPrefTeacher);
        if (numSlotsPrefCourse > numSlotsPrefTeacher) {
            matchToCourse(student);
        } else {
            matchToTeacher(student);
        }
    }


    private void matchToCourse(Student student) {

        System.out.println("1");
        sortLectures();
        for (Lecture lecture : allLectures) {
            for (String pref : student.getLecturePref()) {
                System.out.println("2");

                System.out.println("3");
                if (lecture.getNumOfOpenSlots() > 0
                        && pref.equals(lecture.getAbbreviation())) {
                    System.out.println("4");
                    for (String studypath : lecture.getStudyPaths()) {
                        if (student.getStudyPath().equals(studypath)) {
                            matches.put(student, lecture);
                            System.out.println(allStudents);
                            allStudents.remove(student);
                            System.out.println(allStudents);
                            lecture.decrementSlot();
                            lecture.incrementTutors();
                            return;
                        }
                    }
                }
            }
        }
        allStudents.remove(student);
        hasNoPref.add(student);
    }


    public void matchToTeacher(Student student) {

        System.out.println("Bin ich hier?");
        sortLectures();
        System.out.println(student);
        System.out.println(student.getTeacherPref());
        for (String teachpref : student.getTeacherPref()) {
            for (Teacher teacher : allTeachers) {
                for (Lecture lecture : teacher.getLectures()) {
                    if (lecture.getNumOfOpenSlots() > 0
                            && lecture.getTeacher().equals(teachpref)
                            && student.getStudyPath().equals(lecture.getStudyPaths())) {
                        matches.put(student, lecture);
                        allStudents.remove(student);
                        lecture.decrementSlot();
                        lecture.incrementTutors();
                        return;
                    }
                }
            }
        }
    }

    public void noPrefStudents() {
        //TODO decision tree course > teacher.course?
        noPrefIsAssigned = false;
        if (allStudents.isEmpty()) {
            for (Student student : hasNoPref) {
                System.out.println(student);
                for (Lecture lecture : allLectures) {
                    if ((lecture.getNumOfOpenSlots() > 0
                            && student.getStudyPath().equals(lecture.getStudyPaths()))) {
                        matches.put(student, lecture);
                        noPrefIsAssigned = true;
                        hasNoPref.remove(student);
                        lecture.decrementSlot();
                        break;
                    }
                }
                if (!noPrefIsAssigned) {
                    for (Teacher teacher : allTeachers) {
                        for (Lecture lecture : teacher.getLectures()) {
                            if ((lecture.getNumOfOpenSlots() > 0 && student.getStudyPath().equals(lecture.getStudyPaths()))) {
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

    private Student pickRandomStudent(List<Student> allStudents) {
        Random random = new Random();
        int index = random.nextInt(allStudents.size());
        return allStudents.get(index);
    }

    public void allocateStudents() { //pick random students while thr List is full
        allStudents.addAll(App.getData().getStudents());
        allLectures.addAll(App.getData().getLectures());
        allTeachers.addAll(App.getData().getTeachers());

        while (!allStudents.isEmpty()) {
            Student nextStudent = pickRandomStudent(allStudents);
            balance(nextStudent);
        }
        noPrefStudents();
        App.getData().setMatches(matches);
    }
//
//    public void gotAssigned(Student student) {
//        if (isAssigned) {
//            allStudents.remove(student);
//        }
//    }

    private void sortLectures() {
        System.out.println(allLectures);
        Collections.sort(allLectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                if (o1.getNumOfAssignedTutors() == 0) {
                    return -1;
                } else {
                    return o2.getNumOfOpenSlots() - o1.getNumOfOpenSlots();
                }
            }

        });
        System.out.println(allLectures);
    }
}