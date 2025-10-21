package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminDashboard {
    
    public JPanel panel;
    public JPanel tableBody;
    public JButton logoutButton;

    
    private JPanel leftPanel;
    private JLabel adminNameLabel;
    private JLabel loginTimeLabel;

    private Dashboard dashboard; 

    public AdminDashboard() {
        dashboard = new Dashboard(); 

        
        this.panel = dashboard.getPanel();
        this.tableBody = dashboard.getTableBody();
        this.logoutButton = dashboard.getLogoutButton();

        
        adminNameLabel = new JLabel("admin");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        loginTimeLabel = new JLabel();
        loginTimeLabel.setText(LocalDateTime.now().format(formatter));

        
        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        leftPanel.add(adminNameLabel);
        leftPanel.add(loginTimeLabel);

        
        dashboard.getTopPanel().add(leftPanel, BorderLayout.WEST);
    }

    
    public JPanel getPanel() {
        return panel;
    }

    public JPanel getTableBody() {
        return tableBody;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}
