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
    public void move(int from, int to) {
        // TODO change arguments of move() and uncomment
        /*try {
            graphicsManager.setPlayerMove(fromRow, fromColumn, toRow, toColumn);
        } catch (InvalidPanelException e) {
            System.out.println("Error while making move!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid point position from server!");
        }*/
    }

    @Override
    public void message(String substring) {
        try {
            graphicsManager.setInfoMessage(substring);
        } catch (InvalidPanelException e) {
            System.out.println("Error while getting information!");
        }
    }

    @Override
    public void start() {

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
            // TODO remove, its just test
            paint(1, 1, 2);
            paint(2, 2, 3);
            paint(3, 1, 1);
        } catch (InvalidPanelException e) {
            System.out.println("Error while updating players!");
        }
    }

    public void paint(int row, int column, int player) {
        try {
            graphicsManager.setPlayerOnCircle(row, column, player);
        } catch (InvalidPanelException e) {
            System.out.println("Error while painting board!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid point position from server!");
        }
    }




}
