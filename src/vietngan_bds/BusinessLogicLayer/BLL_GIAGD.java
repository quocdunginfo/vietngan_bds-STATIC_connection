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
import vietngan_bds.DataTranferObject.DTO_GIAGD;
import vietngan_bds.DataTranferObject.DTO_TIENTE;
import vietngan_bds.Utilities.UTL_String;

/**
 *
 * @author quocdunginfo
 */
public class BLL_GIAGD {
    //private Database db=null;
    public BLL_GIAGD()
    {
        //db=new Database();
    }
    public DTO_GIAGD Get(int id)
    {
        String sql = "SELECT `giaban`, `fk_giaban_tiente`, `fk_giaban_donvi`,"
                + " `giathue`, `fk_giathue_tiente`, `fk_giathue_donvi`"
                + " FROM `gia_gd` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        try {
            while(re.next())
            {
                DTO_GIAGD obj = new DTO_GIAGD();
                obj.id = id;
                
                obj.giaban = re.getDouble("giaban");
                obj.fk_giaban_tiente = new BLL_TIENTE().Get(re.getInt("fk_giaban_tiente"));
                if(obj.fk_giaban_tiente==null) obj.fk_giaban_tiente=new DTO_TIENTE();
                obj.fk_giaban_donvi = new BLL_DONVI().Get(re.getInt("fk_giaban_donvi"));
                if(obj.fk_giaban_donvi==null) obj.fk_giaban_donvi=new DTO_DONVI();
                
                obj.giathue = re.getDouble("giathue");
                obj.fk_giathue_tiente = new BLL_TIENTE().Get(re.getInt("fk_giathue_tiente"));
                if(obj.fk_giathue_tiente==null) obj.fk_giathue_tiente=new DTO_TIENTE();
                obj.fk_giathue_donvi = new BLL_DONVI().Get(re.getInt("fk_giathue_donvi"));
                if(obj.fk_giathue_donvi==null) obj.fk_giathue_donvi=new DTO_DONVI();
                
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_GIAGD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int Them(DTO_GIAGD obj)
    {
        String sql = "INSERT INTO `gia_gd`(`giaban`, `fk_giaban_tiente`,"
                + " `fk_giaban_donvi`, `giathue`, `fk_giathue_tiente`,"
                + " `fk_giathue_donvi`)"
                + " VALUES ("+obj.giaban+","+obj.fk_giaban_tiente.id+","
                + obj.fk_giaban_donvi.id+","+obj.giathue+","+obj.fk_giathue_tiente.id + ","
                + obj.fk_giathue_donvi.id+")";
        Boolean re = db.Run(sql);
        if(!re) return -1;
        return MaMoiNhat();
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from `gia_gd`";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_GIAGD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    /**
     * Cập nhật thông tin obj
     * @param obj .id: mã loại, .XXX thông tin cần cập nhật
     * @return true || false
     */
    public Boolean Sua(DTO_GIAGD obj)
    {
        String sql = "UPDATE `gia_gd` SET `giaban`="+obj.giaban+","
                + "`fk_giaban_tiente`="+obj.fk_giaban_tiente.id+","
                + "`fk_giaban_donvi`="+obj.fk_giaban_donvi.id+","
                + "`giathue`="+obj.giathue+","
                + "`fk_giathue_tiente`="+obj.fk_giathue_tiente.id+","
                + "`fk_giathue_donvi`="+obj.fk_giathue_donvi.id
                + " WHERE id="+obj.id;
        Boolean re = db.Run(sql);
        return re;
    }
    /**
     * Xóa obj theo id
     * @param id
     * @return
     */
    public Boolean Xoa(int id)
    {
        String sql="DELETE FROM `gia_gd` WHERE id="+id;
        Boolean re = db.Run(sql);
        return re;
    }
    public ArrayList<Integer> Filter_giaban(ArrayList<Integer> list, Double from, Double to, int idtiente, int iddonvi)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        String sql = "SELECT `id` from `thongtin_bds` WHERE `fk_gia_gd` IN" +
        "(SELECT `id` FROM `gia_gd` WHERE `giaban`>="+from+" AND `giaban`<="+to
                + " AND `fk_giaban_tiente`="+idtiente+" AND `fk_giaban_donvi`="+iddonvi+")";
        
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
            Logger.getLogger(BLL_GIAGD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    public ArrayList<Integer> Filter_giathue(ArrayList<Integer> list, Double from, Double to, int idtiente, int iddonvi)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        String sql = "SELECT `id` from `thongtin_bds` WHERE `fk_gia_gd` IN" +
        "(SELECT `id` FROM `gia_gd` WHERE `giathue`>="+from+" AND `giathue`<="+to
                + " AND `fk_giathue_tiente`="+idtiente+" AND `fk_giathue_donvi`="+iddonvi+")";
        
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
            Logger.getLogger(BLL_GIAGD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
}
