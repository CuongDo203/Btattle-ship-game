package model;

import java.io.Serializable;

public class Ranking implements Serializable{
    
    private int id;
    private int playerId;
    private int totalPoints;
    private int totalWins;
    private int totalLosese;

    public Ranking() {
    }

    public Ranking(int id, int playerId, int totalPoints, int totalWins, int totalLosese) {
        this.id = id;
        this.playerId = playerId;
        this.totalPoints = totalPoints;
        this.totalWins = totalWins;
        this.totalLosese = totalLosese;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public int getTotalLosese() {
        return totalLosese;
    }

    public void setTotalLosese(int totalLosese) {
        this.totalLosese = totalLosese;
    }
    
}
