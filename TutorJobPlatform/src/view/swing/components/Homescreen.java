package view.swing.components;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO: Homescreen sollte wahrscheinlich als erstes öffnen und hat die
//  Schaltflächen "Login" und "Registrieren". Beim Login wird dann
//  übergeben, von welchem USer der Homescreen angezeigt wird (eben der, der
//  sich eingeloggt hat).

// TODO: Unterschiedliche Ansicht von Dozent und Student. Die Schaltflächen
//  und die Inhalte unterscheiden sich ja deutlich zwischen den verschiedenen
//  Nutzergruppen.

// TODO: Weitere Informationen auf dem Homescreen bereitstellen, z.B. etwas
//  wie: "Dies ist eine Plattform für die Zuweisung von Tutor*innenstellen.
//  Bitte wählen Sie Ihre Präferenzen bis zum <Datum>. Die Zuteilung erfolgt
//  dann automatisiert. Wir wünschen Ihnen viel Freude und Erfolg im Tutorium!"
//  Und wenn dann die Zuweisung durchgeführt wurde:
//  "Sie wurden eingeteilt für das Tutorium Name: <Name>, Dozent*in: <Dozent>.
//  Wir wünschen Ihnen viel Freude und Erfolg im Tutorium!"
public class Homescreen extends JFrame {
    private User user;
    private JButton chooseYourPreference;
    private JLabel fill;
    private JLabel name;
    private JLabel studNumber;
    private JLabel infoPreference;

    GridBagConstraints constraints = new GridBagConstraints();

    public Homescreen(User user) {
        this.user = user;

        setTitle("Homescreen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        constraints.insets = new Insets(10, 10, 10, 10);

        JPanel top = new JPanel(); //----------------------------------------------------
        top.setBounds(0, 0, 700, 50);
        top.setLayout(new GridBagLayout());
        add(top);
        top.setBackground(Color.GRAY);

        name =
                new JLabel("Name: " + user.getFirstName() + " " + user.getLastName());

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        top.add(name, constraints);

        if (user.getClass() == Student.class) {
            studNumber = new JLabel("mtr: " + ((Student) user).getMatNummer());
            constraints.gridx = 1;
            constraints.gridy = 0;
            top.add(studNumber, constraints);
        }

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 50, 700, 4);
        separator.setBackground(Color.BLUE);
        add(separator);

        JPanel bottom = new JPanel(); // ----------------------------------------------------
        bottom.setBounds(0, 58, 700, 300);
        bottom.setLayout(new GridLayout());
        add(bottom, BorderLayout.CENTER);

        JPanel bottomLeft = new JPanel(); // ............................
        bottomLeft.setLayout(new GridBagLayout());
        bottomLeft.setBackground(Color.lightGray);
        bottom.add(bottomLeft);

        if (!((Student) user).isChoiceMade()) {
            chooseYourPreference = new JButton("Choose your Preference");

            chooseYourPreference.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new PreferencePopup((Student) user);
                    ((Student) user).setChoiceMade(true);
                    chooseYourPreference.setEnabled(false);
                }
            });
            bottomLeft.add(chooseYourPreference, constraints);
        }

            // TODO neue Methode in Student die prüft ob schon preference gewählt wurde


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

        public static void main (String[]args){
            Student s1 = new Student("Markus", "W", "1234", 3008816);
            Teacher t1 = new Teacher("Markus", "W", "1234", "");
            new Homescreen(t1);
        }


        //TODO: PrefPOPup schließt wenn x --> lieber nur das Fenster schließen


    }

