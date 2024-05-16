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

import java.awt.*;
import java.awt.event.*;

public class LoginPopup extends JFrame implements ActionListener {
	private final Login login = Login.login;

	// Komponenten deklarieren
	private JFrame frame;
	private JPanel mainPanel, formPanel, buttonPanel;
//	private JTextField usernameField;
	private JTextField studNumberField, abbreviationField, passwordField;
	private JComboBox<String> roleDropdown;
	private JButton submitButton, registerButton;

	public LoginPopup() {
		// Fenster erstellen
		frame = new JFrame();
		frame.setTitle("Anmeldung");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 250);

		// Hauptpanel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Formularpanel
		formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 2, 10, 10));
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
			}
		});

		// Textfelder
		studNumberField = new JTextField();
		abbreviationField = new JTextField();
		passwordField = new JPasswordField();

		// Submit-Button
		submitButton = new JButton("Anmelden");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (transmitData()) {
						JOptionPane.showMessageDialog(frame, "Anmeldung erfolgreich!\n" + "Willkommen " + AppData.data.getUser(studNumberField.getText(), abbreviationField.getText(), (String) roleDropdown.getSelectedItem()).getName());
						System.out.println(AppData.data.getStudents());
					} else {
						JOptionPane.showMessageDialog(frame,
								"Anmeldung" + "fehlgeschlagen. Bitte überprüfen Sie Ihre " + "Daten.");

					}
				} catch (HeadlessException | StudentNumberInvalidException | InvalidInputException
						| AbbreviationInvalidException | IncorrectPasswordException | UserDoesNotExistException exception) {
					exception.printStackTrace();
				}
				
			}
		});

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

		// Komponenten zum Formularpanel hinzufügen
		addToFormPanel(new JLabel("Ich bin:"), roleDropdown);
		addToFormPanel(new JLabel("Matrikelnummer:"), studNumberField);
		addToFormPanel(new JLabel("Dozentenkürzel:"), abbreviationField);
		addToFormPanel(new JLabel("Passwort:"), passwordField);

		toggleFieldsBasedOnRole((String) roleDropdown.getSelectedItem());

		// Buttonpanel hinzufügen
		buttonPanel.add(submitButton);
		buttonPanel.add(registerButton);

		// Hauptpanel hinzufügen
		mainPanel.add(formPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Frame konfigurieren
		frame.add(mainPanel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void addToFormPanel(Component label, Component field) {
		formPanel.add(label);
		formPanel.add(field);
	}

	private void toggleFieldsBasedOnRole(String role) {
		boolean isStudent = "Student*in".equals(role);
		studNumberField.setVisible(isStudent);
		abbreviationField.setVisible(!isStudent);
	}

	private boolean transmitData() throws StudentNumberInvalidException, InvalidInputException, AbbreviationInvalidException, IncorrectPasswordException, UserDoesNotExistException {
		String role = (String) roleDropdown.getSelectedItem();
		boolean isStudent = "Student*in".equals(role);

		String studNumber = "";
		String abbreviation = "";
		String password = passwordField.getText();
		
		if (isStudent) {
			studNumber = studNumberField.getText();
		} else {
			abbreviation = abbreviationField.getText();
		}
		
		User user = login.loginUser(role, studNumber, abbreviation, password);
		
		if (user == null) {
			return false;
		}
		return true;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
