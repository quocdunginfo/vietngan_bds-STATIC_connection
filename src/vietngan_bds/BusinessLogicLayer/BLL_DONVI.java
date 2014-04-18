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
import vietngan_bds.DataTranferObject.DTO_DONVI;
import vietngan_bds.DataTranferObject.DTO_HUONG;

/**
 *
 * @author quocdunginfo
 */
public class BLL_DONVI {

    public BLL_DONVI() {
        //db=new Database();
    }
    //private Database db=null;
    public DTO_DONVI Get(int id)
    {
        String sql = "select `id`, `name` from donvi where id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_DONVI obj = new DTO_DONVI();
        try {
            while(re.next())
            {
                obj.id = re.getInt("id");
                obj.name = re.getString("name");
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_DONVI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    public ArrayList<DTO_DONVI> TimKiem(int id, String name)
    {
        ArrayList<DTO_DONVI> list = new ArrayList<DTO_DONVI>();
        String sql = "SELECT `id` FROM `donvi` WHERE 1=1";
        if(id!=-1)
            sql+=" and id="+id;
        sql+=" and name like '%"+name+"%'";
        ResultSet re = db.Get(sql);
        if(re==null) return list;
        DTO_DONVI obj;
        try {
            while(re.next())
            {
                obj = Get(re.getInt("id"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_DONVI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
