package view.swing.components;

import model.Student;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePreferencesButton implements ActionListener {
    Student student;
    JButton deleteButton;

    public DeletePreferencesButton(Student student) {
        this.student = student;
        JButton deleteButton = new JButton("Auswahl zurücksetzen");
        deleteButton.addActionListener(this);
        //TODO: set correct bounds
        deleteButton.setBounds(150,300, 300, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        student.deletePreferences();
        System.out.println(student.isChoiceMade());
    }
}
