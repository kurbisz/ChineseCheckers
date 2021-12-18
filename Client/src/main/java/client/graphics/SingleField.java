package client.graphics;

public class SingleField {

    private int row, column;

    /**
     * Create and store row and column of a single field.
     * @param fieldRow row of field
     * @param fieldColumn column of field
     */
    public SingleField(int fieldRow, int fieldColumn) {
        this.row = fieldRow;
        this.column = fieldColumn;
    }

    /**
     * Get field's column
     * @return column of this field
     */
    public int getColumn() {
        return column;
    }

    /**
     * Get field's row
     * @return row of this field
     */
    public int getRow() {
        return row;
    }

}
