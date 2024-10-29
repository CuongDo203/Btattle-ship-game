package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BattleShipGrid extends JPanel {

    private static final int GRID_SIZE = 10;
    private int cellSize;
    private int [][] gridState; // 0 = empty, 1 = occupied by a ship
    private List<Ship> ships;
    private List<JPanel> cells;
    
    public BattleShipGrid(int cellSize) {
        this.cellSize = cellSize;
        this.gridState = new int[GRID_SIZE][GRID_SIZE];
        this.cells = new ArrayList<>();
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        setPreferredSize(new Dimension(GRID_SIZE * cellSize, GRID_SIZE * cellSize));
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cell.setPreferredSize(new Dimension(cellSize, cellSize));  // Adjust cell size
            add(cell);
            cells.add(cell);
        }
    }

    public void setGridState(int[][] gridState) {
        this.gridState = gridState;
    }

    public int[][] getGridState() {
        return gridState;
    }
    
    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public int getCellSize() {
        return cellSize;
    }

    public List<JPanel> getCells() {
        return cells;
    }

    public void setCells(List<JPanel> cells) {
        this.cells = cells;
    }

}
