package client.game.states;

public class PlayingState implements GameStateBehaviour{

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
}
