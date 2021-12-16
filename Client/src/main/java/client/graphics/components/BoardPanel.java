package client.graphics.components;

import client.graphics.components.utils.SingleRowPanel;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends Panel {

    int boardSize;

    SingleRowPanel boardRow[];

    public BoardPanel(JFrame jFrame, int size) {
        super(jFrame);
        this.boardSize = size;
        this.boardRow = new SingleRowPanel[getAmountOfRows()];
    }

    @Override
    public void initialize() {
        int counter = 0;
        // Create top triangle
        for(int i = 0; i < boardSize; i++) {
            SingleRowPanel rowPanel = new SingleRowPanel(jFrame, counter);
            for(int j = 0; j <= i; j++) {
                rowPanel.addCircle();
            }
            boardRow[counter++] = rowPanel;
            this.add(rowPanel);
        }

        // Create upper (center) part of board
        for(int i = 0; i < boardSize + 1; i++) {
            SingleRowPanel rowPanel = new SingleRowPanel(jFrame, counter);
            for(int j = 0; j <= 3*boardSize-i; j++) {
                rowPanel.addCircle();
            }
            boardRow[counter++] = rowPanel;
            this.add(rowPanel);
        }

        // Create lower (center) part of board
        for(int i = 0; i < boardSize; i++) {
            SingleRowPanel rowPanel = new SingleRowPanel(jFrame, counter);
            for(int j = 0; j <= 2*boardSize+1+i; j++) {
                rowPanel.addCircle();
            }
            boardRow[counter++] = rowPanel;
            this.add(rowPanel);
        }

        // Create bottom triangle
        for(int i = 0; i < boardSize; i++) {
            SingleRowPanel rowPanel = new SingleRowPanel(jFrame, counter);
            for(int j = 0; j < boardSize-i; j++) {
                rowPanel.addCircle();
            }
            boardRow[counter++] = rowPanel;
            this.add(rowPanel);
        }

        GridLayout gridLayout = new GridLayout(0, 1);
        gridLayout.setVgap(1);
        gridLayout.setHgap(1);
        this.setLayout(gridLayout);

        this.setBounds((int) (0.2*jFrame.getWidth()), (int) (0.1*jFrame.getHeight()),
                (int) (0.4*jFrame.getWidth()), (int) (0.5*jFrame.getHeight()));


    }


    private int getAmountOfRows() {
        return 4 * boardSize + 1;
    }


}
