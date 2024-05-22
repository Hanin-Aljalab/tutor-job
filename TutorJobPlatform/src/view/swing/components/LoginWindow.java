package view.swing.components;



import controller.*;
import exceptions.*;
import model.*;
import view.swing.components.homescreens.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends JFrame implements ActionListener {
	private final Login login = Login.login;

	// Declare UI components
	private JFrame frame;
	private JPanel mainPanel, formPanel, buttonPanel, passwordPanel;
	private JTextField studNumberField, teacherIdField;
	private JPasswordField passwordField;
	private JComboBox<String> roleDropdown;
	private JButton submitButton, registerButton, toggleButton;
	private boolean isPasswordVisible = false;

	// Icons
	private ImageIcon openEyeIcon;
	private ImageIcon closedEyeIcon;

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

		// Main panel
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
		passwordField.setEchoChar('*'); // Set the echo character to hide the password

		// Load and scale the eye icons
		openEyeIcon = new ImageIcon(new ImageIcon("src/icons/eye.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		closedEyeIcon = new ImageIcon(new ImageIcon("src/icons/hide.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

		// Password panel with toggle button
		passwordPanel = new JPanel(new BorderLayout());
		passwordPanel.add(passwordField, BorderLayout.CENTER);

		// Toggle button with closed eye icon initially
		toggleButton = new JButton(closedEyeIcon);
		toggleButton.setPreferredSize(new Dimension(30, 30));
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					passwordField.setEchoChar('*');
					toggleButton.setIcon(closedEyeIcon);
				} else {
					passwordField.setEchoChar((char) 0);
					toggleButton.setIcon(openEyeIcon);
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		passwordPanel.add(toggleButton, BorderLayout.EAST);

		// Submit button
		submitButton = new JButton("Anmelden");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (transmitData()) {
						String role = (String) roleDropdown.getSelectedItem();
						boolean isStudent = "Student*in".equals(role);
						User currentUser =
								App.getData().getUser(studNumberField.getText(),
										teacherIdField.getText(), role);
						if (isStudent) {
							frame.dispose();
							new StudentHomescreen((Student) currentUser);
						} else {
							frame.dispose();
							new TeacherHomescreen((Teacher) currentUser);
						}
					}
				} catch (InvalidInputException | IncorrectPasswordException | UserDoesNotExistException exception) {
					JOptionPane.showMessageDialog(new Frame(), exception.getMessage());
				} catch (HeadlessException exception) {
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
		addToFormPanel(new JLabel("Passwort:"), passwordPanel);

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
	 * @throws InvalidInputException         if the input is invalid
	 * @throws IncorrectPasswordException    if the password is incorrect
	 * @throws UserDoesNotExistException     if the user does not exist
	 */
	private boolean transmitData() throws InvalidInputException,
			IncorrectPasswordException, UserDoesNotExistException {
		String role = (String) roleDropdown.getSelectedItem();
		boolean isStudent = "Student*in".equals(role);

		String studNumber = "";
		String teacherId = "";
		String password = new String(passwordField.getPassword());

		if (isStudent) {
			studNumber = studNumberField.getText();
		} else {
			teacherId = teacherIdField.getText();
		}

		if (teacherId.equals("Matcher") && password.equals("matchmaking")) {
			App.match();
			JOptionPane.showMessageDialog(new Frame(), "Zuordnung " +
					"durchgeführt.");
			teacherIdField.setText("");
			passwordField.setText("");
			return false;
		}

		User user = login.loginUser(role, studNumber, teacherId, password);
		return user != null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
