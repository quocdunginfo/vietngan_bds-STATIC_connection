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
import vietngan_bds.DataTranferObject.DTO_TIENICH;

/**
 *
 * @author quocdunginfo
 */
public class BLL_TIENICH {

    public BLL_TIENICH() {
        //db=new Database();
    }
    //private Database db =null;
    public DTO_TIENICH Get(int id)
    {
        String sql = "SELECT `truongmaugiao`, `truongcap`, `truongcddh`,"
                + " `congvien`, `cho_sieuthi`, `nganhang`,"
                + " `benhvien`, `khac` FROM `tienich` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_TIENICH obj = new DTO_TIENICH();
        try {
            while(re.next())
            {
                obj.id = id;
                obj.truongmaugiao = re.getString("truongmaugiao");
                obj.truongcap = re.getString("truongcap");
                obj.truongcddh = re.getString("truongcddh");
                obj.congvien = re.getString("congvien");
                obj.cho_sieuthi = re.getString("cho_sieuthi");
                obj.nganhang = re.getString("nganhang");
                obj.benhvien = re.getString("benhvien");
                obj.khac = re.getString("khac");
                
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TIENICH.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    public int Them(DTO_TIENICH obj)
    {
        String sql = "INSERT INTO `tienich`(`truongmaugiao`, `truongcap`,"
                + " `truongcddh`, `congvien`, `cho_sieuthi`, `nganhang`,"
                + " `benhvien`, `khac`) VALUES "
                + "('"+obj.truongmaugiao+"','"+obj.truongcap+"','"+obj.truongcddh+"',"
                + "'"+obj.congvien+"','"+obj.cho_sieuthi+"','"+obj.nganhang+"',"
                + "'"+obj.benhvien+"','"+obj.khac+"')";
        Boolean re = db.Run(sql);
        if(!re) return -1;
        return MaMoiNhat();
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from tienich";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TIENICH.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    /**
     * Cập nhật thông tin
     * @param obj .id: mã, .XXX thông tin cần cập nhật
     * @return true || false
     */
    public Boolean Sua(DTO_TIENICH obj)
    {
        String sql = "UPDATE `tienich` SET `truongmaugiao`='"+obj.truongmaugiao+"',"
                + "`truongcap`='"+obj.truongcap+"',`truongcddh`='"+obj.truongcddh+"',"
                + "`congvien`='"+obj.congvien+"',`cho_sieuthi`='"+obj.cho_sieuthi+"',"
                + "`nganhang`='"+obj.nganhang+"',`benhvien`='"+obj.benhvien+"',"
                + "`khac`='"+obj.khac+"' WHERE id="+obj.id;
        Boolean re = db.Run(sql);
        return re;
    }
    /**
     * Xóa loại bds theo id
     * @param id
     * @return
     */
    public Boolean Xoa(int id)
    {
        String sql="DELETE FROM `tienich` WHERE id="+id;
        Boolean re = db.Run(sql);
        return re;
    }
}
