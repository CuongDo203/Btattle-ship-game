package handler;

import controller.ServerControl;
import dao.GameDAO;
import dao.PlayerDAO;
import dao.RankingDAO;
import dto.ClientMessage;
import dto.PlayerInfoDTO;
import dto.ServerMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import model.Client;
import model.Game;
import model.MatchHistory;
import model.Player;
import model.Ranking;

public class ClientHandler extends Thread {

    private Client served;
//    private ObjectInputStream ois;
//    private ObjectOutputStream oos;
    private ServerControl serverCtr;
    private PlayerDAO playerDAO;
    private GameDAO gameDAO;
    private RankingDAO rankingDAO;
    private List<PlayerInfoDTO> listPlayersFromDB;
    private Player player1;
    private Player player2;
    private Game game;
    private int[][] opponentMatrix;

    public ClientHandler(Client served, ServerControl serverCtr) {
        this.served = served;
        this.serverCtr = serverCtr;
        playerDAO = new PlayerDAO();
        gameDAO = new GameDAO();
        rankingDAO = new RankingDAO();
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(served.getSocket().getInputStream());
            while (true) {
//                ObjectInputStream ois = new ObjectInputStream(served.getSocket().getInputStream());
                ClientMessage clientMessage = (ClientMessage) ois.readObject();
                ServerMessage serverMessage = new ServerMessage();
                switch (clientMessage.getCommand()) {
                    case ClientMessage.LOGIN:

                        player1 = playerDAO.login(clientMessage.getUsername(), clientMessage.getPassword());
                        listPlayersFromDB = playerDAO.getListPlayerInfo();
                        if (player1 != null && listPlayersFromDB != null) {
                            serverMessage.setCommand(ServerMessage.LOGIN_SUCCESS);
                            serverMessage.setPlayer(player1);
                            served.setUsername(player1.getUsername());
                        } else {
                            serverMessage.setCommand(ServerMessage.LOGIN_FAILED);
                        }
                        sendMessage(serverMessage);
                        broadcastPlayerStatus(listPlayersFromDB);
                        break;

                    case ClientMessage.FIND_RANDOM_PLAYER:
                        synchronized (serverCtr.getPlayers()) {
                            List<Client> waitingPlayers = serverCtr.getPlayers(); // Danh sách chờ
                            // Thêm client vào danh sách chờ ghép đôi
                            waitingPlayers.add(served);
                            System.out.println("Client đang chờ: " + served.getSocket());

                            // Nếu có 2 người chơi trong danh sách chờ thì ghép cặp
                            if (waitingPlayers.size() % 2 == 0) {
//                                Client player1 = waitingPlayers.remove(0); // Lấy người chơi đầu tiên ra khỏi danh sách
                                Client p2 = waitingPlayers.get(waitingPlayers.size() - 2); // Lấy người chơi tiếp theo ra khỏi danh sách

                                System.out.println("Ghép đôi thành công: " + served.getSocket() + " và " + p2.getSocket());

                                // Gửi thông báo cho cả hai người chơi biết rằng họ đã tìm thấy đối thủ
                                serverMessage.setCommand(ServerMessage.PLAYER_FOUND);

                                sendMessage(serverMessage, served.getSocket());
                                sendMessage(serverMessage, p2.getSocket());

                                // Ghép đối thủ cho mỗi người chơi
                                served.setOpponent(p2);
                                p2.setOpponent(served);

                            } else {
                                System.out.println("Đang chờ thêm người chơi...");
                            }
                        }

                        break;
                    case ClientMessage.CANCEL_FIND_RANDOM_PLAYER:
                        serverCtr.getPlayers().remove(served);
                        break;
                    case ClientMessage.LOGOUT:
                        boolean rs = playerDAO.updatePlayerStatus(clientMessage.getUsername(), "offline");
                        serverMessage.setCommand(ServerMessage.PLAYER_LOGOUT);
                        sendMessage(serverMessage);
                        List<PlayerInfoDTO> updatedPlayers = playerDAO.getListPlayerInfo();
                        broadcastPlayerStatus(updatedPlayers);
                        serverCtr.getPlayers().remove(served);
                        return;
                    case ClientMessage.PLAYER_OUT:
                        List<Client> waitingPlayers = serverCtr.getPlayers();
                        boolean outWhilePlaying = clientMessage.isOutWhilePlaying();
                        System.out.println("Out while playing: " + outWhilePlaying);
                        if (waitingPlayers.contains(served.getOpponent())) {
                            serverMessage.setCommand(ServerMessage.OUT);
                            if (outWhilePlaying) {
                                serverMessage.setOutWhilePlaying(true);
                            } else {
                                serverMessage.setOutWhilePlaying(false);
                            }
                            serverMessage.setPlayer(player1);
                            sendMessage(serverMessage, served.getOpponent().getSocket());
                            sendMessage(serverMessage);
                        }
                        waitingPlayers.remove(served);
                        waitingPlayers.remove(served.getOpponent());
                        boolean kq = playerDAO.updatePlayerStatus(served.getUsername(), "online");
                        kq = playerDAO.updatePlayerStatus(served.getOpponent().getUsername(), "online");
                        if (kq) {
                            for (PlayerInfoDTO p : listPlayersFromDB) {
                                if (p.getUsername().equals(player1.getUsername()) || p.getUsername().equals(served.getOpponent().getUsername())) {
                                    p.setStatus("online");
                                    System.out.println("User " + p.getUsername() + " is out");
                                    break;
                                }
                            }
                            updatedPlayers = playerDAO.getListPlayerInfo();
                            broadcastPlayerStatus(updatedPlayers);
                        } else {
                            System.out.println("Có lỗi khi player out");
                        }
                        break;
                    case ClientMessage.REGISTER:
                        String username = clientMessage.getUsername();
                        String pass = clientMessage.getPassword();
                        String email = clientMessage.getEmail();
                        Player newPlayer = new Player();
                        newPlayer.setUsername(username);
                        newPlayer.setPassword(pass);
                        newPlayer.setEmail(email);
                        boolean isPlayerExists = playerDAO.existsByUsernameOrEmail(newPlayer);
                        if(isPlayerExists) {
                            serverMessage.setCommand(ServerMessage.PLAYER_ALREADY_EXISTS);
                            sendMessage(serverMessage);
                        }
                        else {
                            playerDAO.register(newPlayer);
                            serverMessage.setCommand(ServerMessage.REGISTER_SUCCESSFULLY);
                            
                            sendMessage(serverMessage);
                        }
                        break;
                    case ClientMessage.IS_READY:
                        username = clientMessage.getUsername();
                        playerDAO.updatePlayerStatus(username, "playing");
                        player2 = playerDAO.getPlayerByUsername(served.getOpponent().getUsername());
                        for (PlayerInfoDTO p : listPlayersFromDB) {
                            if (p.getUsername().equals(username)) {
                                p.setStatus("playing");
                                System.out.println("status of user " + p.getUsername() + " is " + p.getStatus());
                                break;
                            }
                        }
                        broadcastPlayerStatus(listPlayersFromDB);
                        break;
                    case ClientMessage.PLACED_SHIPS:
                        served.setMatrix(clientMessage.getMatrix());

                        if (served.getOpponent().getMatrix() == null) {
                            serverMessage.setCommand(ServerMessage.OPPONENT_NOT_READY);
                            sendMessage(serverMessage);
                        } else {
                            LocalDateTime now = LocalDateTime.now();
                            Timestamp start = Timestamp.valueOf(now);
                            game = new Game(player1, player2, start, "in_progress");
                            game = gameDAO.createNewGame(game);
                            System.out.println("Game id" + game.getId());
                            serverMessage.setCommand(ServerMessage.READY_TO_PLAY);
                            serverMessage.setGameID(game.getId());
                            serverMessage.setMatrix(served.getMatrix());
                            serverMessage.setYourTurn(true);
                            sendMessage(serverMessage, served.getOpponent().getSocket());
                            opponentMatrix = served.getOpponent().getMatrix();
                            serverMessage.setMatrix(served.getOpponent().getMatrix());
                            serverMessage.setYourTurn(false);
                            sendMessage(serverMessage, served.getSocket());
                        }
                        break;
                    case ClientMessage.SWITCH_TURN:
                        serverMessage.setCommand(ServerMessage.IS_YOUR_TURN);
                        serverMessage.setYourTurn(true);
                        sendMessage(serverMessage, served.getOpponent().getSocket());
                        serverMessage.setYourTurn(false);
                        sendMessage(serverMessage);
                        break;
                    case ClientMessage.SHOW_MATCH_HISTORY:
                        serverMessage.setCommand(ServerMessage.MATCH_HISTORY);
                        Ranking playerHistory = rankingDAO.getRankOfPlayer(player1.getId());
                        List<MatchHistory> lsd = gameDAO.getPlayerMatchHistory(player1.getId());
                        serverMessage.setPlayerHistory(playerHistory);
                        serverMessage.setMatchHistory(lsd);
                        sendMessage(serverMessage);
                        break;
                    case ClientMessage.GET_RANKING:
                        List<PlayerInfoDTO> ranking = playerDAO.getRankings();
                        serverMessage.setCommand(ServerMessage.RANKING);
                        serverMessage.setListPlayer(ranking);
                        sendMessage(serverMessage);
                        break;
                    case ClientMessage.SHOOT:
                        int row = clientMessage.getRow();
                        int col = clientMessage.getCol();
                        boolean hit = served.getOpponent().getMatrix()[row][col] > 0;
                        serverMessage.setRow(row);
                        serverMessage.setCol(col);
                        System.out.println(hit ? "Bắn trúng tàu đối phương" : "Bắn hụt rồi");
                        if (hit) {
                            serverMessage.setCommand(ServerMessage.HIT);
                            serverMessage.setYourTurn(true);
                            sendMessage(serverMessage);
                            
                            serverMessage.setYourTurn(false);
                            serverMessage.setCommand(ServerMessage.SHOOT);
                            sendMessage(serverMessage, served.getOpponent().getSocket());
                        } else {
                            serverMessage.setCommand(ServerMessage.MISS);
                            serverMessage.setYourTurn(false);
                            sendMessage(serverMessage);

                            serverMessage.setYourTurn(true);
                            //Gửi tọa độ cho đối thủ
                            serverMessage.setCommand(ServerMessage.SHOOT);
                            sendMessage(serverMessage, served.getOpponent().getSocket());
                        }
                        break;
                    case ClientMessage.WIN:
                        waitingPlayers = serverCtr.getPlayers();
                        waitingPlayers.remove(served);
                        waitingPlayers.remove(served.getOpponent());
                        username = clientMessage.getUsername();
                        Player winner = playerDAO.getPlayerByUsername(username);
                        rankingDAO.plusOnePoint(winner.getId());
                        playerDAO.updatePlayerStatus(served.getUsername(), "online");
//                        playerDAO.updatePlayerStatus(served.getOpponent().getUsername(), "online");
//                        for (PlayerInfoDTO p : listPlayersFromDB) {
//                            if (p.getUsername().equals(player1.getUsername()) || p.getUsername().equals(served.getOpponent().getUsername())) {
//                                p.setStatus("online");
//                                System.out.println("User " + p.getUsername() + " is out");
//                                break;
//                            }
//                        }
                        updatedPlayers = playerDAO.getListPlayerInfo();
                        broadcastPlayerStatus(updatedPlayers);
                        int gameID = clientMessage.getGameID();
                        System.out.println("Game id = null: " + gameID == null);
                        gameDAO.updateGameOverStatus("finished", gameID, player1.getId());
                        break;
                    case ClientMessage.LOSE:
                        username = clientMessage.getUsername();
                        Player loser = playerDAO.getPlayerByUsername(username);
                        rankingDAO.addALose(loser.getId());
                        playerDAO.updatePlayerStatus(served.getUsername(), "online");
//                        playerDAO.updatePlayerStatus(served.getOpponent().getUsername(), "online");
//                        for (PlayerInfoDTO p : listPlayersFromDB) {
//                            if (p.getUsername().equals(player1.getUsername()) || p.getUsername().equals(served.getOpponent().getUsername())) {
//                                p.setStatus("online");
//                                System.out.println("User " + p.getUsername() + " is out");
//                                break;
//                            }
//                        }
                        updatedPlayers = playerDAO.getListPlayerInfo();
                        broadcastPlayerStatus(updatedPlayers);
                        break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(ServerMessage serverMess) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(served.getSocket().getOutputStream());
            oos.writeObject(serverMess);
//            oos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(ServerMessage serverMess, Socket socket) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(serverMess);
//            output.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void broadcastPlayerStatus(List<PlayerInfoDTO> updatedList) {
        ServerMessage serverMess = new ServerMessage();
        serverMess.setCommand(ServerMessage.UPDATE_PLAYER_LIST);
        serverMess.setListPlayer(updatedList);

        // Broadcast to all connected clients
        synchronized (serverCtr.getClients()) {
            for (ClientHandler clientHandler : serverCtr.getClients()) {
                clientHandler.sendMessage(serverMess);
            }
        }
    }
}
