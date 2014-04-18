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
import vietngan_bds.DataTranferObject.DTO_DIACHI;
import vietngan_bds.DataTranferObject.DTO_VITRI;

/**
 *
 * @author quocdunginfo
 */
public class BLL_VITRI {
    public BLL_VITRI() {
        //db = Database;
    }
    public DTO_VITRI Get(int id)
    {
        String sql = "SELECT `mt`, `mtnb`, `hemchinh`, `hemphu`,"
                + " `cachduong`, `leduong`, `fk_huong`, `fk_diachi` FROM `vitri` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_VITRI obj = new DTO_VITRI();
        try {
            while(re.next())
            {
                obj.id=id;
                obj.mt=re.getString("mt");
                obj.mtnb=re.getString("mtnb");
                obj.hemchinh=re.getString("hemchinh");
                obj.hemphu=re.getString("hemphu");
                obj.cachduong=re.getString("cachduong");
                obj.leduong=re.getString("leduong");
                obj.fk_huong=new BLL_HUONG().Get(re.getInt("fk_huong"));
                
                obj.fk_diachi= new BLL_DIACHI().Get(re.getInt("fk_diachi"));
                if(obj.fk_diachi==null) obj.fk_diachi=new DTO_DIACHI();
                
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_VITRI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Boolean Sua(DTO_VITRI obj)
    {
        //up date doi tuong khoa ngoai
        new BLL_DIACHI().Sua(obj.fk_diachi);
        
        String sql="UPDATE `vitri` SET `mt`='"+obj.mt+"',"
                + "`mtnb`='"+obj.mtnb+"',`hemchinh`='"+obj.hemchinh+"',"
                + "`hemphu`='"+obj.hemphu+"',`cachduong`='"+obj.cachduong+"',"
                + "`leduong`='"+obj.leduong+"',`fk_huong`="+obj.fk_huong.id+","
                + "`fk_diachi`="+obj.fk_diachi.id+" WHERE id="+obj.id;
        Boolean re = db.Run(sql);
        return re;
    }
    public int Them(DTO_VITRI obj)
    {
        //thêm các thành phần con trước
        int re;
        re= new BLL_DIACHI().Them(obj.fk_diachi);
        if(re==-1)
        {
            Rollback(obj);
            return -1;
        }
        obj.fk_diachi.id=re;
        
        String sql="INSERT INTO `vitri`(`mt`, `mtnb`, `hemchinh`,"
                + " `hemphu`, `cachduong`, `leduong`, `fk_huong`, `fk_diachi`)"
                + " VALUES ('"+obj.mt+"','"+obj.mtnb+"','"+obj.hemchinh+"',"
                + "'"+obj.hemphu+"','"+obj.cachduong+"','"+obj.leduong+"',"
                + obj.fk_huong.id+","+obj.fk_diachi.id+")";
        Boolean ree=db.Run(sql);
        if(!ree) return -1;
        return MaMoiNhat();
    }
    /**
     * Xóa obj theo id
     * @param id
     * @return
     */
    public Boolean Xoa(int id)
    {
        //load fk value
        DTO_VITRI obj = Get(id);
        if(obj==null) return true;
        //rollback
        new BLL_DIACHI().Xoa(obj.fk_diachi.id);
        
        String sql="DELETE FROM `vitri` WHERE id="+id;
        Boolean re = db.Run(sql);
        return re;
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from vitri";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_VITRI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    private void Rollback(DTO_VITRI obj) {
        Xoa(obj.id);
    }
}
