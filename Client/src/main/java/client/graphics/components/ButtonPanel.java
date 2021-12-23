package client.graphics.components;

import client.game.states.GameState;
import connection.Messenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends Panel implements ActionListener {

    JButton jButton;
    GameState gameState;

    /**
     * Responsible for button on the bottom of application.
     * @param frame actual jFrame of an application
     */
    public ButtonPanel(JFrame frame) {
        super(frame);
        this.gameState = GameState.WAITING_FOR_GAME;
    }

    /**
     * Creates new button with 'Start game' text.
     */
    @Override
    public void initialize() {

        this.setBounds((int) (0.35*jFrame.getWidth()), (int) (0.8*jFrame.getHeight()),
                (int) (0.3*jFrame.getWidth()), (int) (0.1*jFrame.getHeight()));

        jButton = new JButton("Start game");
        jButton.setPreferredSize(
                new Dimension((int) (0.8 * getWidth()), (int) (0.8 * getHeight())));
        jButton.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        updateButton();
        jButton.addActionListener(this);
        this.add(jButton);

    }

    /**
     * Sets another state of game and changes
     * button's visual and text settings.
     * @param state
     */
    public void setGameState(GameState state) {
        this.gameState = state;
        updateButton();
    }

    private void updateButton() {
        gameState.getStateButton().generate(jButton);
    }

    /**
     * Handles click on a button and executes
     * method click() in a proper StateButton.
     * @param e ActionEvent of this button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameState.getStateButton().click();
    }

}
