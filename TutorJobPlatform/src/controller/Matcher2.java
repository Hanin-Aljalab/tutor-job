package controller;

import model.AppData;
import model.Lecture;
import model.Student;

import java.util.*;


public class Matcher2 {
    private List<Student> registeredStudents;
    private List<Lecture> registeredLectures;

    private HashMap<Student, Lecture> actualMatches;
    private HashMap<Student, Lecture> bestMatches;
    private HashMap<Student, Lecture> finalMatches;

    private int actualScore;
    private int bestScore;

    private AppData data;

    public Matcher2(AppData data) {
        this.data = data;
        actualMatches = new HashMap<>();
        bestMatches = new HashMap<>();
        finalMatches = new HashMap<>();
        registeredStudents = new ArrayList<>();
        registeredLectures = new ArrayList<>();
    }

    public HashMap<Student, Lecture> performMatching() {
        for (int i = 0; i < 3000; i++) {
            initializeLists();
            resetLectureAllocations();
            allocateStudents();
            evaluateMatchResult();
            actualMatches.forEach((student, lecture) -> {
            });
        }
        finalMatches.putAll(bestMatches);
        finalMatches.forEach((student, lecture) -> {
        });

        return finalMatches;
    }

    private void allocateStudents() {
        for (Student student : registeredStudents) {
            if (!registeredLectures.isEmpty()) {
                actualMatches.put(student, getRandomLecture());
            }
        }
    }

    private Lecture getRandomLecture() {
        Random rand = new Random();
        int num = rand.nextInt(registeredLectures.size());

        Lecture randomLecture = registeredLectures.get(num);
        randomLecture.decrementSlot();
        randomLecture.incrementTutors();
        if (randomLecture.getNumOfOpenSlots() == 0) {
            registeredLectures.remove(randomLecture);
        }
        return randomLecture;
    }

    private void evaluateMatchResult() {
        ArrayList<Integer> scores = new ArrayList<>();

        actualMatches.forEach((matchedStudent, matchedLecture) -> {
            int score = evaluateSingleMatch(matchedStudent, matchedLecture);
            scores.add(score);
        });

        actualScore = 0;
        for (int score : scores) {
            actualScore += score;
        }
        actualScore += evaluateEmptyLectures();

        if (actualScore > bestScore) {
            bestScore = actualScore;
            bestMatches.clear();
            bestMatches.putAll(actualMatches);
        }
    }

    private int evaluateSingleMatch(Student matchedStudent,
                                    Lecture matchedLecture) {
        int individualScore = 0;
        for (String pref : matchedStudent.getLecturePref()) {
            if (pref.equals(matchedLecture.getAbbreviation())) {
                individualScore += 3;
            }
        }
        for (String pref : matchedStudent.getTeacherPref()) {
            if (pref.equals(matchedLecture.getTeacher().toString())) {
                individualScore += 2;
            }
        }
        for (String allowedStudyPaths : matchedLecture.getStudyPaths()) {
            if (matchedStudent.getStudyPath().equals(allowedStudyPaths)) {
                individualScore += 20;
                break;
            }
        }
        return individualScore;
    }

    private int evaluateEmptyLectures() {
        int lectureScore = 0;
        for (Lecture lecture : data.getLectures()) {
            if (lecture.getNumOfAssignedTutors() == 0) {
                lectureScore -= 15;
            }
        }
        return lectureScore;
    }

    private void initializeLists() {
        registeredStudents.clear();
        registeredLectures.clear();
        actualMatches.clear();
        registeredStudents.addAll(data.getStudents());
        registeredLectures.addAll(data.getLectures());
    }

    private void resetLectureAllocations() {
        registeredLectures.forEach(lecture -> {
            lecture.resetTutorAssignment();
        });
    }

    public HashMap<Student, Lecture> getMatches() {
        return finalMatches;
    }

    public static void main(String[] args) {
        AppData testData = new AppData();
        Matcher2 matcher = new Matcher2(testData);
        matcher.performMatching();
    }
}