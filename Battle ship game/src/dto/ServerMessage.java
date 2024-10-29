package dto;

import java.io.Serializable;
import java.util.List;
import model.MatchHistory;
import model.Player;
import model.Ranking;

public class ServerMessage implements Serializable{
    public static final int LOGIN_SUCCESS = 1;
    public static final int LOGIN_FAILED = 2;
    public static final int OUT = 3;
    public static final int LIST_PLAYER = 4;
    public static final int UPDATE_PLAYER_LIST = 5;
    public static final int PLAYER_FOUND = 6;
    public static final int PLAYER_LOGOUT = 7;
    public static final int READY_TO_PLAY = 8;
    public static final int OPPONENT_NOT_READY = 9;
    public static final int SHOOT = 10;
    public static final int HIT = 11;
    public static final int MISS = 12;
    public static final int MATCH_HISTORY = 13;
    public static final int IS_YOUR_TURN = 14;
    public static final int RANKING = 15;
    public static final int PLAYER_ALREADY_EXISTS = 16;
    public static final int REGISTER_SUCCESSFULLY = 17;
    
    private int command;
    private Player player;
    List<PlayerInfoDTO> listPlayer;
    private int[][] matrix;
    private int row;
    private int col;
    private boolean yourTurn;
    private int gameID;
    private Ranking playerHistory;
    private List<MatchHistory> matchHistory;
    private boolean outWhilePlaying;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public List<PlayerInfoDTO> getListPlayer() {
        return listPlayer;
    }

    public void setListPlayer(List<PlayerInfoDTO> listPlayer) {
        this.listPlayer = listPlayer;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public Ranking getPlayerHistory() {
        return playerHistory;
    }

    public void setPlayerHistory(Ranking playerHistory) {
        this.playerHistory = playerHistory;
    }

    public List<MatchHistory> getMatchHistory() {
        return matchHistory;
    }

    public void setMatchHistory(List<MatchHistory> matchHistory) {
        this.matchHistory = matchHistory;
    }

    public boolean isOutWhilePlaying() {
        return outWhilePlaying;
    }

    public void setOutWhilePlaying(boolean outWhilePlaying) {
        this.outWhilePlaying = outWhilePlaying;
    }
    
}
