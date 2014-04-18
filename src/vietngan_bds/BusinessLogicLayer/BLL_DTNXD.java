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
import vietngan_bds.DataTranferObject.DTO_DTNXD;
import vietngan_bds.Utilities.UTL_Date;
import vietngan_bds.Utilities.UTL_String;

/**
 *
 * @author quocdunginfo
 */
public class BLL_DTNXD {
    public BLL_DTNXD() {
        //db=new Database();
    }
    //private Database db =null;
    public DTO_DTNXD Get(int id)
    {
        String sql = "SELECT `chieungang`, `chieudai`, `dtkv`, `dtxd`, `dtsd`,"
                + " `dtsan`, `namxaydung` FROM `dientich_namxaydung` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_DTNXD obj = new DTO_DTNXD();
        try {
            while(re.next())
            {
                obj.id = id;
                obj.chieungang = re.getDouble("chieungang");
                obj.chieudai = re.getDouble("chieudai");
                obj.dtkv = re.getDouble("dtkv");
                obj.dtxd = re.getDouble("dtxd");
                obj.dtsd = re.getDouble("dtsd");
                obj.dtsan = re.getDouble("dtsan");
                obj.namxaydung = re.getDate("namxaydung");
                
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_DTNXD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    public int Them(DTO_DTNXD obj)
    {
        String sql = "INSERT INTO `dientich_namxaydung`(`chieungang`, `chieudai`,"
                + " `dtkv`, `dtxd`, `dtsd`, `dtsan`, `namxaydung`)"
                + " VALUES ("+obj.chieungang+","+obj.chieudai+","+obj.dtkv+","
                + obj.dtxd+","+obj.dtsd+","+obj.dtsan+",'"+ UTL_Date.ToMysqlValue(obj.namxaydung)+"')";
        
        Boolean re = db.Run(sql);
        if(!re) return -1;
        return MaMoiNhat();
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from dientich_namxaydung";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_DTNXD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    /**
     * Cập nhật thông tin
     * @param obj .id: mã, .XXX thông tin cần cập nhật
     * @return true || false
     */
    public Boolean Sua(DTO_DTNXD obj)
    {
        String sql = "UPDATE `dientich_namxaydung` SET `chieungang`="+obj.chieungang+","
                + "`chieudai`="+obj.chieudai+",`dtkv`="+obj.dtkv+","
                + "`dtxd`="+obj.dtxd+",`dtsd`="+obj.dtsd+","
                + "`dtsan`="+obj.dtsan+",`namxaydung`='"+UTL_Date.ToMysqlValue(obj.namxaydung)+"'"
                + " WHERE id="+obj.id;
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
        String sql="DELETE FROM `dientich_namxaydung` WHERE id="+id;
        Boolean re = db.Run(sql);
        return re;
    }
    public ArrayList<Integer> Filter_chieungang(ArrayList<Integer> list, Double from, Double to)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        //and (convert(varchar(8),ngaylaphd,112)>='"+formatter.format(ngaylaphd_from)
        String sql = "SELECT `id` FROM `thongtin_bds` where `fk_dientich_namxaydung` IN";
        sql+=" (SELECT `id` from `dientich_namxaydung` WHERE `chieungang`>="+from+" AND `chieungang`<="+to+")";
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
            Logger.getLogger(BLL_DTNXD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    public ArrayList<Integer> Filter_chieudai(ArrayList<Integer> list, Double from, Double to)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        //and (convert(varchar(8),ngaylaphd,112)>='"+formatter.format(ngaylaphd_from)
        String sql = "SELECT `id` FROM `thongtin_bds` where `fk_dientich_namxaydung` IN";
        sql+=" (SELECT `id` from `dientich_namxaydung` WHERE `chieudai`>="+from+" AND `chieudai`<="+to+")";
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
            Logger.getLogger(BLL_DTNXD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    public ArrayList<Integer> Filter_dtkv(ArrayList<Integer> list, Double from, Double to)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        //and (convert(varchar(8),ngaylaphd,112)>='"+formatter.format(ngaylaphd_from)
        String sql = "SELECT `id` FROM `thongtin_bds` where `fk_dientich_namxaydung` IN";
        sql+=" (SELECT `id` from `dientich_namxaydung` WHERE `dtkv`>="+from+" AND `dtkv`<="+to+")";
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
            Logger.getLogger(BLL_DTNXD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    public ArrayList<Integer> Filter_dtxd(ArrayList<Integer> list, Double from, Double to)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        //and (convert(varchar(8),ngaylaphd,112)>='"+formatter.format(ngaylaphd_from)
        String sql = "SELECT `id` FROM `thongtin_bds` where `fk_dientich_namxaydung` IN";
        sql+=" (SELECT `id` from `dientich_namxaydung` WHERE `dtxd`>="+from+" AND `dtxd`<="+to+")";
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
            Logger.getLogger(BLL_DTNXD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    public ArrayList<Integer> Filter_dtsd(ArrayList<Integer> list, Double from, Double to)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        //and (convert(varchar(8),ngaylaphd,112)>='"+formatter.format(ngaylaphd_from)
        String sql = "SELECT `id` FROM `thongtin_bds` where `fk_dientich_namxaydung` IN";
        sql+=" (SELECT `id` from `dientich_namxaydung` WHERE `dtsd`>="+from+" AND `dtsd`<="+to+")";
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
            Logger.getLogger(BLL_DTNXD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    public ArrayList<Integer> Filter_dtsan(ArrayList<Integer> list, Double from, Double to)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        //and (convert(varchar(8),ngaylaphd,112)>='"+formatter.format(ngaylaphd_from)
        String sql = "SELECT `id` FROM `thongtin_bds` where `fk_dientich_namxaydung` IN";
        sql+=" (SELECT `id` from `dientich_namxaydung` WHERE `dtsan`>="+from+" AND `dtsan`<="+to+")";
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
            Logger.getLogger(BLL_DTNXD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    public ArrayList<Integer> Filter_namxaydung(ArrayList<Integer> list, Date from, Date to)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        //and (convert(varchar(8),ngaylaphd,112)>='"+formatter.format(ngaylaphd_from)
        String sql = "SELECT `id` FROM `thongtin_bds` where `fk_dientich_namxaydung` IN";
        sql+=" (SELECT `id` from `dientich_namxaydung` WHERE 1=1";
        sql+= " AND `namxaydung` >= STR_TO_DATE('"+UTL_Date.ToMysqlValue(from)+"', '%Y-%m-%d %H:%i:%s')";
        sql+= " AND `namxaydung` <= STR_TO_DATE('"+UTL_Date.ToMysqlValue(to)+"', '%Y-%m-%d %H:%i:%s')";
        sql+=")";
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
            Logger.getLogger(BLL_DTNXD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
}
