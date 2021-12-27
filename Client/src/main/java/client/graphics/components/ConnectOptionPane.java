package client.graphics.components;

import client.CheckersClient;
import connection.NoConnectionException;

import javax.swing.*;
import java.io.IOException;
import java.net.UnknownHostException;

public class ConnectOptionPane {

    /**
     * Create new option pane for a client.
     * Gets nickname, address and port of a server
     * which client wants to connect.
     * @param jFrame actual jFrame of application
     */
    public static void popup(JFrame jFrame) {
        JTextField nickField = new JTextField("lessnop", 20);
        JTextField addressField = new JTextField("localhost", 20);
        JTextField portField = new JTextField("59898", 20);

        Object panels[] = {
                "Nickname: ", nickField,
                "Address: ", addressField,
                "Port: ", portField,
        };
        int result = JOptionPane.CANCEL_OPTION;
        if(CheckersClient.isHumanMode()) {
            result = JOptionPane.showConfirmDialog(null, panels,
                    "Connect to the server: ", JOptionPane.OK_CANCEL_OPTION);
        }

        if (result == JOptionPane.OK_OPTION) {
            String serverAddress = addressField.getText();
            String port = portField.getText();
            String nickName = nickField.getText();
            int serverPort;
            if (nickName.length() < 5) {
                jFrame.dispose();
                System.out.println("Error! Your nickname is too short!");
            }
            else {
                try {
                    serverPort = Integer.parseInt(port);
                    CheckersClient.getInstance().connectClientToServer(serverAddress, serverPort, nickName);
                } catch (NumberFormatException e) {
                    jFrame.dispose();
                    System.out.println("Error! Given port was not a number!");
                } catch (UnknownHostException e) {
                    jFrame.dispose();
                    System.out.println("Error! There is no such server!");
                } catch (IOException | NoConnectionException e) {
                    jFrame.dispose();
                    System.out.println("Error while connecting to server!");
                }
            }
        }
        else jFrame.dispose();
    }

}
