package client.graphics.components;

import client.CheckersClient;

import javax.swing.*;
import java.awt.*;

public final class ChooseGameOptionPane {

    public static void popup(final JFrame jFrame, final String[] games) {
        ChoosePanel choosePanel = new ChoosePanel("Wybierz gre:", games);
        Object[] panels = {
                choosePanel
        };
        int result = JOptionPane.CANCEL_OPTION;
        if (CheckersClient.isHumanMode()) {
            result = JOptionPane.showConfirmDialog(null, panels,
                    "Connect to the server: ", JOptionPane.OK_CANCEL_OPTION);
        }
        if(result == JOptionPane.OK_OPTION) {
            // TODO
        } else {
            exit(jFrame);
        }

    }

    private static class ChoosePanel extends JPanel {

        private JComboBox choiceBox;

        public ChoosePanel(String text, String options[]) {
            super();
            this.setLayout(new GridLayout(0, 1));

            JLabel label = new JLabel(text);
            label.setAlignmentY(BOTTOM_ALIGNMENT);
            this.add(label);

            choiceBox = new JComboBox(options);
            this.add(choiceBox);

        }

        public String getSelected() {
            return choiceBox.getSelectedItem().toString();
        }

    }

    private static void exit(JFrame jFrame) {
        jFrame.dispose();
        if (CheckersClient.isHumanMode()) {
            System.exit(0);
        }
    }

}
