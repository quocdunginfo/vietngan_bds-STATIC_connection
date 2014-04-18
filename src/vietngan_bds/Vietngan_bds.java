/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds;

import java.sql.ResultSet;
import javax.swing.UIManager;
import vietngan_bds.DataAccessLayer.db;
import vietngan_bds.GUI.F_CHECK;
import vietngan_bds.Setting.My_Setting;
import vietngan_bds.Utilities.Debug;


/**
 *
 * @author quocdunginfo
 */
public class Vietngan_bds {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        My_Setting.Load_Setting();
        Load_Theme();
        //connect database firsr
        db.Open();
        
       
        //F_TTBDS vd=new F_TTBDS("them", 5);
        //F_QLY_TTBDS vd = new F_QLY_TTBDS();
        F_CHECK vd=new F_CHECK();
        vd.setVisible(true);
        //ArrayList<DTO_PHUONGXA> kk = new BLL_PHUONGXA().TimKiem(113);
        //F_DIACHI_PICKUP vd = new F_DIACHI_PICKUP(null,true,"sua",-1);
        //vd.setVisible(true);
        //for(int k=1;k<=5;k++)
        //new BLL_TTBDS().Xoa(k);
        //ArrayList<Integer> kk = new ArrayList<Integer>();
        //kk.add(1);
        
    }
    private static void Load_Theme()
    {
        try{
            //UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
            //UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
        }catch(Exception ey)
        {
            Debug.WriteLine("Theme không có sẵn: "+ey.toString());
        }
    }
}
