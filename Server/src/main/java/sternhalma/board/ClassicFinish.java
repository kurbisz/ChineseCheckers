package sternhalma.board;

import sternhalma.Game;
import sternhalma.Notifer;
import sternhalma.NotiferInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class implementing sternhalma classic rules of game end.
 */
public class ClassicFinish implements FinishInterface {
    private BoardInterface board;
    private Game game;
    private int size;
    private NotiferInterface notifer = Notifer.getInstance();
    private Map<Integer, List<Field>> mp = new HashMap<>();
    private boolean set = false;
    /**
     *
     * @param board reference to board
     * @param size size of the board
     * @param game reference to game
     */
    public ClassicFinish(BoardInterface board, int size, Game game) {
        this.board = board;
        this.size = size;
        this.game = game;
    }
    private void set0(int id) {
        List<Field> ls = new ArrayList<>();
        for (int y = 1; y <= size; y++) {
            for (int x = 0; x < y; x++) {
                ls.add(board.getField(4 * size + 1 - y, x));
            }
        }
        mp.put(id, ls);
    }
    private void set1(int id) {
        List<Field> ls = new ArrayList<>();
        for (int y = 1; y <= size; y++) {
            for (int x = 0; x < y; x++) {
                ls.add(board.getField(y + 2 * size, x));
            }
        }
        mp.put(id, ls);
    }
    private void set2(int id) {
        List<Field> ls = new ArrayList<>();
        for (int y = 1; y <= size; y++) {
            for (int x = 0; x < y; x++) {
                ls.add(board.getField(2 * size - y, x));
            }
        }
        mp.put(id, ls);
    }
    private void set3(int id) {
        List<Field> ls = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < board.getRowSize(y); x++) {
                ls.add(board.getField(y, x));
            }
        }
        mp.put(id, ls);
    }
    private void set4(int id) {
        List<Field> ls = new ArrayList<>();
        for (int y = 1; y <= size; y++) {
            int xmax = board.getRowSize(2 * size - y) - 1;
            for (int x = 0; x < y; x++) {
                ls.add(board.getField(2 * size - y, xmax - x));
            }
        }
        mp.put(id, ls);
    }
    private void set5(int id) {
        List<Field> ls = new ArrayList<>();
        for (int y = 1; y <= size; y++) {
            int xmax = board.getRowSize(y + 2 * size) - 1;
            for (int x = 0; x < y; x++) {
                ls.add(board.getField(y + 2 * size, xmax - x));
            }
        }
        mp.put(id, ls);
    }

    private boolean check(int id) {
        for (Field f : mp.get(id)) {
            if (f.getOwner() != id) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int checkEnd(int num) {
        if (!set) {
            setPlayers(num);
            set = true;
        }
        for (int i = 0; i < num; i++) {
            if (check(i)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public void setPlayers(int num) {
        set0(0);
        if (num == 3) {
            set2(1);
            set4(2);
        }
        if (num == 2) {
            set3(1);
        }
        if (num == 4) {
            set2(1);
            set3(2);
            set5(3);
        }
        if (num == 6) {
            set1(1);
            set2(2);
            set3(3);
            set4(4);
            set5(5);
        }
    }
    @Override
    public List<Field> getCorner(int player) {
        return mp.get(player);
    }
}
