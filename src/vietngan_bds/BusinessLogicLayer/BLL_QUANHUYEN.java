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
import vietngan_bds.DataTranferObject.DTO_QUANHUYEN;

/**
 *
 * @author quocdunginfo
 */
public class BLL_QUANHUYEN {

    public BLL_QUANHUYEN() {
        //db=new Database();
    }
    //public Database db=null;
    public DTO_QUANHUYEN Get(int id)
    {
        String sql = "SELECT `TenQuanHuyen`, `MaTinhThanh`, `ThuTu` FROM `quanhuyen` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_QUANHUYEN obj = new DTO_QUANHUYEN();
        try {
            while(re.next())
            {
                obj.id = id;
                obj.name = re.getString("TenQuanHuyen");
                obj.fk_tinhthanh = re.getInt("MaTinhThanh");
                obj.rank = re.getInt("ThuTu");
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_QUANHUYEN.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }   
    public ArrayList<DTO_QUANHUYEN> TimKiem(int id_tinhthanh)
    {
        String sql = "SELECT `ID` FROM `quanhuyen` WHERE `MaTinhThanh`="+id_tinhthanh+" order by `tenquanhuyen`";
        ArrayList<DTO_QUANHUYEN> list = new ArrayList<DTO_QUANHUYEN>();
        ResultSet re = db.Get(sql);
        if(re==null) return list;
        DTO_QUANHUYEN obj;
        try {
            while(re.next())
            {
                obj=Get(re.getInt("ID"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_QUANHUYEN.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
