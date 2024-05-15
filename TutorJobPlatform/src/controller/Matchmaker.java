package controller;

import java.util.*;

import static model.AppData.data;

import model.*;

public class Matchmaker {

    private List<Student> allStudents;
    private List<Lecture> allLectures;
    private Map<Student, Lecture> match;

    public Matchmaker() {
        allStudents = data.getStudents();
        allLectures = data.getLectures();
        match = new HashMap<>();
    }

    private matchStudent(Student student) {
        for (String pref : student.getLecturePref()) {
            for (Lecture lecture : allLectures) {
                if (pref.equals(lecture.getAbbreviation())) {
                    match.put(student, lecture);
                }
            }
        }

        private int matNummer;
        private ArrayList<String> getLecturePref;
        private String courseName;
        private static Random random;
        //private static int randStudent;
        private boolean isAssigned;


        //For extra fairness, always pick random students from list. No "first come, first served";
    /*public static Student randomPick() {
        randStudent = random.nextInt(allStudents.size());
        return allStudents.get(randStudent);
    }*/

        public static void main (String[]args){

            //This is where all pairs land


            List<Student> assignedStudents = new ArrayList<>();

            for (Student student : allStudents) {
                boolean isAssigned = false;
                for (String pref : student.getLecturePref()) {
                    //Getting the courses that are now available, entered by the prof, instead of all the available courses
                    Course course = Course.findCourseByName(pref);
                    if (course.slots > 0) {
                        if (student.getPrefs().isEmpty()) { //TODO: no preferences, match to left over course?
                            match.put(student.getMatNummer(), course.courseName);
                        }
                        match.put(student.getMatNummer(), course.courseName);
                        course.slots--;
                        assignedStudents.add(student); //student got matched, add to List
                        isAssigned = true;
                        System.out.println("Assign: " + student.matNummer + " to: " + course.courseName);
                        break;
                    }
                }
                if (isAssigned) {
                    allStudents.remove(assignedStudents); //remove assigned students from all students
                }
            }
        }

        public Student( int matNummer, ArrayList<String > lecturePref){
            this.matNummer = matNummer;
            this.lecturePref = lecturePref;
        }

        /*//TODO: -only for testing- use *actual* login and preference data from the login window/from database
        static Student s0 = new Student("0", List.of("MA", "PR", "MI"));
        static Student s1 = new Student("1", List.of("MA", "PR", "MI"));
        static Student s2 = new Student("3", List.of("PR", "MA"));
        static Student s3 = new Student("D", List.of()); //student 3 has no preferences, if possible?
        static Student s4 = new Student("E", List.of("MI", "PR", "MI"));
        static Student s5 = new Student("F", List.of("PR", "MI", "MA"));
        static Student s6 = new Student("G", List.of("MI", "MA", "PR"));
        static Student s7 = new Student("H", List.of("MA", "PR", "MI"));
        static Student s8 = new Student("I", List.of("MI", "MA", "PR"));
        static Student s9 = new Student("J", List.of("MI", "MA", "PR"));
        static ArrayList<Student> allStudents = new ArrayList<>(List.of(s0, s1, s2, s3, s4, s5, s6, s7, s8, s9));

*/
        public int getMatNummer () {
            return matNummer;
        }

        public ArrayList<String> getLecturePref () {
            return lecturePref;
        }




/*
public static void match(){

    for (Student student : allStudents){
        if (student.isAssigned()) {
            continue;
        }
    }

    List<String> getPrefs = student.getPrefs();
    if (course.courseName.equals(student.getPrefs(i))){

    }




}*/
    }


}
