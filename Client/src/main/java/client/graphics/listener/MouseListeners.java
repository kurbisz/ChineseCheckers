package client.graphics.listener;

import client.graphics.GraphicsManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListeners extends MouseAdapter {

    GraphicsManager graphicsManager;

    public MouseListeners(GraphicsManager graphics) {
        this.graphicsManager = graphics;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        graphicsManager.setFromPointClick(-1, -1);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        graphicsManager.setToPointClick(-1, -1);
    }
}
