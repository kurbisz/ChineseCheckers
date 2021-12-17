package sternhalma;

public interface NotiferInterface {
    void notifyAll(String msg, Game game);
    void notifyAllExceptPlayer(String msg, Game game, Player player);
}
