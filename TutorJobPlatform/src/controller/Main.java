package controller;

import javax.swing.SwingUtilities;

import view.swing.components.RegistrationPopup;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new RegistrationPopup();
			}
		});
	}
}