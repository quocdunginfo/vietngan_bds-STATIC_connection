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
import vietngan_bds.DataTranferObject.DTO_HUONG;
import vietngan_bds.Utilities.UTL_String;

/**
 *
 * @author quocdunginfo
 */
public class BLL_HUONG {
    //private Database db = null;
    public BLL_HUONG()
    {
        //db=new Database();
    }
    /**
     * Lấy về đối tượng hướng
     * 
     * @param id id của hướng, 1-> Đông, 2->Tây, 3->Nam, 4->Bắc, 5->Đông nam, 
     * 6->Đông bắc, 7-> Tây nam, 8-> Tây bắc
     * @return đối tượng hướng || null
     */
    public DTO_HUONG Get(int id)
    {
        String sql = "select id, huong from huong where id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_HUONG obj = new DTO_HUONG();
        try {
            while(re.next())
            {
                obj.id = re.getInt("id");
                obj.huong = re.getString("huong");
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_HUONG.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    public ArrayList<DTO_HUONG> TimKiem(int id, String huong)
    {
        ArrayList<DTO_HUONG> list = new ArrayList<DTO_HUONG>();
        String sql = "SELECT `id` FROM `huong` WHERE 1=1";
        if(id!=-1)
            sql+=" and id="+id;
        sql+=" and huong like '%"+huong+"%'";
        ResultSet re = db.Get(sql);
        if(re==null) return list;
        DTO_HUONG obj;
        try {
            while(re.next())
            {
                obj = Get(re.getInt("id"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_HUONG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ArrayList<Integer> Filter_id(ArrayList<Integer> list, int id)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        String sql = "SELECT `id` from `thongtin_bds` WHERE `fk_vitri` IN"
        + " (SELECT `id` FROM `vitri` WHERE `fk_huong`="+id+")";
        
        sql+= " AND `id` IN ("+ UTL_String.From_Array(list)+")";
        
        ResultSet rs = db.Get(sql);
        if(rs==null) return re;
        try {
            while(rs.next())
            {
                re.add(rs.getInt("id"));
            }
            return re;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_HUONG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
}
