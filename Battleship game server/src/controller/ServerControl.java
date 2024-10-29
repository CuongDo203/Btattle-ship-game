package controller;

import java.io.IOException;
import java.sql.Connection;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.Client;
import handler.ClientHandler;
import java.util.HashMap;
import java.util.Map;
import model.Game;

public class ServerControl extends Thread {

    private Connection con;
    private ServerSocket myServer;
    private Socket clientSocket;
    private int serverPort = 8888;
    private List<Client> players = new ArrayList<>();
    private List<ClientHandler> clientHandlers;
    private Map<ClientHandler, Game> activeGames;
    
    public ServerControl() {
        openServer(serverPort);
        while (true) {
            listenning();
        }
    }


    public void openServer(int portNumber) {
        try {
            myServer = new ServerSocket(portNumber);
            System.out.println("Server is running on port: " + serverPort);
            clientHandlers = new ArrayList<>();
            activeGames=  new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenning() {
        while (true) {
            try {
                clientSocket = myServer.accept();
                System.out.println("Client kết nối thành công!");
                Client served = new Client(clientSocket);
//                players.add(served);
                ClientHandler handler = new ClientHandler(served, this);
                clientHandlers.add(handler);
                handler.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    public List<ClientHandler> getClients() {
        return clientHandlers;
    }

    public List<Client> getPlayers() {
        return players;
    }

    public Map<ClientHandler, Game> getActiveGames() {
        return activeGames;
    }

    public void setActiveGames(Map<ClientHandler, Game> activeGames) {
        this.activeGames = activeGames;
    }
    
}
