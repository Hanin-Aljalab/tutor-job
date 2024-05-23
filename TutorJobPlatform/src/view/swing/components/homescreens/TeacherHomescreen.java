package view.swing.components.homescreens;

//import model.AppData;
import controller.App;
import model.*;
import view.swing.components.TeacherInputField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The {@code TeacherHomescreen} class represents the home screen for teacher.
 * It extends the {@link Homescreen} class and provides functionality specific to teacher,
 * such as displaying their courses and managing their lecture creation.
 */
public class TeacherHomescreen extends Homescreen {
    private DefaultListModel<String> lectureModel;
    private JList<String> lectures;

    /**
     * Constructs a new {@code TeacherHomescreen} for the given teacher
     * @param user the teacher for whom the home screen is created
     */
    public TeacherHomescreen(Teacher user) {
        super(user, Color.YELLOW);
    }

    /**
     * Determines which general information text is shown the center of the home screen
     * @return an array of strings to be displayed as general information
     */
    @Override
    protected String[] getGeneralInfo() {
        return InfoText.teacherGeneral;
    }

    /**
     * Determines the match information text to be displayed to the teacher
     * @return an array of strings to be displayed as match information
     */
    @Override
    protected String[] getMatchInfo() {
        Teacher teacher = (Teacher) user;
        ArrayList<String> matchInfo = new ArrayList<>();
        App.getData().getMatches().forEach((student, lecture) -> {
            String teacherId =
                    lecture.getTeacher().getTeacherId();
            if (teacherId.equals(teacher.getTeacherId())) {
                matchInfo.add("<html><font color=blue>" + lecture.getName() + ":  " +
                        student.getFirstName() + " " + student.getLastName() +
                        " (" + student.getStudNumber() + ")</font></html>");
            }
        });
        Collections.sort(matchInfo);

        matchInfo.addFirst(" ");
        matchInfo.addFirst(InfoText.teacherResultMessage);
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
     * Configure and returns the button for adding a new lecture.
     * @return a {@link JButton} configured for adding a new lecture
     */
    @Override
    protected JButton configureButton() {
        JButton lectureButton = new JButton("Neuen Kurs anlegen");
        lectureButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            addLecture();
        }
    });

        lectureButton.setBackground(Color.LIGHT_GRAY);
        lectureButton.setPreferredSize(new Dimension(300, 25));
        return lectureButton;
    }

    /**
     * Updates the status label tp reflect whether the teacher has any courses.
     * @param status the {@link JLabel} to be updated with the status information
     */
    @Override
    protected void adaptStatus(JLabel status) {
        if (((Teacher) user).getLectures().isEmpty()) {
            status.setText(InfoText.teacherWithoutClass);
            status.setForeground(Color.LIGHT_GRAY);
        } else {
            status.setText(InfoText.teacherHasClass);
            status.setForeground(Color.GREEN);
        }
    }

    /**
     * Configures the bottom right panel of the home screen to display
     * the teacher's courses.
     */
    @Override
    protected void configureBottomRightPanel() {
        GridBagConstraints constraintsBotR = new GridBagConstraints();
        constraintsBotR.gridx = 0;
        constraintsBotR.fill = GridBagConstraints.HORIZONTAL;
        constraintsBotR.weightx = 1.0;
        constraintsBotR.insets = new Insets(5, 10, 5, 10);

        JLabel lectureLabel = new JLabel("Ihre Kurse:");
        constraintsBotR.gridy = 0;
        rightPanel.add(lectureLabel, constraintsBotR);

        lectureModel = new DefaultListModel<>();
        updateLectureScroll();
        lectures = new JList<>(lectureModel);
        JScrollPane lectureScroll = new JScrollPane(lectures);
        lectureScroll.setPreferredSize(new Dimension(180, 100));
        constraintsBotR.gridy = 1;
        rightPanel.add(lectureScroll, constraintsBotR);
    }

    /**
     * Updates the lecture scroll pane to display the current list
     * of lectures for the teacher.
     */
    private void updateLectureScroll() {
        lectureModel.clear();
        Teacher teacher = ((Teacher) user);
        App.getData().getLectures().forEach((lecture) -> {
            if (lecture.getTeacher().getTeacherId().equals(teacher.getTeacherId())) {
                lectureModel.addElement(lecture.getName());
            }
        });
    }

    /**
     * Opens a new {@link TeacherInputField} for the teacher to add a new lecture.
     */
    private void addLecture() {
        new TeacherInputField((Teacher) user, this);
    }

    /**
     * Refreshes the list of lectures displayed on the home screen.
     */
    public void refreshLectures() {
        updateLectureScroll();
    }
}
