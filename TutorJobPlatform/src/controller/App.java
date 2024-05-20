package controller;

public class App {
    private static boolean matchingDone = false;

    public static void match() {
      //  Matcher.match() // TODO matching aufrufen
        matchingDone = true;
    }

    public static boolean isMatchingDone() {
        return matchingDone;
    }

}
