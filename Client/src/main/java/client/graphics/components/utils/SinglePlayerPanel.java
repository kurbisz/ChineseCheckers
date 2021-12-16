package client.graphics.components.utils;

import client.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class SinglePlayerPanel extends JPanel {

    private String nickName;
    private int playerNr;

    public SinglePlayerPanel(String name, int nr) {
        super();
        this.playerNr = nr;
        this.nickName = name;
        this.setAlignmentY(BOTTOM_ALIGNMENT);
        this.setBackground(Color.GRAY);
        init();
    }

    private void init() {
        JLabel jLabel = new JLabel(nickName);
        jLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        jLabel.setForeground(GraphicsManager.playerColors[playerNr]);
        this.add(jLabel);
    }



}
