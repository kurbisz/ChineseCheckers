package client.graphics.components;

import client.graphics.components.utils.SinglePlayerPanel;

import javax.swing.*;
import java.awt.*;

public class PlayersPanel extends Panel {

    private JPanel playerCounter;
    private JLabel playerCounterLabel;

    private int clientNumber = 0;

    private SinglePlayerPanel singlePlayerPanel[] = new SinglePlayerPanel[6];

    /**
     * Responsible for whole player's panel
     * on the right part of application.
     * @param frame actual jFrame of application
     */
    public PlayersPanel(JFrame frame) {
        super(frame);
    }

    /**
     * Create text 'Player: 0/6' and generate
     * invisible SinglePlayerPanel's under it.
     * Sets visual properties of this panel.
     */
    @Override
    public void initialize() {

        playerCounter = new JPanel();
        playerCounter.setLayout(new GridBagLayout());
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

    /**
     * Change actual amount of players.
     * Preferred to use updatePlayers() with nicknames.
     * @param am new amount of players
     */
    @Deprecated
    public void updatePlayerAmount(int am) {
        playerCounterLabel.setText("Players: " + am + "/6");
        for(int i = 0; i < am; i++) {
            singlePlayerPanel[i].setVisible(true);
        }
        for(int i = am; i < 6; i++) {
            singlePlayerPanel[i].setVisible(false);
        }
    }

    /**
     * Changes actual players and update each
     * of their nickname and place on list.
     * @param players array with player's nicknames (as strings)
     */
    public void updatePlayers(String[] players) {
        int am = players.length;
        playerCounterLabel.setText("Players: " + am + "/6");
        for(int i = 0; i < am; i++) {
            singlePlayerPanel[i].setNickName(players[i]);
            singlePlayerPanel[i].setVisible(true);
        }
        for(int i = am; i < 6; i++) {
            singlePlayerPanel[i].setVisible(false);
        }
    }

    /**
     * Sets one of the players as actually playing
     * and unsets the rest of players (from list)
     * @param player id of player which is making move
     */
    public void setActualPlayer(int player) {
        for(int i = 0; i < 6; i++) {
            if(i == player) {
                continue;
            }
            singlePlayerPanel[i].setActualPlaying(false);
        }
        singlePlayerPanel[player].setActualPlaying(true);
    }


    /**
     * Mark out the SinglePlayerPanel which represents this
     * client and set normal previous SinglePlayerPanel
     * with this property.
     * @param player id of new player which shows this client
     */
    public void setClientNumber(int player) {
        if (clientNumber >= 0) {
            singlePlayerPanel[clientNumber].setClient(false);
        }
        clientNumber = player;
        if(player>=0) {
            singlePlayerPanel[clientNumber].setClient(true);
        }
    }

    /**
     * Getter of private variable singlePlayerPanel.
     * @return array with 6 singlePlayerPanels
     */
    public SinglePlayerPanel[] getSinglePlayerPanel() {
        return singlePlayerPanel;
    }
}
