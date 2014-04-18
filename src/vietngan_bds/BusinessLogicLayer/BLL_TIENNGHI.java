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
import vietngan_bds.DataTranferObject.DTO_TIENNGHI;

/**
 *
 * @author quocdunginfo
 */
public class BLL_TIENNGHI {
    //private Database db =null;
    public BLL_TIENNGHI() {
        //db=new Database();
    }
    public DTO_TIENNGHI Get(int id)
    {
        String sql = "SELECT `dh_dien`, `dh_nuoc`, `internet`, `dienthoai`,"
                + " `truyenhinhcap`, `hoboi`, `garage`, `maylanh`, `maynuocnong`,"
                + " `mayphatdien`, `khac` FROM `tiennghi` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_TIENNGHI obj = new DTO_TIENNGHI();
        try {
            while(re.next())
            {
                obj.id = id;
                obj.dh_dien = re.getInt("dh_dien");
                obj.dh_nuoc = re.getInt("dh_nuoc");
                obj.internet = re.getInt("internet");
                obj.dienthoai = re.getInt("dienthoai");
                obj.truyenhinhcap = re.getInt("truyenhinhcap");
                obj.hoboi = re.getInt("hoboi");
                obj.garage = re.getInt("garage");
                obj.maylanh = re.getDouble("maylanh");
                obj.maynuocnong = re.getDouble("maynuocnong");
                obj.mayphatdien = re.getDouble("mayphatdien");
                obj.khac = re.getString("khac");
                
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TIENNGHI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    public int Them(DTO_TIENNGHI obj)
    {
        String sql = "INSERT INTO `tiennghi`(`dh_dien`, `dh_nuoc`, `internet`,"
                + " `dienthoai`, `truyenhinhcap`, `hoboi`, `garage`, `maylanh`,"
                + " `maynuocnong`, `mayphatdien`, `khac`)"
                + " VALUES ("+obj.dh_dien+","+obj.dh_nuoc+","+obj.internet+""
                + ","+obj.dienthoai+","+obj.truyenhinhcap+","+obj.hoboi+","+obj.garage+""
                + ","+obj.maylanh+","+obj.maynuocnong+","+obj.mayphatdien
                + ",'"+obj.khac+"')";
        Boolean re = db.Run(sql);
        if(!re) return -1;
        return MaMoiNhat();
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from tiennghi";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TIENNGHI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    /**
     * Cập nhật thông tin
     * @param obj .id: mã, .XXX thông tin cần cập nhật
     * @return true || false
     */
    public Boolean Sua(DTO_TIENNGHI obj)
    {
        String sql = "UPDATE `tiennghi` SET"
                + " `dh_dien`="+obj.dh_dien+",`dh_nuoc`="+obj.dh_nuoc+","
                + "`internet`="+obj.internet+",`dienthoai`="+obj.dienthoai+","
                + "`truyenhinhcap`="+obj.truyenhinhcap+",`hoboi`="+obj.hoboi+","
                + "`garage`="+obj.garage+",`maylanh`="+obj.maylanh+","
                + "`maynuocnong`="+obj.maynuocnong+",`mayphatdien`="+obj.mayphatdien+","
                + "`khac`='"+obj.khac+"' WHERE id="+obj.id;
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
        String sql="DELETE FROM `tiennghi` WHERE id="+id;
        Boolean re = db.Run(sql);
        return re;
    }
    
}
