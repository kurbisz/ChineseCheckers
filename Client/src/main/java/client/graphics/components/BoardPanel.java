package client.graphics.components;

import client.graphics.components.utils.SingleRowPanel;

import javax.swing.JFrame;
import java.awt.GridLayout;

public class BoardPanel extends Panel {

    private int boardSize;

    private SingleRowPanel[] boardRow;

    /**
     * Responsible for board part of application.
     * @param jFrame actual jFrame of application
     * @param size size of a board (amount of rows of a player's triangle)
     */
    public BoardPanel(JFrame jFrame, int size) {
        super(jFrame);
        this.boardSize = size;
        this.boardRow = new SingleRowPanel[getAmountOfRows()];
    }

    /**
     * Creates all circles needed to be placed on board.
     * Sets appropriate visual layouts.
     */
    @Override
    public void initialize() {
        int counter = 0;
        // Create top triangle
        for (int i = 0; i < boardSize; i++) {
            SingleRowPanel rowPanel = new SingleRowPanel(jFrame,
                    counter, i + 1);

            for (int j = 0; j <= i; j++) {
                rowPanel.addCircle();
            }
            boardRow[counter++] = rowPanel;
            this.add(rowPanel);
        }

        // Create upper (center) part of board
        for (int i = 0; i < boardSize + 1; i++) {
            SingleRowPanel rowPanel = new SingleRowPanel(jFrame,
                    counter, 3 * boardSize - i + 1);

            for (int j = 0; j <= 3 * boardSize - i; j++) {
                rowPanel.addCircle();
            }
            boardRow[counter++] = rowPanel;
            this.add(rowPanel);
        }

        // Create lower (center) part of board
        for (int i = 0; i < boardSize; i++) {
            SingleRowPanel rowPanel = new SingleRowPanel(jFrame,
                    counter, 2 * boardSize + i + 1 + 1);

            for (int j = 0; j <= 2 * boardSize + i + 1; j++) {
                rowPanel.addCircle();
            }
            boardRow[counter++] = rowPanel;
            this.add(rowPanel);
        }

        // Create bottom triangle
        for (int i = 0; i < boardSize; i++) {
            SingleRowPanel rowPanel = new SingleRowPanel(jFrame,
                    counter, boardSize - i);

            for (int j = 0; j < boardSize - i; j++) {
                rowPanel.addCircle();
            }
            boardRow[counter++] = rowPanel;
            this.add(rowPanel);
        }

        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setVgap(1);
        gridLayout.setHgap(1);
        this.setLayout(gridLayout);


        this.setBounds((int) (0.2 * jFrame.getWidth()),
                (int) (0.05 * jFrame.getHeight()),
                (int) (0.45 * jFrame.getWidth()),
                (int) (0.7 * jFrame.getHeight()));


    }

    /**
     * Executes setPlayer() in appropriate SingleRowPanel.
     * @param row row nr of this field
     * @param column column nr of this field
     * @param player id of new player
     * @return id of previous player on this field
     * @throws IndexOutOfBoundsException when row is not
     * smaller than amount of rows on board
     */
    public int setPlayerOnCircle(int row, int column, int player)
            throws IndexOutOfBoundsException {
        return boardRow[row].setPlayer(column, player);
    }


    private int getAmountOfRows() {
        return 4 * boardSize + 1;
    }


}
