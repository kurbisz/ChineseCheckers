package sternhalma.board;

import sternhalma.Game;
import sternhalma.Notifer;
import sternhalma.NotiferInterface;

/**
 * Class implementing sternhalma classic rules of game end.
 */
public class Finish implements FinishInterface {
    private Board board;
    private Game game;
    private int size;
    private NotiferInterface notifer = Notifer.getInstance();

    /**
     *
     * @param board reference to board
     * @param size size of the board
     * @param game reference to game
     */
    public Finish(Board board, int size, Game game) {
        this.board = board;
        this.size = size;
        this.game = game;
    }
    private boolean check0(int id) {
        for (int y=1;y<=size;y++) {
            for (int x=0;x<y;x++) {
                if (board.getField(4*size+1-y,x).getOwner()!=id) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean check1(int id) {
        for (int y = 1; y <= size; y++) {
            for (int x = 0; x < y; x++) {
                if (board.getField(y + 2 * size, x).getOwner()!=id) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean check2(int id) {
        for (int y=1;y<=size;y++) {
            for (int x=0;x<y;x++) {
                if ( board.getField(2*size-y,x).getOwner()!=id) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean check3(int id) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < board.getRowSize(y); x++) {
                if (board.getField(y, x).getOwner()!=id) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean check4(int id) {
        for (int y = 1; y <= size; y++) {
            for (int x = 0; x < y; x++) {
                int xmax = board.getRowSize(2 * size - y) - 1;
                if (board.getField(2*size-y,xmax-x).getOwner()!=id) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean check5(int id) {
        for (int y = 1; y <= size; y++) {
            for (int x = 0; x < y; x++) {
                int xmax = board.getRowSize(y + 2 * size) - 1;
                if ( board.getField(y + 2 * size, xmax - x).getOwner()!=id) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public int checkEnd(int num) {
        if (check0(0)) return 0;
        if (num == 3) {
            if (check2(1)) return 1;
            if (check4(2)) return 2;
        }
        if (num == 2) {
            if (check3(1)) return 1;
        }
        if (num == 4) {
            if (check2(1)) return 1;
            if (check3(2)) return 2;
            if (check5(3)) return 3;
        }
        if (num == 6) {
            if (check1(1)) return 1;
            if (check2(2)) return 2;
            if (check3(3)) return 3;
            if (check4(4)) return 4;
            if (check5(5)) return 5;
        }
        return -1;
    }
}
