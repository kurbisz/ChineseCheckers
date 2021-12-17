package client.graphics.listener.circle;

import client.CheckersClient;
import client.graphics.GraphicsManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class CircleListener implements MouseListener {

    protected GraphicsManager graphicsManager;

    protected int row, column;

    public CircleListener(int clickRow, int clickColumn) {
        this.row = clickRow;
        this.column = clickColumn;
        this.graphicsManager = CheckersClient.getInstance().getGraphicsManager();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
