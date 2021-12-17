package client.graphics;

import client.graphics.components.*;
import client.graphics.components.Panel;
import client.graphics.listener.MouseListeners;
import client.graphics.listener.WindowListeners;
import connection.Messenger;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GraphicsManager {

    public static Color[] playerColors;
    private static String frameName;

    private JFrame jFrame;

    private SingleField fromField, toField;

    private int boardSize;
    private ConcurrentHashMap<String, Panel> boardPanels = new ConcurrentHashMap<>();


    public void createNewWindow() {
        jFrame = new JFrame(frameName);
        jFrame.setLayout(null);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.addWindowListener(new WindowListeners());
        jFrame.addMouseListener(new MouseListeners(this));
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

    public void setPlayerOnCircle(int row, int column, int player)
            throws InvalidPanelException, IndexOutOfBoundsException {
        BoardPanel boardPanel = getBoard();
        boardPanel.setPlayerOnCircle(row, column, player);
    }

    public void setPlayerMove(int fromRow, int fromColumn,
                              int toRow, int toColumn)
            throws InvalidPanelException, IndexOutOfBoundsException {
        BoardPanel boardPanel = getBoard();
        int player = boardPanel.setPlayerOnCircle(fromRow, fromColumn, -1);
        boardPanel.setPlayerOnCircle(toRow, toColumn, player);
    }

    public void setFromPointClick(int row, int column) {
        if(row < 0 || column < 0) {
            fromField = null;
        }
        else {
            fromField = new SingleField(row, column);
        }
    }

    public void setToPointClick(int row, int column) {
        if(row >= 0 && column >= 0 && fromField != null) {
            toField = new SingleField(row, column);
            System.out.println("click: " + fromField.getRow() + "." + fromField.getColumn() + " / " + toField.getRow() + "." + toField.getColumn());
            // TODO uncomment when messenger is ok
            /*Messenger.getInstance().move(
                    fromField.getRow(), fromField.getColumn(),
                    toField.getRow(), toField.getColumn());*/
        }
        fromField = null;
        toField = null;
    }

    private void openConnectGui() {
        ConnectOptionPane.popup(jFrame);
    }

    private void initBoard() {
        this.boardPanels = new ConcurrentHashMap<String, Panel>();
        boardPanels.put("board", new BoardPanel(jFrame, boardSize));
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

    private BoardPanel getBoard() throws InvalidPanelException {
        Panel panel = boardPanels.get("board");
        if(panel == null || !(panel instanceof BoardPanel)) {
            throw new InvalidPanelException();
        }
        BoardPanel boardPanel = (BoardPanel) panel;
        return boardPanel;
    }

}
