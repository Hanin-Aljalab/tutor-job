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
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

        // Labels and text fields for input
        JLabel labelName = new JLabel("Veranstaltungsname (ausgeschrieben):");
        txtCourseName = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        add(labelName, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        add(txtCourseName, constraints);

        JLabel labelAbbreviation = new JLabel("Kürzel:");
        txtAbbreviation = new JTextField();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(labelAbbreviation, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        add(txtAbbreviation, constraints);

        JLabel labelTutNumber = new JLabel("Anzahl Tutor*innen:");
        comboBoxDesiredTutorNumber = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(labelTutNumber, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        add(comboBoxDesiredTutorNumber, constraints);

        JLabel labelCourseInfo = new JLabel("Kurzbeschreibung/Anforderungen:");
        txtCourseInfo = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(txtCourseInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        add(labelCourseInfo, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        add(scrollPane, constraints);

        JPanel studyPathPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        studyPathPanel.setPreferredSize(new Dimension(400, 100));

        String[] studyPaths = {"IMB", "UIB", "CSB", "IB"};
        checkBoxesAllowedStudyPaths = new ArrayList<>();
        for (String studyPath : studyPaths) {
            JCheckBox checkBox = new JCheckBox(studyPath);
            checkBox.setPreferredSize(new Dimension(150, 30));
            checkBoxesAllowedStudyPaths.add(checkBox);
            studyPathPanel.add(checkBox);
        }

        JLabel labelStudyPaths = new JLabel("Studiengang der Tutor*innen:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(labelStudyPaths, constraints);
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        add(studyPathPanel, constraints);

        // Button to save data
        btnSave = new JButton("Speichern");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                Lecture lecture = loggedInTeacher.createLecture(courseName, abbreviation, tutorNum, courseInfo, selectedStudyPaths);

                // Save new lecture in AppData
                App.getData().addLecture(lecture);
                JOptionPane.showMessageDialog(TeacherInputField.this, "Kurs erfolgreich hinzugefügt!");
                homescreen.refreshLectures();

                dispose();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        add(btnSave, constraints);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}