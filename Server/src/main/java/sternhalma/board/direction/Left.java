package sternhalma.board.direction;

public class Left implements DirBehaviour {
    private DirBehaviour next;
    private static Left instance=new Left();
    /**
     * Get singleton of this class.
     * @return the only instance of this class
     */
    public static Left getInstance()
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
