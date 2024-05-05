package view.swing.components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckboxAction implements ActionListener {
    PreferencePopup popup;
    ArrayList<String> prefArray;

    CheckboxAction(PreferencePopup popup, ArrayList<String> preferences) {
        this.popup = popup;
        this.prefArray = preferences;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox cb = ((JCheckBox) e.getSource());
        if (cb.isSelected()) {
            popup.addPreference(cb.getText(), prefArray);
        } else {
            popup.removePreference(cb.getText(), prefArray);
        }
    }
}
