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

    public ButtonPanel(JFrame frame) {
        super(frame);
        this.gameState = GameState.WAITING_FOR_GAME;
    }

    @Override
    public void initialize() {

        this.setBounds((int) (0.35*jFrame.getWidth()), (int) (0.75*jFrame.getHeight()),
                (int) (0.3*jFrame.getWidth()), (int) (0.1*jFrame.getHeight()));

        jButton = new JButton("Start game");
        jButton.setPreferredSize(
                new Dimension((int) (0.8 * getWidth()), (int) (0.8 * getHeight())));
        jButton.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        updateButton();
        jButton.addActionListener(this);
        this.add(jButton);

    }

    public void setGameState(GameState state) {
        this.gameState = state;
        updateButton();
    }

    private void updateButton() {
        // TODO use patterns and move it to different classes
        if(gameState.equals(GameState.WAITING_FOR_GAME)) {
            jButton.setText("Start game");
            jButton.setBackground(Color.GREEN);
            jButton.setForeground(Color.DARK_GRAY);
        }
        else if(gameState.equals(GameState.PLAYING)) {
            jButton.setText("End your move");
            jButton.setBackground(Color.GRAY);
            jButton.setForeground(Color.BLUE);
        }
        else if(gameState.equals(GameState.WAITING_FOR_MOVE)) {
            jButton.setText("Opponent's turn");
            jButton.setBackground(Color.LIGHT_GRAY);
            jButton.setForeground(Color.BLACK);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameState.equals(GameState.WAITING_FOR_GAME)) {
            Messenger.getInstance().start();
        }
    }

}
