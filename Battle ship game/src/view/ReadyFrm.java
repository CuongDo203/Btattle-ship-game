package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.ImageManager;
import utils.Sound;

public class ReadyFrm extends JFrame {
    
    private BufferedImage backgroundImg;
    private BattleShipGrid grid;
    private List<Ship> ships;
    private JPanel pnMain;
    private JButton btnReturn;
    private JButton btnReady;
    private JButton btnRandom;
    private Sound sound;
    private String[] images = {ImageManager.SHIP_SIZE_5, ImageManager.SHIP_SIZE_4, ImageManager.SHIP_SIZE_3_1, ImageManager.SHIP_SIZE_3_2, ImageManager.SHIP_SIZE_2};

    public ReadyFrm() {
        setTitle("Battleship Game");
        setLayout(new BorderLayout());
        setSize(800, 700);
        backgroundImg = ImageManager.getImage(ImageManager.READY_BACKGROUND_IMAGE);
//        setContentPane(new CustomPanel());
        sound = new Sound();
        btnReturn = new JButton("Quay lại");
        btnReturn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnTop.setPreferredSize(new Dimension(getWidth(), 60));
        pnTop.add(btnReturn);
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnActionPerformed();
            }

        });

        btnReturn.setPreferredSize(new Dimension(80, 40));
        btnReturn.setLocation(5, 5);
        this.add(pnTop, BorderLayout.NORTH);
        pnMain = new JPanel();
        pnMain.setLayout(null);
        this.add(pnMain, BorderLayout.CENTER);

        grid = new BattleShipGrid(50);
        grid.setBounds(50, 50, 500, 500);
        pnMain.add(grid);
        grid.setLocation(this.getWidth() - grid.getWidth(), 5);
        // Ship panel where ships are placed initially
        JPanel shipPanel = new JPanel();
        shipPanel.setLayout(null);

        // Add ships
        ships = new ArrayList<>();
        ships.add(new Ship(5, grid, 50));
        ships.add(new Ship(4, grid, 50));
        ships.add(new Ship(3, grid, 50));
        ships.add(new Ship(3, grid, 50));
        ships.add(new Ship(2, grid, 50));

        int offset = 10;
        int xPlace = 5;
        int yPlace = 5;
        int imageFile = 0;
        BufferedImage img;
        for (Ship ship : ships) {
            pnMain.add(ship);
            ship.setSize(50 * ship.getLength(), 50);
            ship.setLocation(xPlace, yPlace + 15);
            ship.setInitialPosition(new Point(ship.getX(), ship.getY()));
            ship.setReadyFrm(this);
            img = ImageManager.getImage(images[imageFile]);
//            ship.setImage(img);
            imageFile++;
            yPlace += ship.getHeight() + offset;
            pnMain.repaint();
        }
        JPanel pnBottom = new JPanel();
        pnBottom.setLayout(new FlowLayout());
        btnRandom = new JButton("Ngẫu nhiên");
        btnRandom.setPreferredSize(new Dimension(100, 40));
        btnRandom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReady = new JButton("Sẵn sàng");
        btnReady.setPreferredSize(new Dimension(100, 40));
        btnReady.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnBottom.add(btnRandom);
        pnBottom.add(btnReady);
        btnRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomizeShips();
                sound.soundDrag();
                pnMain.repaint();
            }

        });
        btnReady.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnReaddyactionPerformed();
            }
        });
        this.add(pnBottom, BorderLayout.SOUTH);
    }

    public boolean isOverlap(Ship newShip) {
        for (Ship existingShip : ships) {
            if (existingShip != newShip && existingShip.getBounds().intersects(newShip.getBounds())) {
                return true;
            }
        }
        return false;
    }

    private boolean isAllShipPlaced() {
        for (Ship s : ships) {
            if (s.getLocation().equals(s.getInitialPosition())) {
                return false;
            }
        }
        return true;
    }

    private void randomizeShips() {
        int cellSize = 50;
        int gridSize = 10;  // Assuming a 10x10 grid

        for (Ship ship : ships) {
            boolean placed = false;

            while (!placed) {
                // Randomize orientation (horizontal/vertical)
                boolean isHorizontal = Math.random() > 0.5;
                ship.setHorizontal(isHorizontal);

                // Calculate random position within the grid
                int maxX = isHorizontal ? gridSize - ship.getLength() : gridSize;
                int maxY = isHorizontal ? gridSize : gridSize - ship.getLength();

                int randomX = (int) (Math.random() * maxX) * cellSize;
                int randomY = (int) (Math.random() * maxY) * cellSize;

                ship.setLocation(randomX + grid.getX(), randomY + grid.getY());
                // Check if the ship is placed without overlap
                if (ship.isInsideGrid(ship.getLocation()) && !isOverlap(ship)) {
                    pnMain.setComponentZOrder(ship, 0);
                    placed = true;  // Ship placed successfully
                    pnMain.repaint();
                }
            }
        }
    }

    private void btnReaddyactionPerformed() {
        if (!isAllShipPlaced()) {
            JOptionPane.showMessageDialog(null,
                    "Vui lòng xếp thuyền vào các ô trên lưới", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int[][] gridState = new int[10][10];
        for (Ship s : ships) {
            int startX = (s.getX() - grid.getX()) / grid.getCellSize();
            int startY = (s.getY() - grid.getY()) / grid.getCellSize();
            if (s.isHorizontal()) {
                for (int i = startX; i < startX + s.getLength(); i++) {
                    gridState[startY][i] = 1; 
                }
            } else {
                for (int i = startY; i < startY + s.getLength(); i++) {
                    gridState[i][startX] = 1; // Mark grid cell as occupied
                }
            }
        }
        grid.setGridState(gridState);
        sound.soundButtonClick();
        ReadyFrm.this.dispose();

        BattleViewFrm battleView = new BattleViewFrm(ships);
        battleView.showWindow();
    }

    private void btnActionPerformed() {
        this.dispose();
        MainFrm main = new MainFrm();
        main.showWindow();
    }

    public void showWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(900, 700);
        setResizable(false);
        setVisible(true);
    }
    
    private class CustomPanel extends javax.swing.JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);  // Calls the default painting of components
            // Draw the background image
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
