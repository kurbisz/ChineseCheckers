package connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ConnectionManager {

    private String serverAddress;
    private int serverPort;

    private Socket socket;
    private Scanner scanner;
    private PrintWriter writer;


    public void createNewConnection(String address, int port) throws UnknownHostException, IOException {
        this.serverAddress = address;
        this.serverPort = port;
        connect();
    }

    private void connect() throws UnknownHostException, IOException {
        socket = new Socket(serverAddress, serverPort);
        scanner = new Scanner(socket.getInputStream());
        // TODO change to false when finished project
        // TODO true means printing socket's messages to console
        writer = new PrintWriter(socket.getOutputStream(), true);
    }



}
