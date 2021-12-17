package client.graphics.components;

import client.graphics.components.utils.SinglePlayerPanel;

import javax.swing.*;
import java.awt.*;

public class PlayersPanel extends Panel {

    JPanel playerCounter;
    JLabel playerCounterLabel;

    SinglePlayerPanel singlePlayerPanel[] = new SinglePlayerPanel[6];

    public PlayersPanel(JFrame frame) {
        super(frame);
    }

    @Override
    public void initialize() {

        playerCounter = new JPanel();
        playerCounterLabel = new JLabel("Players: 0/6");
        playerCounterLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        playerCounter.add(playerCounterLabel);
        playerCounter.setAlignmentX(0.1f);
        playerCounter.setBackground(Color.LIGHT_GRAY);
        this.add(playerCounter);

        for(int i = 0; i < 6; i++) {
            singlePlayerPanel[i] = new SinglePlayerPanel("Player nr " + (i+1), i);
            singlePlayerPanel[i].setVisible(false);
            this.add(singlePlayerPanel[i]);
        }

        GridLayout gridLayout = new GridLayout(0, 1);
        this.setLayout(gridLayout);

        this.setBounds((int) (0.75*jFrame.getWidth()), (int) (0.1*jFrame.getHeight()),
                (int) (0.15*jFrame.getWidth()), (int) (0.5*jFrame.getHeight()));
        //this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void updatePlayerAmount(int am) {
        playerCounterLabel.setText("Players: " + am + "/6");
        for(int i = 0; i < am; i++) {
            singlePlayerPanel[i].setVisible(true);
        }
        for(int i = am; i < 6; i++) {
            singlePlayerPanel[i].setVisible(false);
        }
    }




}
