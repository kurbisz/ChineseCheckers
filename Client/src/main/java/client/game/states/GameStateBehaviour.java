package client.game.states;

public interface GameStateBehaviour {

    /**
     * Get actual state of game.
     * @return actual GameState
     */
    GameState getState();

    /**
     * Get GameState after starting game
     * from actual GameStateBehaviour.
     * @return changed GameState
     */
    GameStateBehaviour startGame();

    /**
     * Get GameState after starting move
     * from actual GameStateBehaviour.
     * @return changed GameState
     */
    GameStateBehaviour startMove();

    /**
     * Get GameState after finishing move
     * from actual GameStateBehaviour.
     * @return changed GameState
     */
    GameStateBehaviour endMove();

    /**
     * Get GameState after finishing game
     * from actual GameStateBehaviour.
     * @return changed GameState
     */
    GameStateBehaviour finish();

    /**
     * Set information to server that
     * this client closed application.
     */
    void sendCloseInfo();

}
