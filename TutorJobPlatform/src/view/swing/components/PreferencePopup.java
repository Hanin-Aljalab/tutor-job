package view.swing.components;

import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PreferencePopup extends JFrame implements ActionListener {
    private Student student;
    private ArrayList<String> lecturePref;
    private ArrayList<String> teacherPref;

    private final JPanel wrapperPanel;
    private JLabel title;

    private final int width = 700;
    private final int height = 500;

    public PreferencePopup(Student student) {
        this.student = student;
        lecturePref = new ArrayList<>();
        teacherPref = new ArrayList<>();

        setSize(width, height);
        setLayout(new BorderLayout());
        setTitle();
        wrapperPanel = createWrapper();
        add(wrapperPanel, BorderLayout.CENTER);
        createButton();
        createLecturePanel();
        createTeacherPanel();
        revalidate();
        //      setContentPane(wrapperPanel); // TODO wofür?????

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createWrapper() {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(null);
        return wrapper;
    }

    private void setTitle() {
        JPanel textArea = new JPanel();
        String str1 = "Wählen Sie hier Ihre Präferenzen.";
        String str2 = "(Falls Sie keine Angabe machen, erfolgt die " +
                "Zuteilung zufällig.)";
        JLabel title = new JLabel(str1);
        JLabel subtitle = new JLabel(str2);
        JLabel spacing = new JLabel(" ");

        textArea.setLayout(new BorderLayout());
        textArea.add(spacing, BorderLayout.NORTH);
        textArea.add(title, BorderLayout.CENTER);
        textArea.add(subtitle, BorderLayout.SOUTH);

        this.add(textArea, BorderLayout.NORTH);
        title.setHorizontalAlignment(JLabel.CENTER);
        subtitle.setHorizontalAlignment(JLabel.CENTER);
    }

    private void createLecturePanel() {
        ArrayList<String> lectures = new ArrayList<>();
        lectures.add("PR1");
        lectures.add("PR2");
        lectures.add("SE1");
        lectures.add("MA1");
        lectures.add("MA2");
        int[] bounds = {50, 40, (width/2), height};
        PreferencePanel panel = new PreferencePanel(this, wrapperPanel, bounds,
                "Fächer", lectures, lecturePref);
    }

    private void createTeacherPanel() {
        ArrayList<String> teachers = new ArrayList<>();
        teachers.add("Y. Todorov");
        teachers.add("J. Steinberger");
        teachers.add("J. Fischer");
        teachers.add("W. Schramm");
        int[] bounds = {((width/2)+50), 40, (width/2), height};
        PreferencePanel panel = new PreferencePanel(this, wrapperPanel, bounds,
                "Dozent*innen", teachers, teacherPref);
    }

    private void createButton() {
        JButton confirmButton = new JButton("Auswahl bestätigen");
        confirmButton.addActionListener(this);
        confirmButton.setBounds(((width/2)-150),300, 300, 30);
        wrapperPanel.add(confirmButton);
    }

    public ArrayList<String> getLecturePref() {
        return lecturePref;
    }

    public ArrayList<String> getTeacherPref() {
        return teacherPref;
    }

    public void addPreference(String pref, ArrayList<String> prefArray) {
        prefArray.add(pref);
    }

    public void removePreference(String pref, ArrayList<String> prefArray) {
        prefArray.remove(pref);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        student.setPreferences(lecturePref, teacherPref);
    }

}

