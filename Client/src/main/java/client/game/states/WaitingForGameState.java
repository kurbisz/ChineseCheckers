package client.game.states;

public class WaitingForGameState implements GameStateBehaviour {


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
}
