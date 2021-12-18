package client.game.states;

public class FinishedState implements GameStateBehaviour {

    @Override
    public GameState getState() {
        return GameState.FINISHED;
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
        return this;
    }

    @Override
    public GameStateBehaviour finish() {
        return this;
    }

    @Override
    public void sendCloseInfo() {

    }


}
