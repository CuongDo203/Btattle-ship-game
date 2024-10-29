package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MatchHistory implements Serializable{
    
    private int gameID;
    private Timestamp startTime;
    private Timestamp endTime;
    private String opponentUserName;
    private String matchResult;

    public MatchHistory() {
    }

    public MatchHistory(int gameID, Timestamp startTime, Timestamp endTime, String opponentUserName, String matchResult) {
        this.gameID = gameID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.opponentUserName = opponentUserName;
        this.matchResult = matchResult;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getOpponentUserName() {
        return opponentUserName;
    }

    public void setOpponentUserName(String opponentUserName) {
        this.opponentUserName = opponentUserName;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }
    
}
