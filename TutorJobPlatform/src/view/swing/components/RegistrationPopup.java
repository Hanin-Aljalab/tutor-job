package view.swing.components;

import controller.Registration;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationPopup {
	private final Registration registration = Registration.registration;

	// UI components
	private JFrame frame;
	private JPanel mainPanel, formPanel, buttonPanel;
	private JTextField firstNameField, lastNameField, teacherIdField, studNumberField;
	private JPasswordField passwordField, passwordConfirmField;
	private JComboBox<String> roleDropdown, titleDropdown, studyPathDropdown;
	private JButton submitButton, loginButton;
	private JLabel messageLabel;
	private JButton toggleButton1, toggleButton2;
	private boolean isPasswordVisible1 = false, isPasswordVisible2 = false;

	// Icons
	private ImageIcon openEyeIcon;
	private ImageIcon closedEyeIcon;

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

		// Load and scale the eye icons
		openEyeIcon = new ImageIcon(new ImageIcon("src/icons/eye.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		closedEyeIcon = new ImageIcon(new ImageIcon("src/icons/hide.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

		// Password panel with toggle button for passwordField
		JPanel passwordPanel1 = new JPanel(new BorderLayout());
		passwordPanel1.add(passwordField, BorderLayout.CENTER);

		// Toggle button with closed eye icon initially for passwordField
		toggleButton1 = new JButton(closedEyeIcon);
		toggleButton1.setPreferredSize(new Dimension(30, 30));
		toggleButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible1) {
					passwordField.setEchoChar('*');
					toggleButton1.setIcon(closedEyeIcon);
				} else {
					passwordField.setEchoChar((char) 0);
					toggleButton1.setIcon(openEyeIcon);
				}
				isPasswordVisible1 = !isPasswordVisible1;
			}
		});
		passwordPanel1.add(toggleButton1, BorderLayout.EAST);

		// Password panel with toggle button for passwordConfirmField
		JPanel passwordPanel2 = new JPanel(new BorderLayout());
		passwordPanel2.add(passwordConfirmField, BorderLayout.CENTER);

		// Toggle button with closed eye icon initially for passwordConfirmField
		toggleButton2 = new JButton(closedEyeIcon);
		toggleButton2.setPreferredSize(new Dimension(30, 30));
		toggleButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible2) {
					passwordConfirmField.setEchoChar('*');
					toggleButton2.setIcon(closedEyeIcon);
				} else {
					passwordConfirmField.setEchoChar((char) 0);
					toggleButton2.setIcon(openEyeIcon);
				}
				isPasswordVisible2 = !isPasswordVisible2;
			}
		});
		passwordPanel2.add(toggleButton2, BorderLayout.EAST);

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
					}
				} catch (UserAlreadyExistsException | StudentNumberInvalidException
						| PasswordsNotIdenticalException | InvalidInputException
						| TeacherIdInvalidException exception) {
					JOptionPane.showMessageDialog(new Frame(), exception.getMessage());
				} catch (HeadlessException exception) {
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
		addToFormPanel(new JLabel("Passwort:"), passwordPanel1);
		addToFormPanel(new JLabel("Passwort wiederholen:"), passwordPanel2);
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

	/**
	 * Transmits the data from the registration form to the registration controller.
	 * 
	 * @return true if the registration is successful, false otherwise
	 * @throws UserAlreadyExistsException     if the user already exists
	 * @throws StudentNumberInvalidException  if the student number is invalid
	 * @throws PasswordsNotIdenticalException if the passwords do not match
	 * @throws InvalidInputException          if the input is invalid
	 * @throws TeacherIdInvalidException     if the teacher ID is invalid
	 */
	public boolean transmitData() throws UserAlreadyExistsException, StudentNumberInvalidException,
			PasswordsNotIdenticalException, InvalidInputException, TeacherIdInvalidException {
		String role = (String) roleDropdown.getSelectedItem();
		boolean isStudent = "Student*in".equals(role);

		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String password = new String(passwordField.getPassword());
		String passwordRep = new String(passwordConfirmField.getPassword());
		String title = "";
		String studNumber = "";
		String teacherId = "";
		String studyPath = "";

		if (isStudent) {
			studNumber = studNumberField.getText();
			studyPath = (String) studyPathDropdown.getSelectedItem();
		} else {
			title = (String) titleDropdown.getSelectedItem();
			teacherId = teacherIdField.getText();
		}

		return registration.registerUser(firstName, lastName, password, passwordRep, role, title, studNumber, teacherId,
				studyPath);
	}

	public void setFirstNameField(String text) {
		this.firstNameField.setText(text);
	}

	public void setLastNameField(String text) {
		this.lastNameField.setText(text);
	}

	public void setTeacherIdField(String text) {
		this.teacherIdField.setText(text);
	}

	public void setStudNumberField(String text) {
		this.studNumberField.setText(text);
	}

	public void setPasswordField(String text) {
		this.passwordField.setText(text);
	}

	public void setPasswordConfirmField(String text) {
		this.passwordConfirmField.setText(text);
	}

	public void setRoleDropdown(String text) {
		this.roleDropdown.setSelectedItem(text);
	}

	public void setStudyPathDropdown(String text) {
		this.studyPathDropdown.setSelectedItem(text);
	}

	public void setTitleDropdown(String text) {
		this.titleDropdown.setSelectedItem(text);
	}


}
