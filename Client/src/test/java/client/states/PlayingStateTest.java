package client.states;

import client.game.states.GameState;
import client.game.states.GameStateBehaviour;
import client.game.states.PlayingState;
import client.graphics.GraphicsManager;
import client.graphics.NoJFrameException;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.verify;

public class PlayingStateTest extends SingleStateTest {

    @Override
    public void testOwnState() {
        Assertions.assertEquals(generateGameStateBehaviour().getState(), GameState.PLAYING);
    }

    @Override
    public void testStartGame() {
        Assertions.assertEquals(generateGameStateBehaviour().startGame().getState(), GameState.PLAYING);
    }

    @Override
    public void testStartMove() {
        Assertions.assertEquals(generateGameStateBehaviour().startMove().getState(), GameState.PLAYING);
    }

    @Override
    public void testEndMove() {
        Assertions.assertEquals(generateGameStateBehaviour().endMove().getState(), GameState.WAITING_FOR_MOVE);
    }

    @Override
    public void testFinish() {
        Assertions.assertEquals(generateGameStateBehaviour().finish().getState(), GameState.FINISHED);
    }

    @Override
    public void testCloseClient() {
        try {
            GraphicsManager graphicsManager = new GraphicsManager();
            generateGameStateBehaviour().closeClient(graphicsManager);
            verify(graphicsManager).openLeftGui();
        } catch (NoJFrameException e) {
            // Do not catch this exception - just test
            // if method openLeftGui() was done.
        }
    }

    @Override
    protected GameStateBehaviour generateGameStateBehaviour() {
        return new PlayingState();
    }

}
