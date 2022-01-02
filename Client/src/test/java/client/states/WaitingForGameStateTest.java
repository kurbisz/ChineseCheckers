package client.states;

import client.game.states.GameState;
import client.game.states.GameStateBehaviour;
import client.game.states.WaitingForGameState;
import client.graphics.GraphicsManager;
import client.graphics.NoJFrameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

public class WaitingForGameStateTest extends SingleStateTest  {

    @Override
    public void testOwnState() {
        Assertions.assertEquals(generateGameStateBehaviour().getState(), GameState.WAITING_FOR_GAME);
    }

    @Override
    public void testStartGame() {
        Assertions.assertEquals(generateGameStateBehaviour().startGame().getState(), GameState.WAITING_FOR_MOVE);
    }

    @Override
    public void testStartMove() {
        Assertions.assertEquals(generateGameStateBehaviour().startMove().getState(), GameState.WAITING_FOR_GAME);
    }

    @Override
    public void testEndMove() {
        Assertions.assertEquals(generateGameStateBehaviour().endMove().getState(), GameState.WAITING_FOR_GAME);
    }

    @Override
    public void testFinish() {
        Assertions.assertEquals(generateGameStateBehaviour().finish().getState(), GameState.WAITING_FOR_GAME);
    }

    @Test
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
        return new WaitingForGameState();
    }

}
