package controller;

import model.AppData;
import model.Lecture;
import model.Student;

import java.util.*;

/**
 * This class performs the matchmaking of the tutor job system. A random
 * allocation of students to lectures is performed, then a score of this
 * allocation is calculated. This is performed
 * Aspects of the scoring system:
 * 1.) Study paths of students match requirements of the lecture.
 * 2.) All lectures have at least one tutor.
 * 3.) Student preferences and lecture properties match
 */
public class Matcher2 {
    private List<Student> registeredStudents;
    private List<Lecture> registeredLectures;

    private HashMap<Student, Lecture> actualMatches;
    private HashMap<Student, Lecture> bestMatches;
    private HashMap<Student, Lecture> finalMatches;

    private int actualScore;
    private int bestScore;

    private AppData data;

    /**
     * Initializes lists and links data set
     * @param data data set which the matcher will work on
     */
    public Matcher2(AppData data) {
        this.data = data;
        actualMatches = new HashMap<>();
        bestMatches = new HashMap<>();
        finalMatches = new HashMap<>();
        registeredStudents = new ArrayList<>();
        registeredLectures = new ArrayList<>();
    }

    /**
     * Performs student allocation for several times and evaluates the result.
     * The best result is then returned.
     * @return the best matching result of i matching rounds
     */
    public HashMap<Student, Lecture> performMatching() {
        for (int i = 0; i < 10000; i++) {
            initializeLists();
            resetLectureAllocations();
            allocateStudents();
            evaluateMatchResult();
        }
        finalMatches.putAll(bestMatches);
        finalMatches.forEach((student, lecture) -> {
            System.out.println(student.getLastName() + " unterrichtet: " + lecture.toString());
        });
        System.out.println(bestScore);

        return finalMatches;
    }

    /**
     * Randomly matches all students to lectures
     */
    private void allocateStudents() {
        Collections.shuffle(registeredStudents);
        for (Student student : registeredStudents) {
            if (!registeredLectures.isEmpty()) {
                actualMatches.put(student, getRandomLecture());
            }
        }
    }

    /**
     * Picks random lecture from list, updates tutor slot information,
     * removes fully allocated lectures
     * @return randomly chosen lecture
     */
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

    /**
     * Calculates score of a single matchmaking round. If result ranks higher
     * than former best matching, result is transmitted to best matches map.
     */
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

        if (actualScore >= bestScore) {
            System.out.println(actualScore);
        }
        if (actualScore > bestScore) {
            bestScore = actualScore;
            bestMatches.clear();
            bestMatches.putAll(actualMatches);
        }
    }

    /**
     * Calculates the individual score of a matching pair:
     * 3 points: student's lecture preference matches lecture
     * 2 points: student's teacher preference matches lecture
     * 20 points: student's study path allowed in lecture
     * @param matchedStudent
     * @param matchedLecture
     * @return int scoring result
     */
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

    /**
     * Gives point deduction for lectures without allocated students
     * @return int scoring result
     */
    private int evaluateEmptyLectures() {
        int lectureScore = 0;
        for (Lecture lecture : data.getLectures()) {
            if (lecture.getNumOfAssignedTutors() == 0) {
                lectureScore -= 15;
            }
        }
        return lectureScore;
    }

    /**
     * Clears lists and refills them with objects from data
     */
    private void initializeLists() {
        registeredStudents.clear();
        registeredLectures.clear();
        actualMatches.clear();
        registeredStudents.addAll(data.getStudents());
        registeredLectures.addAll(data.getLectures());
    }

    /**
     * Resets tutor slots in lectures to original values
     */
    private void resetLectureAllocations() {
        registeredLectures.forEach(Lecture::resetTutorAssignment);
    }

    public HashMap<Student, Lecture> getMatches() {
        return finalMatches;
    }
}