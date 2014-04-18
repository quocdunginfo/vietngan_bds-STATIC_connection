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
import vietngan_bds.DataTranferObject.DTO_TIENTE;

/**
 *
 * @author quocdunginfo
 */
public class BLL_TIENTE {

    public BLL_TIENTE() {
        //db=new Database();
    }
    //private Database db=null;
    public DTO_TIENTE Get(int id)
    {
        String sql = "select `id`, `name` from tiente where id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_TIENTE obj = new DTO_TIENTE();
        try {
            while(re.next())
            {
                obj.id = re.getInt("id");
                obj.name = re.getString("name");
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TIENTE.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    public ArrayList<DTO_TIENTE> TimKiem(int id, String name)
    {
        ArrayList<DTO_TIENTE> list = new ArrayList<DTO_TIENTE>();
        String sql = "SELECT `id` FROM `tiente` WHERE 1=1";
        if(id!=-1)
            sql+=" and id="+id;
        sql+=" and name like '%"+name+"%'";
        ResultSet re = db.Get(sql);
        if(re==null) return list;
        DTO_TIENTE obj;
        try {
            while(re.next())
            {
                obj = Get(re.getInt("id"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TIENTE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
