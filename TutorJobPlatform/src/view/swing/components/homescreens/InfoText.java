package view.swing.components.homescreens;

/**
 * The class provides static final strings and arrays of strings
 * uses for displaying various informational messages on the home
 * screens for students and teachers in the application.
 */
public class InfoText {

     //General information text for students
    public static final String[] studentGeneral = new String[]
            {"Dies ist eine Plattform für die Zuweisung", "von " +
                    "Tutor*innenstellen.", "Bitte wählen Sie Ihre " +
                    "Präferenzen", "bis zum 30.05.2024. Die Zuteilung erfolgt",
                    "anschließend automatisiert.", " ", "Wir wünschen Ihnen " +
                    "viel Freude und Erfolg im Tutorium!", " ", " ", " "};

    // Message displayed to students before they have made their choices
    public static final String studentBeforeChoice = "<html><i>Sie " +
            "haben noch keine Auswahl getroffen.</html></i>";

    //Message displayed to students after they made their choices
    public static final String studentAfterChoice = "<html><i>Sie " +
            "haben eine Auswahl getroffen.</html></i>";

    //General information text for teachers
    public static final String[] teacherGeneral = new String[]
            {"Dies ist eine Plattform für die Zuweisung", "von " +
            "Tutor*innenstellen.", "Bitte tragen Sie Ihre Kurse ein bis zum",
                    "25.05.2024. Die Zuteilung der Studierenden erfolgt",
            "automatisiert ab dem 31.05.2024.", " ", "Wir wünschen Ihnen " +
            "eine gute Zusammenarbeit.", " ",
                    " ", " "};

    //Message displayed to teachers who have not created any courses.
    public static final String teacherWithoutClass = "<html><i>Sie " +
            "haben noch keinen Kurs angelegt.</html></i>";

    //Message displayed to teachers who have already created courses.
    public static final String teacherHasClass = "<html><i>Sie " +
            "haben bereits Kurse eingetragen.</html></i>";

    //Prompt for users to choose the preferences.
    public static final String chooseHere = "Wählen Sie hier Ihre Präferenzen.";

    //Information about random assignment if no preferences are provided.
    public static final String noChoiceInfo = "(Falls Sie keine Angabe machen, " +
            "erfolgt die Zuteilung zufällig.)";

    //Message displaying the result of the tutor assignment to teachers.
    public static final String teacherResultMessage = "Folgende Tutor*innen " +
            "wurden Ihren Kursen zugeteilt: ";

    //Message displaying the result of the course assignment to students.
    public static final String studentResultMessage = "Sie wurden folgendem " +
            "Kurs zugeteilt: ";

    //Thank you message and good luck wish for users.
    public static final String[] goodLuck = new String[] {"Vielen Dank für " +
            "die Nutzung von TutorJobSystem.", "Wir wünschen Ihnen eine gute " +
            "Zusammenarbeit."};
}
