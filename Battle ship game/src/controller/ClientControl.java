package controller;

import dto.ClientMessage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import view.LoginFrm;
import view.MainFrm;

public class ClientControl extends Thread {

    private Socket socket;
    private final String SERVER_NAME = "localhost";
    private final int SERVER_PORT = 8888;
    private LoginFrm loginFrm;
//    private MainFrm mainFrm;
    ServerConnector serverConnector;
    private ObjectOutputStream oos;
    private boolean isOpponentReady = false;

    public ClientControl(LoginFrm loginFrm) {
        try {
            socket = new Socket(SERVER_NAME, SERVER_PORT);
            this.loginFrm = loginFrm;
            oos = new ObjectOutputStream(socket.getOutputStream());
            serverConnector = new ServerConnector(socket, this.loginFrm, this);
            serverConnector.setLoginFrm(loginFrm);
            serverConnector.start();
            System.out.println("Connected to server");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                oos.close();
                System.out.println("Connection closed.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ServerConnector getServerConnector() {
        return serverConnector;
    }

    public LoginFrm getLoginFrm() {
        return loginFrm;
    }

    public void setLoginFrm(LoginFrm loginFrm) {
        this.loginFrm = loginFrm;
    }

    public boolean isOpponentReady() {
        return isOpponentReady;
    }

    public void setIsOpponentReady(boolean isOpponentReady) {
        this.isOpponentReady = isOpponentReady;
    }

    public void sendMessage(ClientMessage clientMess) {
        try {
            oos.writeObject(clientMess);
            System.out.println("Gửi thành công!");
            oos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
