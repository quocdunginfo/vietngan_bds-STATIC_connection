/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.BusinessLogicLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vietngan_bds.DataAccessLayer.db;
import vietngan_bds.DataTranferObject.DTO_TINHTRANGPHAPLY;

/**
 *
 * @author quocdunginfo
 */
public class BLL_TINHTRANGPHAPLY {
   // private Database db=null;
    public BLL_TINHTRANGPHAPLY() {
        //db=new  Database();
    }
    public DTO_TINHTRANGPHAPLY Get(int id)
    {
        String sql = "SELECT `sohong`, `sodo`, `khac` FROM `tinhtrangphaply` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_TINHTRANGPHAPLY obj = new DTO_TINHTRANGPHAPLY();
        try {
            while(re.next())
            {
                obj.id = id;
                obj.sohong = re.getInt("sohong");
                obj.sodo = re.getInt("sodo");                
                obj.khac = re.getString("khac");
                
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TINHTRANGPHAPLY.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    public int Them(DTO_TINHTRANGPHAPLY obj)
    {
        String sql = "INSERT INTO `tinhtrangphaply`(`sohong`, `sodo`, `khac`)"
                + " VALUES ("+obj.sohong+","+obj.sodo+",'"+obj.khac+"')";
        Boolean re = db.Run(sql);
        if(!re) return -1;
        return MaMoiNhat();
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from tinhtrangphaply";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TINHTRANGPHAPLY.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    /**
     * Cập nhật thông tin
     * @param obj .id: mã, .XXX thông tin cần cập nhật
     * @return true || false
     */
    public Boolean Sua(DTO_TINHTRANGPHAPLY obj)
    {
        String sql = "UPDATE `tinhtrangphaply` SET `sohong`="+obj.sohong+","
                + "`sodo`="+obj.sodo+",`khac`='"+obj.khac+"' WHERE id="+obj.id;
        Boolean re = db.Run(sql);
        return re;
    }
    /**
     * Xóa theo id
     * @param id
     * @return
     */
    public Boolean Xoa(int id)
    {
        String sql="DELETE FROM `tinhtrangphaply` WHERE id="+id;
        Boolean re = db.Run(sql);
        return re;
    }
    
}
