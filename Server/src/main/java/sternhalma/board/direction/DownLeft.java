package sternhalma.board.direction;

public class DownLeft implements DirBehaviour {
    private DirBehaviour next;
    private static DownLeft instance=new DownLeft();

    /**
     * Get singleton of this class.
     * @return the only instance of this class
     */
    public static DownLeft getInstance()
    {
        return instance;
    }

    @Override
    public void setOpposite(DirBehaviour n) {
        if (next != null) {
            return;
        }
        this.next = n;
        n.setOpposite(this);
    }

    @Override
    public DirBehaviour getOpposite() {
        return next;
    }
}
