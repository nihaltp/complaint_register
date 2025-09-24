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

    int slNo = 1;

    public UserDashboard(String username) {
        userNameLabel = new JLabel(username);

        logoutButton = new JButton("LOGOUT");

        addComplaint = new JButton();
        addComplaint.setText("Add a new Complaint");
        addComplaint.setPreferredSize(new Dimension(60, 20));
        addComplaint.setMaximumSize(new Dimension(60, 20));

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

    /**
     * Adds a row to the table body
     * 
     * @param subject  the subject of the complaint
     * @param priority  the priority of the complaint
     * @param ID  the ID of the complaint
     */
    public void addRow(String subject, String priority, int ID) {
        JPanel row = new JPanel(new GridLayout(1, 4));
        row.setPreferredSize(new Dimension(0, 40));
        row.setMinimumSize(new Dimension(0, 40));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        row.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
        row.add(new JLabel(Integer.toString(slNo++)));
        row.add(new JLabel(subject));
        row.add(new JLabel(priority));
        row.add(new JLabel("#" + Integer.toString(ID)));
        row.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        tableBody.add(row);
    }
}
