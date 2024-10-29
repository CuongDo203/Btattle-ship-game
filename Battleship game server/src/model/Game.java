package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Game {
    private int id;
    private Player player1;
    private Player player2;
    private int winnerId;
    private String status;
    private Timestamp start;
    private Timestamp end;

    public Game() {
    }

    public Game(Player player1, Player player2, Timestamp start, String status) {
        this.player1 = player1;
        this.player2 = player2;
        this.start = start;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
    
}
