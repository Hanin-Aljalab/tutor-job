package view.swing.components;

import javax.swing.*;
import java.util.ArrayList;

public class PreferencePanel extends JPanel{
    PreferencePopup popup;
    ArrayList<String> prefArray;

    public PreferencePanel(PreferencePopup popup, JPanel wrapper, int[] bounds,
                           String title,
                           ArrayList<String> options, ArrayList<String> prefArray) {
        this.popup = popup;
        this.prefArray = prefArray;
        new JPanel();
        setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        wrapper.add(this);
        JLabel label = new JLabel(title);
        JLabel spacing = new JLabel(" ");
        add(label);
        add(spacing);

        options.forEach(s -> prepareBoxes(s));
    }

    public void prepareBoxes(String s) {
        JCheckBox cb = new JCheckBox(s);
        cb.addActionListener(new CheckboxAction(popup, prefArray));
        add(cb);
    }
}
