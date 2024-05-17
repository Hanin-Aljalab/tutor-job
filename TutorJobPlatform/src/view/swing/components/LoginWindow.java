package view.swing.components;

import javax.swing.*;

import controller.Login;
import controller.Registration;

import exceptions.AbbreviationInvalidException;
import exceptions.IncorrectPasswordException;
import exceptions.InvalidInputException;
import exceptions.PasswordsNotIdenticalException;
import exceptions.StudentNumberInvalidException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserDoesNotExistException;

import model.AppData;
import model.User;
import model.Teacher;
import model.Student;

import view.swing.components.homescreens.*;

import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends JFrame implements ActionListener {
	private final Login login = Login.login;
	private AppData data = AppData.data;

	// Declare UI components
	private JFrame frame;
	private JPanel mainPanel, formPanel, buttonPanel;
	private JTextField studNumberField, teacherIdField, passwordField;
	private JComboBox<String> roleDropdown;
	private JButton submitButton, registerButton;

	/**
	 * Constructor to initialize the login window. Sets up the UI components and
	 * their layout.
	 */
	public LoginWindow() {
		// Create the frame
		frame = new JFrame();
		frame.setTitle("Anmeldung");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 250);

		/// Main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Form panel
		formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 2, 10, 10));
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

		// Text fields
		studNumberField = new JTextField();
		teacherIdField = new JTextField();
		passwordField = new JPasswordField();

		// Submit button
		submitButton = new JButton("Anmelden");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (transmitData()) {
						String role = (String) roleDropdown.getSelectedItem();
						boolean isStudent = "Student*in".equals(role);
						User currentuser = data.getUser(studNumberField.getText(), teacherIdField.getText(), role);
						if (isStudent) {
							frame.dispose();
							new StudentHomescreen((Student) currentuser);
						} else {
							frame.dispose();
							new TeacherHomescreen((Teacher) currentuser);
						}

					} else {
						JOptionPane.showMessageDialog(frame,
								"Anmeldung" + "fehlgeschlagen. Bitte überprüfen Sie Ihre " + "Daten.");

					}
				} catch (HeadlessException | StudentNumberInvalidException | InvalidInputException
						| AbbreviationInvalidException | IncorrectPasswordException
						| UserDoesNotExistException exception) {
					exception.printStackTrace();
				}

			}
		});

		// Register button
		registerButton = new JButton("Jetzt Registrieren!");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						frame.dispose();
						new RegistrationPopup();
					}
				});
			}
		});

		// Add components to form panel
		addToFormPanel(new JLabel("Ich bin:"), roleDropdown);
		addToFormPanel(new JLabel("Matrikelnummer:"), studNumberField);
		addToFormPanel(new JLabel("Dozent*innenkürzel:"), teacherIdField);
		addToFormPanel(new JLabel("Passwort:"), passwordField);

		toggleFieldsBasedOnRole((String) roleDropdown.getSelectedItem());

		// Add buttons to button panel
		buttonPanel.add(submitButton);
		buttonPanel.add(registerButton);

		// Add panels to main panel
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Configure frame
		frame.add(mainPanel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Adds components to the form panel.
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
		teacherIdField.setVisible(!isStudent);
	}

	/**
	 * Transmits the data from the login form to the login controller.
	 * 
	 * @return true if the login is successful, false otherwise
	 * @throws StudentNumberInvalidException if the student number is invalid
	 * @throws InvalidInputException         if the input is invalid
	 * @throws AbbreviationInvalidException  if the teacher ID is invalid
	 * @throws IncorrectPasswordException    if the password is incorrect
	 * @throws UserDoesNotExistException     if the user does not exist
	 */
	private boolean transmitData() throws StudentNumberInvalidException, InvalidInputException,
			AbbreviationInvalidException, IncorrectPasswordException, UserDoesNotExistException {
		String role = (String) roleDropdown.getSelectedItem();
		boolean isStudent = "Student*in".equals(role);

		String studNumber = "";
		String teacherId = "";
		String password = passwordField.getText();

		if (isStudent) {
			studNumber = studNumberField.getText();
		} else {
			teacherId = teacherIdField.getText();
		}

		User user = login.loginUser(role, studNumber, teacherId, password);

		if (user == null) {
			return false;
		}
		return true;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
