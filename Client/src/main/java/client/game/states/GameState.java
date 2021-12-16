package client.game.states;

public enum GameState {

    WAITING_FOR_GAME {
        @Override
        public GameStateBehaviour getStateBehaviour() {
            return new WaitingForGameState();
        }
    },

    WAITING_FOR_MOVE {
        @Override
        public GameStateBehaviour getStateBehaviour() {
            return new WaitingForMoveState();
        }
    },

    PLAYING {
        @Override
        public GameStateBehaviour getStateBehaviour() {
            return new PlayingState();
        }
    },

    FINISHED {
        @Override
        public GameStateBehaviour getStateBehaviour() {
            return new FinishedState();
        }
    };

    public GameStateBehaviour getStateBehaviour() {
        return null;
    }

}
