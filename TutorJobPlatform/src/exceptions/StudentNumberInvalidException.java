package exceptions;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class StudentNumberInvalidException extends Exception {
	public StudentNumberInvalidException(String message) {
		super(message);
	}
}
