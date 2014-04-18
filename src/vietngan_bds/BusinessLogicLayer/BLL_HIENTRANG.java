/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.BusinessLogicLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import vietngan_bds.DataAccessLayer.db;
import vietngan_bds.DataTranferObject.DTO_HIENTRANG;
import vietngan_bds.Utilities.UTL_Date;
import vietngan_bds.Utilities.UTL_String;

/**
 *
 * @author quocdunginfo
 */
public class BLL_HIENTRANG {
   // private Database db=null;
    
    public BLL_HIENTRANG() {
        //db=new Database();
    }
    /**
     * Lấy đối tượng
     * 
     * @param id
     * @return obj || null
     */
    public DTO_HIENTRANG Get(int id)
    {
        String sql = "SELECT `solau`, `gac`, `tuong`, `mai`, `phongkhach`,"
                + " `phongngu`, `phongan`, `nhavesinh` FROM `hientrang` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_HIENTRANG obj = new DTO_HIENTRANG();
        try {
            while(re.next())
            {
                obj.id = id;
                obj.solau = re.getDouble("solau");
                obj.gac = re.getDouble("gac");
                obj.tuong = re.getString("tuong");
                obj.mai = re.getString("mai");
                obj.phongkhach = re.getString("phongkhach");
                obj.phongngu = re.getString("phongngu");
                obj.phongan = re.getString("phongan");
                obj.nhavesinh = re.getString("nhavesinh");
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_HIENTRANG.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    
    public ArrayList<DTO_HIENTRANG> TimKiem(String solau, String gac, String tuong,
            String mai, String phongkhach, String phongngu, String phongan, String nhavesinh)
    {
        ArrayList<DTO_HIENTRANG> list = new ArrayList<DTO_HIENTRANG>();
        
        String sql = "select id from hientrang where 1=1";
        sql+=" and solau like '%"+solau+"%'";
        sql+=" and gac like '%"+gac+"%'";
        sql+=" and tuong like '%"+tuong+"%'";
        sql+=" and mai like '%"+mai+"%'";
        sql+=" and phongkhach like '%"+phongkhach+"%'";
        sql+=" and phongngu like '%"+phongngu+"%'";
        sql+=" and phongan like '%"+phongan+"%'";
        sql+=" and nhavesinh like '%"+nhavesinh+"%'";
        
        ResultSet re = db.Get(sql);
        DTO_HIENTRANG obj;
        if(re==null) return list;
        try {
            while(re.next())
            {
                obj= Get(re.getInt("id"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_HIENTRANG.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }
    /**
     * Thêm mới
     * @param obj
     * @return id mới thêm || -1 (lỗi)
     */
    public int Them(DTO_HIENTRANG obj)
    {
        String sql = "INSERT INTO `hientrang`(`solau`, `gac`, `tuong`, `mai`,"
                + " `phongkhach`, `phongngu`, `phongan`, `nhavesinh`) "
                + "VALUES ("+obj.solau+","+obj.gac+",'"+obj.tuong+"','"+obj.mai+"','"+obj.phongkhach+"',"
                + "'"+obj.phongngu+"','"+obj.phongan+"','"+obj.nhavesinh+"')";
        Boolean re = db.Run(sql);
        if(!re) return -1;
        return MaMoiNhat();
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from hientrang";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_HIENTRANG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    /**
     * Cập nhật thông tin obj
     * @param obj .id: mã loại, .XXX thông tin cần cập nhật
     * @return true || false
     */
    public Boolean Sua(DTO_HIENTRANG obj)
    {
        String sql = "UPDATE `hientrang` SET `solau`="+obj.solau+",`gac`="+obj.gac+","
                + "`tuong`='"+obj.tuong+"',`mai`='"+obj.mai+"',`phongkhach`='"+obj.phongkhach+"',"
                + "`phongngu`='"+obj.phongngu+"',`phongan`='"+obj.phongan+"',`nhavesinh`='"+obj.nhavesinh+"' WHERE id="+obj.id;
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
        String sql="DELETE FROM `hientrang` WHERE id="+id;
        Boolean re = db.Run(sql);
        return re;
    }
    public ArrayList<Integer> Filter_solau(ArrayList<Integer> list, Double from, Double to)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        //and (convert(varchar(8),ngaylaphd,112)>='"+formatter.format(ngaylaphd_from)
        String sql = "SELECT `id` FROM `thongtin_bds` where `fk_hientrang` IN";
        sql+=" (SELECT `id` from `hientrang` WHERE `solau`>="+from+" AND `solau`<="+to+")";
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
            Logger.getLogger(BLL_HIENTRANG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    public ArrayList<Integer> Filter_gac(ArrayList<Integer> list, Double from, Double to)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        //and (convert(varchar(8),ngaylaphd,112)>='"+formatter.format(ngaylaphd_from)
        String sql = "SELECT `id` FROM `thongtin_bds` where `fk_hientrang` IN";
        sql+=" (SELECT `id` from `hientrang` WHERE `gac`>="+from+" AND `gac`<="+to+")";
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
            Logger.getLogger(BLL_HIENTRANG.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    
}
