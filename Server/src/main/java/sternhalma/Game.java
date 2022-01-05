package sternhalma;

import sternhalma.board.*;
import sternhalma.exceptions.CannotStartGameException;
import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing one game.
 */
public class Game {
    private List<Player> players = new ArrayList<>();
    private Player currentPlayer = null;
    private NotiferInterface notifer = Notifer.getInstance();
    private int size;
    private BoardInterface board;
    private StartingInterface start;
    private FinishInterface finish;
    private boolean running = false;

    /**
     * Create the game of specific board's size.
     * @param size size of the boards
     */
    public Game(int size) {
        this.size = size;
        FactoryProducer fp = FactoryProducer.getInstance();
        RulesFactory rules = fp.getFactory("classic");
        MovingInterface mv = rules.getMoving();
        this.board = rules.getBoard(size, mv);
        this.start = rules.getStart(board, size, this);
        this.finish = rules.getFinish(board, size, this);
    }

    /**
     * Create new player out of a socket and add him to the game.
     * @param accept socket
     * @param i player id
     * @return Player
     */
    public Player createPlayer(Socket accept, int i) {
        if (running || players.size() == 6) {
            return null;
        }
        Player p = new Player(accept, i, this);
        players.add(p);
        System.out.println("New player connected");
        return p;
    }

    /**
     * Start a game
     * @throws CannotStartGameException game cannot be started
     */
    public void start() throws CannotStartGameException {
        if (running || players.size() < 2 || players.size() == 5) {
            throw new CannotStartGameException();
        }
        this.currentPlayer = players.get(0);
        int i;
        for (i = 0; i < players.size() - 1; i++) {
            Player n = players.get(i + 1);
            players.get(i).setNext(n);
        }
        Player n0 = players.get(0);
        players.get(i).setNext(n0);
        start.prepare(players.size());
        for (Player p : players) {
            p.notifyStart();
        }
        running = true;
        notifer.notifyAll("TURN " + currentPlayer.getId(), this);
        currentPlayer.notify("TURNSET");
    }

    /**
     * Check if game is finished.
     */
    public void checkFinished() {
        int p = finish.checkEnd(players.size());
        if (p == -1) {
            return;
        }
        Player winner = players.get(p);
        running = false;
        winner.notify("VICTORY");
        notifer.notifyAllExceptPlayer(
                "DEFEAT#" + winner.getName(), this, winner);
    }

    /**
     * Proceed move of piece
     * @param p Player who performed a move
     * @param fromR row from which to move
     * @param fromC column from which to move
     * @param toR row to which to move
     * @param toC column to which to move
     * @throws InvalidMoveException move cannot be proceeded
     * @throws InvalidPlayerException move cannot be proceeded
     */
    public synchronized void move(
            Player p, int fromR, int fromC, int toR, int toC)
            throws InvalidMoveException, InvalidPlayerException {
        if (p != currentPlayer) {
            throw new InvalidPlayerException();
        }
        board.move(p.getId(), fromR, fromC, toR, toC);
        notifer.notifyAll(String.format(
                "MOVE %d %d %d %d", fromR, fromC, toR, toC), this);
        checkFinished();
    }

    /**
     * End turn of player.
     * @param p player who wants to end his turn
     * @throws InvalidPlayerException not proper turn
     */
    public void switchPlayer(Player p) throws InvalidPlayerException {
        if (p != currentPlayer) {
            throw new InvalidPlayerException();
        }
        board.endMove();
        currentPlayer = currentPlayer.getNext();
        notifer.notifyAll("TURN " + currentPlayer.getId(), this);
        currentPlayer.notify("TURNSET");
    }

    /**
     * Get all players in game.
     * @return list of players in game
     */
    public List<Player> getAllPlayers() {
        return players;
    }

    /**
     * Get size of the game.
     * @return size of the game
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Get number of players.
     * @return number of players
     */
    public int numPlayers() {
        return players.size();
    }

    /**
     * Send players' names to all players.
     */
    public void sendNames() {
        String msg = "";
        for (Player p: players) {
            msg += p.getName() + "$";
        }
        notifer.notifyAll("PLAYERS#" + msg, this);
    }

    /**
     * Player left the game.
     * @param p player who left the game
     */
    public void leave(Player p) {
        notifer.notifyAll("MESSAGE PLAYER " + p.getId() + " LEFT", this);
        if (!running) {
            players.remove(p);
            sendNames();
        }
        if (running) {
            notifer.notifyAll("LEFT", this);
        }
    }

    /**
     * Checks if another player can join the game.
     * @return true if player can join
     */
    public boolean canJoin() {
        if (running || players.size() == 6) {
            return false;
        }
        return true;
    }
}
