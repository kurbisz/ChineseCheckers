package client.graphics.components;

import connection.Messenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends Panel implements ActionListener {

    JButton jButton;

    public ButtonPanel(JFrame frame) {
        super(frame);
    }

    @Override
    public void initialize() {

        this.setBounds((int) (0.35*jFrame.getWidth()), (int) (0.75*jFrame.getHeight()),
                (int) (0.3*jFrame.getWidth()), (int) (0.1*jFrame.getHeight()));

        jButton = new JButton("Start game");
        jButton.setBackground(Color.GREEN);
        jButton.setForeground(Color.DARK_GRAY);
        jButton.setPreferredSize(
                new Dimension((int) (0.8 * getWidth()), (int) (0.8 * getHeight())));
        jButton.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        jButton.addActionListener(this);
        this.add(jButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Messenger.getInstance().start();
    }
}
