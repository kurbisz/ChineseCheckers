package client.game;

import client.game.states.GameState;
import client.graphics.GraphicsManager;
import client.graphics.InvalidPanelException;
import connection.Interpreter;
import connection.Receiver;

public class GameManager {

    private GraphicsManager graphicsManager;
    private Interpreter interpreter;
    private GameState gameState;

    public GameManager(GraphicsManager graphics) {
        this.graphicsManager = graphics;
        this.gameState = GameState.WAITING_FOR_GAME;
        interpreter = new GameInterpreter(this);
        Receiver.setInterpreter(interpreter);
    }

    public void onWindowClose() {
        gameState.getStateBehaviour().sendCloseInfo();
    }


    public void setPlayerMove(int fromRow, int fromColumn, int toRow, int toColumn)
            throws InvalidPanelException, IndexOutOfBoundsException {
        graphicsManager.setPlayerMove(fromRow, fromColumn, toRow, toColumn);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GraphicsManager getGraphicsManager() {
        return graphicsManager;
    }

    public void setInfoMessage(String substring) throws InvalidPanelException {
        graphicsManager.setInfoMessage(substring);
    }

    public void start() throws InvalidPanelException {
        gameState = gameState.getStateBehaviour().startGame().getState();
        graphicsManager.changeGameState(gameState);
    }

    public void openWinGui() {
        graphicsManager.openWinGui();
    }

    public void openLoseGui(String name) {
        graphicsManager.openLoseGui(name);
    }

    public void openLeftGui() {
        graphicsManager.openLeftGui();
    }

    public void size(int size) {
        graphicsManager.setBoardSize(size);
        graphicsManager.drawBoard();
    }

    public void updatePlayers(int players) throws InvalidPanelException {
        graphicsManager.updatePlayers(players);
    }

    public void setPlayerOnCircle(int row, int col, int id)
            throws InvalidPanelException, IndexOutOfBoundsException {
        graphicsManager.setPlayerOnCircle(row, col, id);
    }

    public void endMove() throws InvalidPanelException {
        gameState = gameState.getStateBehaviour().endMove().getState();
        graphicsManager.changeGameState(gameState);
    }

    public void setClientNumber(int player) throws InvalidPanelException {
        graphicsManager.setClientNumber(player);
    }

    public void startMove() throws InvalidPanelException {
        gameState = gameState.getStateBehaviour().startMove().getState();
        graphicsManager.changeGameState(gameState);
    }

    public void setActualPlayer(int player) throws InvalidPanelException {
        graphicsManager.setActualPlayer(player);
    }
}
