package client.game.states;

import client.graphics.components.buttons.*;

public enum GameState {

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

    public GameStateBehaviour getStateBehaviour() {
        return null;
    }

    public StateButton getStateButton() { return null;}

}
