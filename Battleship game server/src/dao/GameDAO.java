package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Game;
import model.MatchHistory;

/**
 *
 * @author win
 */
public class GameDAO extends DAO {

    public GameDAO() {
        super();
    }

    public Game createNewGame(Game game) {
        try {
            String sql = "insert into game (player1_id,player2_id,game_status,start_time) values (?,?,?,?)";
            var prepareStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setInt(1, game.getPlayer1().getId());
            prepareStatement.setInt(2, game.getPlayer2().getId());
            prepareStatement.setString(3, game.getStatus());
            prepareStatement.setTimestamp(4, game.getStart());
            var rs = prepareStatement.executeUpdate();
            if (rs == 0) {
                throw new Exception("Tạo game lỗi");
            }
            try (ResultSet generatedKey = prepareStatement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    game.setId(generatedKey.getInt(1));
                } else {
                    throw new SQLException("Không lấy được ID game vừa tạo");
                }
            }
            return game;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateGameOverStatus(String status, int gameID, int winnerID) {
        boolean result = false;
        try {
            String sql = "update game set game_status = ?, winner_id = ?, end_time = ? where game_id = ?";
            var prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, status);
            prepareStatement.setInt(2, winnerID);
            LocalDateTime now = LocalDateTime.now();
            Timestamp end = Timestamp.valueOf(now);
            prepareStatement.setTimestamp(3, end);
            prepareStatement.setInt(4, gameID);
            int rc = prepareStatement.executeUpdate();
            result = rc > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MatchHistory> getPlayerMatchHistory(int playerID) {
        List<MatchHistory> lsd = null;
        try{
            String sql = 
                    """
                    SELECT 
                             g.game_id,
                             g.start_time,
                             g.end_time,
                             opp.username AS opponent_username,
                             CASE 
                                 WHEN g.winner_id = ? THEN 'Thắng'
                                 WHEN g.winner_id IS NOT NULL AND g.winner_id != ? THEN 'Thua'
                                 ELSE 'Hòa'
                             END AS match_result
                         FROM 
                             Game g
                         JOIN 
                             Player p ON (g.player1_id = p.player_id OR g.player2_id = p.player_id)
                         LEFT JOIN 
                             Player opp ON (opp.player_id = CASE WHEN g.player1_id = ? THEN g.player2_id ELSE g.player1_id END)
                         WHERE 
                             p.player_id = ?
                         ORDER BY 
                             g.start_time DESC;     
                    """;
            var ps = con.prepareStatement(sql);
            ps.setInt(1, playerID);
            ps.setInt(2, playerID);
            ps.setInt(3, playerID);
            ps.setInt(4, playerID);
            var rs = ps.executeQuery();
            while(rs.next()) {
                if(lsd == null) lsd = new ArrayList<>();
                MatchHistory match = new MatchHistory();
                match.setGameID(rs.getInt("game_id"));
                match.setStartTime(rs.getTimestamp("start_time"));
                match.setEndTime(rs.getTimestamp("end_time"));
                match.setOpponentUserName(rs.getString("opponent_username"));
                match.setMatchResult(rs.getString("match_result"));
                lsd.add(match);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return lsd;
    }
}
