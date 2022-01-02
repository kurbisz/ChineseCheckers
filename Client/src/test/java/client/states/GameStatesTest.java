package client.states;

import client.game.states.*;
import client.graphics.components.buttons.FinishedButton;
import client.graphics.components.buttons.PlayingButton;
import client.graphics.components.buttons.WaitingForGameButton;
import client.graphics.components.buttons.WaitingForMoveButton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameStatesTest {

    @Test
    public void testWaitingForMoveStateBehaviour() {
        assertTrue(GameState.WAITING_FOR_MOVE.getStateBehaviour() instanceof WaitingForMoveState);
    }

    @Test
    public void testWaitingForGameStateBehaviour() {
        assertTrue(GameState.WAITING_FOR_GAME.getStateBehaviour() instanceof WaitingForGameState);
    }

    @Test
    public void testPlayingStateBehaviour() {
        assertTrue(GameState.PLAYING.getStateBehaviour() instanceof PlayingState);
    }

    @Test
    public void testFinishedStateBehaviour() {
        assertTrue(GameState.FINISHED.getStateBehaviour() instanceof FinishedState);
    }

    @Test
    public void testWaitingForMoveStateButton() {
        assertTrue(GameState.WAITING_FOR_MOVE.getStateButton() instanceof WaitingForMoveButton);
    }

    @Test
    public void testWaitingForGameStateButton() {
        assertTrue(GameState.WAITING_FOR_GAME.getStateButton() instanceof WaitingForGameButton);
    }

    @Test
    public void testPlayingStateButton() {
        assertTrue(GameState.PLAYING.getStateButton() instanceof PlayingButton);
    }

    @Test
    public void testFinishedStateButton() {
        assertTrue(GameState.FINISHED.getStateButton() instanceof FinishedButton);
    }

    @Test
    public void testNullStateBehaviour() {
        GameState gameState = GameState.OTHER;
        assertTrue(gameState.getStateBehaviour() == null);
    }

    @Test
    public void testNullStateButton() {
        GameState gameState = GameState.OTHER;
        assertTrue(gameState.getStateButton() == null);
    }

}
