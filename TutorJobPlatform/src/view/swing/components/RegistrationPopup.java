package view.swing.components;

import controller.Registration;
import model.AppData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationPopup {
    private final Registration registration = Registration.registration;

    private JFrame frame;
    private JPanel mainPanel, formPanel, buttonPanel;
    private JTextField firstNameField, lastNameField, abbreviationField, passwordField,
            passwordConfirmField, matriculationNumberField;
    private JComboBox<String> roleDropdown, titleDropdown, studyPathDropdown;
    private JButton submitButton;
    private JLabel messageLabel;

    public RegistrationPopup() {
        // Fenster erstellen
        frame = new JFrame("Registrierung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Hauptpanel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formularpanel
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 10, 10));
        formPanel.setOpaque(false);

        // Buttonpanel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);

        // Rollenauswahl
        String[] roles = {"Student*in", "Dozent*in"};
        roleDropdown = new JComboBox<>(roles);
        roleDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleFieldsBasedOnRole((String) roleDropdown.getSelectedItem());
            }
        });

        String[] teacherTitles = {"", "Dr.", "Prof. Dr."};
        titleDropdown = new JComboBox<>(teacherTitles);

        String[] studyPaths = {"CSB", "IB", "IMB", "UIB"};
        studyPathDropdown = new JComboBox<>(studyPaths);

        // Textfelder
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        abbreviationField = new JTextField();
        passwordField = new JPasswordField();
        passwordConfirmField = new JPasswordField();
        matriculationNumberField = new JTextField();

        // Submit-Button
        submitButton = new JButton("Registrieren");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(transmitData()) {
                    JOptionPane.showMessageDialog(frame, "Sie sind jetzt registriert!");
                    System.out.println(AppData.data.getStudents());
                } else {
                    JOptionPane.showMessageDialog(frame, "Registrierung " +
                            "fehlgeschlagen. Bitte überprüfen Sie Ihre " +
                            "Daten.");
                }
            }
        });

        // Nachrichtenlabel
        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED);

        // Komponenten zum Formularpanel hinzufügen
        addToFormPanel(new JLabel("Ich bin:"), roleDropdown);
        addToFormPanel(new JLabel("Anrede:"), titleDropdown);
        addToFormPanel(new JLabel("Vorname:"), firstNameField);
        addToFormPanel(new JLabel("Nachname:"), lastNameField);
        addToFormPanel(new JLabel("Dozent*innenkürzel:"), abbreviationField);
        addToFormPanel(new JLabel("Matrikelnummer:"), matriculationNumberField);
        addToFormPanel(new JLabel("Studiengang:"), studyPathDropdown);
        addToFormPanel(new JLabel("Passwort:"), passwordField);
        addToFormPanel(new JLabel("Passwort wiederholen:"),
                passwordConfirmField);
        toggleFieldsBasedOnRole((String) roleDropdown.getSelectedItem());

        // Buttonpanel hinzufügen
        buttonPanel.add(submitButton);

        // Hauptpanel hinzufügen
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(messageLabel, BorderLayout.NORTH);

        // Frame konfigurieren
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null); // Zentriert das Fenster
        frame.setVisible(true);
    }

    private void addToFormPanel(Component label, Component field) {
        formPanel.add(label);
        formPanel.add(field);
    }

    private void toggleFieldsBasedOnRole(String role) {
        boolean isStudent = "Student*in".equals(role);
        matriculationNumberField.setVisible(isStudent);
        titleDropdown.setVisible(!isStudent);
        abbreviationField.setVisible(!isStudent);
        studyPathDropdown.setVisible(isStudent);
    }

    // TODO: Pop-up-Nachrichten anzeigen basierend auf dem Ergebnis
    private boolean transmitData() {
        String role = (String) roleDropdown.getSelectedItem();
        boolean isStudent = "Student*in".equals(role);

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String password = passwordField.getText();
        String passwordRep = passwordConfirmField.getText();
        String title = "";
        String studNumber = "";
        String studyPath = "";

        if (isStudent) {
            studNumber = matriculationNumberField.getText();
            studyPath = (String) roleDropdown.getSelectedItem();
        } else {
            title = (String) titleDropdown.getSelectedItem();
        }
        return registration.registerUser(firstName, lastName, password,
                passwordRep, role, title, studNumber, studyPath);
    }

    // TODO Data should not be initiated here (probably in homescreen)
    public static void main(String[] args) {
        new AppData();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegistrationPopup();
            }
        });

    }
}
