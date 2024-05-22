package controller;

public class App {
    private static boolean matchingDone = false;

    public static void match() {
        Matcher matcher = new Matcher();
        matcher.allocateStudents();
        matchingDone = true;
    }

    public static boolean isMatchingDone() {
        return matchingDone;
    }

}
