package client.graphics.listener.circle;

import connection.NoConnectionException;

import java.awt.event.MouseEvent;

public class SingleCircleListener extends CircleListener {

    /**
     * Simple mouse listeners which are
     * handled when clicked on a single circle.
     * @param clickRow row of clicked field (circle)
     * @param clickColumn column of clicked field
     */
    public SingleCircleListener(int clickRow, int clickColumn) {
        super(clickRow, clickColumn);
    }

    /**
     * Set 1st or 2nd field of this move
     * (circle from where client wants to move his pawn).
     * @param e MouseEvent of this press
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            graphicsManager.setPointClick(row, column);
        } catch (NoConnectionException exc) { }
    }


}
