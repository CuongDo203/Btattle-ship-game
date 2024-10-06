package view;

import utils.Sound;

public class RegisterFrm extends javax.swing.JFrame {

    private Sound sound;
    
    public RegisterFrm() {
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

        lblDangKy = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtRetypedPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        linkDangNhap = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(451, 380));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDangKy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDangKy.setForeground(new java.awt.Color(51, 255, 204));
        lblDangKy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDangKy.setText("ĐĂNG KÝ");
        getContentPane().add(lblDangKy, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 438, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Tên đăng nhập:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 54, 110, 30));
        getContentPane().add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 54, 200, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("Mật khẩu:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 102, 99, 30));
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 102, 200, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 102));
        jLabel3.setText("Nhập lại mật khẩu:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 150, -1, 30));

        txtRetypedPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRetypedPassActionPerformed(evt);
            }
        });
        getContentPane().add(txtRetypedPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 150, 200, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 102));
        jLabel4.setText("Email:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 198, 99, 28));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 198, 200, 28));

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Đăng ký");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 260, 127, 36));

        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Đã có tài khoản?");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 98, 40));

        linkDangNhap.setForeground(new java.awt.Color(102, 102, 255));
        linkDangNhap.setText("Đăng nhập ngay");
        linkDangNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        linkDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkDangNhapMouseClicked(evt);
            }
        });
        getContentPane().add(linkDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 99, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/images/Register_background_resized.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRetypedPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRetypedPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRetypedPassActionPerformed

    private void linkDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkDangNhapMouseClicked
        this.dispose();
        sound.stop();
        LoginFrm frm = new LoginFrm();
        frm.showWindow();
    }//GEN-LAST:event_linkDangNhapMouseClicked

    public void showWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        sound = new Sound();
        sound.soundBackground();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblDangKy;
    private javax.swing.JLabel linkDangNhap;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtRetypedPass;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
