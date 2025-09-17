package ui;

import javax.swing.*;
import java.awt.*;

public class UserDashboard {
    JFrame frame;
    JButton addComplaint;
    JButton checkComplaints;

    public UserDashboard() {
        frame = new JFrame();
        frame.setTitle("Complaint Register");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        addComplaint = new JButton();
        addComplaint.setText("Add a new Complaint");

        checkComplaints = new JButton();
        checkComplaints.setText("Check complaints");

        frame.add(addComplaint);
        frame.add(checkComplaints);

        frame.setVisible(true);
        frame.setResizable(false);
    }
}
