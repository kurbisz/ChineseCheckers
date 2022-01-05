package sternhalma.board.direction;

public class UpRight implements DirBehaviour {
    private DirBehaviour next;
    private static UpRight instance=new UpRight();
    /**
     * Get singleton of this class.
     * @return the only instance of this class
     */
    public static UpRight getInstance()
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
