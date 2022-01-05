package sternhalma.board.direction;

public class DownRight implements DirBehaviour {
    private DirBehaviour next;
    private static DownRight instance=new DownRight();
    /**
     * Get singleton of this class.
     * @return the only instance of this class
     */
    public static DownRight getInstance()
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
