package client.graphics.listener.circle;

import java.awt.event.MouseEvent;

public class PlayerCircleListener extends CircleListener {

    public PlayerCircleListener(int clickRow, int clickColumn) {
        super(clickRow, clickColumn);
    }

    /**
     * Set first field of this move
     * (circle from where client wants to move his pawn).
     * @param e MouseEvent of this press
     */
    @Override
    public void mousePressed(MouseEvent e) {
        graphicsManager.setFromPointClick(row, column);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do nothing because this field is not empty
    }
}
