package client.graphics.components.buttons;

import connection.Messenger;
import connection.NoConnectionException;

import javax.swing.*;
import java.awt.*;

public class WaitingForMoveButton extends StateButton {


    @Override
    public void generate(JButton jButton) {
        jButton.setText("Opponent's turn");
        jButton.setBackground(Color.LIGHT_GRAY);
        jButton.setForeground(Color.BLACK);
    }

    @Override
    public void click(Messenger messenger) throws NoConnectionException  {
        messenger.pass();
    }

}
