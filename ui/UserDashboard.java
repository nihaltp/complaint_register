package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class UserDashboard {
  JPanel panel;
  JPanel topPanel;
  JPanel centerPanel;
  JPanel tableHeader;
  JPanel tableBody;
  JPanel header;

  JScrollPane scrollPane;

  JLabel userNameLabel;

  JButton logoutButton;
  JButton addComplaint;

  public UserDashboard(String username) {
    userNameLabel = new JLabel(username);

    logoutButton = new JButton("LOGOUT");

    addComplaint = new JButton();
    addComplaint.setText("Add a new Complaint");
    addComplaint.setPreferredSize(new Dimension(60, 20));
    addComplaint.setMaximumSize(new Dimension(60, 20));

    addComplaint.addActionListener(
        e -> {
          UIUtils.addComplaint();
        });

    topPanel = new JPanel(new BorderLayout());
    topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    topPanel.add(userNameLabel, BorderLayout.WEST);
    topPanel.add(addComplaint);
    topPanel.add(logoutButton, BorderLayout.EAST);

    tableHeader = new JPanel(new GridLayout(1, 4));
    tableHeader.add(new JLabel("sl.no"));
    tableHeader.add(new JLabel("Subject"));
    tableHeader.add(new JLabel("Priority"));
    tableHeader.add(new JLabel("ID"));
    tableHeader.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

    tableBody = new JPanel();
    tableBody.setLayout(new BoxLayout(tableBody, BoxLayout.Y_AXIS));

    scrollPane = new JScrollPane(tableBody);
    scrollPane.setPreferredSize(new Dimension(480, 300));

    header = new JPanel();
    header.setLayout(new BorderLayout());
    header.add(topPanel, BorderLayout.NORTH);
    header.add(tableHeader, BorderLayout.SOUTH);

    panel = new JPanel();
    panel.setBorder(new EmptyBorder(5, 5, 5, 5));
    panel.setLayout(new BorderLayout());
    panel.add(header, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.SOUTH);
  }
}
