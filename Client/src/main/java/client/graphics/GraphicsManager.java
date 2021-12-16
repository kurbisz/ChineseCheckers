package client.graphics;

import client.CheckersClient;
import client.graphics.components.BoardPanel;
import client.graphics.components.ButtonPanel;
import client.graphics.components.Panel;
import client.graphics.components.PlayersPanel;
import client.graphics.listener.ComponentListeners;
import client.graphics.listener.WindowListeners;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GraphicsManager {

    public static Color[] playerColors;
    private static String frameName;

    //TODO private!!!
    public JFrame jFrame;

    private int boardSize;
    private ConcurrentHashMap<String, Panel> boardPanels;


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
        // TODO get board size from interpreter
        initBoard();
        for(Map.Entry<String, Panel> entry : boardPanels.entrySet()) {
            Panel panel = entry.getValue();
            panel.initialize();
            jFrame.add(panel);
        }
        jFrame.revalidate();
        jFrame.repaint();

        try {
            updatePlayers(1);
        } catch (InvalidPlayerPanelException exc) { }

    }

    public void setBoardSize(int size) {
        this.boardSize = size;
    }

    public void updatePlayers(int players) throws InvalidPlayerPanelException {
        Panel panel = boardPanels.get("players");
        if(panel == null || !(panel instanceof PlayersPanel)) {
            throw new InvalidPlayerPanelException();
        }
        PlayersPanel playersPanel = (PlayersPanel) panel;
        playersPanel.updatePlayerAmount(players);
    }

    private void openConnectGui() {
        JTextField nickField = new JTextField("lessnop", 20);
        JTextField addressField = new JTextField("localhost", 20);
        JTextField portField = new JTextField("59898", 20);

        Object panels[] = {
                "Nickname: ", nickField,
                "Address: ", addressField,
                "Port: ", portField,
        };

        int result = JOptionPane.showConfirmDialog(null, panels,
                "Connect to the server: ", JOptionPane.OK_CANCEL_OPTION);

        if(result==JOptionPane.OK_OPTION) {
            String serverAddress = addressField.getText();
            String port = portField.getText();
            String nickName = nickField.getText();
            int serverPort;
            if(nickName.length() < 5) {
                jFrame.dispose();
                System.out.println("Error! Your nickname is too short!");
            }
            else {
                try {
                    serverPort = Integer.parseInt(port);
                    CheckersClient.getInstance().connectClientToServer(serverAddress, serverPort, nickName);
                } catch (NumberFormatException e) {
                    jFrame.dispose();
                    System.out.println("Error! Given port was not a number!");
                } catch (UnknownHostException e) {
                    jFrame.dispose();
                    System.out.println("Error! There is no such server!");
                } catch (IOException e) {
                    jFrame.dispose();
                    System.out.println("Error while connecting to server!");
                }
            }
        }
        else jFrame.dispose();
    }

    private void initBoard() {
        this.boardPanels = new ConcurrentHashMap<String, Panel>();
        boardPanels.put("main", new BoardPanel(jFrame, boardSize));
        boardPanels.put("players", new PlayersPanel(jFrame));
        boardPanels.put("button", new ButtonPanel(jFrame));
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
