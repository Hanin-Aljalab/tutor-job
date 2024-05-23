package view.swing.components;

import controller.App;
import model.*;
import view.swing.components.homescreens.TeacherHomescreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * TeacherInputField is a JFrame that allows a teacher to input information for creating a new course.
 * The input fields include course name, abbreviation, desired number of tutors, allowed study paths,
 * and course information.
 */

public class TeacherInputField extends JFrame {
    private JTextField txtCourseName;
    private JTextField txtAbbreviation;
    private JComboBox<String> comboBoxDesiredTutorNumber;
    private ArrayList<JCheckBox> checkBoxesAllowedStudyPaths;
    private JTextArea txtCourseInfo;
    private JButton btnSave;

    private Teacher loggedInTeacher; // Reference to the logged-in teacher
    private TeacherHomescreen homescreen; // Reference to the TeacherHomescreen

    /**
     * Constructs a TeacherInputField with the specified logged-in teacher and teacher homescreen.
     *
     * @param loggedInTeacher the logged-in teacher
     * @param homescreen      the teacher homescreen
     */

    public TeacherInputField(Teacher loggedInTeacher, TeacherHomescreen homescreen) {
        this.loggedInTeacher = loggedInTeacher;
        this.homescreen = homescreen;

        setTitle("Neuen Kurs anlegen");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        JPanel inputPanel = new JPanel(new GridLayout(10, 2));

        // Labels and text fields for input
        JLabel labelName = new JLabel("  Veranstaltungsname (ausgeschrieben):");
        txtCourseName = new JTextField();
        inputPanel.add(labelName);
        inputPanel.add(txtCourseName);

        JLabel labelAbbreviation = new JLabel("  Kürzel:");
        txtAbbreviation = new JTextField();
        inputPanel.add(labelAbbreviation);
        inputPanel.add(txtAbbreviation);

        JLabel labelTutNumber = new JLabel("  Anzahl Tutor*innen:");
        comboBoxDesiredTutorNumber = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        inputPanel.add(labelTutNumber);
        inputPanel.add(comboBoxDesiredTutorNumber);

        JLabel labelCourseInfo = new JLabel("  Kurzbeschreibung" +
                "/ Anforderungen:");
        txtCourseInfo = new JTextArea();
        inputPanel.add(labelCourseInfo);
        inputPanel.add(new JScrollPane(txtCourseInfo));

        JPanel studyPathPanel = new JPanel(new GridLayout(0, 4));
        studyPathPanel.setPreferredSize(new Dimension(600, 300));

        String[] studyPaths = {"  IMB", "  UIB", "  CSB", "  IB"};
        checkBoxesAllowedStudyPaths = new ArrayList<>();
        for (String studyPath : studyPaths) {
            JCheckBox checkBox = new JCheckBox(studyPath);
            checkBox.setPreferredSize(new Dimension(150, 30)); 
            checkBoxesAllowedStudyPaths.add(checkBox);
            studyPathPanel.add(checkBox);
        }

        JLabel labelStudyPaths = new JLabel("  Studiengang der Tutor*innen:");
        inputPanel.add(labelStudyPaths);
        inputPanel.add(studyPathPanel);

        add(inputPanel, BorderLayout.CENTER);

        // Button for saving data
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

                // Check if all fields are filled
                if (courseName.isEmpty() || abbreviation.isEmpty() || courseInfo.isEmpty() || selectedStudyPaths.isEmpty()) {
                    JOptionPane.showMessageDialog(TeacherInputField.this, "Bitte füllen Sie alle Felder aus.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create new Lecture instance with collected information
                Lecture lecture = loggedInTeacher.createLecture(courseName,
                        abbreviation, tutorNum, courseInfo, selectedStudyPaths);

                // Save new lecture in AppData
                App.getData().addLecture(lecture);

                JOptionPane.showMessageDialog(TeacherInputField.this, "Kurs erfolgreich hinzugefügt! ");

                homescreen.refreshLectures();

               dispose();
            }
        });
        add(btnSave, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        Teacher stg = new Teacher("Jess", "Steinberger", "12345", "Prof. Dr.");
        TeacherInputField field = new TeacherInputField(stg);
    }
}