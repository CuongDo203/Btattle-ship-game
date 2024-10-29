package controller;

import dto.ClientMessage;
import dto.PlayerInfoDTO;
import dto.ServerMessage;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.MatchHistory;
import model.Player;
import model.Ranking;
import view.BattleViewFrm;
import view.CountdownDialog;
import view.LoginFrm;
import view.MainFrm;
import view.MatchHistoryFrm;
import view.RankingFrm;
import view.ReadyFrm;
import view.RegisterFrm;
import view.Ship;

/**
 *
 * @author win
 */
public class ServerConnector extends Thread {

//    private ObjectInputStream ois;
    private Socket socket;
    private LoginFrm loginFrm;
    private MainFrm mainFrm;
    MatchHistoryFrm historyFrm;
    private ReadyFrm readyFrm;
    private BattleViewFrm battleViewFrm;
    private RankingFrm rankingFrm;
    private RegisterFrm registerFrm;
    private CountdownDialog countdownDialog;
    private ClientControl clientCtr;
    private Player player;
    private int hp, enemyHp;
    int[][] opponentMatrix;
    private int gameID;

    public ServerConnector(Socket socket, LoginFrm loginFrm, ClientControl clientCtr) {
        this.socket = socket;
        this.loginFrm = loginFrm;
        this.clientCtr = clientCtr;
//        try {
//            ois = new ObjectInputStream(socket.getInputStream());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

    }

    public Player getPlayer() {
        return player;
    }

    public LoginFrm getLoginFrm() {
        return loginFrm;
    }

    public void setLoginFrm(LoginFrm loginFrm) {
        this.loginFrm = loginFrm;
    }

    public MainFrm getMainFrm() {
        return mainFrm;
    }

    public void setMainFrm(MainFrm mainFrm) {
        this.mainFrm = mainFrm;
    }

    public int healthPoint(int[][] matrix) {
        int hp = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matrix[i][j] > 0) {
                    hp += matrix[i][j];
                }
            }
        }
        return hp;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ServerMessage serverMess = (ServerMessage) ois.readObject();
                System.out.println("Server command: " + serverMess.getCommand());
                switch (serverMess.getCommand()) {
                    case ServerMessage.LOGIN_SUCCESS:
                        player = serverMess.getPlayer();
                        this.loginFrm.dispose();
                        mainFrm = new MainFrm(player, clientCtr);
//                        mainFrm.setClientCtr(clientCtr);
                        mainFrm.getLblAvarta().setText(player.getUsername());
                        mainFrm.showWindow();
                        System.out.println("Đăng nhập thành công!");
                        break;
                    case ServerMessage.LOGIN_FAILED:
                        System.out.println("Đăng nhập thất bại");
                        JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không chính xác!",
                                "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
                        break;
                    case ServerMessage.PLAYER_LOGOUT:
                        System.out.println("Player logout: " + player.getUsername());
                        System.out.println("mainFrm is null = " + mainFrm == null);
//                        if (mainFrm != null) {
//                            SwingUtilities.invokeLater(() -> {
//                                mainFrm.dispose();  // Đóng cửa sổ MainFrm
//                            });
//                        }
                        mainFrm.dispose();
                        loginFrm = new LoginFrm();
                        loginFrm.showWindow();
                        clientCtr.closeConnection();
                        return;
                    case ServerMessage.OUT:
                        boolean outWhilePlaying = serverMess.isOutWhilePlaying();
                        if (outWhilePlaying) {
                            if (!player.getUsername().equals(serverMess.getPlayer().getUsername())) {
                                ClientMessage clientMess = new ClientMessage();
                                clientMess.setCommand(ClientMessage.WIN);
                                clientMess.setGameID(gameID);
                                clientMess.setUsername(player.getUsername());
                                System.out.println("Player out: " + player.getUsername());
                                clientCtr.sendMessage(clientMess);
                                countdownDialog = new CountdownDialog(readyFrm, "Đếm ngược!",
                                        "Bạn đã giành chiến thắng do đối thủ thoát trận!");
                                countdownDialog.showWindow();
                            }
                            this.battleViewFrm.dispose();
                        } else {
                            if (!player.getUsername().equals(serverMess.getPlayer().getUsername())) {
                                countdownDialog = new CountdownDialog(readyFrm, "Đếm ngược!",
                                        "Đối thủ của bạn đã thoát!");
                                countdownDialog.showWindow();
                            }
                            this.readyFrm.dispose();
                        }

                        mainFrm = new MainFrm(player, clientCtr);
                        mainFrm.getLblAvarta().setText(player.getUsername());
                        mainFrm.showWindow();
                        System.out.println("in case OUT");
                        break;
                    case ServerMessage.UPDATE_PLAYER_LIST:
                        List<PlayerInfoDTO> updatedList = serverMess.getListPlayer();
                        if (!Objects.isNull(player)) {
                            List<PlayerInfoDTO> res = updatedList.stream()
                                    .filter(p -> !p.getUsername().equals(player.getUsername()))
                                    .collect(Collectors.toList());
                            mainFrm.showListPlayer(res);
                        }
                        break;
                    case ServerMessage.PLAYER_FOUND:
                        System.out.println("Found a player");
                        ClientMessage clientMess = new ClientMessage();
                        clientMess.setCommand(ClientMessage.IS_READY);
                        clientMess.setUsername(player.getUsername());
                        clientCtr.sendMessage(clientMess);
                        this.readyFrm = new ReadyFrm(mainFrm.getPlayer(), clientCtr);
                        readyFrm.showWindow();
                        mainFrm.dispose();
                        break;
                    case ServerMessage.READY_TO_PLAY:
                        System.out.println("Your turn is: " + serverMess.isYourTurn());
                        System.out.println("Đối thủ đã Sẵn sàng chơi");
                        clientCtr.setIsOpponentReady(true);
                        opponentMatrix = serverMess.getMatrix();
                        gameID = serverMess.getGameID();
                        System.out.println("Vị trí thuyền của địch");
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                System.out.print(opponentMatrix[i][j] + " ");
                            }
                            System.out.println("");
                        }
                        battleViewFrm = new BattleViewFrm(readyFrm.getShips(), clientCtr);
                        battleViewFrm.setOpponentMatrix(opponentMatrix);
                        battleViewFrm.setTurn(serverMess.isYourTurn());
                        boolean turn = serverMess.isYourTurn();
                        battleViewFrm.getLblTurn().setText(turn ? "Lượt của bạn" : "Lượt của đối thủ");
                        battleViewFrm.getLblTurn().setForeground(!turn ? Color.red : Color.BLACK);
                        System.out.println("Vị trí thuyền của mình");
                        int[][] myMatrix = battleViewFrm.getLeftGrid().getGridState();
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                System.out.print(myMatrix[i][j] + " ");
                            }
                            System.out.println("");
                        }
                        readyFrm.dispose();
                        battleViewFrm.showWindow();
                        hp = healthPoint(myMatrix);
                        enemyHp = healthPoint(opponentMatrix);
                        battleViewFrm.getLblHp().setText("HP của đối thủ: " + enemyHp);
                        break;
                    case ServerMessage.OPPONENT_NOT_READY:
                        System.out.println("Đối thủ chưa sẵn sàng ");
                        readyFrm.getBtnRandom().setEnabled(false);
                        List<Ship> ships = readyFrm.getShips();
                        for (Ship ship : ships) {
                            for (MouseMotionListener mml : ship.getMouseMotionListeners()) {
                                ship.removeMouseMotionListener(mml);
                            }

                            for (MouseListener ml : ship.getMouseListeners()) {
                                ship.removeMouseListener(ml);
                            }
                        }
                        JOptionPane.showMessageDialog(readyFrm, "Cảm ơn bạn đã sẵn sàng. Vui lòng chờ đối thủ sẵn sàng!");
                        break;
                    case ServerMessage.MATCH_HISTORY:
                        Ranking playerHistory = serverMess.getPlayerHistory();
                        List<MatchHistory> lsd = serverMess.getMatchHistory();
                        mainFrm.setVisible(false);
                        historyFrm = new MatchHistoryFrm(mainFrm, clientCtr);
                        historyFrm.setPlayerHistory(playerHistory);
                        historyFrm.setLsd(lsd);
                        historyFrm.getLblPlayerName().setText("Thuyền trưởng: " + player.getUsername());
                        historyFrm.getLblTotalsWin().setText("Thắng: " + playerHistory.getTotalWins());
                        historyFrm.getLblTotalLosses().setText("Thua: " + playerHistory.getTotalLosese());
                        historyFrm.showTblPlayerHistory();
                        historyFrm.showWindow();
                        break;
                    case ServerMessage.REGISTER_SUCCESSFULLY:
                        JOptionPane.showMessageDialog(null, "Đăng ký tài khoản thành công!");
                        break;
                    case ServerMessage.PLAYER_ALREADY_EXISTS:
                        JOptionPane.showMessageDialog(null, "Username hoặc mật khẩu này đã tồn tại!");
                        break;
                    case ServerMessage.SHOOT:
                        int row = serverMess.getRow();
                        int col = serverMess.getCol();
                        System.out.println("Shoot Row " + row + " col " + col);
                        myMatrix = battleViewFrm.getLeftGrid().getGridState();

                        if (myMatrix[row][col] == 1) {     //Mình bị bắn trúng
                            hp--;
                            System.out.println("Bị bắn trúng!");
                            System.out.println("HP còn lại: " + hp);
                            battleViewFrm.getLblTurn().setText("Lượt của đối thủ");
                            battleViewFrm.getLblTurn().setForeground(Color.red);
                            int index = row * 10 + col;

                            JPanel clickedCell = battleViewFrm.getLeftGrid().getCells().get(index);
                            clickedCell.setBackground(Color.RED);
                            if (hp == 0) {
                                JOptionPane.showMessageDialog(battleViewFrm, "Bạn đã thua");
                                clientMess = new ClientMessage();
                                clientMess.setCommand(ClientMessage.LOSE);
                                clientMess.setUsername(player.getUsername());
                                clientCtr.sendMessage(clientMess);
                                battleViewFrm.dispose();
                                mainFrm = new MainFrm(player, clientCtr);
                                mainFrm.getLblAvarta().setText(player.getUsername());
                                mainFrm.showWindow();
                                break;
                            }
                        } else {
                            System.out.println("Bị bắn trượt!");
                            battleViewFrm.getLblTurn().setText("Lượt của bạn");
                            int index = row * 10 + col;
                            JPanel clickedCell = battleViewFrm.getLeftGrid().getCells().get(index);
                            clickedCell.setBackground(Color.BLUE);
                            battleViewFrm.setTurn(true);
                        }
                        break;
                    case ServerMessage.RANKING:
                        mainFrm.setVisible(false);
                        rankingFrm = new RankingFrm(mainFrm);
                        rankingFrm.showWindow();
                        rankingFrm.showRankings(serverMess.getListPlayer());
                        break;
                    case ServerMessage.IS_YOUR_TURN:
                        boolean isMyTurn = serverMess.isYourTurn();
                        if (isMyTurn) {
                            battleViewFrm.setTurn(true);
                            System.out.println("This is my turn!!!!");
                            battleViewFrm.getLblTurn().setText("Lượt của bạn");
                            battleViewFrm.startTurn();
                        } else {
                            battleViewFrm.setTurn(false);
                            battleViewFrm.getLblTurn().setText("Lượt của đối thủ");
                        }
                        break;
                    case ServerMessage.HIT:    //Khi bắn trúng tàu địch
                        battleViewFrm.getSound().soundHitShot();
                        enemyHp--;
                        row = serverMess.getRow();
                        col = serverMess.getCol();
                        System.out.println("Hit Row " + row + " col " + col);
                        int index = row * 10 + col;
                        JPanel clickedCell = battleViewFrm.getRightGrid().getCells().get(index);
                        clickedCell.setBackground(Color.red);
                        battleViewFrm.getLblTurn().setText("Lượt của bạn");
                        battleViewFrm.getLblHp().setText("HP của đối thủ: " + enemyHp);
                        battleViewFrm.setTurn(true);
                        if (enemyHp == 0) {
                            JOptionPane.showMessageDialog(battleViewFrm, "Bạn đã thắng!");
                            clientMess = new ClientMessage();
                            clientMess.setCommand(ClientMessage.WIN);
                            clientMess.setUsername(player.getUsername());
                            clientMess.setGameID(gameID);
                            clientCtr.sendMessage(clientMess);
                            battleViewFrm.dispose();
                            mainFrm = new MainFrm(player, clientCtr);
                            mainFrm.getLblAvarta().setText(player.getUsername());
                            mainFrm.showWindow();
                            break;
                        }
                        break;
                    case ServerMessage.MISS:   //Khi bắn hụt tàu địch
                        battleViewFrm.getSound().soundMissShot();
                        row = serverMess.getRow();
                        col = serverMess.getCol();
                        System.out.println("Miss Row " + row + " col " + col);
                        index = row * 10 + col;
                        clickedCell = battleViewFrm.getRightGrid().getCells().get(index);
                        clickedCell.setBackground(Color.blue);
                        battleViewFrm.getLblTurn().setText("Lượt của đối thủ");
                        battleViewFrm.getLblTurn().setForeground(Color.red);
                        battleViewFrm.setTurn(false);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
