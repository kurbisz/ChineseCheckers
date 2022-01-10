package client;

import client.game.GameInterpreter;
import client.game.GameManager;
import client.game.states.GameState;
import client.graphics.components.*;
import connection.NoConnectionException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing main functions of game with
 * GameInterpreter instance as our Interpreter.
 */
public class GameUtilitiesTest extends ClientTest {


    @Test
    public void testSetPlayers() {
        GameManager gameManager = getGameManager();
        initGameManager(gameManager);

        String players = "P1\\$P2\\$P3";
        GameInterpreter gameInterpreter = (GameInterpreter) gameManager.getInterpreter();
        gameInterpreter.setPlayers(players);

        Panel panel = gameManager.getGraphicsManager().getPanel("players");
        assertTrue(panel instanceof PlayersPanel);
        PlayersPanel playersPanel = (PlayersPanel) panel;
        List<String> playerList = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            playerList.add(playersPanel.getSinglePlayerPanel()[i].getNickName());
        }
        assertEquals(List.of(players.split("\\$")), playerList);
    }

    @Test
    public void testSetClientNumber() {
        GameManager gameManager = getGameManager();
        initGameManager(gameManager);

        int clientNr = 2;
        GameInterpreter gameInterpreter = (GameInterpreter) gameManager.getInterpreter();

        gameInterpreter.setClientNumber(clientNr);

        Panel panel = gameManager.getGraphicsManager().getPanel("players");
        assertTrue(panel instanceof PlayersPanel);
        PlayersPanel playersPanel = (PlayersPanel) panel;

        assertTrue(playersPanel.getSinglePlayerPanel()[clientNr].isThisClient());
        for(int i = 0; i < 6; i++) {
            if(i!=clientNr) {
                assertFalse(playersPanel.getSinglePlayerPanel()[i].isThisClient());
            }
        }
    }

    @Test
    public void testMessage() {
        GameManager gameManager = getGameManager();
        initGameManager(gameManager);

        String msg = "Test messaging";
        GameInterpreter gameInterpreter = (GameInterpreter) gameManager.getInterpreter();
        gameInterpreter.message(msg);

        Panel panel = gameManager.getGraphicsManager().getPanel("info");
        assertTrue(panel instanceof InformationPanel);
        InformationPanel informationPanel = (InformationPanel) panel;

        assertEquals(msg, informationPanel.getMessage());
    }

    @Test
    public void testStatesChanging() {
        GameManager gameManager = getGameManager();
        initGameManager(gameManager);

        Panel panel = gameManager.getGraphicsManager().getPanel("button");
        assertTrue(panel instanceof ButtonPanel);
        ButtonPanel buttonPanel = (ButtonPanel) panel;


        String previousText = buttonPanel.getJButton().getText();

        assertTrue(testSingleButtonClick(buttonPanel));

        GameInterpreter gameInterpreter = (GameInterpreter) gameManager.getInterpreter();
        gameInterpreter.start();
        // Check if GameState and text on button has changed
        assertEquals(buttonPanel.getGameState(), GameState.WAITING_FOR_MOVE);
        assertNotEquals(buttonPanel.getJButton().getText(), previousText);
        previousText = buttonPanel.getJButton().getText();

        assertTrue(testSingleButtonClick(buttonPanel));

        gameInterpreter.setTurn();
        // Check if GameState and text on button has changed
        assertEquals(buttonPanel.getGameState(), GameState.PLAYING);
        assertNotEquals(buttonPanel.getJButton().getText(), previousText);
        previousText = buttonPanel.getJButton().getText();

        assertTrue(testSingleButtonClick(buttonPanel));

        gameInterpreter.changeTurn(0);
        // Check if GameState and text on button has changed
        assertEquals(buttonPanel.getGameState(), GameState.WAITING_FOR_MOVE);
        assertNotEquals(buttonPanel.getJButton().getText(), previousText);

    }

    private boolean testSingleButtonClick(ButtonPanel buttonPanel) {
        boolean messengerSent = false;
        try {
            buttonPanel.click();
        } catch (NoConnectionException e) {
            // It means that messenger tried to send message,
            // but we did not connect to any server.
            messengerSent = true;
        }
        return messengerSent;
    }

}
