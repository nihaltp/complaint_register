package ui.helpers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.UIUtils;

public class RowHelper {
    private static int slNo = 1;

    /**
     * Adds a row to the table body
     * 
     * @param tableBody the body the rows are being added to
     * @param subject   the subject of the complaint
     * @param priority  the priority of the complaint
     * @param ID        the ID of the complaint
     */
    public static void addRow(JPanel tableBody, String subject, String priority, int ID) {
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

        row.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                UIUtils.showComplaint(ID);
            }
        });

        tableBody.add(row);
    }

    /**
     * Resets the slNo to 1.
     * This is used when the user switches after logout
     */
    public static void resetSlNo() {
        slNo = 1;
    }
}
