package client.graphics.components;

import client.CheckersClient;
import connection.Messenger;
import connection.NoConnectionException;

import javax.swing.*;

public final class EndGameOptionPane {

    private EndGameOptionPane() { }

    /**
     * Show an option pane with a message to client.
     * Then it closes window.
     * @param jFrame actual jFrame of application
     * @param message message which has to be shown in this menu
     */
    public static void popup(final JFrame jFrame, final String message, boolean save) {
        JCheckBox saveGame = new JCheckBox("Save the game");
        Object[] panels = {
                message
        };
        if(save) {
            panels = new Object[]{message, saveGame};
        }
        if (CheckersClient.isHumanMode()) {
            JOptionPane.showMessageDialog(jFrame, panels);
        }
        if(saveGame.isSelected()) {
            try {
                Messenger.getInstance().save();
            } catch (NoConnectionException e) { }
        }
        jFrame.setSize(10, 10);
        jFrame.dispose();
        if (CheckersClient.isHumanMode()) {
            System.exit(0);
        }
    }

}
