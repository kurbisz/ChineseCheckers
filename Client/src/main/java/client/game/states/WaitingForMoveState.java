package client.game.states;

import client.graphics.GraphicsManager;
import client.graphics.NoJFrameException;

public class WaitingForMoveState implements GameStateBehaviour {

    @Override
    public GameState getState() {
        return GameState.WAITING_FOR_MOVE;
    }

    @Override
    public GameStateBehaviour startGame() {
        return this;
    }

    @Override
    public GameStateBehaviour startMove() {
        return GameState.PLAYING.getStateBehaviour();
    }

    @Override
    public GameStateBehaviour endMove() {
        return this;
    }

    @Override
    public GameStateBehaviour finish() {
        return GameState.FINISHED.getStateBehaviour();
    }

    @Override
    public void closeClient(GraphicsManager graphicsManager)
            throws NoJFrameException {
        graphicsManager.openLeftGui();
    }

}
