package client.graphics.components;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public abstract class Panel extends JPanel {

    protected JFrame jFrame;

    public Panel(JFrame frame) {
        this.jFrame = frame;
    }

    public abstract void initialize();

}
