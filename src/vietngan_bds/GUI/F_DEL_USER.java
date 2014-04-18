/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.GUI;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import vietngan_bds.BusinessLogicLayer.BLL_TTBDS;
import vietngan_bds.BusinessLogicLayer.BLL_USER;
import vietngan_bds.DataTranferObject.DTO_USER;
import vietngan_bds.Utilities.UTL_ComboBox;

/**
 *
 * @author quocdunginfo
 */
public class F_DEL_USER extends javax.swing.JDialog {

    /**
     * Creates new form F_DEL_USER
     */
    private String label_alert = "<html>Xóa nhân viên có mã là [id] và gán mọi hoạt động liên sang"
            + "<br>cho nhân viên khác. Chọn nhân viên để gán";
    private int id=0;
    /**
     *
     * @param id id user cần xóa
     * @param modal
     */
    public F_DEL_USER(int id, Boolean modal) {
        initComponents();
        
        this.id=id;
        setModal(modal);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_alert = new javax.swing.JLabel();
        jComboBox_nv = new javax.swing.JComboBox();
        jButton_ok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel_alert.setText("<html>Xóa nhân viên có mã là 23 và gán mọi hoạt động liên sang<br>cho nhân viên khác. Chọn nhân viên để gán");

        jComboBox_nv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton_ok.setText("Hoàn tất");
        jButton_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_okActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_alert)
                    .addComponent(jComboBox_nv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_ok)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_alert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox_nv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_ok)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
        //hien thi label_alert
        jLabel_alert.setText(label_alert.replace("[id]", id+""));
        
        //load combobox nhanvien phu hop
        Load_combobox_nv();
    }//GEN-LAST:event_formWindowOpened

    private void jButton_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okActionPerformed
        // TODO add your handling code here:
        //thực hiện xóa tại đây luôn
        //xoa nhan vien duoc chon khoi he thong
        Boolean re = new BLL_USER().Xoa(this.id,UTL_ComboBox.GetComboBoxValueInt(jComboBox_nv));
        if(re)
        {
            JOptionPane.showMessageDialog(null, "Xóa thành công nhân viên có mã "+this.id);
            dispose();
        }
    }//GEN-LAST:event_jButton_okActionPerformed
    private void Load_combobox_nv()
    {
        jComboBox_nv.removeAllItems();
        ArrayList<DTO_USER> re = new BLL_USER().TimKiem(-1, "", "", -1, -1);
        for(DTO_USER tmp:re)
        {
            if(tmp.id==this.id) continue;
            //add to combobox
            UTL_ComboBox.AddComboBoxItem(jComboBox_nv, tmp.id, tmp.username+" ("+tmp.fullname+")");
        }
        if(jComboBox_nv.getItemCount()>0)
        {
            jComboBox_nv.setSelectedIndex(0);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(F_DEL_USER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_DEL_USER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_DEL_USER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_DEL_USER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_DEL_USER(0,true).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_ok;
    private javax.swing.JComboBox jComboBox_nv;
    private javax.swing.JLabel jLabel_alert;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}