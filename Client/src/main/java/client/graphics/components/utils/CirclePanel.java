package client.graphics.components.utils;

import client.CheckersClient;
import client.graphics.GraphicsManager;
import client.graphics.listener.circle.CircleListener;
import client.graphics.listener.circle.EmptyCircleListener;
import client.graphics.listener.circle.PlayerCircleListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CirclePanel extends JPanel {

    private static double size = 0.3, playerSize = 0.6, scaleSizeX = 0.02, scaleSizeY = 0.03;

    private int rowNumber;
    private int columnNumber;
    private int playerNr = -1;

    private JFrame jFrame;

    private CircleListener circleListener;

    public CirclePanel(JFrame frame, int row, int column) {
        this.jFrame = frame;
        this.rowNumber = row;
        this.columnNumber = column;
        circleListener = new EmptyCircleListener(rowNumber, columnNumber);
        this.addMouseListener(circleListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (scaleSizeX*jFrame.getWidth()), (int) (scaleSizeY * jFrame.getHeight()));
        if(playerNr < 0) {
            g.setColor(Color.BLACK);
            int radius = (int) (getWidth() * size);
            g.fillOval(0, 0, radius, radius);
        }
        else {
            g.setColor(GraphicsManager.playerColors[playerNr]);
            int radius = (int) (getWidth() * playerSize);
            g.fillOval(0, 0, radius, radius);
            g.fillOval(0, 0, radius, radius);
        }
    }

    public int setPlayer(int player) {
        int copy = playerNr;
        this.playerNr = player;
        this.repaint();
        this.refreshListener();
        return copy;
    }

    private void refreshListener() {
        this.removeMouseListener(circleListener);
        if(playerNr<0) {
            circleListener = new EmptyCircleListener(rowNumber, columnNumber);
        }
        else {
            circleListener = new PlayerCircleListener(rowNumber, columnNumber);
        }
        this.addMouseListener(circleListener);
    }

}
