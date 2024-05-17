package view.swing.components.homescreens;

import model.AppData;
import model.Student;
import model.Teacher;

import javax.swing.*;
import java.awt.*;

public class TeacherHomescreen extends Homescreen{
    private DefaultListModel<String> lectureModel;
    private JList<String> lectures;

    public TeacherHomescreen(Teacher user) {
        super(user, Color.YELLOW);
        button = configureButton();
        choicePanel.add(button);
    }

    @Override
    protected String[] setInfoText() {
        return InfoText.teacherGeneral;
    }

    @Override
    protected JButton configureButton() {
        JButton lectureButton = new JButton("Neuen Kurs anlegen");
        lectureButton.addActionListener(event
                    -> addLecture());
        lectureButton.setBackground(Color.LIGHT_GRAY);
        lectureButton.setPreferredSize(new Dimension(300, 25));
        return lectureButton;
    }

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
        lectures = new JList<>(lectureModel);
        JScrollPane lectureScroll = new JScrollPane(lectures);
        lectureScroll.setPreferredSize(new Dimension(180, 100));
        constraintsBotR.gridy = 1;
        rightPanel.add(lectureScroll, constraintsBotR);
    }

    // TODO add functional methods
    private void addLecture() {
    }

    // TODO temp main
    public static void main(String[] args) {
        new TeacherHomescreen(AppData.data.getTeachers().getFirst());
    }
}
