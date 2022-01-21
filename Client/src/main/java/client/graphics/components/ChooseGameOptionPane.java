package client.graphics.components;

import client.CheckersClient;
import connection.Messenger;
import connection.NoConnectionException;

import javax.swing.*;
import java.awt.*;

public final class ChooseGameOptionPane {

    /**
     * Create new option pane for a client
     * with choosing game to show.
     * @param jFrame actual jFrame of application
     * @param index array of identities of games to show
     * @param games array of names of games to show
     */
    public static void popup(final JFrame jFrame, int[] index, String[] games) {
        ChoosePanel choosePanel = new ChoosePanel("Choose a game:", index, games);
        Object[] panels = {
                choosePanel
        };
        int result = JOptionPane.CANCEL_OPTION;
        if (CheckersClient.isHumanMode()) {
            result = JOptionPane.showConfirmDialog(null, panels,
                    " ", JOptionPane.OK_CANCEL_OPTION);
        }
        if(result == JOptionPane.OK_OPTION) {
            try {
                Messenger.getInstance().select(choosePanel.getChooseId());
            } catch (NoConnectionException e) {
                exit(jFrame);
                System.out.println("Error while connecting to server!");
            }
        } else {
            exit(jFrame);
        }

    }

    private static class ChoosePanel extends JPanel {

        private JComboBox choiceBox;
        private int chooseId[];

        public ChoosePanel(String text, int id[], String options[]) {
            super();
            String[] optionsStr = new String[options.length];
            for(int i = 0; i < options.length; i++) {
                optionsStr[i] = id[i] + ": " + options[i];
            }

            this.chooseId = id;
            this.setLayout(new GridLayout(0, 1));

            JLabel label = new JLabel(text);
            label.setAlignmentY(BOTTOM_ALIGNMENT);
            this.add(label);

            choiceBox = new JComboBox(optionsStr);
            this.add(choiceBox);

        }

        public String getSelected() {
            return choiceBox.getSelectedItem().toString();
        }

        public int getChooseId() {
            return chooseId[choiceBox.getSelectedIndex()];
        }
    }

    private static void exit(JFrame jFrame) {
        jFrame.dispose();
        if (CheckersClient.isHumanMode()) {
            System.exit(0);
        }
    }

}
