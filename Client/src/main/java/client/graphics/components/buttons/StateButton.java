package client.graphics.components.buttons;

import javax.swing.*;

public abstract class StateButton {

    /**
     * Set actual appearance of button.
     * @param jButton button which has to be changed
     */
    public abstract void generate(JButton jButton);

    /**
     * Do some stuff when button in this
     * state was clicked.
     */
    public abstract void click();

}
