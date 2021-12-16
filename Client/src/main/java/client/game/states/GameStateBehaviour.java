package client.game.states;

public interface GameStateBehaviour {



    GameStateBehaviour startGame();

    GameStateBehaviour startMove();

    GameStateBehaviour endMove();

    GameStateBehaviour finish();

}
