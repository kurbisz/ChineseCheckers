package client.graphics.components.utils;

import client.CheckersClient;
import client.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CirclePanel extends JPanel implements MouseListener {

    private static double sizeX = 0.3, sizeY = 0.4, scaleSizeX = 0.02, scaleSizeY = 0.03;

    private int rowNumber;
    private int columnNumber;

    private JFrame jFrame;

    private GraphicsManager graphicsManager;

    public CirclePanel(JFrame frame, int row, int column) {
        this.jFrame = frame;
        this.rowNumber = row;
        this.columnNumber = column;
        this.graphicsManager = CheckersClient.getInstance().getGraphicsManager();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (scaleSizeX*jFrame.getWidth()), (int) (scaleSizeY * jFrame.getHeight()));
        g.setColor(Color.BLACK);
        g.fillOval(0 , 0,
                (int) (getWidth() * sizeX) , (int) (getHeight() * sizeY));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
