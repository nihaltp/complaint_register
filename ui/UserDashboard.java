package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserDashboard extends Dashboard {
  private JPanel topLeftPanel;
  private JLabel userNameLabel;
  private JButton addComplaintButton;

  public UserDashboard(String username) {
    super();

    userNameLabel = new JLabel(username);

    addComplaintButton = new JButton("Add a new Complaint");

    addComplaintButton.setPreferredSize(new Dimension(180, 25));
    addComplaintButton.setMaximumSize(new Dimension(180, 25));

    addComplaintButton.addActionListener(
        e -> {
          UIUtils.addComplaint();
        });

    topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topLeftPanel.add(userNameLabel);
    topLeftPanel.add(addComplaintButton);

    topPanel.add(topLeftPanel, BorderLayout.WEST);
  }

  public JButton getAddComplaintButton() {
    return addComplaintButton;
  }
}
