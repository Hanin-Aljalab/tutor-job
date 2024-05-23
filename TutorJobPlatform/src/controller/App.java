package controller;

import model.*;
import view.swing.components.*;

import java.io.*;
import java.util.HashMap;

/**
 * Aggregates and handels central functions of the program
 */
public class App {
    private static final String FILE_PATH = "./tutorjobsystem_data.ser";
    private static AppData data;
    private static Matcher2 matcher;

    public static void main(String[] args) {
        startApp();
    }

    /**
     * Central entry point of the tutor job system.
     */
    private static void startApp() {
        // At program start, objects are deserialised. If no serialized file
        // exists, new data object is initiated.
        data = deserializeObjects();
        if (data == null) {
            data = new AppData();
        }
        // Shutdown hook: ensures serialisation of abject at program termination
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            serializeObjects();
        }));

        // Entry point into the GUI: login window pops up
        new LoginWindow();
    }

    /**
     * Serializes the data object
     */
    private static void serializeObjects() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(data);
            out.close();
            System.out.println("Objekte wurden serialisiert.");
        } catch (IOException exception) {
            System.out.println("Objekte konnten nicht serialisiert werden!");
        }
    }

    /**
     * Tries to deserialize data from input file. I there is no object to
     * deserialize, it will catch the ClassNotFoundException and
     * return null.
     *
     * @return if deserialization successfull, the deserialized objects. Else null.
     */
    private static AppData deserializeObjects() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            data = (AppData) in.readObject();
            in.close();
            System.out.println("Objekte wurden deserialisiert");
            System.out.println(data.getStudents());
            System.out.println(data.getTeachers());
            System.out.println(data.getLectures());
            return data;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Keine gespeicherten Daten gefunden oder Fehler beim Laden.");
            return null;
        }
    }

    /**
     * Initiates the matchmaking process
     */
    public static void match() {
        matcher = new Matcher2(data);
        HashMap<Student, Lecture> result = matcher.performMatching();
        data.setMatches(result);
        data.setMatchingDone(true);
    }

    public static Matcher2 getMatcher() {
        return matcher;
    }

    public static AppData getData() {
        return data;
    }
}
