package client.game;

import client.game.states.GameState;
import client.graphics.GraphicsManager;
import connection.Interpreter;

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
        System.out.println(substring);
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
    public void size(int size) {
        graphicsManager.setBoardSize(size);
        graphicsManager.drawBoard();
    }




}
