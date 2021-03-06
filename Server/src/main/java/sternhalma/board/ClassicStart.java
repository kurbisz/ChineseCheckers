package sternhalma.board;

import sternhalma.Game;
import sternhalma.Notifer;
import sternhalma.NotiferInterface;

/**
 * Class generating pieces acording to sternhalma classic rules.
 */
public class ClassicStart implements StartingInterface {
    private BoardInterface board;
    private Game game;
    private int size;
    private NotiferInterface notifer = Notifer.getInstance();

    /**
     * Send message about field being set.
     * @param message message to be sent
     */
    public void send(String message) {
        notifer.notifyAll(message, game);
    }

    /**
     * Create the pieces.
     * @param board reference to the board
     * @param size board size
     * @param game reference to the game
     */
    public ClassicStart(BoardInterface board, int size, Game game) {
        this.board = board;
        this.size = size;
        this.game = game;
    }
    private void set0(int id) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < board.getRowSize(y); x++) {
                board.getField(y, x).setOwner(id);
                send(String.format("SET %d %d %d", y, x, id));
            }
        }
    }
    private void set2(int id) {
        for (int y = 1; y <= size; y++) {
            for (int x = 0; x < y; x++) {
                int xmax = board.getRowSize(y + 2 * size) - 1;
                board.getField(y + 2 * size, xmax - x).setOwner(id);
                send(String.format("SET %d %d %d", y + 2 * size, xmax - x, id));
            }
        }
    }
    private void set4(int id) {
        for (int y = 1; y <= size; y++) {
            for (int x = 0; x < y; x++) {
                board.getField(y + 2 * size, x).setOwner(id);
                send(String.format("SET %d %d %d", y + 2 * size, x, id));
            }
        }
    }
    private void set3(int id) {
        for (int y = 1; y <= size; y++) {
            for (int x = 0; x < y; x++) {
                board.getField(4 * size + 1 - y, x).setOwner(id);
                send(String.format("SET %d %d %d", 4 * size + 1 - y, x, id));
            }
        }
    }
    private void set5(int id) {
        for (int y = 1; y <= size; y++) {
            for (int x = 0; x < y; x++) {
                board.getField(2 * size - y, x).setOwner(id);
                send(String.format("SET %d %d %d", 2 * size - y, x, id));
            }
        }
    }
    private void set1(int id) {
        for (int y = 1; y <= size; y++) {
            for (int x = 0; x < y; x++) {
                int xmax = board.getRowSize(2 * size - y) - 1;
                board.getField(2 * size - y, xmax - x).setOwner(id);
                send(String.format("SET %d %d %d", 2 * size - y, xmax - x, id));

            }
        }
    }
    /**
     * Generate pieces
     * @param num number of players
     */
    public void prepare(int num) {
        set0(0);
        if (num == 3) {
            set2(1);
            set4(2);
            return;
        }
        if (num == 2) {
            set3(1);
            return;
        }
        if (num == 4) {
            set2(1);
            set3(2);
            set5(3);
            return;
        }
        if (num == 6) {
            set1(1);
            set2(2);
            set3(3);
            set4(4);
            set5(5);
            return;
        }
    }
}
