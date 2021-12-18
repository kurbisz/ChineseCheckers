package client.game.states;

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
        return this;
    }

    @Override
    public void sendCloseInfo() {
        // TODO uncomment when Messenger is ready
        // Messenger.getInstance().leave();
    }

}
