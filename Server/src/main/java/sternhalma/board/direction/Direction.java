package sternhalma.board.direction;

/**
 * Enum listing all directions on the board.
 */
public enum Direction {
    LEFT {
        public DirBehaviour getStateBehaviour() {
            return Left.getInstance();
        }
    },
    RIGHT {
        public DirBehaviour getStateBehaviour() {
            return Right.getInstance();
        }
    },
    UPLEFT {
        public DirBehaviour getStateBehaviour() {
            return UpLeft.getInstance();
        }
    },
    UPRIGHT {
        public DirBehaviour getStateBehaviour() {
            return UpRight.getInstance();
        }
    },
    DOWNLEFT {
        public DirBehaviour getStateBehaviour() {
            return DownLeft.getInstance();
        }
    },
    DOWNRIGHT {
        public DirBehaviour getStateBehaviour() {
            return DownRight.getInstance();
        }
    };

    /**
     * Get object describing selected direction.
     * @return DirBehaviour responsible for chosen direction
     */
    public DirBehaviour getStateBehaviour() {
        return null;
    }
}
