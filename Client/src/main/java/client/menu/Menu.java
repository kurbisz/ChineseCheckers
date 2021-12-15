package client.menu;

import javax.swing.*;

public abstract class Menu {

    protected JFrame jFrame;

    public Menu(JFrame frame) {
        this.jFrame = frame;
    }

    public abstract void generate();

    public abstract void clear();

}
