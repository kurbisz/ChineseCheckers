package client.graphics.components;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public abstract class Panel extends JPanel {

    protected JFrame jFrame;

    /**
     * Creates new panel which extends JPanel.
     * @param frame actual jFrame of application
     */
    public Panel(JFrame frame) {
        this.jFrame = frame;
    }

    /**
     * Creates new panel and initialize all
     * needed variables and components.
     */
    public abstract void initialize();

}
