package client.game;

import client.CheckersClient;
import client.graphics.InvalidPanelException;
import client.graphics.NoJFrameException;
import client.graphics.components.ChooseGameOptionPane;
import client.graphics.components.EndGameOptionPane;
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
        try {
            String[] players = s.split("\\$");
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
        } catch (InvalidPanelException e) {
            System.out.println("Error while initializing board!");
        }
    }

    @Override
    public void victory() {
        try {
            gameManager.openWinGui();
        } catch (NoJFrameException e) {
            System.out.println("No application opened!");
        }
    }

    @Override
    public void defeat(String name) {
        try {
            gameManager.openLoseGui(name);
        } catch (NoJFrameException e) {
            System.out.println("No application opened!");
        }
    }

    @Override
    public void left() {
        try {
            gameManager.openLeftGui();
        } catch (NoJFrameException e) {
            System.out.println("No application opened!");
        }
    }

    @Override
    public void size(int size, int players) {
        gameManager.setBoardSize(size);
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
    public void setClientNumber(int player) {
        try {
            gameManager.setClientNumber(player);
        } catch (InvalidPanelException e) {
            System.out.println("Error while setting client number!");
        }
    }

    @Override
    public void listGames(String s) {
        if(s.equals("")) {
            CheckersClient.getInstance().finish("Save games list is empty!");
            return;
        }
        String[] fullGame = s.split("\\$");
        int size = fullGame.length;

        int[] index = new int[size];
        String[] games = new String[size];

        for(int i = 0; i < size; i++) {
            String[] str = fullGame[i].split(";");
            index[i] = Integer.parseInt(str[0]);
            games[i] = str[1];
        }

        CheckersClient.getInstance().chooseGame(index, games);
    }

    @Override
    public void setTurn() {
        try {
            gameManager.startMove();
        } catch (InvalidPanelException e) {
            System.out.println("Error while starting your turn!");
        }
    }

    @Override
    public void changeTurn(int player) {
        try {
            gameManager.endMove();
            gameManager.setActualPlayer(player);
        } catch (InvalidPanelException e) {
            System.out.println("Error while changing active player!");
        }
    }

}
