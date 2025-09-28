package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ComplaintUI {
    JPanel complaintUI;
    JPanel topPanel;
    JPanel centerPanel;
    JPanel idPanel;
    JPanel subjectPanel;
    JPanel bottomPanel;

    JButton backButton;

    JLabel idLabel;
    JLabel complaintLabel;
    JLabel timeLabel;
    JLabel complainerLabel;
    
    JTextArea subjectArea;
    JTextArea descriptionArea;

    JScrollPane descScroll;

    String subject;
    String complaint;
    String description;
    String complainer;

    /**
     * Show the complain details
     * 
     * @param ID the id of the complaint
     */
    public ComplaintUI(int ID) {
        subject = "sample subject";
        description = "Sample description, should be updated with backed to retrive from the database\n";
        description += "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        complainer = "Anonymous";

        backButton = new JButton("← BACK");

        idLabel = new JLabel("#" + ID);
        idLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        idPanel = new JPanel(new BorderLayout());
        idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.X_AXIS));
        idPanel.add(idLabel, BorderLayout.WEST);
        idPanel.add(Box.createHorizontalGlue());

        subjectArea = new JTextArea();
        subjectArea.setText("Subject: " + subject);

        if (UIUtils.username.equals("admin")) {
            subjectArea.setEditable(false);
        }

        subjectPanel = new JPanel();
        subjectPanel.setLayout(new BoxLayout(subjectPanel, BoxLayout.X_AXIS));
        subjectPanel.add(subjectArea, BorderLayout.WEST);
        subjectPanel.add(Box.createHorizontalGlue());

        descriptionArea = new JTextArea(5, 30);
        descriptionArea.setText(description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        if (UIUtils.username.equals("admin")) {
            descriptionArea.setEditable(false);
        }

        descScroll = new JScrollPane(descriptionArea);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        timeLabel = new JLabel();
        timeLabel.setText(LocalDateTime.now().format(formatter));
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        complainerLabel = new JLabel("by: " + complainer);
        complainerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        topPanel = new JPanel(new BorderLayout());
        topPanel.add(backButton, BorderLayout.EAST);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(timeLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        bottomPanel.add(complainerLabel);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(idPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(subjectPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(descScroll);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(bottomPanel);

        complaintUI = new JPanel();
        complaintUI.setLayout(new BorderLayout(10, 10));
        complaintUI.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        complaintUI.add(topPanel, BorderLayout.NORTH);
        complaintUI.add(centerPanel, BorderLayout.CENTER);
    }
}
