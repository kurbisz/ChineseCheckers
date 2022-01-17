package sternhalma.database;


public interface Writer {
    /**
     * Save game representation together with moves
     * @param entry game representation
     */
    void addGame(GameEntry entry);
}
