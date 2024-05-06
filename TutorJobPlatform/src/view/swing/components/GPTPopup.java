package view.swing.components;

import model.Student;
import model.Teacher;
import model.User;
import model.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GPTPopup {
    private JFrame frame;
    private JPanel mainPanel, formPanel, buttonPanel;
    private JTextField firstNameField, lastNameField, emailField, passwordField,
     passwordConfirmField, matriculationNumberField;
    private JComboBox<String> roleDropdown, titleDropdown;
    private JButton submitButton;
    private JLabel messageLabel;

    public GPTPopup() {
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
                System.out.println("listened!");
            }
        });

        String[] teacherTitles = {"", "Dr.", "Prof. Dr."};
        titleDropdown = new JComboBox<>(teacherTitles);

        // Textfelder
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        passwordConfirmField = new JPasswordField();
        matriculationNumberField = new JTextField();

        // Submit-Button
        submitButton = new JButton("Registrieren");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
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
        addToFormPanel(new JLabel("Matrikelnummer:"), matriculationNumberField);
        addToFormPanel(new JLabel("E-Mail:"), emailField);
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
    }

    private void registerUser() {
        String role = (String) roleDropdown.getSelectedItem();
        boolean isStudent = "Student*in".equals(role);
        User newUser;
        if (isStudent) {
            int studNumber =
                    Integer.parseInt(matriculationNumberField.getText());
            newUser = new Student(firstNameField.getText(),
                    lastNameField.getText(), passwordField.getText(), studNumber);
        } else {
            newUser = new Teacher(firstNameField.getText(),
                    lastNameField.getText(), passwordField.getText(),
                    (String) titleDropdown.getSelectedItem());
        }

        Data.addUser(newUser);
        System.out.println(Data.getStudents());

        // Validierung und Registrierungslogik hier
        // Pop-up-Nachrichten anzeigen basierend auf dem Ergebnis
        JOptionPane.showMessageDialog(frame, "Sie sind jetzt registriert!");
    }



    public static void main(String[] args) {
        new Data();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GPTPopup();
            }
        });

    }
}
