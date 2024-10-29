package model;

import java.net.Socket;
import java.util.Objects;

public class Client {
    
    private Socket socket;
    private int[][] matrix;
    private String username;

    private Client opponent;
    

    public Client(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Client getOpponent() {
        return opponent;
    }

    public void setOpponent(Client opponent) {
        this.opponent = opponent;
    }
    
}
