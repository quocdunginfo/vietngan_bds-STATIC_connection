/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.BusinessLogicLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vietngan_bds.DataAccessLayer.db;
import vietngan_bds.DataTranferObject.DTO_PHUONGXA;

/**
 *
 * @author quocdunginfo
 */
public class BLL_PHUONGXA {

    public BLL_PHUONGXA() {
        //db=new Database();
    }
    //public Database db=null;
    public DTO_PHUONGXA Get(int id)
    {
        String sql = "SELECT `TenPhuongXa`, `MaQuanHuyen`,"
                + " `ThuTu` FROM `phuongxa` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_PHUONGXA obj = new DTO_PHUONGXA();
        try {
            while(re.next())
            {
                obj.id = id;
                obj.name = re.getString("TenPhuongXa");
                obj.fk_quanhuyen = re.getInt("MaQuanHuyen");
                obj.rank = re.getInt("ThuTu");
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_PHUONGXA.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }   
    public ArrayList<DTO_PHUONGXA> TimKiem(int id_quanhuyen)
    {
        String sql = "SELECT `ID` FROM `phuongxa` WHERE `MaQuanHuyen`="+id_quanhuyen+" order by `tenphuongxa`";
        ArrayList<DTO_PHUONGXA> list = new ArrayList<DTO_PHUONGXA>();
        ResultSet re = db.Get(sql);
        if(re==null) return list;
        DTO_PHUONGXA obj;
        try {
            while(re.next())
            {
                obj=Get(re.getInt("ID"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_PHUONGXA.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
