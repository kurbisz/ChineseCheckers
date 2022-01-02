package client.graphics.components.buttons;

import connection.Messenger;
import connection.NoConnectionException;

import javax.swing.*;
import java.awt.*;

public class PlayingButton extends StateButton {

    @Override
    public void generate(JButton jButton) {
        jButton.setText("End your move");
        jButton.setBackground(new Color(8, 8, 180));
        jButton.setForeground(Color.WHITE);
    }

    @Override
    public void click(Messenger messenger) throws NoConnectionException {
        messenger.pass();
    }

}
