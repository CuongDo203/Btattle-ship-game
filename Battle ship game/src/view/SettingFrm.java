 package view;

/**
 *
 * @author win
 */
public class SettingFrm extends javax.swing.JDialog {

    private MainFrm mainFrm;
    
    public SettingFrm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mainFrm = (MainFrm) parent;
        if(mainFrm.isBgMusicOn()) {
            toggleBgMusic.setText("ON");
            toggleBgMusic.setSelected(true);
        }
        else {
            toggleBgMusic.setText("OFF");
            toggleBgMusic.setSelected(false);
        }
        if(mainFrm.isSfxOn()) {
            toggleSFX.setText("ON");
            toggleSFX.setSelected(true);
        }
        else {
            toggleSFX.setText("OFF");
            toggleSFX.setSelected(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        toggleSFX = new javax.swing.JToggleButton();
        toggleBgMusic = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cài đặt");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("1. Nhạc nền");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("2. Hiệu ứng âm thanh");

        toggleSFX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        toggleSFX.setSelected(true);
        toggleSFX.setText("ON");
        toggleSFX.setMaximumSize(new java.awt.Dimension(48, 26));
        toggleSFX.setPreferredSize(new java.awt.Dimension(48, 26));
        toggleSFX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleSFXActionPerformed(evt);
            }
        });

        toggleBgMusic.setSelected(true);
        toggleBgMusic.setText("ON");
        toggleBgMusic.setMaximumSize(new java.awt.Dimension(112, 26));
        toggleBgMusic.setPreferredSize(new java.awt.Dimension(112, 26));
        toggleBgMusic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleBgMusicActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(toggleSFX, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toggleBgMusic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(toggleBgMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toggleSFX, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void toggleSFXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleSFXActionPerformed
        if(toggleSFX.isSelected()) {
            mainFrm.setIsSfxOn(true);
            toggleSFX.setText("ON");
        }
        else {
            mainFrm.setIsSfxOn(false);
            toggleSFX.setText("OFF");
        }
    }//GEN-LAST:event_toggleSFXActionPerformed

    private void toggleBgMusicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleBgMusicActionPerformed
        if(toggleBgMusic.isSelected()) {
            toggleBgMusic.setText("ON");
            mainFrm.setIsBgMusicOn(true);
        }
        else {
            toggleBgMusic.setText("OFF");
            mainFrm.setIsBgMusicOn(false);
            mainFrm.getSound().stop();
        }
    }//GEN-LAST:event_toggleBgMusicActionPerformed

    public void showDialog() {
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JToggleButton toggleBgMusic;
    private javax.swing.JToggleButton toggleSFX;
    // End of variables declaration//GEN-END:variables
}
