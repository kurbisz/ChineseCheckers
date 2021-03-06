package sternhalma.board;

import sternhalma.board.direction.Direction;
import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

/**
 * Class representing the board.
 */
public class ClassicBoard implements BoardInterface {
    private int size;
    private Field[][] tab;
    private MovingInterface moving;

    /**
     * Generate board of fixed size.
     * @param s size of each corner
     * @param mv interface responsible for moving on the board
     */
    public ClassicBoard(int s, MovingInterface mv) {
        this.size = s;
        this.tab = new Field[4 * s + 1][];
        init();
        setDirections();
        addNeighbours();
        this.moving = mv;
    }

    /**
     * Get field at specific location.
     * @param row row
     * @param col column
     * @return field
     */
    public Field getField(int row, int col) {
        return tab[row][col];
    }

    /**
     * Get size of specific row.
     * @param y row number
     * @return row size
     */
    public int getRowSize(int y) {
        return tab[y].length;
    }

    /**
     * Move a piece from one field to another.
     * @param id id of player performing move
     * @param fromR row from which to move
     * @param fromC column from which to move
     * @param toR row to which to move
     * @param toC column to which to move
     * @throws InvalidMoveException move cannot be proceeded
     * @throws InvalidPlayerException move cannot be proceeded
     */
    public void move(int id, int fromR, int fromC, int toR, int toC)
            throws InvalidMoveException, InvalidPlayerException {
        Field from = getField(fromR, fromC);
        Field to = getField(toR, toC);
        moving.move(id, from, to);
    }

    /**
     * End move.
     */
    public void endMove() {
        moving.end();
    }


    /**
     * Initialise the directions on the board.
     * */
    private void setDirections() {
    Direction.LEFT.getStateBehaviour().setOpposite(Direction.RIGHT.getStateBehaviour());
    Direction.UPLEFT.getStateBehaviour().setOpposite(Direction.DOWNRIGHT.getStateBehaviour());
    Direction.DOWNLEFT.getStateBehaviour().setOpposite(Direction.UPRIGHT.getStateBehaviour());
    }
    /**
     * Initialise the board.
     */
    private void init() {
        for (int i = 0; i < size; i++) {
            tab[i] = new Field[i + 1];
        }
        for (int i = 0; i <= size; i++) {
            tab[i + size] = new Field[3 * size + 1 - i];
        }
        for (int i = 1; i <= size; i++) {
            tab[i + 2 * size] = new Field[2 * size + 1 + i];
        }
        for (int i = 0; i < size; i++) {
            tab[4 * size - i] = new Field[i + 1];
        }
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = new Field();
            }
        }
    }

    /**
     * Add proper neighbours to fields.
     */
    private void addNeighbours() {
        //HORIZONTAL ONES
        for (int y = 0; y < tab.length; y++) {
            for (int x = 0; x < tab[y].length; x++) {
                try {
                    tab[y][x].addNeighbour(Direction.RIGHT.getStateBehaviour(), tab[y][x + 1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //This field don't exists.
                }
            }
        }
        for (int y = 0; y <= size - 2; y++) {
            for (int x = 0; x < tab[y].length; x++) {
                try {
                    tab[y][x].addNeighbour(Direction.DOWNLEFT.getStateBehaviour(),tab[y + 1][x]);
                    tab[y][x].addNeighbour(Direction.DOWNRIGHT.getStateBehaviour(), tab[y + 1][x + 1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //This field don't exists.
                }
            }
        }
        for (int y = 2 * size; y <= 3 * size - 1; y++) {
            for (int x = 0; x < tab[y].length; x++) {
                try {
                    tab[y][x].addNeighbour(Direction.DOWNLEFT.getStateBehaviour(), tab[y + 1][x]);
                    tab[y][x].addNeighbour(Direction.DOWNRIGHT.getStateBehaviour(), tab[y + 1][x + 1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //This field don't exists.
                }
            }
        }
        for (int y = 3 * size + 2; y <= 4 * size; y++) {
            for (int x = 0; x < tab[y].length; x++) {
                try {
                    tab[y][x].addNeighbour(Direction.UPLEFT.getStateBehaviour(), tab[y - 1][x]);
                    tab[y][x].addNeighbour(Direction.UPRIGHT.getStateBehaviour(), tab[y - 1][x + 1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //This field don't exists.
                }
            }
        }
        for (int y = size + 1; y <= 2 * size; y++) {
            for (int x = 0; x < tab[y].length; x++) {
                try {
                    tab[y][x].addNeighbour(Direction.UPLEFT.getStateBehaviour(), tab[y - 1][x]);
                    tab[y][x].addNeighbour(Direction.UPRIGHT.getStateBehaviour(), tab[y - 1][x + 1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //This field don't exists.
                }
            }
        }
        int y1 = size - 1;
        for (int x = 0; x < tab[y1].length; x++) {
                tab[y1][x].addNeighbour(Direction.DOWNLEFT.getStateBehaviour(), tab[y1 + 1][x + size]);
                tab[y1][x].addNeighbour(Direction.DOWNRIGHT.getStateBehaviour(), tab[y1 + 1][x + size + 1]);
        }
        int y2 = 3 * size + 1;
        for (int x = 0; x < tab[y2].length; x++) {
                tab[y2][x].addNeighbour(Direction.UPLEFT.getStateBehaviour(), tab[y2 - 1][x + size]);
                tab[y2][x].addNeighbour(Direction.UPRIGHT.getStateBehaviour(), tab[y2 - 1][x + size + 1]);
        }
    }
}
