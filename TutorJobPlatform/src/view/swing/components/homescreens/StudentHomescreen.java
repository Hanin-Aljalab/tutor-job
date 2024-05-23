package view.swing.components.homescreens;

import controller.*;
import model.*;
import view.swing.components.PreferencePopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * The {@code StudentHomescreen} class represents the home screen for student.
 * It extends the {@link Homescreen} class and provides functionality specific to student users
 * such as displaying their student number and managing their preference selections.
 */
public class StudentHomescreen extends Homescreen {
    private JLabel studNumber;
    private JList<String> lecturePrefList, teacherPrefList;
    private DefaultListModel<String> lectureModel, teacherModel;

    /**
     * Constructs a new {@code StudentHomescreen} for the given student user.
     * @param user the student user for whom the home screen is created.
     */
    public StudentHomescreen(Student user) {
        super(user, Color.CYAN);
        displayStudNumber();
        updatePreferenceLists();
    }

    //Displays the student's matriculation number on the home screen.
    private void displayStudNumber() {
        studNumber = new JLabel("Mtr: " + ((Student) user).getStudNumber());
        constraints.gridx = 1;
        top.add(studNumber, constraints);
    }

    /**
     * Determines which welcome text is shown in the center of the homescreen
     * @return array of strings which should be displayed
     */
    @Override
    protected String[] getGeneralInfo() {
        return InfoText.studentGeneral;
    }

    /**
     * Determines the match information text to be displayed to the student user.
     * @return an array of strings to be displayed as match information
     */
    @Override
    protected String[] getMatchInfo() {
        Student student = (Student) user;

        ArrayList<String> matchInfo = new ArrayList<>();
        matchInfo.add(InfoText.studentResultMessage);
        matchInfo.add(" ");
        matchInfo.add("<html><font color=blue>" +
                App.getData().getMatches().get(student).toString() +
                "</font></html>");
        matchInfo.add(" ");
        matchInfo.add(" ");
        for (String str : InfoText.goodLuck) {
            matchInfo.add(str);
        }

        String[] infoText = new String[matchInfo.size()];
        for (int i = 0; i < matchInfo.size(); i++) {
            infoText[i] = matchInfo.get(i);
        }
        return infoText;
    }


    /**
     * Manages the display of correct preference button and status text
     * (choose preferences vs. already chosen)
     */
    protected void updateButtonAndStatus() {
        choicePanel.remove(button);
        button = configureButton();
        choicePanel.add(button);
        adaptStatus(status);
        choicePanel.revalidate();
        choicePanel.repaint();
    }

    /**
     * Manages the display of the preference status below the preference
     * button (info: not yet chosen/already chosen)
     * @param status JLabel which is adapted
     */
    @Override
    protected void adaptStatus(JLabel status) {
        if (((Student) user).isChoiceMade()) {
            status.setText(InfoText.studentAfterChoice);
            status.setForeground(Color.GREEN);
        } else {
            status.setText(InfoText.studentBeforeChoice);
            status.setForeground(Color.LIGHT_GRAY);
        }
    }

    /**
     * Displays the relevant preference button (choose preferences vs. delete
     * preferences)
     * @return version of preference button as needed
     */
    @Override
    protected JButton configureButton() {
        JButton prefButton;
        if (((Student) user).isChoiceMade()) {
            prefButton = new JButton("Präferenzen löschen");
            prefButton.addActionListener(event
                    -> removePreferences());
        } else {
            prefButton = new JButton("Wählen Sie Ihre Präferenzen");
            prefButton.addActionListener(event -> handlePreferenceSelection());
        }
        prefButton.setBackground(Color.LIGHT_GRAY);
        prefButton.setPreferredSize(new Dimension(300, 25));
        return prefButton;
    }

    /**
     * Handles the preference selection process by opening a
     * {@link PreferencePopup}
     */
    public void handlePreferenceSelection() {
        PreferencePopup popup = new PreferencePopup((Student) user);
        popup.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
               updatePreferenceLists();
                updateButtonAndStatus();
            }
        });
    }

    //Removes the student's preferences after confirming the deletion.
    public void removePreferences() {
        if (confirmDeletion()) {
            ((Student) user).deletePreferences();
            updatePreferenceLists();
            updateButtonAndStatus();
        }
    }

    /**
     * Confirms the deletion of the student's preferences.
     * @return {@code true} if the deletion is confirmed,
     * {@code false} otherwise
     */
    public boolean confirmDeletion() {
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Möchten Sie wirklich Ihre Auswahl zurücksetzen","",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return (confirmation == JOptionPane.YES_OPTION);
    }

    //Updates the preference lists displayed on the home screen.
    private void updatePreferenceLists() {
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

    //Configures the bottom right panel of the home screen.
    @Override
    protected void configureBottomRightPanel() {
        GridBagConstraints constraintsBotR = new GridBagConstraints();
        constraintsBotR.gridx = 0;
        constraintsBotR.fill = GridBagConstraints.HORIZONTAL;
        constraintsBotR.weightx = 1.0;
        constraintsBotR.insets = new Insets(5, 10, 5, 10);

        JLabel lectureLabel = new JLabel("Präferierte Kurse:");
        constraintsBotR.gridy = 0;
        rightPanel.add(lectureLabel, constraintsBotR);

        lectureModel = new DefaultListModel<>();
        lecturePrefList = new JList<>(lectureModel);
        JScrollPane lectureScroll = new JScrollPane(lecturePrefList);
        lectureScroll.setPreferredSize(new Dimension(180, 100));
        constraintsBotR.gridy = 1;
        rightPanel.add(lectureScroll, constraintsBotR);

        JLabel teacherLabel = new JLabel("Präferierte Dozent*innen:");
        constraintsBotR.gridy = 2;
        rightPanel.add(teacherLabel, constraintsBotR);

        teacherModel = new DefaultListModel<>();
        teacherPrefList = new JList<>(teacherModel);
        JScrollPane teacherScroll = new JScrollPane(teacherPrefList);
        teacherScroll.setPreferredSize(new Dimension(180, 100));
        constraintsBotR.gridy = 3;
        rightPanel.add(teacherScroll, constraintsBotR);
    }
}

