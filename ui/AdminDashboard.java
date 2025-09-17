package ui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard {
    JFrame frame;

    public AdminDashboard() {
        frame = new JFrame();
        frame.setTitle("Complaint Register");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        frame.setVisible(true);
        frame.setResizable(false);
    }
}
