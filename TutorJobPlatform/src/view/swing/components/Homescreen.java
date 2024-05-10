package view.swing.components;

import model.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

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

//  TODO: infoText geht über Panel hinaus

public class Homescreen extends JFrame {
    private User user;
    private JButton chooseYourPreference;
    private JButton removePreferencesButton;
    private JLabel name, matrikelnumber, preferenceStatus;
    private JList<String> lecturePrefList, teacherPrefList;
    private DefaultListModel<String> lectureModel, teacherModel;
    private JPanel bottom, leftPanel, rightPanel;
    private GridBagConstraints constraints;

    /**
     * Home constructor
     *
     * @param user
     */
    public Homescreen(User user) {
        this.user = user;
        setTitle("Tutor Job System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        configureTopPanel();
        configureBottomPanel();

        setVisible(true);
    }

    /**
     * configure the top panel
     */
    private void configureTopPanel() {
        JPanel top = new JPanel(new GridBagLayout());
        top.setBackground(Color.CYAN);
        add(top, BorderLayout.NORTH);

        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); //abstand von anderen El.
        constraints.weightx = 1;

        name = new JLabel("Name: " + user.getFirstName() + " " + user.getLastName());
        constraints.gridx = 0;
        constraints.gridy = 0;
        top.add(name, constraints);

        if (user instanceof Student) {
            matrikelnumber = new JLabel("Mtr: " + ((Student) user).getMatNummer());
            constraints.gridx = 1;
            top.add(matrikelnumber, constraints);
        }

        JLabel logoLabel = new JLabel("TutorJob v1.2.");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logoLabel.setForeground(Color.BLUE);
        constraints.gridx = 2;
        top.add(logoLabel, constraints);

        JButton logoutButton = createLogoutButton();
        constraints.gridx = 3;
        constraints.weightx = 0;
        constraints.anchor = GridBagConstraints.EAST;
        top.add(logoutButton, constraints);
    }

    /**
     * configure the bottom panel
     */
    private void configureBottomPanel() {
        bottom = new JPanel(new GridBagLayout());
        bottom.setBackground(Color.LIGHT_GRAY);
        add(bottom, BorderLayout.CENTER);

        leftPanel = new JPanel(new GridBagLayout());
        rightPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraintsBot = new GridBagConstraints();
        constraintsBot.fill = GridBagConstraints.BOTH;
        constraintsBot.insets = new Insets(5, 5, 5, 5);

        // config for left panel
        constraintsBot.gridx = 0;
        constraintsBot.gridy = 0;
        constraintsBot.weightx = 0.66;
        constraintsBot.weighty = 1.0;
        bottom.add(leftPanel, constraintsBot);

        // config for right panel
        constraintsBot.gridx = 1;
        constraintsBot.weightx = 0.34;
        bottom.add(rightPanel, constraintsBot);

        configureBottomLeftPanel();
        configureBottomRightPanel();
    }

    /**
     * Method to configure the bottom left panel
     */
    private void configureBottomLeftPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0;

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints topConstraints = new GridBagConstraints();
        topConstraints.gridx = 0;
        topConstraints.gridy = 0;
        topConstraints.weightx = 1.0;
        topConstraints.weighty = 0;
        topConstraints.fill = GridBagConstraints.BOTH;
        topConstraints.anchor = GridBagConstraints.CENTER;

        JLabel infoLabel = new JLabel("Langer Text der leider das Panel erweitert.", JLabel.CENTER);
        infoLabel.setForeground(Color.DARK_GRAY);
        topPanel.add(infoLabel, topConstraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.3;
        leftPanel.add(topPanel, constraints);

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints bottomConstraints = new GridBagConstraints();
        bottomConstraints.insets = new Insets(5, 5, 5, 5);
        bottomConstraints.fill = GridBagConstraints.HORIZONTAL;
        bottomConstraints.anchor = GridBagConstraints.SOUTH;

        bottomConstraints.gridx = 0;
        bottomConstraints.gridy = 0;
        bottomConstraints.weightx = 1.0;

        if (user instanceof Student && !((Student) user).isChoiceMade()) {
            chooseYourPreference = new JButton("Choose your Preference");
            chooseYourPreference.setBackground(Color.LIGHT_GRAY);
            chooseYourPreference.setPreferredSize(new Dimension(300, 25));
            chooseYourPreference.addActionListener(event -> handlePreferenceSelection((Student) user));
            bottomPanel.add(chooseYourPreference, bottomConstraints);

            removePreferencesButton = new JButton("X");
            removePreferencesButton.setPreferredSize(new Dimension(1, 25));
            removePreferencesButton.addActionListener(event -> removePreferences((Student) user));
            removePreferencesButton.setEnabled(((Student) user).isChoiceMade());
            bottomConstraints.gridx = 1;
            bottomPanel.add(removePreferencesButton, bottomConstraints);
        }

        preferenceStatus = new JLabel("no preference chosen yet", JLabel.CENTER);
        preferenceStatus.setForeground(Color.LIGHT_GRAY);
        if (!(user instanceof Student && !((Student) user).isChoiceMade())) {
            preferenceStatus.setText("Preference already chosen!");
            preferenceStatus.setForeground(Color.GREEN);
        }

        bottomConstraints.gridx = 0;
        bottomConstraints.gridy = 1;
        bottomConstraints.gridwidth = 1;

        bottomPanel.add(preferenceStatus, bottomConstraints);

        constraints.gridy = 1;
        constraints.weighty = 0.05; //config height of panel
        leftPanel.add(bottomPanel, constraints);
    }


    /**
     * method to configure the bottom right panel
     */
    private void configureBottomRightPanel() {
        GridBagConstraints constraintsBotR = new GridBagConstraints();
        constraintsBotR.gridx = 0;
        constraintsBotR.fill = GridBagConstraints.HORIZONTAL;
        constraintsBotR.weightx = 1.0;
        constraintsBotR.insets = new Insets(5, 10, 5, 10);

        JLabel lectureLabel = new JLabel("Preferred lectures:");
        constraintsBotR.gridy = 0;
        rightPanel.add(lectureLabel, constraintsBotR);

        lectureModel = new DefaultListModel<>();
        lecturePrefList = new JList<>(lectureModel);
        JScrollPane lectureScroll = new JScrollPane(lecturePrefList);
        lectureScroll.setPreferredSize(new Dimension(180, 100));
        constraintsBotR.gridy = 1;
        rightPanel.add(lectureScroll, constraintsBotR);

        JLabel teacherLabel = new JLabel("Preferred teachers:");
        constraintsBotR.gridy = 2;
        rightPanel.add(teacherLabel, constraintsBotR);

        teacherModel = new DefaultListModel<>();
        teacherPrefList = new JList<>(teacherModel);
        JScrollPane teacherScroll = new JScrollPane(teacherPrefList);
        teacherScroll.setPreferredSize(new Dimension(180, 100));
        constraintsBotR.gridy = 3;
        rightPanel.add(teacherScroll, constraintsBotR);
    }

    /**
     * Method to update th current Preference List
     */
    private void updatePreferenceLists() {
        if (user instanceof Student) {
            lectureModel.clear();
            ArrayList<String> lectures = ((Student) user).getLecturePref();
            if (lectures != null) {
                for (String lecture : lectures) {
                    lectureModel.addElement(lecture);
                }
            }

            teacherModel.clear();
            ArrayList<String> teachers = ((Student) user).getTeacherPref();
            if (teachers != null) {
                for (String teacher : teachers) {
                    teacherModel.addElement(teacher);
                }
            }
        }
    }

    /**
     * Method to handle the preference selection
     *
     * @param student
     */
    private void handlePreferenceSelection(Student student) {
        PreferencePopup popup = new PreferencePopup(this, student);
        popup.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (student.isChoiceMade()) {
                    chooseYourPreference.setEnabled(false);
                    updatePreferenceLists();
                    removePreferencesButton.setEnabled(true);
                    preferenceStatus.setText("Preference saved!");
                    preferenceStatus.setForeground(Color.BLUE);

                    JOptionPane.showMessageDialog(
                            Homescreen.this,
                            "Saved!",
                            "Confirmation",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    removePreferencesButton.setEnabled(false);
                    preferenceStatus.setText("No preferences chosen yet");
                    preferenceStatus.setForeground(Color.GRAY);
                }
            }
        });
    }


    /**
     * create new logout button
     *
     * @return logout button
     */
    private JButton createLogoutButton() {
        JButton logout = new JButton("Logout");
        logout.setBackground(Color.LIGHT_GRAY);
        logout.setForeground(Color.DARK_GRAY);
        logout.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this,
                    "Möchten Sie sich wirklich ausloggen?",
                    "Logout bestätigen",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                dispose();
                new LoginPopup();
            }
        });
        return logout;
    }

    /**
     * method to set up new Preference Lists
     */
    private void setupPreferenceLists() {
        lectureModel = new DefaultListModel<>();
        lecturePrefList = new JList<>(lectureModel);
        teacherModel = new DefaultListModel<>();
        teacherPrefList = new JList<>(teacherModel);

        JScrollPane lectureScroll = new JScrollPane(lecturePrefList);
        JScrollPane teacherScroll = new JScrollPane(teacherPrefList);
        lectureScroll.setPreferredSize(new Dimension(200, 150));
        teacherScroll.setPreferredSize(new Dimension(200, 150));

        GridBagConstraints prefList = new GridBagConstraints();
        prefList.gridx = 0;
        prefList.gridy = GridBagConstraints.RELATIVE;
        prefList.fill = GridBagConstraints.HORIZONTAL;
        prefList.weightx = 1.0;
        prefList.weighty = 1.0;

        rightPanel.add(lectureScroll, prefList);
        rightPanel.add(teacherScroll, prefList);

        updatePreferenceLists();
    }

    /**
     * This method will handle the logic to remove preferences
     *
     * @param student the student whose preferences will be removed
     */
    private void removePreferences(Student student) {

        int response = JOptionPane.showConfirmDialog(this,
                "Möchten Sie wirklich alle Ihre Präferenzen löschen?",
                "löschen?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            student.clearPreferences();
            updatePreferenceLists();
            preferenceStatus.setText("Preferences removed");
            preferenceStatus.setForeground(Color.RED);
            chooseYourPreference.setEnabled(true);
        }
    }

    //TODO temp main
    public static void main(String[] args) {
        Student s1 = new Student("Markus", "Winklhofer", "1234", 3008816);
        Teacher t1 = new Teacher("Yordan", "Todorov", "1234", "Dr");
        new Homescreen(s1);
    }
}


