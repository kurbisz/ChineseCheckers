package client.game;

import client.game.states.GameState;
import client.graphics.GraphicsManager;
import client.graphics.InvalidPanelException;
import connection.Interpreter;

import javax.swing.*;

public class GameInterpreter implements Interpreter {

    GameState gameState;
    GraphicsManager graphicsManager;

    public GameInterpreter(GameState state, GraphicsManager graphics) {
        this.gameState = state;
        this.graphicsManager = graphics;
    }

    @Override
    public void setPlayers(String s) {

    }

    @Override
    public void move(int fromRow, int fromColumn, int toRow, int toColumn) {
        try {
            graphicsManager.setPlayerMove(fromRow, fromColumn, toRow, toColumn);
        } catch (InvalidPanelException e) {
            System.out.println("Error while making move!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid point position from server!");
        }
    }


    @Override
    public void message(String substring) {
        try {
            graphicsManager.setInfoMessage(substring);
        } catch (InvalidPanelException e) {
            System.out.println("Information from server: " + substring);
        }
    }

    @Override
    public void start() {
        try {
            gameState.getStateBehaviour().startGame();
            graphicsManager.changeGameState(gameState);
        } catch (InvalidPanelException e) {
            System.out.println("Error while initializing board!");
        }
    }

    @Override
    public void victory() {

    }

    @Override
    public void defeat(String name) {

    }

    @Override
    public void left() {

    }

    @Override
    public void size(int size, int players) {
        graphicsManager.setBoardSize(size);
        graphicsManager.drawBoard();
        numPlayers(players);
    }

    @Override
    public void numPlayers(int players) {
        try {
            graphicsManager.updatePlayers(players);
        } catch (InvalidPanelException e) {
            System.out.println("Error while updating players!");
        }
    }

    @Override
    public void setField(int row, int col, int id) {
        try {
            graphicsManager.setPlayerOnCircle(row, col, id);
        } catch (InvalidPanelException e) {
            System.out.println("Error while loading board!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid point position from server!");
        }
    }

    @Override
    public void turn() {
        try {
            gameState.getStateBehaviour().endMove();
            graphicsManager.changeGameState(gameState);
        } catch (InvalidPanelException e) {
            System.out.println("Error while finishing move!");
        }
    }

}
