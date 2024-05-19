package controller;

import model.*;
import java.util.HashMap;

public class Matcher {
    private static HashMap<Student, Lecture> matches;

    public static HashMap<Student, Lecture> getMatches() {
        return AppData.getMatches();
    }
}
