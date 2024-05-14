package model;
import java.util.ArrayList;

public class Lecture {
  
    private String name;
    private String ort;
    private String dauer;
    private int anzahlTutoren;
    private String anforderungen;
    private Teacher teacher;
    private ArrayList<Student> tutors;
    private ArrayList<String> selectedStudyPaths;

    public Lecture(String name, int anzahlTutoren, String anforderungen,
                   Teacher teacher, ArrayList<String> selectedStudyPaths) {
        this.name = name;
        this.ort = ort;
        this.dauer = dauer;
        this.anzahlTutoren = anzahlTutoren;
        this.anforderungen = anforderungen;
        this.teacher = teacher;
        this.selectedStudyPaths = selectedStudyPaths;
    }


    public String getName() {
        return name;
    }

    public String getOrt() {
        return ort;
    }

    public String getDauer() {
        return dauer;
    }

    public int getAnzahlTutoren() {
        return anzahlTutoren;
    }

    public String getAnforderungen() {
        return anforderungen;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ArrayList<Student> getTutors() {
        return tutors;
    }
    
    public void addTutor(Student student) {
        tutors.add(student);
    }

    public ArrayList<String> getSelectedStudyPaths() {
        return selectedStudyPaths;
    }
}
