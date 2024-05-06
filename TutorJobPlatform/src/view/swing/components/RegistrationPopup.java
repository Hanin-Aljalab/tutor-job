package view.swing.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationPopup extends JFrame implements ActionListener {
    private final int width = 700;
    private final int height = 500;
    GridBagConstraints gbc;

    private static final Insets WEST_INSETS = new Insets(5, 30, 5, 5);
    private static final Insets EAST_INSETS = new Insets(5, 5, 5, 30);

    public RegistrationPopup() {
        createFrame();
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.CENTER);
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createFrame() {
        setLayout(new BorderLayout());
        setSize(width, height);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setSize(500,300);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Ihre Daten"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        for (int i = 0; i < FieldTitle.values().length; i++) {
            FieldTitle fieldTitle = FieldTitle.values()[i];
            gbc = createGbc(0, i);
            panel.add(new JLabel(fieldTitle.getTitle() + ":", JLabel.LEFT),
                    gbc);
            gbc = createGbc(1, i);
            JTextField textField = new JTextField(8);
            panel.add(textField, gbc);
        }
        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(width,(height/4)));

        GridBagConstraints gbc = new GridBagConstraints();

        JButton confirmButton = new JButton("Registrieren");
        JButton cancelButton = new JButton("Abbrechen");
        cancelButton.addActionListener(this);
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(confirmButton);
        panel.add(cancelButton);
        return panel;
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
        gbc.fill = (x == 0) ? GridBagConstraints.BOTH
                : GridBagConstraints.HORIZONTAL;

        gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
        gbc.weightx = (x == 0) ? 0.1 : 1.0;
        gbc.weighty = 1.0;
        return gbc;
    }
    private void createTextfield(String fieldname) {
        JLabel label = new JLabel(fieldname + ":    ");
        add(label, gbc);
        gbc.gridx++;

        JTextField field = new JTextField(fieldname);
        add(field, gbc);
        gbc.gridx--;
        gbc.gridy++;
    }

    enum FieldTitle {
        SURNAME("Name"), NAME("Vorname"), STUD_NUMBER("Matrikelnummer"),
        EMAIL("E-Mail"), PASSWORD("Passwort"), REPEAT_PW("Passwort wiederholen");
        private String title;

        private FieldTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    };

    @Override
    public Insets getInsets() {
        return new Insets(50,10,10,10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }

    public static void main(String[] args) {
        new RegistrationPopup();
    }
}
