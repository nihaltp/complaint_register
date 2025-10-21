package ui;

import auth.Auth;
import data.Retrieve;
import data.Store;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ComplaintUI {
  JPanel complaintUI;
  JPanel topPanel;
  JPanel bottomPanel;

  JButton backButton;
  JButton editButton;
  JButton saveButton;
  JButton deleteButton; // Add this

  JComboBox<String> priorityBox;

  JLabel idLabel;
  JLabel timeLabel;
  JLabel complainerLabel;

  JTextField subjectField;
  JTextArea descriptionArea;

  JScrollPane descScroll;

  String complainer;

  public ComplaintUI(int ID) {
    String priority, subject, description;
    try {
      String[] data = Retrieve.getComplaint(ID).toArray(new String[0]);
      priority = data.length > 0 ? data[0] : "new";
      subject = data.length > 1 ? data[1] : "Error retrieving complaint";
      description = data.length > 2 ? data[2] : "Error retrieving complaint details.";
      complainer = data.length > 3 ? data[3] : "Error retrieving complainer";
    } catch (Exception e) {
      priority = "new";
      subject = "Error";
      description = "Error";
      complainer = "Unknown";
      System.err.println("An error occurred while retrieving the complaint details: " + e);
    }

    // --- UI Component Initialization ---
    backButton = new JButton("← BACK");
    editButton = new JButton("Edit");
    saveButton = new JButton("Save");
    deleteButton = new JButton("Delete");
    deleteButton.setForeground(Color.RED);

    idLabel = new JLabel("#" + ID);

    subjectField = new JTextField("Subject: " + subject);
    subjectField.setEditable(false);

    descriptionArea = new JTextArea(description);
    descriptionArea.setLineWrap(true);
    descriptionArea.setWrapStyleWord(true);
    descriptionArea.setEditable(false);

    descScroll = new JScrollPane(descriptionArea);

    timeLabel =
        new JLabel(
            java.time.LocalTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
    complainerLabel = new JLabel("by: " + complainer);

    topPanel = new JPanel(new BorderLayout());
    topPanel.add(backButton, BorderLayout.WEST);

    if (Auth.isAdmin(UIUtils.username)) {
      priorityBox =
          new JComboBox<>(new String[] {"new", "low", "medium", "high", "resolved"});
      priorityBox.setSelectedItem(priority);
      priorityBox.addActionListener(
          e -> {
            Store.updatePriority(ID, (String) priorityBox.getSelectedItem());
          });
      topPanel.add(priorityBox, BorderLayout.EAST);
    }

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
    bottomPanel.add(Box.createHorizontalGlue());
    bottomPanel.add(complainerLabel);

    // Add Edit and Save buttons for the user who created the complaint
    if (!Auth.isAdmin(UIUtils.username) && UIUtils.username.equals(complainer)) {
      bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
      bottomPanel.add(editButton);
      bottomPanel.add(saveButton);
      bottomPanel.add(deleteButton); 
      saveButton.setVisible(false);

      editButton.addActionListener(
          e -> {
            subjectField.setEditable(true);
            descriptionArea.setEditable(true);
            editButton.setVisible(false);
            saveButton.setVisible(true);
          });

      saveButton.addActionListener(
          e -> {
            Store.updateComplaint(ID, subjectField.getText(), descriptionArea.getText());
            subjectField.setEditable(false);
            descriptionArea.setEditable(false);
            editButton.setVisible(true);
            saveButton.setVisible(false);
          });
    }

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
