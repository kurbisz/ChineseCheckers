package board;

public class Board {
    private int size;
    private Field[][] tab;
    public Board(int size) {
        this.size = size;
        tab = new Field[4*size+1][];
        init();
        addNeighbours();
    }
    private void init() {
        for(int i=0;i<size;i++) {
            tab[i] = new Field[i];
        }
        for (int i=0;i<=size;i++) {
            tab[i+size] = new Field[3*size+1-i];
        }
        for (int i=1;i<=size;i++) {
            tab[i+2*size] = new Field[2*size+1+i];
        }
        for (int i=0;i<size;i++) {
            tab[4*size-i] = new Field[i];
        }
        for (int i=0;i<tab.length;i++) {
            for (int j=0;j<tab[i].length;j++) {
                tab[i][j] = new Field(this);
            }
        }
    }
    private void addNeighbours() {
        //TODO
    }
}
