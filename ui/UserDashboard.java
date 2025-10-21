package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout; 
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserDashboard {
    public JPanel panel;
    public JPanel tableBody;
    public JButton logoutButton;

    private JPanel topLeftPanel; 
    private JLabel userNameLabel;
    private JButton addComplaintButton;

    private Dashboard dashboard; 

    public UserDashboard(String username) {
        dashboard = new Dashboard(); 

        
        this.panel = dashboard.getPanel();
        this.tableBody = dashboard.getTableBody();
        this.logoutButton = dashboard.getLogoutButton();

        
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

        
        dashboard.getTopPanel().add(topLeftPanel, BorderLayout.WEST);
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

    
    public JButton getAddComplaintButton() {
        return addComplaintButton;
    }
}
