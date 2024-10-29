package dto;

import java.io.Serializable;

public class PlayerInfoDTO implements Serializable{
    
    private String username;
    private String status;
    private int totalPoint;
    private int totalWins;
    private int totalLosese;

    public PlayerInfoDTO() {
    }

    public PlayerInfoDTO(String username, String status, int totalPoint) {
        this.username = username;
        this.status = status;
        this.totalPoint = totalPoint;
    }

    public PlayerInfoDTO(String username, int totalPoint, int totalWins, int totalLosese) {
        this.username = username;
        this.totalPoint = totalPoint;
        this.totalWins = totalWins;
        this.totalLosese = totalLosese;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
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
