package client.graphics.listener;

import client.graphics.GraphicsManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListeners extends MouseAdapter {

    GraphicsManager graphicsManager;

    /**
     * Mouse listeners of whole jFrame.
     * @param graphics
     */
    public MouseListeners(GraphicsManager graphics) {
        this.graphicsManager = graphics;
    }

    /**
     * Resets first field of click on circle.
     * @param e mouse event of this press
     */
    @Override
    public void mousePressed(MouseEvent e) {
        graphicsManager.setFromPointClick(-1, -1);
    }

    /**
     * Resets second field of click on circle.
     * @param e mouse event of this release
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        graphicsManager.setToPointClick(-1, -1);
    }
}
