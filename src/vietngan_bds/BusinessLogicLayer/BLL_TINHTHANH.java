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
import vietngan_bds.DataTranferObject.DTO_TINHTHANH;

/**
 *
 * @author quocdunginfo
 */
public class BLL_TINHTHANH {

    public BLL_TINHTHANH() {
        //db=new Database();
    }
    //public Database db=null;
    public DTO_TINHTHANH Get(int id)
    {
        String sql = "SELECT `TenTinhThanh`,"
                + " `ThuTu` FROM `tinhthanh` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_TINHTHANH obj = new DTO_TINHTHANH();
        try {
            while(re.next())
            {
                obj.id = id;
                obj.name = re.getString("TenTinhThanh");
                obj.rank = re.getInt("ThuTu");
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TINHTHANH.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    public ArrayList<DTO_TINHTHANH> TimKiem()
    {
        String sql = "SELECT `ID` FROM `tinhthanh` WHERE 1=1 order by `tentinhthanh`";
        ArrayList<DTO_TINHTHANH> list = new ArrayList<DTO_TINHTHANH>();
        ResultSet re = db.Get(sql);
        if(re==null) return list;
        DTO_TINHTHANH obj;
        try {
            while(re.next())
            {
                obj=Get(re.getInt("ID"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TINHTHANH.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
