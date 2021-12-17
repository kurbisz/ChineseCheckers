package connection;

public interface Interpreter {
    void setPlayers(String s);
    void move(int from_row, int from_col, int to_row, int to_col);
    void message(String substring);
    void start();
    void victory();
    void defeat(String name);
    void left();
    void size(int size, int players);
    void numPlayers(int players);
    void setField(int row, int col, int id);
    void turn();
}
