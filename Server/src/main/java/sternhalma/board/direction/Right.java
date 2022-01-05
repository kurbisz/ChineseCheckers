package sternhalma.board.direction;

public class Right implements DirBehaviour {
    private DirBehaviour next;
    private static Right instance=new Right();
    /**
     * Get singleton of this class.
     * @return the only instance of this class
     */
    public static Right getInstance()
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
