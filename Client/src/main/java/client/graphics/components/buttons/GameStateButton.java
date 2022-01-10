package client.graphics.components.buttons;

import connection.Messenger;
import connection.NoConnectionException;

import javax.swing.JButton;

public abstract class GameStateButton {

    /**
     * Set actual appearance of button.
     * @param jButton button which has to be changed
     */
    public abstract void generate(JButton jButton);

    /**
     * Do some stuff when button in this
     * state was clicked.
     * @param messenger messenger which will trigger
     *                  something on server
     * @throws NoConnectionException when messenger is not connected
     */
    public abstract void click(Messenger messenger)
            throws NoConnectionException;

}
