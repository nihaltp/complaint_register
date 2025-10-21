package ui.helpers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ui.UIUtils;

public class RowHelper {
  private static int slNo = 1;
  private static JPanel currentTableBody;

  /**
   * Adds a row to the table body
   *
   * @param tableBody the body the rows are being added to
   * @param subject the subject of the complaint
   * @param priority the priority of the complaint
   * @param ID the ID of the complaint
   */
  public static void addRow(JPanel tableBody, String subject, String priority, int ID) {
    currentTableBody = tableBody; // Store reference
    JPanel row = new JPanel(new GridLayout(1, 4));
    row.setName("row_" + ID); // Set a name
    row.setPreferredSize(new Dimension(0, 40));
    row.setMinimumSize(new Dimension(0, 40));
    row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
    row.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));

    row.add(new JLabel(Integer.toString(slNo++)));
    row.add(new JLabel(subject));

    JLabel priorityLabel = new JLabel(priority);
    priorityLabel.setName("priorityLabel");
    priorityLabel.setIcon(createCircleIcon(getPriorityColor(priority), 8));
    priorityLabel.setIconTextGap(8);
    row.add(priorityLabel);

    row.add(new JLabel("#" + Integer.toString(ID)));
    row.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.LIGHT_GRAY));

    row.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent event) {
            UIUtils.showComplaint(ID);
          }
        });

    tableBody.add(row);
  }

  /** Resets the slNo to 1. This is used when the user switches after logout */
  public static void resetSlNo() {
    slNo = 1;
    currentTableBody = null;
  }

  private static Color getPriorityColor(String priority) {
    if (priority == null) {
      priority = "new";
    }
    return switch (priority.toLowerCase()) {
      case "resolved" -> Color.GRAY;
      case "high" -> Color.RED;
      case "medium" -> Color.ORANGE;
      case "low" -> Color.GREEN;
      case "new" -> Color.BLUE;
      default -> Color.DARK_GRAY;
    };
  }

  private static Icon createCircleIcon(Color color, int size) {
    return new Icon() {
      @Override
      public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
      }

      @Override
      public int getIconWidth() {
        return size;
      }

      @Override
      public int getIconHeight() {
        return size;
      }
    };
  }

  /**
   * Updates the priority display of a specific row in the table body.
   *
   * @param ID the ID of the complaint row to update
   * @param priority the new priority string
   */
  public static void update(int ID, String priority) {
    if (currentTableBody == null) {
      System.err.println("RowHelper.update: tableBody reference is null.");
      return;
    }

    for (Component comp : currentTableBody.getComponents()) {
      if (comp instanceof JPanel) {
        JPanel row = (JPanel) comp;
        if (("row_" + ID).equals(row.getName())) {
          for (Component rowComp : row.getComponents()) {
            if (rowComp instanceof JLabel && "priorityLabel".equals(rowComp.getName())) {
              JLabel priorityLabel = (JLabel) rowComp;
              priorityLabel.setText(priority);
              priorityLabel.setIcon(createCircleIcon(getPriorityColor(priority), 8));
              break;
            }
          }
          break;
        }
      }
    }
  }
}
