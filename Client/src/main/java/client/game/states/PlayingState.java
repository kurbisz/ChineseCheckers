package client.game.states;

import connection.Messenger;

public class PlayingState implements GameStateBehaviour{

    @Override
    public GameState getState() {
        return GameState.PLAYING;
    }

    @Override
    public GameStateBehaviour startGame() {
        return this;
    }

    @Override
    public GameStateBehaviour startMove() {
        return this;
    }

    @Override
    public GameStateBehaviour endMove() {
        return GameState.WAITING_FOR_MOVE.getStateBehaviour();
    }

    @Override
    public GameStateBehaviour finish() {
        return GameState.FINISHED.getStateBehaviour();
    }

    @Override
    public void sendCloseInfo() {
        // TODO uncomment when Messenger is ready
        // Messenger.getInstance().leave();
    }

}
