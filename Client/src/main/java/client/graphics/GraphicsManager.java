package client.graphics;

import client.CheckersClient;
import client.ClientType;
import client.game.states.GameState;
import client.graphics.components.*;
import client.graphics.components.Panel;
import client.graphics.listener.WindowListeners;
import connection.NoConnectionException;

import javax.swing.JFrame;
import java.awt.Color;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GraphicsManager {

    /**
     * Colors of each player which are set
     * in method initVariables().
     */
    public static Color[] playerColors;
    private static String frameName;

    private JFrame jFrame;

    private SingleField fromField;
    private SingleField toField;

    private int boardSize;
    private ConcurrentHashMap<String, Panel> boardPanels =
            new ConcurrentHashMap<>();


    /**
     * Create new application, jFrame and
     * open connect option pane.
     */
    public void createNewWindow() {
        jFrame = new JFrame(frameName);
        jFrame.setLayout(null);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.addWindowListener(new WindowListeners());
        jFrame.setVisible(true);

        ConnectOptionPane.popup(jFrame);
    }

    /**
     * Set minimum size and disable resizing window.
     */
    public void lockAppSize() {
        jFrame.setMinimumSize(jFrame.getSize());
        jFrame.setResizable(false);
    }

    /**
     * Draw whole application - all panels
     * and repaint window.
     */
    public void drawBoard() {
        initBoard();
        for (Map.Entry<String, Panel> entry : boardPanels.entrySet()) {
            Panel panel = entry.getValue();
            panel.initialize();
            jFrame.add(panel);
        }
        jFrame.revalidate();
        jFrame.repaint();

    }

    /**
     * Add new Panel to JFrame using
     * external project.
     * @param key string which will be key to this panel
     * @param panel new panel which has to be shown in application
     */
    public void addPanel(String key, Panel panel) {
        boardPanels.put(key, panel);
        panel.initialize();
        jFrame.add(panel);
        jFrame.revalidate();
        jFrame.repaint();
    }

    /**
     * Get certain panel by its id.
     * Size of characters makes difference.
     * @param key string key of panel
     * @return panel by its id, null when there is no such panel
     */
    public Panel getPanel(String key) {
        return boardPanels.get(key);
    }

    /**
     * Set size of board.
     * @param size board size - amount of rows of
     *             each player triangle on the
     *             beginning of a game
     */
    public void setBoardSize(int size) {
        this.boardSize = size;
    }

    /**
     * Update amount of players in the game.
     * Preferred to use updatePlayers(String[] players).
     * @param players amount of players
     * @throws InvalidPanelException when PlayersPanel is not added to panel map
     */
    @Deprecated
    public void updatePlayers(int players) throws InvalidPanelException {
        PlayersPanel playersPanel = getPlayers();
        playersPanel.updatePlayerAmount(players);
    }

    /**
     * Update players and their nicknames in the game.
     * @param players amount of players
     * @throws InvalidPanelException when PlayersPanel is not added to panel map
     */
    public void updatePlayers(String... players)
            throws InvalidPanelException {
        PlayersPanel playersPanel = getPlayers();
        playersPanel.updatePlayers(players);
    }

    /**
     * Set making move player in client application.
     * Executes setActualPlayer() in PlayerPanel.
     * @param player id of player
     * @throws InvalidPanelException when PlayersPanel is not added to panel map
     */
    public void setActualPlayer(int player) throws InvalidPanelException {
        PlayersPanel playersPanel = getPlayers();
        playersPanel.setActualPlayer(player);
    }

    /**
     * Save id of client's player and mark his SinglePlayerPanel.
     * Executes setClientNumber() in PlayerPanel.
     * @param player id of this client player
     * @throws InvalidPanelException when PlayersPanel is not added to panel map
     */
    public void setClientNumber(int player) throws InvalidPanelException {
        PlayersPanel playersPanel = getPlayers();
        playersPanel.setClientNumber(player);
    }

    /**
     * Show information message in proper box.
     * Executes setMessage() in InformationPanel.
     * @param message message which has to bo shown in box
     * @throws InvalidPanelException when InformationPanel
     * is not added to panel map
     */
    public void setInfoMessage(String message) throws InvalidPanelException {
        Panel panel = boardPanels.get("info");
        if (!(panel instanceof InformationPanel)) {
            throw new InvalidPanelException();
        }
        InformationPanel informationPanel = (InformationPanel) panel;
        informationPanel.setMessage(message);
    }

    /**
     * Change button appearance according to given GameState argument.
     * Executes setGameState() in ButtonPanel.
     * @param gameState actual game state
     * @throws InvalidPanelException when ButtonPanel is not added to panel map
     */
    public void changeGameState(GameState gameState)
            throws InvalidPanelException {
        Panel panel = boardPanels.get("button");
        if (!(panel instanceof ButtonPanel)) {
            throw new InvalidPanelException();
        }
        ButtonPanel buttonPanel = (ButtonPanel) panel;
        buttonPanel.setGameState(gameState);
    }

    /**
     * Changes pawn on a proper field.
     * @param row row of field
     * @param column column of field
     * @param player id of player
     * @throws InvalidPanelException when BoardPanel is not added to panel map
     * @throws IndexOutOfBoundsException when given field does not exist
     */
    public void setPlayerOnCircle(int row, int column, int player)
            throws InvalidPanelException, IndexOutOfBoundsException {
        BoardPanel boardPanel = getBoard();
        boardPanel.setPlayerOnCircle(row, column, player);
    }

    /**
     * Move a pawn from 1st field to 2nd field.
     * @param fromRow row of 1st field
     * @param fromColumn column of 1st field
     * @param toRow row of 2nd field
     * @param toColumn column of 2nd field
     * @throws InvalidPanelException when BoardPanel is not added to panel map
     * @throws IndexOutOfBoundsException when one of given fields does not exist
     */
    public void setPlayerMove(int fromRow, int fromColumn,
                              int toRow, int toColumn)
            throws InvalidPanelException, IndexOutOfBoundsException {
        BoardPanel boardPanel = getBoard();
        fromField = null;
        toField = null;
        if(CheckersClient.getInstance().getClientType().equals(ClientType.PLAYER)) {
            setInfoMessage("");
        }
        int player = boardPanel.setPlayerOnCircle(fromRow, fromColumn, -1);
        boardPanel.setPlayerOnCircle(toRow, toColumn, player);
    }

    /**
     * Set 1st field on handling player's move or 2nd field
     * when 1st field (fromField variable) is not null.
     * @param row row of clicked field
     * @param column column of clicked field
     * @throws NoConnectionException when occurred problem to connect
     * to server (by Messenger)
     */
    public void setPointClick(int row, int column)
            throws NoConnectionException {
        if (fromField != null) {
            setToPointClick(row, column);
        } else {
            if (row < 0 || column < 0) {
                fromField = null;
            } else {
                fromField = new SingleField(row, column);
            }
        }
    }

    /**
     * Set 2nd field on handling player's move.
     * @param row row of clicked field
     * @param column column of clicked field
     */
    private void setToPointClick(int row, int column)
            throws NoConnectionException {
        if (row >= 0 && column >= 0 && fromField != null) {
            toField = new SingleField(row, column);
            if (fromField.getRow() != toField.getRow()
                    || fromField.getColumn() != toField.getColumn()) {
                    CheckersClient.getMessenger().move(
                            fromField.getRow(), fromField.getColumn(),
                            toField.getRow(), toField.getColumn());
            }
        }
        fromField = toField;
        toField = null;
    }

    /**
     * Open option pane after player won the game.
     */
    public void openWinGui() throws NoJFrameException {
        if (jFrame == null) {
            throw new NoJFrameException("Invalid JFrame!");
        }
        EndGameOptionPane.popup(jFrame, "You won the game!", true);
    }

    /**
     * Open option pane after another player won the game.
     * @param string nickname of player who has won the game
     */
    public void openLoseGui(String string) throws NoJFrameException {
        if (jFrame == null) {
            throw new NoJFrameException("Invalid JFrame!");
        }
        EndGameOptionPane.popup(jFrame, "You lose the game. Player "
                + string + " won!", true);
    }

    /**
     * Open option pane after one of players left the game.
     */
    public void openLeftGui() throws NoJFrameException {
        if (jFrame == null) {
            throw new NoJFrameException("Invalid JFrame!");
        }
        EndGameOptionPane.popup(jFrame, "One of the players left the game!", true);
    }

    /**
     * Show menu with choosing game to show (to viewer).
     * @param index array of identities of games to show
     * @param games array of names of games to show
     */
    public void chooseGame(int[] index, String[] games) {
        ChooseGameOptionPane.popup(jFrame, index, games);
    }

    /**
     * Close client with message in option pane.
     * It doesn't save the game.
     * @param msg message which have to be shown in option pane
     */
    public void finish(String msg) {
        EndGameOptionPane.popup(jFrame, msg, false);
    }

    /**
     * Initialize basic static variables.
     */
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

    /**
     * Getter from private jFrame variable.
     * @return actual jFrame of this graphicsManager
     */
    public JFrame getJFrame() {
        return jFrame;
    }

    /**
     * Setter from private jFrame variable.
     * It should not be changed to other instance
     * while application is running!
     * @param jFrame new jFrame for this graphicsManager
     */
    public void setJFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    private void initBoard() {
        this.boardPanels = new ConcurrentHashMap<>();
        ClientType clientType = CheckersClient.getInstance().getClientType();
        if (clientType.equals(ClientType.PLAYER)) {
            boardPanels.put("info", new InformationPanel(jFrame));
            boardPanels.put("button", new ButtonPanel(jFrame));
        }
        else if(clientType.equals(ClientType.VIEWER)) {
            boardPanels.put("viewerButton", new ViewerButtonsPanel(jFrame));
        }
        boardPanels.put("players", new PlayersPanel(jFrame));
        boardPanels.put("board", new BoardPanel(jFrame, boardSize));
    }

    private BoardPanel getBoard() throws InvalidPanelException {
        Panel panel = boardPanels.get("board");
        if (!(panel instanceof BoardPanel)) {
            throw new InvalidPanelException();
        }
        return (BoardPanel) panel;
    }

    private PlayersPanel getPlayers() throws InvalidPanelException {
        Panel panel = boardPanels.get("players");
        if (!(panel instanceof PlayersPanel)) {
            throw new InvalidPanelException();
        }
        return (PlayersPanel) panel;
    }
}
