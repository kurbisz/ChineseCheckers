package client.states;

import client.game.states.GameStateBehaviour;
import org.junit.Test;

public abstract class SingleStateTest {

    @Test
    public abstract void testOwnState();

    @Test
    public abstract void testStartGame();

    @Test
    public abstract void testStartMove();

    @Test
    public abstract void testEndMove();

    @Test
    public abstract void testFinish();

    @Test
    public abstract void testCloseClient();

    protected abstract GameStateBehaviour generateGameStateBehaviour();

}
