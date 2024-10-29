package dto;

import java.io.Serializable;

public class LoginDTO implements Serializable{
    private String username;
    private String password;
//    private int command;
//
//    public int getCommand() {
//        return command;
//    }
//
//    public void setCommand(int command) {
//        this.command = command;
//    }
    
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
    
}