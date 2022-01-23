package sternhalma.database;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * MySQL database representation.
 */
public class MySQLDatabase implements Database {
    private static MySQLDatabase insance = new MySQLDatabase();
    public static MySQLDatabase getInstance() {
        return insance;
    }
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    /**
     * Test class behaviour.
     */
    public void test() {
        GameEntry game = new GameEntry(1, 2, "classic");
        game.setTime(new Timestamp(System.currentTimeMillis()));
        MoveEntry m1 = new MoveEntry(1, 1, 1, 1, 1,  game, 1);
        MoveEntry m2 = new MoveEntry(2, 2, 2, 2, 2,  game, 2);
        MoveEntry m3 = new MoveEntry(3, 3, 3, 3, 3,  game, 3);
        List<MoveEntry> st = new LinkedList<>();
        st.add(m1);
        st.add(m2);
        st.add(m3);
        game.setMoves(st);
        addGame(game);
        System.out.println(getGames().get(0).getFormattedTime());
    }
    @Override
    public void addGame(GameEntry entry) {
        if (entry == null) {
            return;
        }
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            KeyHolder key = new GeneratedKeyHolder();
            jdbcTemplateObject.update(new PreparedStatementCreator() {
                String INSERTGAME = "INSERT INTO `games` (boardSize, numPlayers, config, time, players) VALUES (?,?,?,?,?)";

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    final PreparedStatement ps = connection.prepareStatement(INSERTGAME,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, entry.getBoardSize());
                    ps.setInt(2, entry.getNumPlayers());
                    ps.setString(3, entry.getConfig());
                    ps.setTimestamp(4,entry.getTime());
                    ps.setString(5, entry.getPlayersString());
                    return ps;
                }
            }, key);
            long gameid = key.getKey().longValue();
            for (MoveEntry move : entry.getMoves()) {
                String SQL = "INSERT INTO `moves` (gameId,fromR,fromC,toR,toC,player) VALUES (?,?,?,?,?,?)";
                jdbcTemplateObject.update(
                        SQL,
                        gameid,
                        move.getFromR(),
                        move.getFromC(),
                        move.getToR(),
                        move.getToC(),
                        move.getPlayer()
                );
            }
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public List<GameEntry> getGames() {
        String SQL = "SELECT * FROM `games`";
        List<GameEntry> games = jdbcTemplateObject.query(SQL, new GameMapper());
        return games;
    }

    @Override
    public List<MoveEntry> getMoves(int id) {
        String SQL = "SELECT * FROM `moves` WHERE gameId = ?";
        List<MoveEntry> moves = jdbcTemplateObject.query(SQL,new MoveMapper(), id);
        return moves;
    }
    @Override
    public GameEntry getGame(int id) {
        String SQL = "SELECT * FROM `games` where gameId = ?";
        GameEntry game = jdbcTemplateObject.queryForObject(SQL, new GameMapper(), id);
        return game;
    }
}
