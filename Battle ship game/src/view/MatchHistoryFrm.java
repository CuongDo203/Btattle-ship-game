package view;

import controller.ClientControl;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.MatchHistory;
import model.Ranking;
import utils.ImageManager;

public class MatchHistoryFrm extends JFrame {

    private JLabel btnReturn;
    private JPanel pnMain;
    private MainFrm mainFrm;
    private JLabel lblPlayerName, lblTotalsWin, lblTotalLosses, lblTbName;
    private JTable tblHistory;
    private BufferedImage backgroundImg;
    private BufferedImage nameTag;
    private ClientControl clientCtr;
    private Ranking playerHistory;
    private List<MatchHistory> lsd;

    public MatchHistoryFrm(MainFrm frm, ClientControl clientCtr) {
        super("History");
        this.setSize(700, 600);
        this.mainFrm = frm;
        this.clientCtr = clientCtr;
        this.backgroundImg = ImageManager.getImage(ImageManager.HISTORY_BACKGROUND_IMAGE);
        this.nameTag = ImageManager.getImage(ImageManager.NAME_TAG);
        this.setContentPane(new CustomPanel());
        setLayout(new BorderLayout(0, 10));
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnTop.setOpaque(false);
        btnReturn = new JLabel("");
        ImageIcon returnIcon = new ImageIcon(getClass().getResource("/assets/icons/back.png"));
        btnReturn.setIcon(returnIcon);
        btnReturn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnReturn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnReturnActionPerformed();
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
        pnTop.add(btnReturn);
        add(pnTop, BorderLayout.NORTH);
        pnMain = new JPanel();
        pnMain.setOpaque(false);
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        lblPlayerName = new JLabel("Thuyền trưởng: ");
        lblPlayerName.setAlignmentX(CENTER_ALIGNMENT);
        lblTotalsWin = new JLabel("Thắng: ");
        lblTotalsWin.setAlignmentX(CENTER_ALIGNMENT);
        lblTotalLosses = new JLabel("Thua: ");
        lblTotalLosses.setAlignmentX(CENTER_ALIGNMENT);
        pnMain.add(lblPlayerName);
        pnMain.add(lblTotalsWin);
        pnMain.add(lblTotalLosses);

        // Table container panel with padding
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tablePanel.setOpaque(false);
        tablePanel.setBorder(new EmptyBorder(60, 50, 10, 50)); // Padding: top, left, bottom, right
        
        lblTbName = new JLabel("Lịch sử đấu");
        lblTbName.setFont(new Font("Serif", Font.BOLD, 30));
        lblTbName.setAlignmentX(CENTER_ALIGNMENT);
        tablePanel.add(lblTbName);
        
        // Match history table
        String[] columnNames = {"Thời gian", "Đối thủ", "Kết quả"};
        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0);
        tblHistory = new JTable(dtm);
        tblHistory.setPreferredScrollableViewportSize(new Dimension(500, 300)); // Set table size
        JScrollPane scrollPane = new JScrollPane(tblHistory);
        tablePanel.add(scrollPane); // Add table to the centered panel
        pnMain.add(tablePanel);

        add(pnMain, BorderLayout.CENTER);
    }

    private void btnReturnActionPerformed() {
        this.dispose();
        mainFrm.setVisible(true);
    }

    public void setPlayerHistory(Ranking playerHistory) {
        this.playerHistory = playerHistory;
    }

    public void setLsd(List<MatchHistory> lsd) {
        this.lsd = lsd;
    }

    public void showTblPlayerHistory() {
        if(lsd == null) return;
        DefaultTableModel dtm = (DefaultTableModel) tblHistory.getModel();
        dtm.setRowCount(0);
        for(MatchHistory h : lsd) {
            Vector<Object> row = new Vector<>();
            row.add(h.getStartTime());
            row.add(h.getOpponentUserName());
            row.add(h.getMatchResult());
            dtm.addRow(row);
        }
    }
    
    public void showWindow() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public JLabel getLblPlayerName() {
        return lblPlayerName;
    }

    public void setLblPlayerName(JLabel lblPlayerName) {
        this.lblPlayerName = lblPlayerName;
    }

    public JLabel getLblTotalsWin() {
        return lblTotalsWin;
    }

    public JLabel getLblTotalLosses() {
        return lblTotalLosses;
    }

    public void setLblTotalsWin(JLabel lblTotalsWin) {
        this.lblTotalsWin = lblTotalsWin;
    }

    public void setLblTotalLosses(JLabel lblTotalLosses) {
        this.lblTotalLosses = lblTotalLosses;
    }

    private class CustomPanel extends javax.swing.JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);  // Calls the default painting of components
            // Draw the background image
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
            int centerX = (getWidth()/2) - (nameTag.getWidth()*50/100 / 2);
            g.drawImage(nameTag, centerX, 10, nameTag.getWidth()*50/100, nameTag.getHeight()*50/100,null);
        }
    }
    
}
