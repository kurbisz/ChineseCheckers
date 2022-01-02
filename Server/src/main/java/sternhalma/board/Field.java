package sternhalma.board;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a field.
 */
public class Field {
    private List<Field> neighbours;
    private int owner=-1;

    /**
     * Create a field
     */
    public Field() {
        this.neighbours = new ArrayList<>();
    }

    /**
     * Add neighbour to this field.
     * @param f Field to add
     */
    public void addNeighbour(Field f) {
        this.neighbours.add(f);
        f.neighbours.add(this);
    }

    /**
     * Get list of neighbours of this field.
     * @return list of neighbours of this field
     */
    public List<Field> getNeighbours() {
        return neighbours;
    }

    /**
     * Set the owner of this field.
     * @param id new owner id (-1 if empty)
     */
    public void setOwner(int id) {
        owner = id;
    }

    /**
     * Get the owner of this field.
     * @return id of this field's owner (-1 if empty)
     */
    public int getOwner() {
        return owner;
    }

}
