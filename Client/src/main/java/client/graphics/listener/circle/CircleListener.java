package client.graphics.listener.circle;

import client.CheckersClient;
import client.graphics.GraphicsManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class CircleListener implements MouseListener {

    protected GraphicsManager graphicsManager;

    protected int row;
    protected int column;

    /**
     * Mouse listeners which are handled when clicked
     * on a single circle.
     * @param clickRow row of clicked field (circle)
     * @param clickColumn column of clicked field
     */
    public CircleListener(int clickRow, int clickColumn) {
        this.row = clickRow;
        this.column = clickColumn;
        this.graphicsManager = CheckersClient.
                getInstance().getGraphicsManager();
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

}
