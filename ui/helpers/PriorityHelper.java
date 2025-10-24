package ui.helpers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

public class PriorityHelper {
  /**
   * Returns a color corresponding to the given priority.
   *
   * @param priority the priority to get the color for
   * @return a color corresponding to the given priority
   */
  public static Color getPriorityColor(String priority) {
    if (priority == null) {
      priority = "new";
    }
    return switch (priority.toLowerCase()) {
      case "resolved" -> Color.GRAY;
      case "high" -> Color.RED;
      case "medium" -> Color.ORANGE;
      case "low" -> Color.GREEN;
      case "new" -> Color.BLUE;
      default -> Color.BLACK;
    };
  }

  /**
   * Creates a circle icon of the given color and size.
   *
   * @param color the color of the icon
   * @param size the size of the icon
   * @return a circle icon of the given color and size
   */
  public static Icon createCircleIcon(Color color, int size) {
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
}
