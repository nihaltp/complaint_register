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
import javax.swing.JTextField;

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
    
    JTextField subjectField;
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
        // --- Your original variable declarations and hardcoded data ---
        subject = "sample subject";
        description = "Sample description, should be updated with backed to retrive from the database\n";
        description += "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        complainer = "Anonymous";
        
        // --- UI Component Initialization ---
        backButton = new JButton("← BACK");
        idLabel = new JLabel("#" + ID);
        
        // --- FIX: Use a JTextField for the single-line subject ---
        subjectField = new javax.swing.JTextField("Subject: " + subject);
        subjectField.setEditable(!UIUtils.username.equals("admin"));
        
        // The multi-line description area
        descriptionArea = new JTextArea(description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(!UIUtils.username.equals("admin"));

        descScroll = new JScrollPane(descriptionArea);
        
        timeLabel = new JLabel(java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
        complainerLabel = new JLabel("by: " + complainer);
    
        // --- NEW LAYOUT STRUCTURE ---
        
        // 1. Top panel for the back button
        topPanel = new JPanel(new BorderLayout());
        topPanel.add(backButton, BorderLayout.WEST);
    
        // 2. Header panel for ID and Subject
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(idLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        headerPanel.add(subjectField);
    
        // 3. Bottom panel for timestamp and complainer
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(timeLabel);
        bottomPanel.add(Box.createHorizontalGlue()); // Pushes complainer to the right
        bottomPanel.add(complainerLabel);
    
        // 4. Assemble the main panel using BorderLayout
        complaintUI = new JPanel(new BorderLayout(10, 10));
        complaintUI.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        complaintUI.add(topPanel, BorderLayout.NORTH);
        
        // The header goes in a new panel to group it
        JPanel centerContent = new JPanel(new BorderLayout(0, 10));
        centerContent.add(headerPanel, BorderLayout.NORTH);
        // The description scroll pane goes in the CENTER to make it expand
        centerContent.add(descScroll, BorderLayout.CENTER); 
        
        complaintUI.add(centerContent, BorderLayout.CENTER);
        complaintUI.add(bottomPanel, BorderLayout.SOUTH);
    }
}
