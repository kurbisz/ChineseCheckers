package client.graphics.components.buttons;

import connection.Messenger;
import connection.NoConnectionException;

import javax.swing.JButton;
import java.awt.Color;

public class PlayingButton extends GameStateButton {


    /**
     * (8, 8, 180) is new color of JButton in RGB.
     * @param jButton button which has to be changed
     */
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
