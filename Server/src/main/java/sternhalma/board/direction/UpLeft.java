package sternhalma.board.direction;

public class UpLeft implements DirBehaviour {
    private DirBehaviour next;
    private static UpLeft instance=new UpLeft();
    /**
     * Get singleton of this class.
     * @return the only instance of this class
     */
    public static UpLeft getInstance()
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
