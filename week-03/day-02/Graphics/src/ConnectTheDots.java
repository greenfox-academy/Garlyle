import javax.swing.*;

import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ConnectTheDots {

  public static void mainDraw(Graphics graphics){
    // create a function that takes 1 parameter:
    // an array of {x, y} points
    // and connects them with green lines.
    // connect these to get a box: {{10, 10}, {290,  10}, {290, 290}, {10, 290}}
    // connect these: {{50, 100}, {70, 70}, {80, 90}, {90, 90}, {100, 70},
    // {120, 100}, {85, 130}, {50, 100}}

    int[][] input = {{10, 10}, {290,  10}, {290, 290}, {10, 290}};
    graphics.setColor(Color.red);
    drawConnections(graphics, input);

    int[][] input2 = {{50, 100}, {70, 70}, {80, 90}, {90, 90}, {100, 70}, {120, 100}, {85, 130}, {50, 100}};
    graphics.setColor(Color.green);
    drawConnections(graphics, input2);
  }

  private static void drawConnections(Graphics graphics, int[][] input) {
    int[] xpoints = new int[input.length];
    int[] ypoints = new int[input.length];
    int npoints = input.length;

    for (int i = 0; i < input.length; i++)
    {
      xpoints[i] = input[i][0];
      ypoints[i] = input[i][1];
    }

    graphics.fillPolygon(xpoints, ypoints, npoints);
  }

  //    Don't touch the code below
  public static void main(String[] args) {
    JFrame jFrame = new JFrame("Connect The Dots");
    jFrame.setSize(new Dimension(300, 300));
    jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    jFrame.add(new ImagePanel());
    jFrame.setLocationRelativeTo(null);
    jFrame.setVisible(true);
  }
  static class ImagePanel extends JPanel{
    @Override
    protected void paintComponent(Graphics graphics) {
      super.paintComponent(graphics);
      mainDraw(graphics);

    }
  }

}


// create a 300x300 canvas.