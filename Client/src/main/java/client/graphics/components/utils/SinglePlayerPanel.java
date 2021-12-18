package client.graphics.components.utils;

import client.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class SinglePlayerPanel extends JPanel {

    private final static int smallFontSize = 24, bigFontSize = 32;

    private String nickName;
    private int playerNr;
    private JLabel playerLabel;

    public SinglePlayerPanel(String name, int nr) {
        super();
        this.playerNr = nr;
        this.nickName = name;
        this.setAlignmentY(BOTTOM_ALIGNMENT);
        this.setBackground(Color.GRAY);
        init();
    }

    private void init() {
        playerLabel = new JLabel(nickName);
        playerLabel.setFont(new Font(Font.SERIF, Font.PLAIN, smallFontSize));
        playerLabel.setForeground(GraphicsManager.playerColors[playerNr]);
        this.add(playerLabel);
    }

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

    public void setActualPlaying(boolean isPlaying) {
        Font font = playerLabel.getFont();
        Map attributes = font.getAttributes();
        if(isPlaying)
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        else
            attributes.remove(TextAttribute.UNDERLINE);
        font = font.deriveFont(attributes);
        playerLabel.setFont(font);
    }

    public void setNickName(String nick) {
        this.nickName = nick;
        this.playerLabel.setText(nick);
    }

}
