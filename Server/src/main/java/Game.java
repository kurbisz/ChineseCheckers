import exceptions.CannotStartGameException;
import exceptions.InvalidMoveException;
import exceptions.InvalidPlayerException;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<>();
    private Player currentPlayer = null;
    private Notifer notifer = Notifer.getInstance();
    private int size = 4;

    public Game(int size) {
        this.size = size;
    }

    public Player createPlayer(Socket accept, int i) {
        Player p = new Player(accept, i, this);
        players.add(p);
        System.out.println("New player connected");
        return p;
    }
    public void start() throws CannotStartGameException {
        if (players.size() < 2 || players.size() == 5) {
            throw new CannotStartGameException();
        }
        this.currentPlayer = players.get(0);
        int i;
        for (i = 0; i < players.size() - 1; i++) {
            Player n = players.get(i + 1);
            players.get(0).setNext(n);
        }
        Player n0 = players.get(0);
        players.get(i).setNext(n0);
        //TODO POSITION
        for (Player p : players) {
            p.notifyStart();
        }
    }
    public synchronized void move(Player p, int from, int to) throws InvalidMoveException, InvalidPlayerException {
        //TODO
        switchPlayer(p);
    }
    public void switchPlayer(Player p) throws InvalidPlayerException {
        currentPlayer = currentPlayer.getNext();
        currentPlayer.notify("MESSAGE Your move");
        notifer.notifyAllExceptPlayer("MESSAGE Wait for other player to move", this, currentPlayer);
    }
    public List<Player> getAllPlayers() {
        return players;
    }
    public int getSize(){
        return this.size;
    }
}
