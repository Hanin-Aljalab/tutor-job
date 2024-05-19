package controller;

import model.*;

import java.util.Map;

public class Matcher {
    private static Map<Student, Lecture> matches;

    public static Map<Student, Lecture> getMatches() {
        return matches;
    }
}
