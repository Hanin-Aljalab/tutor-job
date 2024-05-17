package exceptions;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class TeacherIdInvalidException extends Exception {
	public TeacherIdInvalidException(String message) {
		super(message);
		JOptionPane.showMessageDialog(new Frame(), "Ungültiges Dozentenkürzel!");
	}
}