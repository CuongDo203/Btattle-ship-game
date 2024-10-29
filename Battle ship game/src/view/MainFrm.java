package view;

import controller.ClientControl;
import dto.ClientMessage;
import dto.PlayerInfoDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Player;
import utils.ImageManager;
import utils.Sound;

/**
 *
 * @author win
 */
public class MainFrm extends javax.swing.JFrame {

    private Sound sound;
    private BufferedImage backgroundImg;
    private boolean isSfxOn = true;
    private boolean isBgMusicOn = true;
    private ClientControl clientCtr;
    private List<PlayerInfoDTO> listPlayer;
    private LoadingSpinner spinner;
    private Player player;

    public MainFrm(Player player, ClientControl clientCtr) {
        initComponents();
        this.player = player;
        this.clientCtr = clientCtr;
        backgroundImg = ImageManager.getImage(ImageManager.MAIN_BACKGROUND_IMAGE);
        sound = new Sound();
        if (isBgMusicOn) {
            sound.soundBackground();
        } else {
            sound.stop();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMain = pnMain = new CustomPanel();
        lblAvarta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListUser = new javax.swing.JTable();
        lblRank = new javax.swing.JLabel();
        lblSetting = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();
        btnPlay = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAvarta.setBackground(new java.awt.Color(204, 204, 204));
        lblAvarta.setForeground(new java.awt.Color(255, 255, 255));
        lblAvarta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvarta.setText("Avatar");
        lblAvarta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lblAvarta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAvarta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAvartaMouseClicked(evt);
            }
        });

        tblListUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Trạng thái", "Điểm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblListUser);

        lblRank.setBackground(new java.awt.Color(255, 255, 255));
        lblRank.setForeground(new java.awt.Color(255, 255, 255));
        lblRank.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/ranking.png"))); // NOI18N
        lblRank.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRankMouseClicked(evt);
            }
        });

        lblSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/setting.png"))); // NOI18N
        lblSetting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSettingMouseClicked(evt);
            }
        });

        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/logout.png"))); // NOI18N
        lblLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoutMouseClicked(evt);
            }
        });

        btnPlay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/fight.png"))); // NOI18N
        btnPlay.setText("Chiến");
        btnPlay.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPlay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMainLayout = new javax.swing.GroupLayout(pnMain);
        pnMain.setLayout(pnMainLayout);
        pnMainLayout.setHorizontalGroup(
            pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMainLayout.createSequentialGroup()
                .addGroup(pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnMainLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblAvarta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRank, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(lblSetting, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(lblLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnMainLayout.createSequentialGroup()
                        .addContainerGap(206, Short.MAX_VALUE)
                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        pnMainLayout.setVerticalGroup(
            pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPlay)
                    .addGroup(pnMainLayout.createSequentialGroup()
                        .addGroup(pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAvarta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRank)
                            .addComponent(lblSetting)
                            .addComponent(lblLogout))
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void showListPlayer(List<PlayerInfoDTO> listPlayer) {
        DefaultTableModel dtm = (DefaultTableModel) tblListUser.getModel();
        dtm.setRowCount(0);
        listPlayer.sort((p1, p2) -> {
            if (p1.getStatus().equals("online") && !p2.getStatus().equals("online")) {
                return -1;
            } else if (p1.getStatus().equals("playing") && p2.getStatus().equals("offline")) {
                return -1;
            } else if (p1.getStatus().equals("offline") && !p2.getStatus().equals("offline")) {
                return 1;
            }
            return 0;
        });
        setListPlayer(listPlayer);
        for (PlayerInfoDTO p : listPlayer) {
            Vector<Object> row = new Vector<>();
            row.add(p.getUsername());
            row.add(p.getStatus());
            row.add(p.getTotalPoint());
            dtm.addRow(row);
        }

        // Custom rendering for the table to apply color based on the status
        tblListUser.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) table.getValueAt(row, 1); // Assuming column 1 is the status column

                if (status.equals("online")) {
                    cell.setForeground(Color.GREEN);
                } else if (status.equals("playing")) {
                    cell.setForeground(Color.BLUE);
                } else if (status.equals("offline")) {
                    cell.setForeground(Color.GRAY);
                }

                return cell;
            }
        });
    }

    public JLabel getLblAvarta() {
        return lblAvarta;
    }

    public void setClientCtr(ClientControl clientCtr) {
        this.clientCtr = clientCtr;
    }

    public void setIsSfxOn(boolean isSfxOn) {
        this.isSfxOn = isSfxOn;
    }

    public void setIsBgMusicOn(boolean isBgMusicOn) {
        this.isBgMusicOn = isBgMusicOn;
    }

    public boolean isSfxOn() {
        return isSfxOn;
    }

    public boolean isBgMusicOn() {
        return isBgMusicOn;
    }

    public Sound getSound() {
        return sound;
    }

    public List<PlayerInfoDTO> getListPlayer() {
        return listPlayer;
    }

    public void setListPlayer(List<PlayerInfoDTO> listPlayer) {
        this.listPlayer = listPlayer;
    }

    public LoadingSpinner getSpinner() {
        return spinner;
    }

    public Player getPlayer() {
        return player;
    }

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        sound.stop();
        setIsBgMusicOn(false);
//        this.dispose();
        if (isSfxOn) {
            sound.soundButtonClick();
        }
//        ReadyFrm readyFrm = new ReadyFrm();
//        readyFrm.showWindow();
        
        ClientMessage clientMess = new ClientMessage();
        clientMess.setCommand(ClientMessage.FIND_RANDOM_PLAYER);
        System.out.println("Find a random player: "+ clientMess.getCommand());
        clientCtr.sendMessage(clientMess);
        spinner = new LoadingSpinner(this, clientCtr, "Đang tìm kiếm đối thủ");
        spinner.showWindow();
    }//GEN-LAST:event_btnPlayActionPerformed

    private void lblRankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRankMouseClicked
        sound.stop();
        setIsBgMusicOn(false);
        if (isSfxOn) {
            sound.soundButtonClick();
        }
//        this.setVisible(false);
//        RankingFrm rank = new RankingFrm(this);
//        rank.showWindow();
        ClientMessage clientMess = new ClientMessage();
        clientMess.setCommand(ClientMessage.GET_RANKING);
        clientCtr.sendMessage(clientMess);
    }//GEN-LAST:event_lblRankMouseClicked

    private void lblSettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSettingMouseClicked
        if (isSfxOn) {
            sound.soundButtonClick();
        }
        SettingFrm setting = new SettingFrm(this, rootPaneCheckingEnabled);
        setting.setLocationRelativeTo(this);
        setting.showDialog();
    }//GEN-LAST:event_lblSettingMouseClicked

    private void lblLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseClicked
        sound.stop();
        setIsBgMusicOn(false);
        if (isSfxOn) {
            sound.soundButtonClick();
        }
        ClientMessage clientMess = new ClientMessage();
        clientMess.setCommand(ClientMessage.LOGOUT);
        String username = player.getUsername();
        clientMess.setUsername(username);
        clientCtr.sendMessage(clientMess);
//        this.dispose();
//        LoginFrm login = new LoginFrm();
//        login.showWindow();
    }//GEN-LAST:event_lblLogoutMouseClicked

    private void lblAvartaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAvartaMouseClicked
        sound.stop();
        setIsBgMusicOn(false);
        
        if (isSfxOn) {
            sound.soundButtonClick();
        }
        ClientMessage clientMess = new ClientMessage();
        clientMess.setCommand(ClientMessage.SHOW_MATCH_HISTORY);
        clientMess.setPlayerID(player.getId());
        clientCtr.sendMessage(clientMess);
//        this.setVisible(false);
//        MatchHistoryFrm history = new MatchHistoryFrm(this);
//        history.showWindow();
    }//GEN-LAST:event_lblAvartaMouseClicked

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), null);
        pnMain.paintComponents(g);
    }

    // Custom panel for painting the background
    private class CustomPanel extends javax.swing.JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);  // Calls the default painting of components
            // Draw the background image
            g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), null);
        }
    }

    public void showWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlay;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvarta;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblRank;
    private javax.swing.JLabel lblSetting;
    private javax.swing.JPanel pnMain;
    private javax.swing.JTable tblListUser;
    // End of variables declaration//GEN-END:variables
}
