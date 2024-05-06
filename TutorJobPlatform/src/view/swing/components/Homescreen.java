package view.swing.components;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Homescreen extends JFrame {
    private User user;
    private JButton chooseYourPreference;
    private JLabel fill;
    private JLabel name;
    private JLabel matrikelnumber;
    private JLabel infoPreference;

    GridBagConstraints constraints = new GridBagConstraints();

    public Homescreen(User user) {
        this.user = user;

        setTitle("Homescreen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        constraints.insets = new Insets(10, 10, 10, 10);

        JPanel top = new JPanel(); //----------------------------------------------------
        top.setBounds(0,0,700,25);
        top.setLayout(new GridBagLayout());
        add(top);
        top.setBackground(Color.GRAY);

        name = new JLabel("Name: " + user.getName() + " " + user.getSurname());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        top.add(name, constraints);

        if(user.getClass() == Student.class) {
            matrikelnumber = new JLabel("mtr: " + ((Student) user).getMatNummer());
            constraints.gridx = 1;
            constraints.gridy = 0;
            top.add(matrikelnumber, constraints);
        }

        JSeparator separator = new JSeparator();
        separator.setBounds(0,25,700,4);
        separator.setBackground(Color.BLUE);
        add(separator);

        JPanel bottom = new JPanel(); // ----------------------------------------------------
        bottom.setBounds(0,100,700,300);
        bottom.setLayout(new GridLayout());
        add(bottom, BorderLayout.CENTER);

        JPanel bottomLeft = new JPanel(); // ............................
        bottomLeft.setLayout(new GridBagLayout());
        bottomLeft.setBackground(Color.lightGray);
        bottom.add(bottomLeft);

        chooseYourPreference = new JButton("Choose your Preference");
        // TODO: ActionListener für PerfPopUp
        // TODO: setEnabled(false) wenn schon gewählt

        constraints.gridx = 0;
        constraints.gridy = 0;
        bottomLeft.add(chooseYourPreference, constraints);

        // TODO neue Methode in Student die prüft ob schon preference gewählt wurde

//        DeletePreferencesButton delPref = new DeletePreferencesButton((Student) user);
//        constraints.gridx = 1;
//        constraints.gridy = 0;
//        bottomLeft.add(delPref,constraints);

        JPanel bottomRight = new JPanel(); // ...........................
        bottomRight.setLayout(new GridBagLayout());
        //bottomRight.setBackground(Color.white);
        bottom.add(bottomRight);

        infoPreference = new JLabel("HIER STEHEN DIE PRÄFERENZEN"); // TODO: Listen von Student ausgeben mit SwingList
        constraints.gridx = 2;
        constraints.gridy = 0;
        bottomRight.add(infoPreference, constraints);

        setVisible(true);
    }

    public static void main(String[] args) {
        Student s1 = new Student("Markus", "W", "1234", 3008816);
        Teacher t1 = new Teacher("Markus", "W", "1234");
        new Homescreen(t1);
    }
}
