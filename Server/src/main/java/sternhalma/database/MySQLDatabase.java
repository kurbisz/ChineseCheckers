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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public void test() {
        GameEntry game = new GameEntry(1, 2, "classic");
        MoveEntry m1 = new MoveEntry(1, 1, 1, 1, 1,  game, 1);
        MoveEntry m2 = new MoveEntry(2, 2, 2, 2, 2,  game, 2);
        MoveEntry m3 = new MoveEntry(3, 3, 3, 3, 3,  game, 3);
        Set<MoveEntry> st = new HashSet<>();
        st.add(m1);
        st.add(m2);
        st.add(m3);
        game.setMoves(st);
        addGame(game);
        System.out.println(getGames());
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
                String INSERTGAME = "INSERT INTO `games` (boardSize, numPlayers, config) VALUES (?,?,?)";

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    final PreparedStatement ps = connection.prepareStatement(INSERTGAME,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, entry.getBoardSize());
                    ps.setInt(2, entry.getNumPlayers());
                    ps.setString(3, entry.getConfig());
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
        List<MoveEntry> moves = jdbcTemplateObject.query(SQL, new Object[]{id},new MoveMapper());
        return moves;
    }
    @Override
    public GameEntry getGame(int id) {
        String SQL = "SELECT * FROM `games` where gameId = ?";
        GameEntry game = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new GameMapper());
        return game;
    }
}
