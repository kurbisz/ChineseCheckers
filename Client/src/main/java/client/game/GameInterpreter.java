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

    }

    @Override
    public void message(String substring) {
        try {
            graphicsManager.setInfoMessage(substring);
        } catch (InvalidPanelException e) { }
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
        } catch (InvalidPanelException e) {
            System.out.println("Error while updating players!");
        }
    }




}
