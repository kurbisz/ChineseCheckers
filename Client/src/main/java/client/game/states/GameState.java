package client.game.states;

import client.graphics.components.buttons.*;

public enum GameState {

    /**
     * State of waiting for game.
     */
    WAITING_FOR_GAME {
        @Override
        public GameStateBehaviour getStateBehaviour() {
            return new WaitingForGameState();
        }

        @Override
        public StateButton getStateButton() {
            return new WaitingForGameButton();
        }
    },

    /**
     * State of waiting for move.
     */
    WAITING_FOR_MOVE {
        @Override
        public GameStateBehaviour getStateBehaviour() {
            return new WaitingForMoveState();
        }

        @Override
        public StateButton getStateButton() {
            return new WaitingforMoveButton();
        }
    },

    /**
     * State of doing a move (playing).
     */
    PLAYING {
        @Override
        public GameStateBehaviour getStateBehaviour() {
            return new PlayingState();
        }

        @Override
        public StateButton getStateButton() {
            return new PlayingButton();
        }
    },

    /**
     * State when game is finished.
     * It is not used but can be useful
     * when we want some utilities after end.
     */
    FINISHED {
        @Override
        public GameStateBehaviour getStateBehaviour() {
            return new FinishedState();
        }

        @Override
        public StateButton getStateButton() {
            return new FinishedButton();
        }
    };


    /**
     * Get behaviour of this type of state.
     * @return proper type of new GameStateBehaviour
     */
    public GameStateBehaviour getStateBehaviour() {
        return null;
    }

    /**
     * Get button of this type of state.
     * @return proper type of new StateButton
     */
    public StateButton getStateButton() { return null;}

}
