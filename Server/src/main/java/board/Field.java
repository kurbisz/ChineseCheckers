package board;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private List<Field> neighbours;
    private Board board;
    public Field(Board board) {
        this.neighbours = new ArrayList<>();
        this.board = board;
    }
}
