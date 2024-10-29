package view;

import controller.ClientControl;
import dto.ClientMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import utils.Sound;

public class BattleViewFrm extends JFrame implements WindowListener {

    private BattleShipGrid leftGrid;
    private BattleShipGrid rightGrid;
    private List<Ship> ships;
    private JPanel pnMain;
    private JButton btnAction;
    private JLabel lblTurn;
    private JLabel lblHp;
    private JLabel lblTime;
    private boolean turn;
    private int[][] opponentMatrix;
    private int myShootX;
    private int myShootY;
    private ClientControl clientCtr;
    private boolean[][] clicked;
    private Sound sound;
    private static int countdown = 30;
    private Timer timer;
    private boolean isTimerRunning;

    public BattleViewFrm(List<Ship> ships, ClientControl clientCtr) {
        this.ships = ships;
        this.clientCtr = clientCtr;
        this.sound = new Sound();
        setTitle("Battleship Battle View");
        setSize(1000, 800);
        setLayout(new BorderLayout());

        btnAction = new JButton("Thoát");
        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outGameWhilePlaying();
            }

        });
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnTop.add(btnAction);
        add(pnTop, BorderLayout.NORTH);

        // Panel for both grids
        pnMain = new JPanel();
        pnMain.setLayout(null);
//        pnMain.setSize(new Dimension(1000, 800));

        lblTurn = new JLabel("");
        lblTurn.setSize(lblTurn.getPreferredSize());
        lblTurn.setFont(new Font("Serif", Font.BOLD, 25));
        lblTurn.setBounds(this.getWidth() / 2 - lblTurn.getWidth() / 2, 20, 300, 50);
        lblHp = new JLabel("HP của đối thủ: ");
        lblHp.setFont(new Font("Serif", Font.BOLD, 20));
        lblHp.setBounds(this.getWidth() / 2 - lblHp.getWidth() / 2, 50, 300, 50);
        lblTime = new JLabel("Thời gian suy nghĩ còn: " + countdown + " s");
        lblTime.setFont(new Font("Serif", Font.BOLD, 20));
        lblTime.setBounds(this.getWidth() / 2 - lblTime.getWidth() / 2, 90, 300, 50);
        lblTime.setVisible(false);
        pnMain.add(lblTurn);
        pnMain.add(lblHp);
        pnMain.add(lblTime);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown--;
                lblTime.setText("Thời gian suy nghĩ còn: " + countdown + " s");

                if (countdown == 0) {
                    // Switch turn when countdown reaches zero
                    ((Timer) e.getSource()).stop(); // Stop the Timer
                    isTimerRunning = false;
                    switchTurn();
                }
            }
        });
        if (turn) {
            startTurn();
            System.out.println("My turn in BattleviewFrm");
        } else {
            lblTime.setText("");
        }
        // Left grid (showing the ships)
        leftGrid = new BattleShipGrid(20);
        leftGrid.setBounds(20, 20, 200, 200);
        pnMain.add(leftGrid);
        leftGrid.setLocation(10, 10);
        for (Ship ship : ships) {
            leftGrid.setGridState(ship.getGrid().getGridState());
            // Set the new cell size for the ship to match the smaller grid
            ship.setCellSize(20);

            // Convert the ship's position from the previous grid to the new grid system
            int newWidth = ship.getWidth() / ship.getGrid().getCellSize() * ship.getCellSize();
            int newHeight = ship.getHeight() / ship.getGrid().getCellSize() * ship.getCellSize();
            Point p = ship.getLocation();
            int px = (int) ((p.getX() - ship.getGrid().getLocation().getX()) / ship.getGrid().getCellSize()) * leftGrid.getCellSize();
            int py = (int) ((p.getY() - ship.getGrid().getLocation().getY()) / ship.getGrid().getCellSize()) * leftGrid.getCellSize();

            // Set the correct size for the ship on the left grid
            ship.setSize(new Dimension(newWidth, newHeight));

            // Set the location of the ship on the left grid
            ship.setLocation(px + leftGrid.getX(), py + leftGrid.getY());

            // Set the grid to the left grid
            ship.setGrid(leftGrid);

            for (MouseMotionListener mml : ship.getMouseMotionListeners()) {
                ship.removeMouseMotionListener(mml);
            }

            for (MouseListener ml : ship.getMouseListeners()) {
                ship.removeMouseListener(ml);
            }

            // Add the ship to the panel and repaint
            pnMain.add(ship);
            pnMain.setComponentZOrder(ship, 0);
            pnMain.repaint();
        }
        // Right grid (for gameplay)
        rightGrid = new BattleShipGrid(50);
        rightGrid.setBounds(50, 50, 500, 500);
//        rightGrid.setPreferredSize(new Dimension(500, 500));  // Full size 10x10 grid for gameplay
        pnMain.add(rightGrid);
        Point centerPoint = new Point((getWidth() / 2) - (rightGrid.getWidth() / 2), (getHeight() / 2) - (rightGrid.getHeight() / 2));
        rightGrid.setLocation(centerPoint);
        clicked = new boolean[10][10];
        rightGrid.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (turn) {
                    clickOnRightGrid(e);
                }
//                int index = gridY*10+ gridX;
//                JPanel clickedCell = rightGrid.getCells().get(index);
//                clickedCell.setBackground(Color.red);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        // Add panel to frame
        add(pnMain, BorderLayout.CENTER);
    }

    // Start the turn and the timer
    public void startTurn() {
        turn = true;
        countdown = 30;
        lblTime.setVisible(true);
        lblTime.setText("Thời gian suy nghĩ còn: " + countdown + " s");
        timer.start();
        isTimerRunning = true;
    }

    // Switch the turn to the opponent
    public void switchTurn() {
        turn = false;
        lblTime.setVisible(false);
        ClientMessage clientMess = new ClientMessage();
        clientMess.setCommand(ClientMessage.SWITCH_TURN);
        clientCtr.sendMessage(clientMess);
    }
    
    private void clickOnRightGrid(MouseEvent e) {
        Point clickPoint = e.getPoint();
        int cellSize = rightGrid.getCellSize();
        int gridX = (int) (clickPoint.getX() / cellSize);
        int gridY = (int) (clickPoint.getY() / cellSize);
        System.out.println("Click point: " + gridX + "-" + gridY);
        if (!clicked[gridX][gridY]) {
            clicked[gridX][gridY] = true;
            myShootX = gridX;
            myShootY = gridY;
            ClientMessage clientMess = new ClientMessage();
            clientMess.setCommand(ClientMessage.SHOOT);
            clientMess.setRow(gridY);
            clientMess.setCol(gridX);
            clientCtr.sendMessage(clientMess);
        }
    }

    private void outGameWhilePlaying() {
        int choose = JOptionPane.showConfirmDialog(this,
                "Bạn có muốn thoát game? Nếu thoát bạn sẽ bị xử thua!",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choose == JOptionPane.YES_OPTION) {
            ClientMessage clientMess = new ClientMessage();
            clientMess.setCommand(ClientMessage.PLAYER_OUT);
            clientMess.setOutWhilePlaying(true);
            clientCtr.sendMessage(clientMess);
        }
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean isMyTurn) {
        if (isMyTurn) {
            startTurn();
        } else {
            turn = false;
            timer.stop();
            isTimerRunning = false;
            lblTime.setVisible(false);
        }
    }

    public int[][] getOpponentMatrix() {
        return opponentMatrix;
    }

    public void setOpponentMatrix(int[][] opponentMatrix) {
        this.opponentMatrix = opponentMatrix;
    }

    public BattleShipGrid getLeftGrid() {
        return leftGrid;
    }

    public BattleShipGrid getRightGrid() {
        return rightGrid;
    }

    public JLabel getLblTurn() {
        return lblTurn;
    }

    public void setLblTurn(JLabel lblTurn) {
        this.lblTurn = lblTurn;
    }

    public int getMyShootX() {
        return myShootX;
    }

    public void setMyShootX(int myShootX) {
        this.myShootX = myShootX;
    }

    public int getMyShootY() {
        return myShootY;
    }

    public void setMyShootY(int myShootY) {
        this.myShootY = myShootY;
    }

    public void showWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JLabel getLblHp() {
        return lblHp;
    }

    public void setLblHp(JLabel lblHp) {
        this.lblHp = lblHp;
    }

    public Sound getSound() {
        return sound;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        ClientMessage clientMess = new ClientMessage();
        clientMess.setOutWhilePlaying(true);
        clientCtr.sendMessage(clientMess);
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
