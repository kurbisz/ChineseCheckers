package connection;

public interface Interpreter {
    void setPlayers(String s);
    void move(int from, int to);
    void message(String substring);
    void start();
    void victory();
    void defeat(String name);
    void left();
    void size(int size, int players);
    void numPlayers(int players);
}
