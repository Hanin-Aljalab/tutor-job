package exceptions;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class UserDoesNotExistException extends Exception {
	public UserDoesNotExistException(String message) {
		super(message);
        JOptionPane.showMessageDialog(new Frame(),  "Benutzer ist noch nicht registriert!");
	}
}
