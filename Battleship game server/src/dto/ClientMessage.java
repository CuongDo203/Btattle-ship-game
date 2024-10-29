package dto;

import java.io.Serializable;

public class ClientMessage implements Serializable {
    public static final int LOGIN = 1;
    public static final int LOGOUT = 2;
    public static final int SHOOT = 3;
    public static final int GET_LIST_PLAYER = 4;
    public static final int FIND_RANDOM_PLAYER = 5;
    public static final int PLAYER_OUT = 6;
    public static final int IS_READY = 7;
    public static final int PLACED_SHIPS = 8;
    public static final int WIN = 9;
    public static final int LOSE = 10;
    public static final int CANCEL_FIND_RANDOM_PLAYER = 11;
    public static final int SHOW_MATCH_HISTORY = 12;
    public static final int QUIT_GAME = 13;
    public static final int SWITCH_TURN = 14;
    public static final int GET_RANKING = 15;
    public static final int REGISTER = 16;
    
    private int command;
    private String username;
    private String password;
    private String email;
    private int[][] matrix;
    private int row;
    private int col;
    private int gameID;
    private int playerID;
    private boolean outWhilePlaying;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
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

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public boolean isOutWhilePlaying() {
        return outWhilePlaying;
    }

    public void setOutWhilePlaying(boolean outWhilePlaying) {
        this.outWhilePlaying = outWhilePlaying;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
