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

	// Declare UI components
	private JFrame frame;
	private JPanel mainPanel, formPanel, buttonPanel;
	private JTextField firstNameField, lastNameField, teacherIdField, passwordField, passwordConfirmField,
			studNumberField;
	private JComboBox<String> roleDropdown, titleDropdown, studyPathDropdown;
	private JButton submitButton, loginButton;
	private JLabel messageLabel;

	/**
	 * Constructor to initialize the registration popup window. Sets up the UI
	 * components and their layout.
	 */
	public RegistrationPopup() {
		// Create the frame
		frame = new JFrame("Registrierung");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);

		// Main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Form panel
		formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(0, 2, 10, 10));
		formPanel.setOpaque(false);

		// Button panel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		buttonPanel.setOpaque(false);

		// Role selection
		String[] roles = { "Student*in", "Dozent*in" };
		roleDropdown = new JComboBox<>(roles);
		roleDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleFieldsBasedOnRole((String) roleDropdown.getSelectedItem());
			}
		});

		// Teacher titles
		String[] teacherTitles = { "", "Dr.", "Prof. Dr." };
		titleDropdown = new JComboBox<>(teacherTitles);

		// Study paths
		String[] studyPaths = { "CSB", "IB", "IMB", "UIB" };
		studyPathDropdown = new JComboBox<>(studyPaths);

		// Text fields
		firstNameField = new JTextField();
		lastNameField = new JTextField();
		teacherIdField = new JTextField();
		passwordField = new JPasswordField();
		passwordConfirmField = new JPasswordField();
		studNumberField = new JTextField();

		// Submit button
		submitButton = new JButton("Registrieren");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (transmitData()) {
						JOptionPane.showMessageDialog(frame, "Sie sind jetzt registriert!");
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								frame.dispose();
								new LoginWindow();
							}
						});
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

		// Login button
		loginButton = new JButton("Jetzt Anmelden!");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						frame.dispose();
						new LoginWindow();
					}
				});
			}
		});

		// Message label
		messageLabel = new JLabel(" ");
		messageLabel.setForeground(Color.RED);

		// Add components to form panel
		addToFormPanel(new JLabel("Ich bin:"), roleDropdown);
		addToFormPanel(new JLabel("Anrede:"), titleDropdown);
		addToFormPanel(new JLabel("Vorname:"), firstNameField);
		addToFormPanel(new JLabel("Nachname:"), lastNameField);
		addToFormPanel(new JLabel("Studiengang:"), studyPathDropdown);
		addToFormPanel(new JLabel("Matrikelnummer:"), studNumberField);
		addToFormPanel(new JLabel("Dozent*innenkürzel:"), teacherIdField);
		addToFormPanel(new JLabel("Passwort:"), passwordField);
		addToFormPanel(new JLabel("Passwort wiederholen:"), passwordConfirmField);
		toggleFieldsBasedOnRole((String) roleDropdown.getSelectedItem());

		// Add buttons to button panel
		buttonPanel.add(submitButton);
		buttonPanel.add(loginButton);

		// Add panels to main panel
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(messageLabel, BorderLayout.NORTH);

		// Frame konfigurieren
		frame.add(mainPanel);
		frame.setLocationRelativeTo(null); // Zentriert das Fenster
		frame.setVisible(true);
	}

	/**
	 * Adds a label and a corresponding field to the form panel.
	 * 
	 * @param label the label component
	 * @param field the field component
	 */
	private void addToFormPanel(Component label, Component field) {
		formPanel.add(label);
		formPanel.add(field);
	}

	/**
	 * Toggles the visibility of fields based on the selected role.
	 * 
	 * @param role the selected role
	 */
	private void toggleFieldsBasedOnRole(String role) {
		boolean isStudent = "Student*in".equals(role);
		studNumberField.setVisible(isStudent);
		titleDropdown.setVisible(!isStudent);
		teacherIdField.setVisible(!isStudent);
		studyPathDropdown.setVisible(isStudent);
	}

	// TODO: Pop-up-Nachrichten anzeigen basierend auf dem Ergebnis

	/**
	 * Transmits the data from the registration form to the registration controller.
	 * 
	 * @return true if the registration is successful, false otherwise
	 * @throws UserAlreadyExistsException     if the user already exists
	 * @throws StudentNumberInvalidException  if the student number is invalid
	 * @throws PasswordsNotIdenticalException if the passwords do not match
	 * @throws InvalidInputException          if the input is invalid
	 * @throws AbbreviationInvalidException   if the teacher ID is invalid
	 */
	private boolean transmitData() throws UserAlreadyExistsException, StudentNumberInvalidException,
			PasswordsNotIdenticalException, InvalidInputException, AbbreviationInvalidException {
		String role = (String) roleDropdown.getSelectedItem();
		boolean isStudent = "Student*in".equals(role);

		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String password = passwordField.getText();
		String passwordRep = passwordConfirmField.getText();
		String title = "";
		String studNumber = "";
		String teacherId = "";
		String studyPath = "";

		if (isStudent) {
			studNumber = studNumberField.getText();
			studyPath = (String) roleDropdown.getSelectedItem();
		} else {
			title = (String) titleDropdown.getSelectedItem();
			teacherId = teacherIdField.getText();
		}

		return registration.registerUser(firstName, lastName, password, passwordRep, role, title, studNumber, teacherId,
				studyPath);
	}

}
