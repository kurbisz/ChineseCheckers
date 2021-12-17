package sternhalma;

import sternhalma.board.Board;
import sternhalma.board.Start;
import sternhalma.exceptions.CannotStartGameException;
import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<>();
    private Player currentPlayer = null;
    private NotiferInterface notifer = Notifer.getInstance();
    private int size = 4;
    private Board board;
    private Start start;
    public Game(int size) {
        this.size = size;
        this.board = new Board(size);
        this.start = new Start(board, size, this);

    }

    public Player createPlayer(Socket accept, int i) {
        Player p = new Player(accept, i, this);
        players.add(p);
        System.out.println("New player connected");
        notifer.notifyAllExceptPlayer("NUM "+numPlayers(),this, p);
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

        start.prepare(players.size());
        for (Player p : players) {
            p.notifyStart();
        }
    }
    public synchronized void move(Player p, int fromR, int fromC, int toR, int toC) throws InvalidMoveException, InvalidPlayerException {
        if (p!=currentPlayer) {
            throw new InvalidPlayerException();
        }
        board.move(p.getId(), fromR, fromC, toR, toC);
        notifer.notifyAll(String.format("MOVE %d %d %d %d", fromR, fromC, toR, toC), this);

    }
    public void switchPlayer(Player p) throws InvalidPlayerException {
        if(p!=currentPlayer) {
            throw new InvalidPlayerException();
        }
        board.endMove();
        currentPlayer = currentPlayer.getNext();
        notifer.notifyAll("TURN", this);
        currentPlayer.notify("MESSAGE Your move");
        notifer.notifyAllExceptPlayer("MESSAGE Wait for other player to move", this, currentPlayer);
    }
    public List<Player> getAllPlayers() {
        return players;
    }
    public int getSize(){
        return this.size;
    }
    public int numPlayers() {
        return players.size();
    }
}
