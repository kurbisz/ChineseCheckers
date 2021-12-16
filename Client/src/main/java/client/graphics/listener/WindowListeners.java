package client.graphics.listener;

import client.graphics.GraphicsManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowListeners extends WindowAdapter {


    @Override
    public void windowClosing(WindowEvent e) {
        e.getWindow().dispose();
    }
}
