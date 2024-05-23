package view.swing.components.homescreens;

import controller.App;
import model.*;
import view.swing.components.LoginWindow;

import javax.swing.*;
import java.awt.*;


public abstract class Homescreen extends JFrame {
    protected String[] infoText;
    private Color topColor;
    protected User user;
    protected JLabel name, status;
    protected JPanel top, bottom, leftPanel, rightPanel, choicePanel;
    protected JButton button;
    protected GridBagConstraints constraints;

    private final int WIDTH = 700;
    private final int HEIGHT = 400;
    /**
     * Home constructor
     *
     * @param user
     */
    public Homescreen(User user, Color color) {
        this.user = user;
        this.infoText = setInfoText();
        this.topColor = color;

        setTitle("Tutor Job System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        configureTopPanel();
        configureBottomPanel();

        setVisible(true);

        if(!App.isMatchingDone()) {
            button = configureButton();
            choicePanel.add(button);
        }
    }

// Methods which have to be defined in child classes
    /**
     * Displays the button relevant for the user group
     * @return version of button as needed
     */
    abstract protected JButton configureButton();

    /**
     * Displays status, e.g. in student "preferences not yet chosen vs.
     * already chosen"
     * @param status JLabel which represents status
     */
    abstract protected void adaptStatus(JLabel status);
    abstract protected void configureBottomRightPanel();
    abstract protected String[] getGeneralInfo();
    abstract protected String[] getMatchInfo();

    /**
     * configure the top panel
     */
    private void configureTopPanel() {
        top = new JPanel(new GridBagLayout());
        top.setBackground(topColor);
        add(top, BorderLayout.NORTH);

        constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); //abstand von anderen El.
        constraints.weightx = 1;

        name = new JLabel("Name: " + user.getTitle() + " "
                        + user.getFirstName() + " " + user.getLastName());
        constraints.gridx = 0;
        constraints.gridy = 0;
        top.add(name, constraints);

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
    protected void configureBottomLeftPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 0;

        JPanel centerPanelLeft = new JPanel(new GridBagLayout());
        GridBagConstraints topConstraints = new GridBagConstraints();
        topConstraints.gridx = 0;
        topConstraints.gridy = 0;
        topConstraints.weightx = 1.0;
        topConstraints.weighty = 0;
        topConstraints.fill = GridBagConstraints.BOTH;
        topConstraints.anchor = GridBagConstraints.CENTER;

        setWelcomeText(centerPanelLeft, topConstraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.3;
        leftPanel.add(centerPanelLeft, constraints);

        choicePanel = new JPanel(new GridBagLayout());
        GridBagConstraints bottomConstraints = new GridBagConstraints();
        bottomConstraints.insets = new Insets(5, 5, 5, 5);
        bottomConstraints.fill = GridBagConstraints.HORIZONTAL;
        bottomConstraints.anchor = GridBagConstraints.SOUTH;

        if (!App.isMatchingDone()) {
            status = new JLabel("", JLabel.CENTER);
            adaptStatus(status);
            bottomConstraints.gridx = 0;
            bottomConstraints.gridy = 1;
            bottomConstraints.gridwidth = 1;
            choicePanel.add(status, bottomConstraints);
        }
        constraints.gridy = 1;
        constraints.weighty = 0.05; //config height of panel
        leftPanel.add(choicePanel, constraints);
    }

    private void setWelcomeText(JPanel panel, GridBagConstraints gbc) {
        for (String line : infoText) {
            JLabel infoLabel = new JLabel(line,
                    JLabel.CENTER);
            infoLabel.setForeground(Color.DARK_GRAY);
            panel.add(infoLabel, gbc);
            gbc.gridy ++;
        }
    }

    /**
     * Determines which welcome text is shown in the center of the homescreen
     * @return array of strings which should be displayed
     */
    private String[] setInfoText() {
        if (App.isMatchingDone()) {
            return getMatchInfo();
        } else {
            return InfoText.teacherGeneral;
        }
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
                new LoginWindow();
            }
        });
        return logout;
    }
}


