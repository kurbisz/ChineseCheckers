package client.graphics.listener.circle;

import java.awt.event.MouseEvent;

public class EmptyCircleListener extends CircleListener {

    public EmptyCircleListener(int clickRow, int clickColumn) {
        super(clickRow, clickColumn);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Do nothing because this field is empty
    }

    /**
     * Set second field of this move
     * (circle where client wants to move his pawn).
     * @param e MouseEvent of this release
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        graphicsManager.setToPointClick(row, column);
    }

}
