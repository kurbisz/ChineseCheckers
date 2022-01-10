package connection;

public interface Interpreter {
    /**
     * Set names of all players.
     * @param s String containing all players' names seperated by '$'
     */
    void setPlayers(String s);

    /**
     * Move piece from source field to target field.
     * @param from_row number of row of source field
     * @param from_col number in row of source field
     * @param to_row number of row of target field
     * @param to_col number in row of target field
     */
    void move(int from_row, int from_col, int to_row, int to_col);

    /**
     * Server sent a message.
     * @param substring message from server
     */
    void message(String substring);

    /**
     * Game has been started.
     */
    void start();

    /**
     * You have won!
     */
    void victory();

    /**
     * You have lost.
     * @param name winner's name
     */
    void defeat(String name);

    /**
     * One of the players left the game.
     */
    void left();

    /**
     * Set size of the board.
     * @param size size of the board
     * @param players number of players.
     */
    void size(int size, int players);

    /**
     * Set number of players.
     * @param players number of players
     */
    void numPlayers(int players);

    /**
     * Set owner of the field
     * @param row row of the field
     * @param col number in row of the field
     * @param id owner id
     */
    void setField(int row, int col, int id);

    /**
     * Change the turn of players.
     * @param id id of current turn player
     */
    void changeTurn(int id);

    /**
     * It's your turn.
     */
    void setTurn();

    /**
     * Id you were give by the server.
     * @param id your id
     */
    void setClientNumber(int id);
}
