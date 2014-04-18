/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.GUI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import vietngan_bds.BusinessLogicLayer.BLL_PHUONGXA;
import vietngan_bds.BusinessLogicLayer.BLL_QUANHUYEN;
import vietngan_bds.BusinessLogicLayer.BLL_TINHTHANH;
import vietngan_bds.DataTranferObject.DTO_DIACHI;
import vietngan_bds.DataTranferObject.DTO_PHUONGXA;
import vietngan_bds.DataTranferObject.DTO_QUANHUYEN;
import vietngan_bds.DataTranferObject.DTO_TINHTHANH;
import vietngan_bds.Utilities.UTL_ComboBox;
import vietngan_bds.Utilities.UTL_TextField;

/**
 *
 * @author quocdunginfo
 */
public class F_DIACHI_PICKUP extends javax.swing.JDialog {
    
    /**
     * Creates new form F_DIACHI_PICKUP
     */
    private JFrame owner=new JFrame();
    private DTO_DIACHI obj=new DTO_DIACHI();
    //sua, them
    private String mode="";
    public DTO_DIACHI result=new DTO_DIACHI();
    public F_DIACHI_PICKUP() {
        initComponents();
    }
    public F_DIACHI_PICKUP(JFrame owner, Boolean modal, String mode, DTO_DIACHI id_diachi) {
        initComponents();
        setModal(modal);
        this.owner=owner;
        this.mode=mode;
        if(this.mode.equals("xemchitiet"))
        {
            this.mode = "sua";
        }
        if(this.mode.equals("sua"))
        {
            //this.obj=new BLL_DIACHI().Get(id_diachi);
            this.obj = id_diachi;
            if(this.obj==null) this.obj=new DTO_DIACHI();
        }
        else
        {
            
        }
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
                jButton_ok.doClick();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                
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
        });
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_tinhthanh = new javax.swing.JComboBox();
        jComboBox_quanhuyen = new javax.swing.JComboBox();
        jComboBox_phuongxa = new javax.swing.JComboBox();
        jTextField_duong = new javax.swing.JTextField();
        jTextField_sonha = new javax.swing.JTextField();
        jButton_ok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Tỉnh thành:");

        jLabel2.setText("Quận huyện:");

        jLabel3.setText("Phường xã:");

        jLabel4.setText("Đường:");

        jLabel5.setText("Số nhà:");

        jComboBox_tinhthanh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_tinhthanh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tinhthanhItemStateChanged(evt);
            }
        });

        jComboBox_quanhuyen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_quanhuyen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_quanhuyenItemStateChanged(evt);
            }
        });

        jComboBox_phuongxa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_phuongxa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_phuongxaItemStateChanged(evt);
            }
        });

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox_phuongxa, 0, 137, Short.MAX_VALUE)
                            .addComponent(jTextField_duong)
                            .addComponent(jTextField_sonha)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox_quanhuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jComboBox_tinhthanh, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox_tinhthanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox_quanhuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox_phuongxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_duong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_sonha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_ok)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Load_tinhthanh();
        if(this.mode.equals("sua"))
        {
            jTextField_duong.setText(obj.duong);
            jTextField_sonha.setText(obj.sonha);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jComboBox_tinhthanhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_tinhthanhItemStateChanged
        // TODO add your handling code here:
        Load_quanhuyen();
    }//GEN-LAST:event_jComboBox_tinhthanhItemStateChanged

    private void jComboBox_phuongxaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_phuongxaItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox_phuongxaItemStateChanged

    private void jComboBox_quanhuyenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_quanhuyenItemStateChanged
        // TODO add your handling code here:
        Load_phuongxa();
    }//GEN-LAST:event_jComboBox_quanhuyenItemStateChanged

    private void jButton_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_okActionPerformed
        // TODO add your handling code here:
        this.result=Get_form_data();
        this.dispose();
    }//GEN-LAST:event_jButton_okActionPerformed
    private DTO_DIACHI Get_form_data()
    {
        DTO_DIACHI obj_tmp=new DTO_DIACHI();
        if(this.mode.equals("sua"))
        {
            obj_tmp.id = obj.id;
        }
        else
        {
            //not thing
        }
        obj_tmp.duong = UTL_TextField.GetString(jTextField_duong);
        obj_tmp.sonha = UTL_TextField.GetString(jTextField_sonha);
        obj_tmp.fk_phuongxa = new BLL_PHUONGXA().Get(UTL_ComboBox.GetComboBoxValueInt(jComboBox_phuongxa));
        if(obj_tmp.fk_phuongxa==null) obj_tmp.fk_phuongxa = new DTO_PHUONGXA();
        
        obj_tmp.fk_quanhuyen = new BLL_QUANHUYEN().Get(UTL_ComboBox.GetComboBoxValueInt(jComboBox_quanhuyen));
        if(obj_tmp.fk_quanhuyen==null) obj_tmp.fk_quanhuyen = new DTO_QUANHUYEN();
        
        obj_tmp.fk_tinhthanh = new BLL_TINHTHANH().Get(UTL_ComboBox.GetComboBoxValueInt(jComboBox_tinhthanh));
        if(obj_tmp.fk_tinhthanh==null) obj_tmp.fk_tinhthanh = new DTO_TINHTHANH();
        
        return obj_tmp;
    }
    private void Load_tinhthanh()
    {
        ArrayList<DTO_TINHTHANH> list = new BLL_TINHTHANH().TimKiem();
        jComboBox_tinhthanh.removeAllItems();
        UTL_ComboBox.AddComboBoxItem(jComboBox_tinhthanh, -1, "(Không xác định)");
        for(DTO_TINHTHANH obj_tmp:list)
        {
            UTL_ComboBox.AddComboBoxItem(jComboBox_tinhthanh, obj_tmp.id, obj_tmp.name);
        }
        if(jComboBox_tinhthanh.getItemCount()>0)
        {
            if(this.mode.equals("them"))
                jComboBox_tinhthanh.setSelectedIndex(0);
            else
                UTL_ComboBox.SetComboBoxSelectedItem(jComboBox_tinhthanh, obj.fk_tinhthanh.id);
        }
    }
    private void Load_quanhuyen()
    {
        if(jComboBox_tinhthanh.getItemCount()<=0 || jComboBox_tinhthanh.getSelectedIndex()==-1) return;
        ArrayList<DTO_QUANHUYEN> list = new BLL_QUANHUYEN().TimKiem(UTL_ComboBox.GetComboBoxValueInt(jComboBox_tinhthanh));
        jComboBox_quanhuyen.removeAllItems();
        UTL_ComboBox.AddComboBoxItem(jComboBox_quanhuyen, -1, "(Không xác định)");
        for(DTO_QUANHUYEN obj_tmp:list)
        {
            UTL_ComboBox.AddComboBoxItem(jComboBox_quanhuyen, obj_tmp.id, obj_tmp.name);
        }
        if(jComboBox_quanhuyen.getItemCount()>0)
        {
            if(this.mode.equals("them"))
                jComboBox_quanhuyen.setSelectedIndex(0);
            else
                UTL_ComboBox.SetComboBoxSelectedItem(jComboBox_quanhuyen, obj.fk_quanhuyen.id);
        }
    }
    private void Load_phuongxa()
    {
        if(jComboBox_quanhuyen.getItemCount()<=0 || jComboBox_quanhuyen.getSelectedIndex()==-1) return;
        
        ArrayList<DTO_PHUONGXA> list = new BLL_PHUONGXA().TimKiem(UTL_ComboBox.GetComboBoxValueInt(jComboBox_quanhuyen));
        jComboBox_phuongxa.removeAllItems();
        UTL_ComboBox.AddComboBoxItem(jComboBox_phuongxa, -1, "(Không xác định)");
        for(DTO_PHUONGXA obj_tmp:list)
        {
            UTL_ComboBox.AddComboBoxItem(jComboBox_phuongxa, obj_tmp.id, obj_tmp.name);
        }
        if(jComboBox_phuongxa.getItemCount()>0)
        {
            if(this.mode.equals("them"))
                jComboBox_phuongxa.setSelectedIndex(0);
            else
                UTL_ComboBox.SetComboBoxSelectedItem(jComboBox_phuongxa, obj.fk_phuongxa.id);
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
            java.util.logging.Logger.getLogger(F_DIACHI_PICKUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_DIACHI_PICKUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_DIACHI_PICKUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_DIACHI_PICKUP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_DIACHI_PICKUP().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_ok;
    private javax.swing.JComboBox jComboBox_phuongxa;
    private javax.swing.JComboBox jComboBox_quanhuyen;
    private javax.swing.JComboBox jComboBox_tinhthanh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_duong;
    private javax.swing.JTextField jTextField_sonha;
    // End of variables declaration//GEN-END:variables
}