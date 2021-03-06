package client.graphics.components.utils;

import client.CheckersClient;
import client.ClientType;
import client.graphics.GraphicsManager;
import client.graphics.listener.circle.CircleListener;
import client.graphics.listener.circle.SingleCircleListener;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;

public class CirclePanel extends JPanel {

    /**
     * These static variables define size of a single circle
     * comparing to size of a full window.
     */
    private static double size = 0.3;
    private static double playerSize = 0.6;
    private static double scaleSizeX = 0.02;
    private static double scaleSizeY = 0.03;

    private int rowNumber;
    private int columnNumber;
    private int playerNr = -1;

    private JFrame jFrame;

    private CircleListener circleListener;

    /**
     * Initialize new circle with proper row and column.
     * On the beginning create mouse listener and register it.
     * @param frame actual JFrame of application
     * @param row number of field's row
     * @param column number of field's column
     */
    public CirclePanel(JFrame frame, int row, int column) {
        this.jFrame = frame;
        this.rowNumber = row;
        this.columnNumber = column;
        if (CheckersClient.getInstance().getClientType().equals(ClientType.PLAYER)) {
            circleListener = new SingleCircleListener(rowNumber, columnNumber);
            this.addMouseListener(circleListener);
        }
    }

    /**
     * Draw small black circle on empty field
     * or big painted circle on player's field.
     * @param g graphics of this component
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (scaleSizeX * jFrame.getWidth()),
                (int) (scaleSizeY * jFrame.getHeight()));
        if (playerNr < 0) {
            g.setColor(Color.BLACK);
            int radius = (int) (getWidth() * size);
            g.fillOval(radius / 2, radius / 2, radius, radius);
        } else {
            g.setColor(GraphicsManager.playerColors[playerNr]);
            int radius = (int) (getWidth() * playerSize);
            g.fillOval(0, 0, radius, radius);
            g.fillOval(0, 0, radius, radius);
        }
    }

    /**
     * Change its owner (player on this field).
     * @param player id of new player, -1 if it has to be empty
     * @return id of previous player
     */
    public int setPlayer(int player) {
        int copy = playerNr;
        this.playerNr = player;
        this.repaint();
        return copy;
    }


}
