package client.game.states;

import client.graphics.GraphicsManager;
import client.graphics.NoJFrameException;

public class WaitingForGameState implements GameStateBehaviour {

    @Override
    public GameState getState() {
        return GameState.WAITING_FOR_GAME;
    }

    @Override
    public GameStateBehaviour startGame() {
        return GameState.WAITING_FOR_MOVE.getStateBehaviour();
    }

    @Override
    public GameStateBehaviour startMove() {
        return this;
    }

    @Override
    public GameStateBehaviour endMove() {
        return this;
    }

    @Override
    public GameStateBehaviour finish() {
        return this;
    }

    @Override
    public void closeClient(GraphicsManager graphicsManager) throws NoJFrameException {
        graphicsManager.openLeftGui();
    }

}
