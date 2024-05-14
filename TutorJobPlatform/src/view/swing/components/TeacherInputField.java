package view.swing.components;

import model.Lecture;
import model.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TeacherInputField extends JFrame {
    private JTextField txtVeranstaltungName;
    private JComboBox<String> comboBoxAnzahlTutoren;
    private ArrayList<JCheckBox> checkBoxesStudiengaenge;
    private JTextArea txtAnforderungen;
    private JButton btnSpeichern;

    private Teacher loggedInTeacher; // Referenz auf den eingeloggten Lehrer

    public TeacherInputField(Teacher loggedInTeacher) {
        this.loggedInTeacher = loggedInTeacher;

        setTitle("Dozenteninformationen eingeben");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(10, 2));

        // Labels und Textfelder für die Eingaben
        JLabel lblVeranstaltungName = new JLabel("Veranstaltungname:");
        txtVeranstaltungName = new JTextField();
        inputPanel.add(lblVeranstaltungName);
        inputPanel.add(txtVeranstaltungName);

        JLabel lblAnzahlTutoren = new JLabel("Anzahl an Tutoren:");
        comboBoxAnzahlTutoren = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        inputPanel.add(lblAnzahlTutoren);
        inputPanel.add(comboBoxAnzahlTutoren);

        JLabel lblAnforderungen = new JLabel("Anforderungen an die Tutoren:");
        txtAnforderungen = new JTextArea();
        inputPanel.add(lblAnforderungen);
        inputPanel.add(new JScrollPane(txtAnforderungen));

        JPanel studiengaengePanel = new JPanel(new GridLayout(0, 4));
        studiengaengePanel.setPreferredSize(new Dimension(600, 300)); 

        String[] studiengaenge = {"IMB", "UIB", "CSB", "IB"};
        checkBoxesStudiengaenge = new ArrayList<>();
        for (String studiengang : studiengaenge) {
            JCheckBox checkBox = new JCheckBox(studiengang);
            checkBox.setPreferredSize(new Dimension(150, 30)); 
            checkBoxesStudiengaenge.add(checkBox);
            studiengaengePanel.add(checkBox);
        }

        JLabel lblStudiengang = new JLabel("Studiengang der TutorInnen:");
        inputPanel.add(lblStudiengang);
        inputPanel.add(studiengaengePanel);

        add(inputPanel, BorderLayout.CENTER);

        // Button zum Speichern der Daten
        btnSpeichern = new JButton("Speichern");
        btnSpeichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Eingaben aus der UI werden verwendet, um eine neue Lecture-Instanz zu erstellen
                String name = txtVeranstaltungName.getText();
                int anzahlTutoren = Integer.parseInt((String) comboBoxAnzahlTutoren.getSelectedItem());
                String anforderungen = txtAnforderungen.getText();
                ArrayList<String> selectedStudyPaths = new ArrayList<>();
                for (JCheckBox checkBox : checkBoxesStudiengaenge) {
                    if (checkBox.isSelected()) {
                        selectedStudyPaths.add(checkBox.getText());
                    }
                }

                // Erstellt eine neue Lecture-Instanz mit den gesammelten Informationen
                Lecture lecture = loggedInTeacher.createLecture(name, anzahlTutoren, anforderungen, selectedStudyPaths);

               
            }
        });
        add(btnSpeichern, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        Teacher stg = new Teacher("Jess", "Steinberger", "12345", "Prof. Dr.");
        TeacherInputField field = new TeacherInputField(stg);
    }
}