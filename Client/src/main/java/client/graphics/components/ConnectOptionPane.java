package client.graphics.components;

import client.CheckersClient;
import client.ClientType;
import connection.NoConnectionException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.net.UnknownHostException;

public final class ConnectOptionPane {

    private static int minNickNameLength = 2;

    private ConnectOptionPane() { }
    /**
     * Create new option pane for a client.
     * Gets nickname, address and port of a server
     * which client wants to connect.
     * @param jFrame actual jFrame of application
     */
    public static void popup(final JFrame jFrame) {
        JTextField nickField = new JTextField("lessnop", 20);
        JTextField addressField = new JTextField("localhost", 20);
        JTextField portField = new JTextField("59898", 20);
        TypePanel typeField = new TypePanel("Typ:",
                new String[]{ClientType.PLAYER.getName(),
                        ClientType.OBSERVER.getName()});

        Object[] panels = {
                "Nickname: ", nickField,
                "Address: ", addressField,
                "Port: ", portField,
                typeField
        };
        int result = JOptionPane.CANCEL_OPTION;
        if (CheckersClient.isHumanMode()) {
            result = JOptionPane.showConfirmDialog(null, panels,
                    "Connect to the server: ", JOptionPane.OK_CANCEL_OPTION);
        }

        if (result == JOptionPane.OK_OPTION) {
            String serverAddress = addressField.getText();
            String port = portField.getText();
            String nickName = nickField.getText();
            ClientType clientType = ClientType.getByString(typeField.getSelected());
            int serverPort;
            if (nickName.length() < minNickNameLength) {
                exit(jFrame);
                System.out.println("Error! Your nickname is too short!");
            } else {
                try {
                    serverPort = Integer.parseInt(port);
                    CheckersClient.getInstance().connectClientToServer(
                            serverAddress, serverPort, nickName, clientType);
                } catch (NumberFormatException e) {
                    exit(jFrame);
                    System.out.println("Error! Given port was not a number!");
                } catch (UnknownHostException e) {
                    exit(jFrame);
                    System.out.println("Error! There is no such server!");
                } catch (IOException | NoConnectionException e) {
                    exit(jFrame);
                    System.out.println("Error while connecting to server!");
                }
            }
        } else {
            exit(jFrame);
        }
    }

    private static class TypePanel extends JPanel {

        private JComboBox choiceBox;

        public TypePanel(String text, String options[]) {
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
