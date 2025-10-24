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
  private JPanel complaintUI;
  private JPanel topPanel;
  private JPanel bottomPanel;

  private JButton backButton;
  private JButton editButton;
  private JButton saveButton;
  private JButton deleteButton;
  private JButton anonymousButton;

  private JComboBox<String> priorityBox;

  private JLabel idLabel;
  private JLabel timeLabel;
  private JLabel complainerLabel;

  private JTextField subjectField;
  private JTextArea descriptionArea;

  private JScrollPane descScroll;

  private String complainer;

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
    anonymousButton = new JButton("Submit Anonymously"); // Add this

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

    if (Auth.isAdmin(UIUtils.getUsername())) {
      priorityBox = new JComboBox<>(new String[] {"new", "low", "medium", "high", "resolved"});
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
    if (!Auth.isAdmin(UIUtils.getUsername()) && UIUtils.getUsername().equals(complainer)) {
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


/**
 * Returns the main panel of the ComplaintUI class, which contains the
 * UI elements for displaying and editing a complaint.
 *
 * @return JPanel complaintUI
 */
  public JPanel getComplaintUI() {
    return complaintUI;
  }

/**
 * Returns the JButton for submitting the complaint anonymously.
 * @return JButton anonymousButton 
 */
  public JButton getAnonymousButton() {
    return anonymousButton;
  }

/**
 * Returns the JPanel containing the back, edit, save, and delete buttons.
 *
 * @return JPanel bottomPanel
 */
  public JPanel getBottomPanel() {
    return bottomPanel;
  }

/**
 * Returns the JButton for going back to the dashboard.
 * @return JButton backButton
 */
  public JButton getBackButton() {
    return backButton;
  }

/**
 * Returns the JButton for deleting the complaint.
 * @return JButton deleteButton
 */
  public JButton getDeleteButton() {
    return deleteButton;
  }

/**
 * Returns the JLabel that displays the time when the complaint was submitted.
 *
 * @return JLabel timeLabel
 */
  public JLabel getTimeLabel() {
    return timeLabel;
  }

/**
 * Returns the JLabel that displays the name of the user who submitted the complaint.
 *
 * @return JLabel complainerLabel
 */
  public JLabel getComplainerLabel() {
    return complainerLabel;
  }

/**
 * Returns the JTextField that contains the subject of the complaint.
 * This is used to edit the subject of the complaint.
 *
 * @return JTextField subjectField
 */
  public JTextField getSubjectField() {
    return subjectField;
  }

/**
 * Returns the JTextArea that contains the description of the complaint.
 * This is used to edit the description of the complaint.
 * @return JTextArea descriptionArea
 */
  public JTextArea getDescriptionArea() {
    return descriptionArea;
  }


/**
 * Sets the editability of the subject field.
 *
 * @param editable true if the subject field should be editable, false otherwise
 */
  public void subjectEditable(boolean editable) {
    subjectField.setEditable(editable);
  }

/**
 * Sets the editability of the description area.
 *
 * @param editable true if the description area should be editable, false otherwise
 */
  public void descriptionEditable(boolean editable) {
    descriptionArea.setEditable(editable);
  }

/**
 * Sets the visibility of the anonymous button.
 *
 * @param visible true if the anonymous button should be visible, false otherwise
 */
  public void anonymousButtonVisible(boolean visible) {
    anonymousButton.setVisible(visible);
  }
}
