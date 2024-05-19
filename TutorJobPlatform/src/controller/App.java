package controller;

public class App {
    private static boolean matchingDone = true;

    public static void match() {
      //  Matcher.match() // TODO matching aufrufen
        matchingDone = true;
    }

    public static boolean isMatchingDone() {
        return matchingDone;
    }

}
