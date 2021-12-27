package client;

import client.game.GameInterpreter;
import client.game.GameManager;
import client.game.states.GameState;
import connection.NoConnectionException;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FinishGameTest extends ClientTest {

    @Test
    public void testPlayerLeft() {
        GameManager gameManager = getGameManager();
        GameInterpreter gameInterpreter = (GameInterpreter) gameManager.getInterpreter();
        initGameManager(gameManager);

        gameInterpreter.left();
        JFrame jFrame = gameManager.getGraphicsManager().getJFrame();
        assertTrue(isClosed(jFrame));
    }

    @Test
    public void testWin() {
        GameManager gameManager = getGameManager();
        GameInterpreter gameInterpreter = (GameInterpreter) gameManager.getInterpreter();
        initGameManager(gameManager);

        gameInterpreter.victory();
        JFrame jFrame = gameManager.getGraphicsManager().getJFrame();
        assertTrue(isClosed(jFrame));
    }

    @Test
    public void testLose() {
        GameManager gameManager = getGameManager();
        GameInterpreter gameInterpreter = (GameInterpreter) gameManager.getInterpreter();
        initGameManager(gameManager);

        gameInterpreter.defeat("Defeat");
        JFrame jFrame = gameManager.getGraphicsManager().getJFrame();
        assertTrue(isClosed(jFrame));
    }

    private boolean isClosed(JFrame jFrame) {
        return jFrame.getHeight()==10 && jFrame.getWidth() == 10;
    }

    @Test
    public void testWindowClose() {
        GameManager gameManager = getGameManager();
        initGameManager(gameManager);

        boolean informationSent = false;
        try {
            gameManager.onWindowClose();
        } catch (NoConnectionException exc) {
            informationSent = true;
            // If this exception is thrown it means
            // that information to server was sent
            // and window should be already closed.
        }

        assertTrue(informationSent);

        assertEquals(GameState.FINISHED, gameManager.getGameState());

    }

}
