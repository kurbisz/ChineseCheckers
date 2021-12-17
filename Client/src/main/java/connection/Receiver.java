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
                    int fromR = Integer.parseInt(tab[1]);
                    int fromC = Integer.parseInt(tab[2]);
                    int toR = Integer.parseInt(tab[3]);
                    int toC = Integer.parseInt(tab[4]);
                    interpreter.move(fromR, fromC, toR, toC);
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
                    int players = Integer.parseInt(tab[2]);
                    interpreter.size(size, players);
                } else if (response.startsWith("NUM")) {
                    String[] tab = response.split(" ");
                    int players = Integer.parseInt(tab[1]);
                    interpreter.numPlayers(players);
                } else if (response.startsWith("TURN")) {
                    interpreter.turn();
                } else if (response.startsWith("SET")) {
                    String[] tab = response.split(" ");
                    int row = Integer.parseInt(tab[1]);
                    int col = Integer.parseInt(tab[2]);
                    int id = Integer.parseInt(tab[3]);
                    interpreter.setField(row, col, id);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
