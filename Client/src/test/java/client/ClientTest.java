package client;

import client.game.GameManager;
import client.graphics.GraphicsManager;

public abstract class ClientTest {

    protected GameManager getGameManager() {
        CheckersClient.setHumanMode(false);
        CheckersClient checkersClient = CheckersClient.generateClient();
        GameManager gameManager = checkersClient.getGameManager();
        return gameManager;
    }

    protected void initGameManager(GameManager gameManager) {
        GraphicsManager graphicsManager = gameManager.getGraphicsManager();
        graphicsManager.drawBoard();
    }


}
