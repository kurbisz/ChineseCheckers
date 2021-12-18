package client.graphics;

import client.game.states.GameState;
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

        ConnectOptionPane.popup(jFrame);
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
        PlayersPanel playersPanel = getPlayers();
        playersPanel.updatePlayerAmount(players);
    }

    public void setActualPlayer(int player) throws InvalidPanelException {
        PlayersPanel playersPanel = getPlayers();
        playersPanel.setActualPlayer(player);
    }

    public void setClientNumber(int player) throws InvalidPanelException {
        PlayersPanel playersPanel = getPlayers();
        playersPanel.setClientNumber(player);
    }

    public void setInfoMessage(String message) throws InvalidPanelException {
        Panel panel = boardPanels.get("info");
        if(panel == null || !(panel instanceof InformationPanel)) {
            throw new InvalidPanelException();
        }
        InformationPanel informationPanel = (InformationPanel) panel;
        informationPanel.setMessage(message);
    }

    public void changeGameState(GameState gameState) throws InvalidPanelException {
        Panel panel = boardPanels.get("button");
        if(panel == null || !(panel instanceof ButtonPanel)) {
            throw new InvalidPanelException();
        }
        ButtonPanel boardPanel = (ButtonPanel) panel;
        boardPanel.setGameState(gameState);
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
            // System.out.println("click: " + fromField.getRow() + "." + fromField.getColumn() + " / " + toField.getRow() + "." + toField.getColumn());
            Messenger.getInstance().move(
                    fromField.getRow(), fromField.getColumn(),
                    toField.getRow(), toField.getColumn());
        }
        fromField = null;
        toField = null;
    }

    public void openWinGui() {
        EndGameOptionPane.popup(jFrame, "You won the game!");
    }

    public void openLoseGui(String string) {
        EndGameOptionPane.popup(jFrame, string);
    }

    public void openLeftGui() {
        EndGameOptionPane.popup(jFrame, "One of the players left the game!");
    }

    private void initBoard() {
        this.boardPanels = new ConcurrentHashMap<>();
        boardPanels.put("info", new InformationPanel(jFrame));
        boardPanels.put("players", new PlayersPanel(jFrame));
        boardPanels.put("button", new ButtonPanel(jFrame));
        boardPanels.put("board", new BoardPanel(jFrame, boardSize));
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
        return (BoardPanel) panel;
    }

    private PlayersPanel getPlayers() throws InvalidPanelException {
        Panel panel = boardPanels.get("players");
        if(panel == null || !(panel instanceof PlayersPanel)) {
            throw new InvalidPanelException();
        }
        return (PlayersPanel) panel;
    }

}
