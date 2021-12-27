package connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ConnectionManager {

    private String serverAddress;
    private int serverPort;
    private String nickName;

    private Socket socket;
    private Scanner scanner;
    private PrintWriter writer;
    private Messenger msg;
    private Receiver rec;
    public ConnectionManager() {
        Messenger.setCMD(this);
        Receiver.setCMD(this);
        this.msg = Messenger.getInstance();
        this.rec = Receiver.getInstance();
    }

    public void createNewConnection(String address, int port, String nick)
            throws UnknownHostException, IOException, NoConnectionException {
        this.serverAddress = address;
        this.serverPort = port;
        this.nickName = nick;
        connect();
    }

    private void connect() throws UnknownHostException, IOException, NoConnectionException {
        socket = new Socket(serverAddress, serverPort);
        scanner = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);
        msg.name(nickName);
        Receiver.getInstance().listen();
    }
    public void send(String msg) throws NoConnectionException {
        if(writer == null) {
            throw new NoConnectionException("No writer is set!");
        }
        writer.println(msg);
    }
    public Scanner getScanner() {
        return this.scanner;
    }


}
