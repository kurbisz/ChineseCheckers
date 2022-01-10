package client.states;

import client.game.states.FinishedState;
import client.game.states.GameState;
import client.game.states.GameStateBehaviour;
import org.junit.jupiter.api.Assertions;


public class FinishedStateTest extends SingleStateTest {

    @Override
    public void testOwnState() {
        Assertions.assertEquals(generateGameStateBehaviour().getState(), GameState.FINISHED);
    }

    @Override
    public void testStartGame() {
        Assertions.assertEquals(generateGameStateBehaviour().startGame().getState(), GameState.FINISHED);
    }

    @Override
    public void testStartMove() {
        Assertions.assertEquals(generateGameStateBehaviour().startMove().getState(), GameState.FINISHED);
    }

    @Override
    public void testEndMove() {
        Assertions.assertEquals(generateGameStateBehaviour().endMove().getState(), GameState.FINISHED);
    }

    @Override
    public void testFinish() {
        Assertions.assertEquals(generateGameStateBehaviour().finish().getState(), GameState.FINISHED);
    }

    @Override
    public void testCloseClient() {
        // Do nothing
    }

    @Override
    protected GameStateBehaviour generateGameStateBehaviour() {
        return new FinishedState();
    }

}
