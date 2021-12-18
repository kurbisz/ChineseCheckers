package client.graphics.components;

import javax.swing.*;

public class EndGameOptionPane {

    public static void popup(JFrame jFrame, String win) {
        JOptionPane.showMessageDialog(jFrame, win);
        jFrame.dispose();
    }

}
