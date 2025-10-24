package ui;

import java.awt.FlowLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class AdminDashboard extends Dashboard {
  private JPanel leftPanel;
  private JLabel adminNameLabel;
  private JLabel loginTimeLabel;

  public AdminDashboard() {
    super();

    adminNameLabel = new JLabel("admin");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    loginTimeLabel = new JLabel();
    loginTimeLabel.setText(LocalDateTime.now().format(formatter));

    leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    leftPanel.add(adminNameLabel);
    leftPanel.add(loginTimeLabel);

    topPanel.add(leftPanel, BorderLayout.WEST);
  }
}
