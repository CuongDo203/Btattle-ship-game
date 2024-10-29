package dao;

import dto.PlayerInfoDTO;
import java.util.ArrayList;
import java.util.List;
import model.Player;

/**
 *
 * @author win
 */
public class PlayerDAO extends DAO{

    public PlayerDAO() {
        super();
    }
    
    public Player login(String username, String password) {
        try {
            // Query the database to validate username and password
            String query = "SELECT * FROM player WHERE username = ? AND password = ?";
            var preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            var resultSet = preparedStatement.executeQuery();
            Player player = null;
            if(resultSet.next()) {
                player = new Player();
                String updateStatusQuery = "UPDATE player SET status = 'online' WHERE username = ?";
                var updatePreparedStatement = con.prepareStatement(updateStatusQuery);
                updatePreparedStatement.setString(1, username);
                updatePreparedStatement.executeUpdate();
                player.setId(resultSet.getInt("player_id"));
                player.setUsername(resultSet.getString("username"));
                player.setPassword(resultSet.getString("password"));
                player.setEmail(resultSet.getString("email"));
                player.setStatus(resultSet.getString("status"));
                return player;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean logout(String username) {
        boolean res = false;
        try{
            String sql = "update player set status = ? where username = ?";
            var prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, "offline");
            prepareStatement.setString(2, username);
            var rs = prepareStatement.executeUpdate();
            res = rs > 0;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public List<PlayerInfoDTO> getListPlayerInfo() {
        List<PlayerInfoDTO> players = null;
        try {
            String query = "SELECT p.username, p.status, r.total_points " +
                           "FROM player p " +
                           "LEFT JOIN ranking r ON p.player_id = r.player_id";
            var preparedStatement = con.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if(players == null) players = new ArrayList<>();
                PlayerInfoDTO player = new PlayerInfoDTO();
                player.setUsername(resultSet.getString("username"));
                player.setStatus(resultSet.getString("status"));
                player.setTotalPoint(resultSet.getInt("total_points"));
                players.add(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }
    
    public boolean updatePlayerStatus(String username, String status) {
        boolean res = false;
        try {
            String sql = "update player set status = ? where username = ?";
            var prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1, status);
            prepareStatement.setString(2, username);
            var rs = prepareStatement.executeUpdate();
            res = rs > 0;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    public Player getPlayerByUsername(String username) {
        try{
            String sql = "select * from player where username = ?";
            var ps = con.prepareStatement(sql);
            ps.setString(1, username);
            var rs = ps.executeQuery();
            Player player = null;
            if(rs.next()) {
                player = new Player();
                player.setId(rs.getInt("player_id"));
                player.setUsername(rs.getString("username"));
            }
            return player;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<PlayerInfoDTO> getRankings() {
        List<PlayerInfoDTO> rankings = null;
        try{
            String sql = """
                         SELECT 
                             p.username,
                             r.total_wins,
                             r.total_losese,
                             r.total_points
                         FROM 
                             Player p
                         JOIN 
                             Ranking r ON p.player_id = r.player_id
                         ORDER BY 
                             r.total_points DESC, r.total_wins DESC;
                         """;
            var ps = con.prepareStatement(sql);
            var rs = ps.executeQuery();
            while(rs.next()) {
                if(rankings==null) rankings = new ArrayList<>();
                PlayerInfoDTO player = new PlayerInfoDTO(rs.getString("username"), 
                        rs.getInt("total_points"), 
                        rs.getInt("total_wins"), 
                        rs.getInt("total_losese"));
                rankings.add(player);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return rankings;
    }
    
    public boolean existsByUsernameOrEmail(Player player) {
        boolean rs = false;
        try {
            String sql = "select * from player where username = ? or email = ?";
            var ps = con.prepareStatement(sql);
            ps.setString(1, player.getUsername());
            ps.setString(2, player.getEmail());
            var resultSet = ps.executeQuery();
            if(resultSet.next()) {
                rs = true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public void register(Player player) {
        try {
            String sql = "insert into player(username, password, email) value (?,?,?)";
            var ps = con.prepareStatement(sql);
            ps.setString(1, player.getUsername());
            ps.setString(2, player.getPassword());
            ps.setString(3, player.getEmail());
            ps.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
