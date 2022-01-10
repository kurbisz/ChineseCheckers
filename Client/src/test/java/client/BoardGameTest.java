package client;

import client.game.GameInterpreter;
import client.game.GameManager;
import client.graphics.GraphicsManager;
import client.graphics.components.BoardPanel;
import client.graphics.components.Panel;
import connection.NoConnectionException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardGameTest extends ClientTest {

    @Test
    public void testMovingAndBoardSize() {
        GameManager gameManager = getGameManager();
        GameInterpreter gameInterpreter = (GameInterpreter) gameManager.getInterpreter();
        gameInterpreter.size(5, 4);

        initGameManager(gameManager);

        // Set owner of field (0,0) to player nr 2
        gameInterpreter.setField(0, 0, 2);
        // Move pawn from (0,0) to (7,3)
        gameInterpreter.move(0, 0, 7, 3);

        Panel panel = gameManager.getGraphicsManager().getPanel("board");
        assertTrue(panel instanceof BoardPanel);
        BoardPanel boardPanel = (BoardPanel) panel;
        int previousPlayer = boardPanel.setPlayerOnCircle(7, 3, -1);
        assertEquals(2, previousPlayer);
    }

    @Test
    public void testSendingMove() {
        GameManager gameManager = getGameManager();
        GraphicsManager graphicsManager = gameManager.getGraphicsManager();
        initGameManager(gameManager);
        try {
            graphicsManager.setPointClick(0, 0);
        } catch (NoConnectionException e) {
            // First click should not send any information to server
        }

        boolean informationSent = false;
        try {
            graphicsManager.setPointClick(4, 5);
        } catch (NoConnectionException e) {
            // If this exception has occurred it means that
            // GameManager has sent information to Messenger
            // but it was not read (no connection)
            informationSent = true;
        }
        assertTrue(informationSent);
    }

}
