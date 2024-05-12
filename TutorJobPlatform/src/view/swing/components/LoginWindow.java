package view.swing.components;

import model.Student;
import model.Teacher;
import view.swing.components.homescreens.StudentHomescreen;
import view.swing.components.homescreens.TeacherHomescreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    private JTextField firstNameField, lastNameField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton, forgotPasswordButton;
    private JComboBox<String> roleComboBox;
    private JLabel messageLabel;
    private JPanel buttonPanel; // Panel für die Knöpfe

    private Student s1; // TODO nur für Testzwecke hier!!
    private Teacher t1; // TODO nur für Testzwecke hier!!

    public LoginWindow() {
        // TODO nur für Testzwecke hier!!!
        s1 = new Student("Markus", "Winklhofer", "1234", 3008816, "IMB");
        t1 = new Teacher("Yordan", "Todorov", "1234", "Dr");

        // Fensterkonfiguration
        setTitle("Login");
        setLayout(new BorderLayout(5, 5));
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hauptpanel für die Eingabefelder
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        add(inputPanel, BorderLayout.CENTER);

        // Komponenten
        inputPanel.add(new JLabel(" Vorname:"));
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel(" Nachname:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel(" Passwort:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        inputPanel.add(new JLabel(" Rolle:"));
        roleComboBox = new JComboBox<>(new String[] { "Dozent*in",
                "Student*in" });
        inputPanel.add(roleComboBox);

        // Button-Panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        add(buttonPanel, BorderLayout.SOUTH);

        loginButton = new JButton("✅ Einloggen");
        loginButton.setForeground(Color.GREEN);
        loginButton.addActionListener(new LoginActionListener());
        buttonPanel.add(loginButton);

        cancelButton = new JButton("❌ Abbrechen");
        cancelButton.setForeground(Color.RED);
        cancelButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(cancelButton);

        forgotPasswordButton = new JButton("🔐 Passwort vergessen?");
        forgotPasswordButton.setForeground(Color.ORANGE);
        forgotPasswordButton.addActionListener(e -> resetPassword());
        buttonPanel.add(forgotPasswordButton);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        add(messageLabel, BorderLayout.NORTH);

        // Fenster zentrieren und anzeigen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!firstNameField.getText().trim().isEmpty() && !lastNameField.getText().trim().isEmpty()
                    && passwordField.getPassword().length > 0) {
                openMainPage();
            } else {
                messageLabel.setText("❗ Falsche Eingaben ❗");
                messageLabel.setForeground(Color.RED);
            }
        }
    }

    private void resetPassword() {
        // Dialog zum Zurücksetzen des Passworts
        String email = JOptionPane.showInputDialog(this,
                "Bitte geben Sie Ihre E-Mail-Adresse ein, um Ihr Passwort zurückzusetzen:");
        if (email != null && !email.isEmpty()) {
            // Logik zum Senden des Passwort-Reset-Links
            JOptionPane.showMessageDialog(this,
                    "Ein Link zum Zurücksetzen Ihres Passworts wurde an " + email + " gesendet.");
            System.out.println("Passwort-Reset-Link wurde an " + email + " gesendet.");
        }
    }

    private void openMainPage() {
        JFrame homescreen;
        if (roleComboBox.getSelectedItem().toString().equals("Student*in")) {
            homescreen = new StudentHomescreen(s1);
        } else {
            homescreen = new TeacherHomescreen(t1);
        }

        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginWindow::new);
    }
}

