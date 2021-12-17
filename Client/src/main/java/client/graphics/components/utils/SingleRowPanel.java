package client.graphics.components.utils;

import javax.swing.*;
import java.awt.*;

public class SingleRowPanel extends JPanel {

    private static double size = 0.02;

    private int counter = 0;
    private int rowNumber;

    private JFrame jFrame;

    private CirclePanel circlePanels[];

    public SingleRowPanel(JFrame frame, int row, int maxSize) {
        this.jFrame = frame;
        this.rowNumber = row;
        this.circlePanels = new CirclePanel[maxSize];
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        layout.setHgap((int) (jFrame.getWidth()*size));
        setLayout(layout);
    }

    public void addCircle() {
        CirclePanel circlePanel = new CirclePanel(jFrame, rowNumber, counter);
        circlePanels[counter] = circlePanel;
        this.add(circlePanel);
        counter++;
    }

    public int setPlayer(int column, int player) throws IndexOutOfBoundsException {
        return circlePanels[column].setPlayer(player);
    }

}
