package dao;

import model.Ranking;

public class RankingDAO extends DAO{
    
    public void plusOnePoint(int userID) {
        try {
            String sql = "update ranking set total_points = total_points + 1, total_wins = total_wins + 1 where player_id = ?";
            var prepareStatement = con.prepareStatement(sql);
            prepareStatement.setInt(1, userID);
            prepareStatement.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addALose(int userID) {
        try{
            String sql = "update ranking set total_losese = total_losese + 1 where player_id = ?";
            var prepareStatement = con.prepareStatement(sql);
            prepareStatement.setInt(1, userID);
            prepareStatement.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public Ranking getRankOfPlayer(int playerID) {
        Ranking rank = null;
        try {
            String sql = "select * from ranking where player_id = ?";
            var prepareStatement = con.prepareStatement(sql);
            prepareStatement.setInt(1, playerID);
            var rs = prepareStatement.executeQuery();
            if(rs.next()) {
                if(rank == null) rank = new Ranking();
                rank.setId(rs.getInt("ranking_id"));
                rank.setPlayerId(rs.getInt("player_id"));
                rank.setTotalPoints(rs.getInt("total_points"));
                rank.setTotalWins(rs.getInt("total_wins"));
                rank.setTotalLosese(rs.getInt("total_losese"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return rank;
    }
    
    
}
