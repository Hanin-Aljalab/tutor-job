package exceptions;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class InvalidInputException extends Exception {
	public InvalidInputException(String message) {
		super(message);
	}
}
