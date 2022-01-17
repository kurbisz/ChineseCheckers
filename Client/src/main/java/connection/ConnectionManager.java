package connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Class responsible for handling the connection.
 */
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

    /**
     * Create new connection.
     * @param address server address
     * @param port server port
     * @param nick your nickname
     * @throws UnknownHostException
     * @throws IOException
     * @throws NoConnectionException
     */
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
        msg.join();
        /*
        or msg.wait()
        TODO add popup where user makes a choice.
        */
        synchronized (this) {
            try {
                wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        msg.name(nickName);
        Receiver.getInstance().listen();
    }

    /**
     * Send message to server.
     * @param msg message
     * @throws NoConnectionException
     */
    public void send(String msg) throws NoConnectionException {
        if(writer == null) {
            throw new NoConnectionException("No writer is set!");
        }
        writer.println(msg);
    }

    /**
     * Get input from server.
     * @return input stream from server
     */
    public Scanner getScanner() {
        return this.scanner;
    }


}
