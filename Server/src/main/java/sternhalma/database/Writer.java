package sternhalma.database;


public interface Writer {
    void addGame(GameEntry entry);
    void addMove(MoveEntry entry);

}
