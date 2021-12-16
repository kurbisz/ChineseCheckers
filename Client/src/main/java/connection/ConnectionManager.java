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
    private Messenger msg;
    private Receiver rec;
    public ConnectionManager() {
        this.msg = Messenger.getInstance();
        this.rec = Receiver.getInstance();
        Messenger.setCMD(this);
        Receiver.setCMD(this);
    }

    public void createNewConnection(String address, int port) throws UnknownHostException, IOException {
        this.serverAddress = address;
        this.serverPort = port;
        connect();
    }

    private void connect() throws UnknownHostException, IOException {
        socket = new Socket(serverAddress, serverPort);
        scanner = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);
    }
    public void send(String msg) {
        writer.println(msg);
    }
    public Scanner getScanner() {
        return this.scanner;
    }


}
