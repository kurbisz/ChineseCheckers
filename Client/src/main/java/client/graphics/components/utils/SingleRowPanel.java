package client.graphics.components.utils;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class SingleRowPanel extends JPanel {

    private static double size = 0.02;

    private int counter = 0;
    private int rowNumber;

    private JFrame jFrame;

    private CirclePanel[] circlePanels;

    /**
     * Initialize new empty row of circles.
     * In the beginning set graphic settings to
     * make circles equally placed in each row.
     * @param frame actual JFrame of application
     * @param row number of this row panel
     * @param maxSize amount of circles in this row
     */
    public SingleRowPanel(JFrame frame, int row, int maxSize) {
        this.jFrame = frame;
        this.rowNumber = row;
        this.circlePanels = new CirclePanel[maxSize];
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        layout.setHgap((int) (jFrame.getWidth() * size));
        setLayout(layout);
    }

    /**
     * Adds a new circle in this row.
     */
    public void addCircle() {
        CirclePanel circlePanel = new CirclePanel(jFrame, rowNumber, counter);
        circlePanels[counter] = circlePanel;
        this.add(circlePanel);
        counter++;
    }

    /**
     * Executes setPlayer(int player) in CirclePanel.
     * having column nr 'column'
     * @param column column of a field
     * @param player nr of player to set in this field
     * @return nr of previous player from this field
     * @throws IndexOutOfBoundsException when column is not smaller
     * than size of circlePanels array (amount of circles)
     */
    public int setPlayer(int column, int player)
            throws IndexOutOfBoundsException {
        return circlePanels[column].setPlayer(player);
    }

}
