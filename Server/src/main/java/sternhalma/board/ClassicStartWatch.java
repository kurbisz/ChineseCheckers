package sternhalma.board;

import sternhalma.Watch;

/**
 * Class generating board for one watching user.
 */
public class ClassicStartWatch extends ClassicStart {
    Watch watch;
    public void send(String message) {
        watch.send(message);
    }
    public ClassicStartWatch(BoardInterface board, int size, Watch watch) {
        super(board, size, null);
        this.watch = watch;
    }
}
