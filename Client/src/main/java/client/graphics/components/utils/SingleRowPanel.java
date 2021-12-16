package client.graphics.components.utils;

import javax.swing.*;
import java.awt.*;

public class SingleRowPanel extends JPanel {

    private static double size = 0.02;

    int counter = 0;
    int rowNumber;

    JFrame jFrame;

    public SingleRowPanel(JFrame frame, int row) {
        this.jFrame = frame;
        this.rowNumber = row;
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        layout.setHgap((int) (jFrame.getWidth()*size));
        setLayout(layout);
    }

    public void addCircle() {
        this.add(new CirclePanel(jFrame, rowNumber, counter++));
    }

}
