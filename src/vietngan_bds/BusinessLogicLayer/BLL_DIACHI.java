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
import vietngan_bds.DataTranferObject.DTO_DIACHI;
import vietngan_bds.DataTranferObject.DTO_PHUONGXA;
import vietngan_bds.DataTranferObject.DTO_QUANHUYEN;
import vietngan_bds.DataTranferObject.DTO_TINHTHANH;
import vietngan_bds.Utilities.UTL_String;

/**
 *
 * @author quocdunginfo
 */
public class BLL_DIACHI {

    public BLL_DIACHI() {
        //db=new Database();
    }
    //public Database db=null;
    public DTO_DIACHI Get(int id)
    {
        String sql = "SELECT `fk_tinhthanh`, `fk_quanhuyen`, `fk_phuongxa`,"
                + " `duong`, `sonha` FROM `diachi` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_DIACHI obj = new DTO_DIACHI();
        try {
            while(re.next())
            {
                obj.id = id;
                obj.fk_tinhthanh = new BLL_TINHTHANH().Get(re.getInt("fk_tinhthanh"));
                if(obj.fk_tinhthanh==null) obj.fk_tinhthanh=new DTO_TINHTHANH();
                
                obj.fk_quanhuyen = new BLL_QUANHUYEN().Get(re.getInt("fk_quanhuyen"));
                if(obj.fk_quanhuyen==null) obj.fk_quanhuyen=new DTO_QUANHUYEN();
                
                obj.fk_phuongxa = new BLL_PHUONGXA().Get(re.getInt("fk_phuongxa"));
                if(obj.fk_phuongxa==null) obj.fk_phuongxa=new DTO_PHUONGXA();
                
                obj.duong = re.getString("duong");
                obj.sonha = re.getString("sonha");
                
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_DIACHI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    public int Them(DTO_DIACHI obj)
    {
        String sql = "INSERT INTO `diachi`(`fk_tinhthanh`, `fk_quanhuyen`,"
                + " `fk_phuongxa`, `duong`, `sonha`)"
                + " VALUES"
                + " ("+obj.fk_tinhthanh.id+","+obj.fk_quanhuyen.id+","+obj.fk_phuongxa.id+","
                + "'"+obj.duong+"','"+obj.sonha+"')";
        Boolean re = db.Run(sql);
        if(!re) return -1;
        return MaMoiNhat();
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from diachi";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_DIACHI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    /**
     * Cập nhật thông tin
     * @param obj .id: mã, .XXX thông tin cần cập nhật
     * @return true || false
     */
    public Boolean Sua(DTO_DIACHI obj)
    {
        String sql = "UPDATE `diachi` SET "
                + "`fk_tinhthanh`="+obj.fk_tinhthanh.id+","
                + "`fk_quanhuyen`="+obj.fk_quanhuyen.id+","
                + "`fk_phuongxa`="+obj.fk_phuongxa.id+","
                + "`duong`='"+obj.duong+"',"
                + "`sonha`='"+obj.sonha+"'"
                + " WHERE id="+obj.id;
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
        String sql="DELETE FROM `diachi` WHERE id="+id;
        Boolean re = db.Run(sql);
        return re;
    }
    public ArrayList<Integer> Filter_All(ArrayList<Integer> list, int idtinhthanh,
            int idquanhuyen, int idphuongxa, String duong, String sonha)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return re;
        String sql = "SELECT `id` from `thongtin_bds` WHERE fk_vitri IN"
                + " (SELECT `id` FROM `vitri` WHERE fk_diachi IN"
                + " (SELECT `id` FROM `diachi` where 1=1";
        if(idtinhthanh>0) sql+=" AND `fk_tinhthanh`="+idtinhthanh;
        if(idquanhuyen>0) sql+=" AND `fk_quanhuyen`="+idquanhuyen;
        if(idphuongxa>0) sql+=" AND `fk_phuongxa`="+idphuongxa;
        sql+=" AND `duong` LIKE '%"+duong+"%'";
        sql+=" AND `sonha` LIKE '%"+sonha+"%'))";
        
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
            Logger.getLogger(BLL_DIACHI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
}
