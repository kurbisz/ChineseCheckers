package client.graphics.listener.circle;

import java.awt.event.MouseEvent;

public class PlayerCircleListener extends CircleListener {

    public PlayerCircleListener(int clickRow, int clickColumn) {
        super(clickRow, clickColumn);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        graphicsManager.setFromPointClick(row, column);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do nothing because this field is not empty
    }
}