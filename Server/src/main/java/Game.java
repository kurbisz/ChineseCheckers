import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players= new ArrayList<>();
    private Player currentPlayer = null;
    private Notifer notifer = Notifer.getInstance();
    public Player createPlayer(Socket accept, int i)
    {
        Player p = new Player(accept, i, this);
        players.add(p);
        return p;
    }
    public void start()
    {
        if(players.size()<2||players.size()==5)
            return;
        this.currentPlayer = players.get(0);
        int i;
        for (i=0;i<players.size()-1;i++)
        {
            Player n = players.get(i+1);
            players.get(0).setNext(n);
        }
        Player n0 = players.get(0);
        players.get(i).setNext(n0);
    }
    public synchronized void move(Player p, int from, int to)
    {
        currentPlayer = currentPlayer.getNext();
    }
    public List<Player> getAllPlayers(){
        return players;
    }
}
