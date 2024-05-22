package view.swing.components.homescreens;

//import model.AppData;
import controller.Matcher;
import model.*;
import view.swing.components.TeacherInputField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class TeacherHomescreen extends Homescreen {
    private DefaultListModel<String> lectureModel;
    private JList<String> lectures;

    public TeacherHomescreen(Teacher user) {
        super(user, Color.YELLOW);
    }

    @Override
    protected String[] getGeneralInfo() {
        return InfoText.teacherGeneral;
    }

    @Override
    protected String[] getMatchInfo() {
        Teacher teacher = (Teacher) user;
        ArrayList<String> matchInfo = new ArrayList<>();

        Matcher.getMatches().forEach((student, lecture) -> {
            String teacherId =
                    lecture.getTeacher().getTeacherId();
            if (teacherId.equals(teacher.getTeacherId())) {
                matchInfo.add("<html><font color=blue>" + lecture.getName() + ":  " +
                        student.getFirstName() + " " + student.getLastName() +
                        " " + student.getStudNumber() + "</font></html>");
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
        updateLectureScroll();
        lectures = new JList<>(lectureModel);
        JScrollPane lectureScroll = new JScrollPane(lectures);
        lectureScroll.setPreferredSize(new Dimension(180, 100));
        constraintsBotR.gridy = 1;
        rightPanel.add(lectureScroll, constraintsBotR);
    }

    private void updateLectureScroll() {
        lectureModel.clear();
        Teacher teacher = ((Teacher) user);
        AppData.data.getLectures().forEach((lecture) -> {
            if (lecture.getTeacher().getTeacherId().equals(teacher.getTeacherId())) {
                lectureModel.addElement(lecture.getName());
            }
        });
    }

    // TODO add functional methods
    private void addLecture() {
        new TeacherInputField((Teacher) user, this);
    }

    public void refreshLectures() {
        updateLectureScroll();
    }

    // TODO temp main
    public static void main(String[] args) {
        new TeacherHomescreen(AppData.data.getTeachers().getFirst());
    }
}
