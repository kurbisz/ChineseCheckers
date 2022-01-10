package sternhalma.board;

import sternhalma.board.direction.DirBehaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing a field.
 */
public class Field {
    private Map<DirBehaviour, Field> neighbours;
    private int owner=-1;

    /**
     * Create a field.
     */
    public Field() {
        this.neighbours = new HashMap<>();
    }

    /**
     * Add neighbour to this field.
     * @param f Field to add
     */
    public void addNeighbour(DirBehaviour d, Field f) {
        this.neighbours.put(d,f);
        f.neighbours.put(d.getOpposite(),this);
    }

    /**
     * Get list of neighbours of this field.
     * @return list of neighbours of this field
     */
    public List<Field> getNeighbours() {
        return new ArrayList<>(neighbours.values());
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

    /**
     * Get direction between two fields;
     * @param f the other field
     * @return direction between this field and field f
     */
    public DirBehaviour getDirBehaviour(Field f) {
        if (f==null) {
            return null;
        }
        for (Map.Entry<DirBehaviour, Field> e : neighbours.entrySet()) {
            if (f.equals(e.getValue())) {
               return e.getKey();
            }
        }
        return null;
    }
}
