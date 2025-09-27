package ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminDashboard {
    JPanel panel;
    JPanel header;
    JPanel topPanel;
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel contentPanel;
    JPanel tableHeader;
    JPanel tableBody;

    JScrollPane scrollPane;

    JLabel adminNameLabel;
    JLabel loginTimeLabel;

    JButton logoutButton;

    public AdminDashboard() {
        adminNameLabel = new JLabel("admin");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        loginTimeLabel = new JLabel();
        loginTimeLabel.setText(LocalDateTime.now().format(formatter));

        leftPanel = new JPanel(new FlowLayout());
        leftPanel.add(adminNameLabel);
        leftPanel.add(loginTimeLabel);

        logoutButton = new JButton("LOGOUT");

        rightPanel = new JPanel(new FlowLayout());
        rightPanel.add(logoutButton);

        topPanel = new JPanel(new BorderLayout());
        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);

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
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
    }
}
