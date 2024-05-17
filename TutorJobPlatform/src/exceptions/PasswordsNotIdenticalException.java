package exceptions;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class PasswordsNotIdenticalException extends Exception {
	public PasswordsNotIdenticalException(String message) {
		super(message);
	}
}
