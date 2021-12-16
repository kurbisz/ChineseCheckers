package connection;

import java.util.Scanner;

public class Receiver {
    private static Receiver instance;
    public static Interpreter interpreter;
    private static ConnectionManager cmd;
    private Scanner scanner;
    public static Receiver getInstance() {
        if (instance == null) {
            instance = new Receiver();
        }
        return instance;
    }
    public static void setCMD(ConnectionManager cmd) {
        Receiver.cmd = cmd;
    }
    public static void setInterpreter(Interpreter in) {
        Receiver.interpreter = in;
    }
    public void listen() {
        try {
            scanner = cmd.getScanner();
            String response;
            while (scanner.hasNextLine()) {
                response = scanner.nextLine();
                if (response.startsWith("PLAYERS")) {
                    String[] tab = response.split(" ");
                    interpreter.setPlayers(tab[1]);
                } else if (response.startsWith("MOVE")) {
                    String[] tab = response.split(" ");
                    int from = Integer.parseInt(tab[1]);
                    int to = Integer.parseInt(tab[2]);
                    interpreter.move(from, to);
                } else if (response.startsWith("MESSAGE")) {
                    interpreter.message(response.substring(7));
                } else if (response.startsWith("START")) {
                    interpreter.start();
                } else if (response.startsWith("VICTORY")) {
                    interpreter.victory();
                } else if (response.startsWith("DEFEAT")) {
                    String[] tab = response.split(" ");
                    interpreter.defeat(tab[1]);
                } else if (response.startsWith("LEFT")) {
                    interpreter.left();
                } else if (response.startsWith("SIZE")){
                    String[] tab = response.split(" ");
                    int size = Integer.parseInt(tab[1]);
                    interpreter.size(size);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
