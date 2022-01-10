package client.graphics.components;

import javax.swing.JPanel;
import javax.swing.JFrame;

public abstract class Panel extends JPanel {

    protected JFrame jFrame;

    /**
     * Creates new panel which extends JPanel.
     * @param frame actual jFrame of application
     */
    public Panel(final JFrame frame) {
        this.jFrame = frame;
    }

    /**
     * Creates new panel and initialize all
     * needed variables and components.
     */
    public abstract void initialize();

}
