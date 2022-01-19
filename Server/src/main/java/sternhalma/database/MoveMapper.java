package sternhalma.database;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MoveMapper implements RowMapper<MoveEntry> {
    public MoveEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        MoveEntry move = new MoveEntry();
        move.setFromC(rs.getInt("fromC"));
        move.setFromR(rs.getInt("fromR"));
        move.setToR(rs.getInt("toR"));
        move.setToC(rs.getInt("toC"));
        move.setPlayer(rs.getInt("player"));
        return move;
    }
}