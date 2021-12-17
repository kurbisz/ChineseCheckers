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

    @Override
    public void mouseReleased(MouseEvent e) {
        graphicsManager.setToPointClick(row, column);
    }

}
