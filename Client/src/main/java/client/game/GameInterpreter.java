package client.game;

import client.graphics.InvalidPanelException;
import connection.Interpreter;

public class GameInterpreter implements Interpreter {

    private final GameManager gameManager;

    /**
     * Supports connection with server and handles exceptions.
     * @param gameManager manager of active game
     */
    public GameInterpreter(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Execute GameManager's method setPlayers()
     * with array of nicknames from argument.
     * @param s string with all nicknames separated by ','
     */
    @Override
    public void setPlayers(String s) {
        // TODO
        try {
            String[] players = s.split(",");
            gameManager.updatePlayers(players);
        } catch (InvalidPanelException e) {
            System.out.println("Error while updating players!");
        }
    }

    @Override
    public void move(int fromRow, int fromColumn, int toRow, int toColumn) {
        try {
            gameManager.setPlayerMove(fromRow, fromColumn, toRow, toColumn);
        } catch (InvalidPanelException e) {
            System.out.println("Error while making move!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid point position from server!");
        }
    }

    @Override
    public void message(String substring) {
        try {
            gameManager.setInfoMessage(substring);
        } catch (InvalidPanelException e) {
            System.out.println("Information from server: " + substring);
        }
    }

    @Override
    public void start() {
        try {
            gameManager.start();
            // TODO remove next lines - just tests
            // setClientNumber(1);
            // changeTurn(1);
            // setTurn();
        } catch (InvalidPanelException e) {
            System.out.println("Error while initializing board!");
        }
    }

    @Override
    public void victory() {
        gameManager.openWinGui();
    }

    @Override
    public void defeat(String name) {
        gameManager.openLoseGui(name);
    }

    @Override
    public void left() {
        gameManager.openLeftGui();
    }

    @Override
    public void size(int size, int players) {
        gameManager.size(size);
        numPlayers(players);
    }

    /**
     * Preferred to use setPlayer().
     * @param players amount of players
     */
    @Deprecated
    @Override
    public void numPlayers(int players) {
        try {
            gameManager.updatePlayers(players);
        } catch (InvalidPanelException e) {
            System.out.println("Error while updating players!");
        }
    }

    @Override
    public void setField(int row, int col, int id) {
        try {
            gameManager.setPlayerOnCircle(row, col, id);
        } catch (InvalidPanelException e) {
            System.out.println("Error while loading board!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid point position from server!");
        }
    }

    @Override
    public void turn() {
        try {
            gameManager.endMove();
        } catch (InvalidPanelException e) {
            System.out.println("Error while finishing move!");
        }
    }

    public void setClientNumber(int player) {
        try {
            gameManager.setClientNumber(player);
        } catch (InvalidPanelException e) {
            System.out.println("Error while setting client number!");
        }
    }

    public void setTurn() {
        try {
            gameManager.startMove();
        } catch (InvalidPanelException e) {
            System.out.println("Error while starting your turn!");
        }
    }

    public void changeTurn(int player) {
        try {
            gameManager.setActualPlayer(player);
        } catch (InvalidPanelException e) {
            System.out.println("Error while changing active player!");
        }
    }

}
