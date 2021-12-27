package client.graphics.listener;

import client.CheckersClient;
import client.graphics.GraphicsManager;
import connection.NoConnectionException;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowListeners extends WindowAdapter {


    /**
     * Execute onWindowClose from CheckersClient
     * and close whole window.
     * @param e WindowEvent of closing window
     */
    @Override
    public void windowClosing(WindowEvent e) {
        try {
            CheckersClient.getInstance().onWindowClose();
        } catch (NoConnectionException exc) { }
        e.getWindow().dispose();
    }
}
