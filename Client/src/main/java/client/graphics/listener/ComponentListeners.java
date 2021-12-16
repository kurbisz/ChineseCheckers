package client.graphics.listener;

import client.graphics.GraphicsManager;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ComponentListeners extends ComponentAdapter {

    GraphicsManager graphicsManager;

    public ComponentListeners(GraphicsManager graphics) {
        this.graphicsManager = graphics;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        //graphicsManager.repaintBoard();
    }
}
