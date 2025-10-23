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

public class Dashboard {
  protected JPanel panel;
  protected JPanel headerPanel;
  protected JPanel topPanel;
  protected JPanel tableHeader;
  protected JPanel tableBody;
  protected JScrollPane scrollPane;
  protected JButton logoutButton;

  public Dashboard() {
    panel = new JPanel(new BorderLayout(5, 5));
    panel.setBorder(new EmptyBorder(5, 5, 5, 5));

    headerPanel = new JPanel(new BorderLayout());
    topPanel = new JPanel(new BorderLayout(5, 5));
    topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

    tableHeader = new JPanel(new GridLayout(1, 4));
    tableBody = new JPanel();
    tableBody.setLayout(new BoxLayout(tableBody, BoxLayout.Y_AXIS));

    logoutButton = new JButton("LOGOUT");

    tableHeader.add(new JLabel("sl.no"));
    tableHeader.add(new JLabel("Subject"));
    tableHeader.add(new JLabel("Priority"));
    tableHeader.add(new JLabel("ID"));
    tableHeader.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

    scrollPane = new JScrollPane(tableBody);
    scrollPane.setPreferredSize(new Dimension(480, 300));

    headerPanel.add(topPanel, BorderLayout.NORTH);
    headerPanel.add(tableHeader, BorderLayout.SOUTH);

    panel.add(headerPanel, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);

    topPanel.add(logoutButton, BorderLayout.EAST);
  }

  /**
   * Provides access to the main panel of the dashboard.
   *
   * @return The main JPanel.
   */
  public JPanel getPanel() {
    return panel;
  }

  /**
   * Provides access to the table body panel where complaint rows are added.
   *
   * @return The JPanel used for the table body.
   */
  public JPanel getTableBody() {
    return tableBody;
  }

  /**
   * Provides access to the logout button.
   *
   * @return The logout JButton.
   */
  public JButton getLogoutButton() {
    return logoutButton;
  }

  /**
   * Provides access to the top panel for adding specific controls.
   *
   * @return The top JPanel.
   */
  public JPanel getTopPanel() {
    return topPanel;
  }
}
