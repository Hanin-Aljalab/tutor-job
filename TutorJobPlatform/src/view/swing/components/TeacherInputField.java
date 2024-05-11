package view.swing.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TeacherInputField extends JFrame {
    private JTextField txtVeranstaltungName;
    private JTextField txtOrt;
    private JTextField txtDauer;
    private JComboBox<String> comboBoxAnzahlTutoren;
    private ArrayList<JCheckBox> checkBoxesStudiengaenge;
    private JTextArea txtAnforderungen;
    private JTextField txtKontaktInfo;
    private JButton btnSpeichern;

    public TeacherInputField() {
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

        JLabel lblOrt = new JLabel("Gebäude und Raum:");
        txtOrt = new JTextField();
        inputPanel.add(lblOrt);
        inputPanel.add(txtOrt);

        JLabel lblDauer = new JLabel("Dauer der Veranstaltung in Stunden:");
        txtDauer = new JTextField();
        inputPanel.add(lblDauer);
        inputPanel.add(txtDauer);

        JLabel lblAnzahlTutoren = new JLabel("Anzahl an Tutoren:");
        comboBoxAnzahlTutoren = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
        inputPanel.add(lblAnzahlTutoren);
        inputPanel.add(comboBoxAnzahlTutoren);
        
        JLabel lblAnforderungen = new JLabel("Anforderungen an die Tutoren:");
        txtAnforderungen = new JTextArea();
        inputPanel.add(lblAnforderungen);
        inputPanel.add(new JScrollPane(txtAnforderungen));

        JLabel lblKontaktInfo = new JLabel("Kontakinformationen der Dozenten:");
        txtKontaktInfo = new JTextField();
        inputPanel.add(lblKontaktInfo);
        inputPanel.add(txtKontaktInfo);

        JPanel studiengaengePanel = new JPanel(new GridLayout(0, 4)); // Hier können Sie die Anzahl der Spalten ändern
        studiengaengePanel.setPreferredSize(new Dimension(600, 300)); // Hier können Sie die Größe des Panels ändern

        String[] studiengaenge = {"BCB", "BB", "CB", "CSB", "EEB", "ETB", "IB", "IEB", "ELB", "KI-Ingenieurswissenschaften",
                "DB", "MB", "MEB", "IMB", "MTB", "NTB", "SB", "TIB", "TS", "UIB",
                "VB", "WB", "WBI"};
        checkBoxesStudiengaenge = new ArrayList<>();
        for (String studiengang : studiengaenge) {
            JCheckBox checkBox = new JCheckBox(studiengang);
            checkBox.setPreferredSize(new Dimension(150, 30)); // Hier können Sie die Größe der Checkboxen ändern
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
                String ort = txtOrt.getText();
                String dauer = txtDauer.getText();
                int anzahlTutoren = Integer.parseInt((String) comboBoxAnzahlTutoren.getSelectedItem());
                String anforderungen = txtAnforderungen.getText();
                String kontaktInfo = txtKontaktInfo.getText();
                ArrayList<String> selectedStudyPaths = new ArrayList<>();
                for (JCheckBox checkBox : checkBoxesStudiengaenge) {
                    if (checkBox.isSelected()) {
                        selectedStudyPaths.add(checkBox.getText());
                    }
                }

                // Erstelle eine neue Lecture-Instanz mit den gesammelten Informationen
                // Lecture lecture = new Lecture(name, ort, dauer, arbeitszeit, entlohnung, anzahlTutoren, anforderungen, kontaktInfo, selectedStudyPath);
            }
        });
        add(btnSpeichern, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TeacherInputField();
            }
        });
    }
}