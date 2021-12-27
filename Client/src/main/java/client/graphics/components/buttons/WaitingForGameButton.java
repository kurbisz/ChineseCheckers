package client.graphics.components.buttons;

import connection.Messenger;
import connection.NoConnectionException;

import javax.swing.*;
import java.awt.*;

public class WaitingForGameButton extends StateButton {

    @Override
    public void generate(JButton jButton) {
        jButton.setText("Start game");
        jButton.setBackground(Color.GREEN);
        jButton.setForeground(Color.DARK_GRAY);
    }

    @Override
    public void click(Messenger messenger) throws NoConnectionException  {
        messenger.start();
    }

}
