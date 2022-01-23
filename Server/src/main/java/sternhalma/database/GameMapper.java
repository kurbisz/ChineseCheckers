package sternhalma.database;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class mapping game from SQL to GameEntry object.
 */
public class GameMapper implements RowMapper<GameEntry> {
    /**
     * Map SQL row to game.
     * @param rs row with game info
     * @param rowNum number of rows
     * @return Configured GameEntry
     * @throws SQLException
     */
    public GameEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        GameEntry game = new GameEntry();
        game.setGameID(rs.getInt("gameId"));
        game.setBoardSize(rs.getInt("boardSize"));
        game.setConfig(rs.getString("config"));
        game.setNumPlayers(rs.getInt("numPlayers"));
        game.setTime(rs.getTimestamp("time"));
        game.setPlayersString(rs.getString("players"));
        return game;
    }
}