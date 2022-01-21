package sternhalma.database;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper<GameEntry> {
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