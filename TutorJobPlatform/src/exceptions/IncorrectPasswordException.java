package exceptions;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}