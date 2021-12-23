package client.game;

import client.game.states.GameState;
import client.graphics.GraphicsManager;
import client.graphics.InvalidPanelException;
import connection.Interpreter;
import connection.Messenger;
import connection.Receiver;

public class GameManager {

    private GraphicsManager graphicsManager;
    private Interpreter interpreter;
    private GameState gameState;

    /**
     * Main game controller which can execute
     * many commands connected with the game.
     * Constructor sets state of game to WAITING_FOR_GAME
     * and creates new Interpreter.
     * @param graphics active GraphicsManager
     */
    public GameManager(GraphicsManager graphics) {
        this.graphicsManager = graphics;
        this.gameState = GameState.WAITING_FOR_GAME;
        interpreter = new GameInterpreter(this);
        Receiver.setInterpreter(interpreter);
    }

    /**
     * Send information to server about client window close.
     */
    public void onWindowClose() {
        gameState = gameState.getStateBehaviour().finish().getState();
        Messenger.getInstance().leave();
    }

    /**
     * Move pawn from 1st field to 2nd field.
     * @param fromRow row of 1st field
     * @param fromColumn column of 1st field
     * @param toRow row of 2nd field
     * @param toColumn column of 2nd field
     */
    public void setPlayerMove(int fromRow, int fromColumn, int toRow, int toColumn)
            throws InvalidPanelException, IndexOutOfBoundsException {
        graphicsManager.setPlayerMove(fromRow, fromColumn, toRow, toColumn);
    }

    /**
     * Get actual state of the game.
     * @return actual GameState
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Show message in client's information box.
     * @param substring message to be shown
     */
    public void setInfoMessage(String substring) throws InvalidPanelException {
        graphicsManager.setInfoMessage(substring);
    }

    /**
     * Start a game.
     */
    public void start() throws InvalidPanelException {
        gameState = gameState.getStateBehaviour().startGame().getState();
        graphicsManager.changeGameState(gameState);
    }

    /**
     * Finish game and show victory screen.
     */
    public void openWinGui() {
        graphicsManager.openWinGui();
    }

    /**
     * Finish game and show defeat screen.
     */
    public void openLoseGui(String name) {
        graphicsManager.openLoseGui(name);
    }

    /**
     * Finish game and show that one of the players left.
     */
    public void openLeftGui() {
        gameState.getStateBehaviour().closeClient(graphicsManager);
        gameState = gameState.getStateBehaviour().finish().getState();
    }

    /**
     * Set board size and generate it and then
     * go to update players list.
     * @param size board size
     */
    public void size(int size) {
        graphicsManager.setBoardSize(size);
        graphicsManager.drawBoard();
    }

    /**
     * Set amount of players without nicknames
     * and update players list.
     * @param players amount of players
     */
    public void updatePlayers(int players) throws InvalidPanelException {
        graphicsManager.updatePlayers(players);
    }

    /**
     * Update player list in client's application.
     * @param players array of strings with players nicknames
     */
    public void updatePlayers(String[] players)
            throws InvalidPanelException {
        graphicsManager.updatePlayers(players);
    }

    /**
     * Paint field with a color.
     * @param row row of a field
     * @param col column of a field
     * @param id id of player which has to own this field
     */
    public void setPlayerOnCircle(int row, int col, int id)
            throws InvalidPanelException, IndexOutOfBoundsException {
        graphicsManager.setPlayerOnCircle(row, col, id);
    }

    /**
     * Finish turn of this client.
     */
    public void endMove() throws InvalidPanelException {
        gameState = gameState.getStateBehaviour().endMove().getState();
        graphicsManager.changeGameState(gameState);
    }

    /**
     * Send to client his id and mark him on players list.
     * @param player id of this client
     */
    public void setClientNumber(int player) throws InvalidPanelException {
        graphicsManager.setClientNumber(player);
    }

    /**
     * Set active player to this client.
     */
    public void startMove() throws InvalidPanelException {
        gameState = gameState.getStateBehaviour().startMove().getState();
        graphicsManager.changeGameState(gameState);
    }

    /**
     * Set active player in client application.
     * @param player id of active client
     */
    public void setActualPlayer(int player) throws InvalidPanelException {
        graphicsManager.setActualPlayer(player);
    }
}
