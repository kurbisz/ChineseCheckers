package client.graphics;

public class SingleField {

    private int row, column;

    public SingleField(int fieldRow, int fieldColumn) {
        this.row = fieldRow;
        this.column = fieldColumn;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

}
