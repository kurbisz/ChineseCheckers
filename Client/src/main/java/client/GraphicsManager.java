package client;

import client.listener.MainListeners;
import client.menu.ConnectMenu;
import client.menu.Menu;

import javax.swing.*;

public class GraphicsManager {

    private String frameName = "Chinese Checkers";

    private JFrame jFrame;

    private Menu actualMenu;

    public void createNewWindow() {
        jFrame = new JFrame(frameName);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setResizable(true);
        jFrame.addWindowListener(new MainListeners());
        actualMenu = new ConnectMenu(jFrame);
        actualMenu.generate();
        jFrame.setVisible(true);
    }


}
