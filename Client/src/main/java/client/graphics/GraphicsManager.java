package client.graphics;

import client.CheckersClient;
import client.graphics.components.MainPanel;
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

    private static String frameName = "Chinese Checkers";

    private JFrame jFrame;

    private int boardSize;
    private ConcurrentHashMap<String, Panel> boardPanels;


    public void createNewWindow() {
        jFrame = new JFrame(frameName);
        jFrame.setLayout(null);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setResizable(true);
        jFrame.addWindowListener(new WindowListeners());
        jFrame.addComponentListener(new ComponentListeners(this));
        jFrame.setVisible(true);

        openConnectGui();

    }

    public void drawBoard() {
        // TODO get board size from interpreter
        jFrame.setMinimumSize(jFrame.getSize());
        jFrame.setResizable(false);
        boardSize = 4;
        initBoard();
        for(Map.Entry<String, Panel> entry : boardPanels.entrySet()) {
            Panel panel = entry.getValue();
            panel.initialize();
            jFrame.add(panel);
        }
        jFrame.revalidate();
        jFrame.repaint();
    }


    private void openConnectGui() {
        JTextField addressField = new JTextField("localhost", 20);
        JTextField portField = new JTextField("59898", 20);

        Object panels[] = {
                "Address: ", addressField,
                "Port: ", portField,
        };

        int result = JOptionPane.showConfirmDialog(null, panels,
                "Connect to the server: ", JOptionPane.OK_CANCEL_OPTION);

        if(result==JOptionPane.OK_OPTION) {
            String serverAddress = addressField.getText();
            String port = portField.getText();
            int serverPort;
            try {
                serverPort = Integer.parseInt(port);
                CheckersClient.getInstance().connectClientToServer(serverAddress, serverPort);
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
        else jFrame.dispose();
    }

    private void initBoard() {
        this.boardPanels = new ConcurrentHashMap<String, Panel>();
        boardPanels.put("main", new MainPanel(jFrame, boardSize));
        boardPanels.put("players", new PlayersPanel(jFrame));
    }


}
