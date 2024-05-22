package controller;

import model.*;
import view.swing.components.*;

import java.io.*;

public class App {
    private static boolean matchingDone = false;
    private static final String FILE_PATH = "./tutorjobsystem_data.ser";
    private static AppData data;
    private static Matcher matcher;

    public static void main(String[] args) {
        data = deserializeObjects(); //deserialisieren, wenn Programm startet
        if (data == null) {
            data = new AppData();
            data.createDummies();
        }
        // Füge den Shutdown-Hook hinzu
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            serializeObjects();
        }));

        new LoginWindow(); //als erstes wird ein Loginfenster gestartet
    }

    /**
     * This Method serializes AppData on closing the window and prints out
     * the object in the console.
     *
     * @throws IOException
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
     * This Method tries to deserialize the object of the given input file and returns them.
     * If there is no object to deserialize, it will just catch an ClassNotFoundException and
     * return nothing.
     *
     * @return the data of the objects in the serialized input file.
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

    public static void match() {
        matcher = new Matcher();
        matcher.allocateStudents();
        matchingDone = true;
    }

    public static Matcher getMatcher() {
        return matcher;
    }

    public static AppData getData() {
        return data;
    }

    public static boolean isMatchingDone() {
        return matchingDone;
    }
}
