package client.graphics.components;

import javax.swing.*;

public class EndGameOptionPane {

    /**
     * Show an option pane with a message to client.
     * Then it closes window.
     * @param jFrame actual jFrame of application
     * @param message message which has to be shown in this menu
     */
    public static void popup(JFrame jFrame, String message) {
        JOptionPane.showMessageDialog(jFrame, message);
        jFrame.dispose();
    }

}
