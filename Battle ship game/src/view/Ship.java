package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;
import utils.Sound;

public class Ship extends DraggableComponent {

    private int length;
    private int cellSize;
    private boolean isHorizontal = true;  // Initial orientation
    private Point initialPosition;
    private BattleShipGrid grid;
    private ReadyFrm readyFrm;
    private Sound sound;

    public Ship() {
    }
    
    public Ship(int length, BattleShipGrid grid, ReadyFrm readyFrm, int cellSize) {
        this.length = length;
        this.grid = grid;
        this.readyFrm = readyFrm;
        this.cellSize = cellSize;
        sound = new Sound();
        setOpaque(true);
        setBackground(Color.GRAY);
        setBorder(new LineBorder(Color.CYAN, 2));
        setPreferredSize(new Dimension(length * cellSize, cellSize));  // Horizontal size

        // Store the initial position of the ship
        initialPosition = getLocation();

        // Enable Drag and Drop
        setTransferHandler(new TransferHandler("ship"));

        // Right-click for rotating the ship
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    rotate();
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                handleDrop();
            }
        });
    }

    public void setGrid(BattleShipGrid grid) {
        this.grid = grid;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }
    
    public void setHorizontal(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
        rotate();  // Call the rotate method if orientation changes
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setInitialPosition(Point initialPosition) {
        this.initialPosition = initialPosition;
    }

    public int getLength() {
        return length;
    }

    // Rotate the ship between horizontal and vertical
    public void rotate() {
        if (isHorizontal) {
            setPreferredSize(new Dimension(50, length * 50));  // Vertical size
        } else {
            setPreferredSize(new Dimension(length * 50, 50));  // Horizontal size
        }
        isHorizontal = !isHorizontal;
        setSize(getPreferredSize());
        revalidate();
        repaint();
    }

    private void handleDrop() {
        // Get the current position of the ship
        Point shipPosition = getLocation();
        int cellSize = 50;

        if (isInsideGrid(shipPosition)) {
            int gridx = (int) ((shipPosition.getX() - grid.getX()) / cellSize) * cellSize + grid.getX();
            int gridy = (int) ((shipPosition.getY() - grid.getY()) / cellSize) * cellSize + grid.getY();
            setLocation(gridx, gridy);
            sound.soundDrag();
            if (readyFrm.isOverlap(this)) {
                // If the ship overlaps, reset it to the initial position
                if (!isHorizontal) {
                    rotate();
                    isHorizontal = !isHorizontal;
                }
                setLocation(initialPosition);
            }
        } else {
            if (!isHorizontal) {
                rotate();
                isHorizontal = !isHorizontal;
            }
            setLocation(initialPosition);
        }

    }

    public boolean isInsideGrid(Point dropPoint) {
        int cellSize = 50; // Kích thước của 1 ô vuông
        Rectangle gridBounds = grid.getBounds();

        // Mở rộng gridBounds thêm 50 pixel ở mỗi cạnh
        Rectangle extendedBounds = new Rectangle(
                gridBounds.x - cellSize, // Dịch lùi 50 pixel ở cạnh trái
                gridBounds.y - cellSize, // Dịch lùi 50 pixel ở cạnh trên
                gridBounds.width + 2 * cellSize, // Tăng chiều rộng thêm 100 pixel (50 ở mỗi bên)
                gridBounds.height + 2 * cellSize // Tăng chiều cao thêm 100 pixel (50 ở mỗi bên)
        );

        // Tính các đỉnh của hình chữ nhật
        Point topRight = new Point((int) dropPoint.getX() + getWidth(), (int) dropPoint.getY());
        Point bottomLeft = new Point((int) dropPoint.getX(), (int) dropPoint.getY() + getHeight());
        Point bottomRight = new Point((int) dropPoint.getX() + getWidth(), (int) dropPoint.getY() + getHeight());

        // Kiểm tra nếu tất cả các đỉnh đều nằm trong grid mở rộng
        return extendedBounds.contains(dropPoint)
                && extendedBounds.contains(topRight)
                && extendedBounds.contains(bottomLeft)
                && extendedBounds.contains(bottomRight);
    }

}