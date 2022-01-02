package client.graphics.listener.circle;

import connection.NoConnectionException;

import java.awt.event.MouseEvent;

public class SingleCircleListener extends CircleListener {


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
