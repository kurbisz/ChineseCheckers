package client.game.states;

public class WaitingForMoveState implements GameStateBehaviour {


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
}
