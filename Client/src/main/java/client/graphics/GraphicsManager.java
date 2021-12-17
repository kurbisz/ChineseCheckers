package client.graphics;

import client.graphics.components.*;
import client.graphics.components.Panel;
import client.graphics.listener.ComponentListeners;
import client.graphics.listener.WindowListeners;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GraphicsManager {

    public static Color[] playerColors;
    private static String frameName;

    private JFrame jFrame;

    private int boardSize;
    private ConcurrentHashMap<String, Panel> boardPanels = new ConcurrentHashMap<>();


    public void createNewWindow() {
        jFrame = new JFrame(frameName);
        jFrame.setLayout(null);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.addWindowListener(new WindowListeners());
        jFrame.addComponentListener(new ComponentListeners(this));
        jFrame.setVisible(true);

        openConnectGui();
    }

    public void lockAppSize() {
        jFrame.setMinimumSize(jFrame.getSize());
        jFrame.setResizable(false);
    }

    public void drawBoard() {
        initBoard();
        for(Map.Entry<String, Panel> entry : boardPanels.entrySet()) {
            Panel panel = entry.getValue();
            panel.initialize();
            jFrame.add(panel);
        }
        jFrame.revalidate();
        jFrame.repaint();

    }

    public void setBoardSize(int size) {
        this.boardSize = size;
    }

    public void updatePlayers(int players) throws InvalidPanelException {
        Panel panel = boardPanels.get("players");
        if(panel == null || !(panel instanceof PlayersPanel)) {
            throw new InvalidPanelException();
        }
        PlayersPanel playersPanel = (PlayersPanel) panel;
        playersPanel.updatePlayerAmount(players);
    }

    public void setInfoMessage(String message) throws InvalidPanelException {
        Panel panel = boardPanels.get("info");
        if(panel == null || !(panel instanceof InformationPanel)) {
            throw new InvalidPanelException();
        }
        InformationPanel informationPanel = (InformationPanel) panel;
        informationPanel.setMessage(message);
    }

    private void openConnectGui() {
        ConnectOptionPane.popup(jFrame);
    }

    private void initBoard() {
        this.boardPanels = new ConcurrentHashMap<String, Panel>();
        boardPanels.put("main", new BoardPanel(jFrame, boardSize));
        boardPanels.put("players", new PlayersPanel(jFrame));
        boardPanels.put("button", new ButtonPanel(jFrame));
        boardPanels.put("info", new InformationPanel(jFrame));
    }

    public void initVariables() {
        frameName = "Chinese Checkers";
        playerColors = new Color[6];
        playerColors[0] = Color.BLUE;
        playerColors[1] = Color.YELLOW;
        playerColors[2] = Color.GREEN;
        playerColors[3] = Color.RED;
        playerColors[4] = Color.CYAN;
        playerColors[5] = Color.MAGENTA;
    }

}
