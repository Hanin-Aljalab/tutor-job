package exceptions;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
