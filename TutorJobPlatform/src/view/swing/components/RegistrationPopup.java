package view.swing.components;

import controller.Registration;
import exceptions.AbbreviationInvalidException;
import exceptions.InvalidInputException;
import exceptions.PasswordsNotIdenticalException;
import exceptions.StudentNumberInvalidException;
import exceptions.UserAlreadyExistsException;
import model.AppData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationPopup {
	private final Registration registration = Registration.registration;

	private JFrame frame;
	private JPanel mainPanel, formPanel, buttonPanel;
	private JTextField nameField, surnameField, abbreviationField, passwordField, passwordConfirmField, studNumberField;
	private JComboBox<String> roleDropdown, titleDropdown;
	private JButton submitButton, loginButton;
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
		String[] roles = { "Student*in", "Dozent*in" };
		roleDropdown = new JComboBox<>(roles);
		roleDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleFieldsBasedOnRole((String) roleDropdown.getSelectedItem());
				System.out.println("listened!");
			}
		});

		String[] teacherTitles = { "", "Dr.", "Prof. Dr." };
		titleDropdown = new JComboBox<>(teacherTitles);

		// Textfelder
		nameField = new JTextField();
		surnameField = new JTextField();
		abbreviationField = new JTextField();
		passwordField = new JPasswordField();
		passwordConfirmField = new JPasswordField();
		studNumberField = new JTextField();

		// Submit-Button
		submitButton = new JButton("Registrieren");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (transmitData()) {
						JOptionPane.showMessageDialog(frame, "Sie sind jetzt registriert!");
						System.out.println(AppData.data.getStudents());  
					} else {
						JOptionPane.showMessageDialog(frame,
								"Registrierung " + "fehlgeschlagen. Bitte überprüfen Sie Ihre " + "Daten.");

					}
				} catch (HeadlessException | UserAlreadyExistsException | StudentNumberInvalidException
						| PasswordsNotIdenticalException | InvalidInputException
						| AbbreviationInvalidException exception) {
					exception.printStackTrace();
				}
			}
		});

		// Jetzt Einloggen
		loginButton = new JButton("Jetzt Anmelden!");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						frame.dispose();
						new LoginPopup();
					}
				});
			}
		});

		// Nachrichtenlabel
		messageLabel = new JLabel(" ");
		messageLabel.setForeground(Color.RED);

		// Komponenten zum Formularpanel hinzufügen
		addToFormPanel(new JLabel("Ich bin:"), roleDropdown);
		addToFormPanel(new JLabel("Anrede:"), titleDropdown);
		addToFormPanel(new JLabel("Vorname:"), nameField);
		addToFormPanel(new JLabel("Nachname:"), surnameField);
		addToFormPanel(new JLabel("Matrikelnummer:"), studNumberField);
		addToFormPanel(new JLabel("Dozentenkürzel:"), abbreviationField);
		addToFormPanel(new JLabel("Passwort:"), passwordField);
		addToFormPanel(new JLabel("Passwort wiederholen:"), passwordConfirmField);
		toggleFieldsBasedOnRole((String) roleDropdown.getSelectedItem());

		// Buttonpanel hinzufügen
		buttonPanel.add(submitButton);
        buttonPanel.add(loginButton);

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
		studNumberField.setVisible(isStudent);
		titleDropdown.setVisible(!isStudent);
		abbreviationField.setVisible(!isStudent);
	}

	// TODO: Pop-up-Nachrichten anzeigen basierend auf dem Ergebnis
	private boolean transmitData() throws UserAlreadyExistsException, StudentNumberInvalidException,
			PasswordsNotIdenticalException, InvalidInputException, AbbreviationInvalidException {
		String role = (String) roleDropdown.getSelectedItem();
		boolean isStudent = "Student*in".equals(role);

		String name = nameField.getText();
		String surname = surnameField.getText();
		String password = passwordField.getText();
		String passwordRep = passwordConfirmField.getText();
		String title = "";
		String studNumber = "";
		String abbreviation = "";

		if (isStudent) {
			studNumber = studNumberField.getText();
		} else {
			title = (String) titleDropdown.getSelectedItem();
			abbreviation = abbreviationField.getText();
		}

		return registration.registerUser(name, surname, password, passwordRep, role, title, studNumber, abbreviation);
	}

}
