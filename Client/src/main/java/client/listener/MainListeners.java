package client.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainListeners extends WindowAdapter {

    @Override
    public void windowClosed(WindowEvent e) {
        super.windowClosed(e);
        e.getWindow().dispose();
    }
}
