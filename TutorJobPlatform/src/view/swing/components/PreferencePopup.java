package view.swing.components;

import model.AppData;
import model.Student;
import view.swing.components.homescreens.InfoText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PreferencePopup extends JFrame {
    private Student student;
    private JPanel wrapper, lecturePanel, teacherPanel;

    private final int width = 500;
    private final int height = 400;

    public PreferencePopup(Student student) {
        this.student = student;

        setSize(width, height);
        setResizable(false);
        setLayout(new BorderLayout());
        setTitle();
        wrapper = createWrapper();
        add(wrapper, BorderLayout.CENTER);
        wrapper.add(createButton());

        lecturePanel = createLecturePanel();
        teacherPanel = createTeacherPanel();
        wrapper.add(lecturePanel);
        wrapper.add(teacherPanel);
        revalidate();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createWrapper() {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(null);
        return wrapper;
    }

    private void setTitle() {
        JPanel textArea = new JPanel();
        JLabel title = new JLabel(InfoText.chooseHere);
        JLabel subtitle = new JLabel(InfoText.noChoiceInfo);
        JLabel spacing = new JLabel(" ");

        textArea.setLayout(new BorderLayout());
        textArea.add(spacing, BorderLayout.NORTH);
        textArea.add(title, BorderLayout.CENTER);
        textArea.add(subtitle, BorderLayout.SOUTH);

        add(textArea, BorderLayout.NORTH);
        title.setHorizontalAlignment(JLabel.CENTER);
        subtitle.setHorizontalAlignment(JLabel.CENTER);
    }

    private JPanel createLecturePanel() {
        int[] bounds = {100, 40, 100, height};
        return createPreferencePanel(bounds, "Kurse",
                AppData.data.getLectureNamesWithoutDuplicates());
    }

    private JPanel createTeacherPanel() {
        int[] bounds = {((width / 2) + 20), 40, (width / 2), height};
        return createPreferencePanel(bounds, "Dozent*innen",
                AppData.data.getTeacherNames());
    }

    private JButton createButton() {
        JButton confirmButton = new JButton("Auswahl bestätigen");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student.setChoiceMade(true);
                dispose();
            }
        });
        confirmButton.setBounds(((width / 2) - 150), (height-150), 300, 30);
        return confirmButton;
    }

    private JPanel createPreferencePanel(int[] bounds, String header,
                                       ArrayList<String> options) {
        JPanel prefPanel = new JPanel();
        prefPanel.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        prefPanel.setLayout(new BoxLayout(prefPanel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(header);
        JLabel spacing = new JLabel(" ");
        prefPanel.add(label);
        prefPanel.add(spacing);
        options.forEach(option -> createCheckBox(option, header, prefPanel));
        return prefPanel;
    }

    private void createCheckBox(String option,
                                String prefCategory, JPanel panel) {
        JCheckBox cb = new JCheckBox(option);
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox cb = ((JCheckBox) e.getSource());
                if (cb.isSelected()) {
                    student.addPreference(cb.getText(), prefCategory);
                } else {
                    student.removePreference(cb.getText());
                }
            }
        });
        panel.add(cb);
    }
}
