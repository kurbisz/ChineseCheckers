package sternhalma.board;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private List<Field> neighbours;
    private int owner=-1;
    public Field() {
        this.neighbours = new ArrayList<>();
    }
    public void addNeighbour(Field f) {
        this.neighbours.add(f);
        f.neighbours.add(this);
    }
    public List<Field> getNeighbours() {
        return neighbours;
    }
    public void setOwner(int id) {
        owner = id;
    }

}
