package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.ImageManager;
import utils.Sound;

public class LoginFrm extends javax.swing.JFrame {

    private Sound sound;
    private BufferedImage backgroundImg;

    public LoginFrm() {
        backgroundImg = ImageManager.getImage(ImageManager.LOGIN_IMAGE);
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnDangNhap = new javax.swing.JPanel();
        lblDangNhap = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        btnDangNhap = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        linkDangKy = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(226, 180, 63));

        pnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        pnDangNhap.setPreferredSize(new java.awt.Dimension(430, 292));
        pnDangNhap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDangNhap.setForeground(new java.awt.Color(204, 45, 44));
        lblDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDangNhap.setText("ĐĂNG NHẬP");
        pnDangNhap.add(lblDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 417, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tên đăng nhập:");
        jLabel1.setAlignmentY(0.0F);
        pnDangNhap.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, -1, 30));
        pnDangNhap.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 75, 230, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setLabelFor(txtPass);
        jLabel2.setText("Mật khẩu: ");
        jLabel2.setAlignmentY(5.0F);
        pnDangNhap.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 123, 100, 30));
        pnDangNhap.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 123, 230, 30));

        btnDangNhap.setBackground(new java.awt.Color(102, 255, 102));
        btnDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.setBorderPainted(false);
        btnDangNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        pnDangNhap.add(btnDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 201, -1, 36));

        jLabel3.setText("Chưa có tài khoản ?");
        pnDangNhap.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 255, 113, 40));

        linkDangKy.setForeground(new java.awt.Color(51, 102, 255));
        linkDangKy.setText("Đăng ký ngay");
        linkDangKy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        linkDangKy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkDangKyMouseClicked(evt);
            }
        });
        pnDangNhap.add(linkDangKy, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 255, -1, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/Login_background_resized.jpg"))); // NOI18N
        pnDangNhap.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void showWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        sound = new Sound();
//        sound.soundBackground();
    }


    private void linkDangKyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkDangKyMouseClicked
        this.dispose();
        sound.soundButtonClick();
//        sound.stop();
        RegisterFrm frm = new RegisterFrm();
        frm.showWindow();
    }//GEN-LAST:event_linkDangKyMouseClicked

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        sound.stop();
        sound.soundButtonClick();
        this.dispose();
        MainFrm mainFrm = new MainFrm();
        mainFrm.showWindow();
    }//GEN-LAST:event_btnDangNhapActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblDangNhap;
    private javax.swing.JLabel linkDangKy;
    private javax.swing.JPanel pnDangNhap;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
