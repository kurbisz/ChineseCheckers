package client.graphics.components;

import connection.Messenger;
import connection.NoConnectionException;

import javax.swing.*;
import java.awt.*;

public class ViewerButtonsPanel extends Panel {

    private JButton nextButton;
    private JButton previousButton;

    /**
     * Responsible for next and previous buttons
     * on the bottom of viewer application.
     * @param frame actual jFrame of an application
     */
    public ViewerButtonsPanel(JFrame frame) {
        super(frame);
    }

    /**
     * Creates new buttons with 'Next move'
     * and 'Previous move' text.
     */
    @Override
    public void initialize() {

        this.setBounds((int) (0.35 * jFrame.getWidth()),
                (int) (0.8 * jFrame.getHeight()),
                (int) (0.5 * jFrame.getWidth()),
                (int) (0.1 * jFrame.getHeight()));

        this.setLayout(new GridLayout(1, 2));

        previousButton = new JButton("Previous move");
        previousButton.setPreferredSize(new Dimension(
                (int) (0.4 * getWidth()),
                (int) (0.8 * getHeight())));
        previousButton.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        previousButton.addActionListener(e -> {
            try {
                Messenger.getInstance().next();
            } catch (NoConnectionException ex) { }
        });
        this.add(previousButton);

        nextButton = new JButton("Next move");
        nextButton.setPreferredSize(new Dimension(
                        (int) (0.8 * getWidth()),
                        (int) (0.8 * getHeight())));
        nextButton.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        nextButton.addActionListener(e -> {
            try {
                Messenger.getInstance().next();
            } catch (NoConnectionException ex) { }
        });
        this.add(nextButton);

    }

    /**
     * Getter for private variable nextButton.
     * @return actual next button shown in application
     */
    public JButton getNextButton() {
        return nextButton;
    }

    /**
     * Getter for private variable previousButton.
     * @return actual previous button shown in application
     */
    public JButton getPreviousButton() {
        return previousButton;
    }

}
