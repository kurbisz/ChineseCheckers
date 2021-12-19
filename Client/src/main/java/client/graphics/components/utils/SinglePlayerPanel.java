package client.graphics.components.utils;

import client.graphics.GraphicsManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class SinglePlayerPanel extends JPanel {

    private final static int smallFontSize = 24, bigFontSize = 32;

    private String nickName;
    private int playerNr;
    private JLabel playerLabel;

    /**
     * Initialize new rectangle with player's nickname.
     * On the beginning set its graphic settings.
     * @param name player's nickname
     * @param nr number of this single player panel
     */
    public SinglePlayerPanel(String name, int nr) {
        super();
        this.playerNr = nr;
        this.nickName = name;
        this.setBackground(Color.GRAY);
        init();
    }

    private void init() {
        playerLabel = new JLabel(nickName);
        playerLabel.setFont(new Font(Font.SERIF, Font.PLAIN, smallFontSize));
        playerLabel.setForeground(GraphicsManager.playerColors[playerNr]);
        this.add(playerLabel);
    }

    /**
     * Set this rectangle to main/normal panel (for this client).
     * For example if this client is player nr 4 then
     * panel nr 4 is current main panel.
     * Change its size and set weight of text.
     * @param isClient set true if this is main panel, false for normal panel
     */
    public void setClient(boolean isClient) {
        int fontSize = smallFontSize;
        int weight = Font.PLAIN;
        if(isClient) {
            fontSize = bigFontSize;
            weight = Font.BOLD;
        }

        Font font = playerLabel.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.SIZE, fontSize);
        attributes.put(TextAttribute.WEIGHT, weight);
        font = font.deriveFont(attributes);
        playerLabel.setFont(font);
    }

    /**
     * Change appearance of panel of player which
     * is actually making moves.
     * @param isPlaying set true if this player is playing, false otherwise
     */
    public void setActualPlaying(boolean isPlaying) {
        Font font = playerLabel.getFont();
        Map attributes = font.getAttributes();
        if(isPlaying) {
            Border border = BorderFactory.createLineBorder(Color.RED, 5);
            playerLabel.setBorder(border);
        }
        else {
            playerLabel.setBorder(BorderFactory.createEmptyBorder());
        }
        font = font.deriveFont(attributes);
        playerLabel.setFont(font);
    }


    /**
     * Change nickname of a player and update panel.
     * @param nick new nickname of player
     */
    public void setNickName(String nick) {
        this.nickName = nick;
        this.playerLabel.setText(nick);
    }

}
