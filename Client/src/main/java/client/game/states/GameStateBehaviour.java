package client.game.states;

public interface GameStateBehaviour {

    GameState getState();

    GameStateBehaviour startGame();

    GameStateBehaviour startMove();

    GameStateBehaviour endMove();

    GameStateBehaviour finish();

    void sendCloseInfo();

}
