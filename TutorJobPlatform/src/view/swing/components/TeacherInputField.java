package view.swing.components;

import model.*;
import view.swing.components.homescreens.TeacherHomescreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TeacherInputField extends JFrame {
    private JTextField txtCourseName;
    private JTextField txtAbbreviation;
    private JComboBox<String> comboBoxDesiredTutorNumber;
    private ArrayList<JCheckBox> checkBoxesAllowedStudyPaths;
    private JTextArea txtCourseInfo;
    private JButton btnSave;

    private Teacher loggedInTeacher; // Referenz auf den eingeloggten Lehrer
    private TeacherHomescreen homescreen; // Referenz auf den TeacherHomescreen

    public TeacherInputField(Teacher loggedInTeacher, TeacherHomescreen homescreen) {
        this.loggedInTeacher = loggedInTeacher;
        this.homescreen = homescreen;

        setTitle("Neuen Kurs anlegen");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(10, 2));

        // Labels und Textfelder für die Eingaben
        JLabel labelName = new JLabel("Veranstaltungsname (ausgeschrieben):");
        txtCourseName = new JTextField();
        inputPanel.add(labelName);
        inputPanel.add(txtCourseName);

        JLabel labelAbbreviation = new JLabel("Kürzel:");
        txtAbbreviation = new JTextField();
        inputPanel.add(labelAbbreviation);
        inputPanel.add(txtAbbreviation);

        JLabel labelTutNumber = new JLabel("Anzahl Tutor*innen:");
        comboBoxDesiredTutorNumber = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        inputPanel.add(labelTutNumber);
        inputPanel.add(comboBoxDesiredTutorNumber);

        JLabel labelCourseInfo = new JLabel("Kurzbeschreibung" +
                "/Anforderungen:");
        txtCourseInfo = new JTextArea();
        inputPanel.add(labelCourseInfo);
        inputPanel.add(new JScrollPane(txtCourseInfo));

        JPanel studyPathPanel = new JPanel(new GridLayout(0, 4));
        studyPathPanel.setPreferredSize(new Dimension(600, 300));

        String[] studyPaths = {"IMB", "UIB", "CSB", "IB"};
        checkBoxesAllowedStudyPaths = new ArrayList<>();
        for (String studyPath : studyPaths) {
            JCheckBox checkBox = new JCheckBox(studyPath);
            checkBox.setPreferredSize(new Dimension(150, 30)); 
            checkBoxesAllowedStudyPaths.add(checkBox);
            studyPathPanel.add(checkBox);
        }

        JLabel labelStudyPaths = new JLabel("Studiengang der Tutor*innen:");
        inputPanel.add(labelStudyPaths);
        inputPanel.add(studyPathPanel);

        add(inputPanel, BorderLayout.CENTER);

        // Button zum Speichern der Daten
        btnSave = new JButton("Speichern");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Eingaben aus der UI werden verwendet, um eine neue Lecture-Instanz zu erstellen
                String courseName = txtCourseName.getText();
                String abbreviation = txtAbbreviation.getText();
                int tutorNum = Integer.parseInt((String) comboBoxDesiredTutorNumber.getSelectedItem());
                String courseInfo = txtCourseInfo.getText();
                ArrayList<String> selectedStudyPaths = new ArrayList<>();
                for (JCheckBox checkBox : checkBoxesAllowedStudyPaths) {
                    if (checkBox.isSelected()) {
                        selectedStudyPaths.add(checkBox.getText());
                    }
                }

                // Überprüfen, ob alle erforderlichen Felder ausgefüllt sind
                if (courseName.isEmpty() || abbreviation.isEmpty() || courseInfo.isEmpty() || selectedStudyPaths.isEmpty()) {
                    JOptionPane.showMessageDialog(TeacherInputField.this, "Bitte füllen Sie alle Felder aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Erstellt eine neue Lecture-Instanz mit den gesammelten Informationen
                Lecture lecture = loggedInTeacher.createLecture(courseName,
                        abbreviation, tutorNum, courseInfo, selectedStudyPaths);

                AppData.data.addLecture(lecture); //Speichern der neuen Lecture in AppData

                JOptionPane.showMessageDialog(TeacherInputField.this, "Kurs erfolgreich hinzugefügt! ");

                homescreen.refreshLectures();

               dispose();
            }
        });
        add(btnSave, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        Teacher teacher = new Teacher("Max", "Mustermann", "1234567", "hallo", "TOY"); // Beispiel-Lehrer erstellen
        TeacherHomescreen homescreen = new TeacherHomescreen(teacher); // Beispiel-Homescreen erstellen
        new TeacherInputField(teacher, homescreen); // Fenster öffnen
    }
}