package client.game.states;

import connection.Messenger;

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
    public void sendCloseInfo() {
        Messenger.getInstance().leave();
    }

}
