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
import vietngan_bds.DataTranferObject.DTO_DIACHI;
import vietngan_bds.DataTranferObject.DTO_DTNXD;
import vietngan_bds.DataTranferObject.DTO_GIAGD;
import vietngan_bds.DataTranferObject.DTO_HIENTRANG;
import vietngan_bds.DataTranferObject.DTO_LOAIBDS;
import vietngan_bds.DataTranferObject.DTO_TIENICH;
import vietngan_bds.DataTranferObject.DTO_TIENNGHI;
import vietngan_bds.DataTranferObject.DTO_TINHTRANGPHAPLY;
import vietngan_bds.DataTranferObject.DTO_TTBDS;
import vietngan_bds.DataTranferObject.DTO_TTGD;
import vietngan_bds.DataTranferObject.DTO_USER;
import vietngan_bds.DataTranferObject.DTO_VITRI;
import vietngan_bds.Utilities.UTL_Date;
import vietngan_bds.Utilities.UTL_String;

/**
 *
 * @author quocdunginfo
 */
public class BLL_TTBDS {
    //private Database db=null;
    public BLL_TTBDS() {
        //db=new Database();
    }
    public DTO_TTBDS Get(int id)
    {
        String sql = "SELECT `ngay`, `csh_hoten`, `csh_gioitinh`,"
                + " `fk_csh_diachi`, `csh_dienthoai`, `csh_email`,"
                + " `fk_thongtin_gd`, `fk_loai_bds`, `fk_vitri`,"
                + " `fk_dientich_namxaydung`, `fk_hientrang`, `fk_tiennghi`,"
                + " `fk_tienich`, `ghichu`, `fk_user`, `fk_tinhtrangphaply`,"
                + " `fk_gia_gd` FROM `thongtin_bds`"
                + " WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_TTBDS obj = new DTO_TTBDS();
        try {
            while(re.next())
            {
                obj.id=id;
                obj.ngay = re.getDate("ngay");
                obj.csh_hoten = re.getString("csh_hoten");
                obj.csh_gioitinh = re.getInt("csh_gioitinh");
                
                obj.fk_csh_diachi = new BLL_DIACHI().Get(re.getInt("fk_csh_diachi"));
                if(obj.fk_csh_diachi==null) obj.fk_csh_diachi=new DTO_DIACHI();
                
                obj.csh_dienthoai = re.getString("csh_dienthoai");
                obj.csh_email = re.getString("csh_email");
                obj.fk_thongtin_gd = new BLL_TTGD().Get(re.getInt("fk_thongtin_gd"));
                if(obj.fk_thongtin_gd==null) obj.fk_thongtin_gd=new DTO_TTGD();
                
                obj.fk_loai_bds = new BLL_LOAIBDS().Get(re.getInt("fk_loai_bds"));
                if(obj.fk_loai_bds==null) obj.fk_loai_bds=new DTO_LOAIBDS();
                
                obj.fk_vitri = new BLL_VITRI().Get(re.getInt("fk_vitri"));
                if(obj.fk_vitri==null) obj.fk_vitri=new DTO_VITRI();
                
                obj.fk_dientich_namxaydung = new BLL_DTNXD().Get(re.getInt("fk_dientich_namxaydung"));
                if(obj.fk_dientich_namxaydung==null) obj.fk_dientich_namxaydung=new DTO_DTNXD();
                
                obj.fk_hientrang = new BLL_HIENTRANG().Get(re.getInt("fk_hientrang"));
                if(obj.fk_hientrang==null) obj.fk_hientrang=new DTO_HIENTRANG();
                
                obj.fk_tiennghi = new BLL_TIENNGHI().Get(re.getInt("fk_tiennghi"));
                if(obj.fk_tiennghi==null) obj.fk_tiennghi=new DTO_TIENNGHI();
                
                obj.fk_tienich = new BLL_TIENICH().Get(re.getInt("fk_tienich"));
                if(obj.fk_tienich==null) obj.fk_tienich=new DTO_TIENICH();
                
                obj.ghichu = re.getString("ghichu");
                
                obj.fk_user = new BLL_USER().Get(re.getInt("fk_user"));
                if(obj.fk_user==null) obj.fk_user=new DTO_USER();
                
                obj.fk_tinhtrangphaply = new BLL_TINHTRANGPHAPLY().Get(re.getInt("fk_tinhtrangphaply"));
                if(obj.fk_tinhtrangphaply==null) obj.fk_tinhtrangphaply=new DTO_TINHTRANGPHAPLY();
                
                obj.fk_gia_gd = new BLL_GIAGD().Get(re.getInt("fk_gia_gd"));
                if(obj.fk_gia_gd==null) obj.fk_gia_gd=new DTO_GIAGD();
                
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TTBDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int Them(DTO_TTBDS obj)
    {
        //thêm các thành phần con trước
        int re;
        re= new BLL_LOAIBDS().Them(obj.fk_loai_bds);
        if(re==-1)
        {
            Rollback(obj);
            return -1;
        }
        obj.fk_loai_bds.id=re;
        
        re= new BLL_VITRI().Them(obj.fk_vitri);
        if(re==-1)
        {
            Rollback(obj);
            return -1;
        }
        obj.fk_vitri.id=re;
        
        re = new BLL_DTNXD().Them(obj.fk_dientich_namxaydung);
        if(re==-1)
        {
            Rollback(obj);
            return -1;
        }
        obj.fk_dientich_namxaydung.id=re;
        
        re= new BLL_HIENTRANG().Them(obj.fk_hientrang);
        if(re==-1)
        {
            Rollback(obj);
            return -1;
        }
        obj.fk_hientrang.id=re;
        
        re= new BLL_TIENNGHI().Them(obj.fk_tiennghi);
        if(re==-1)
        {
            Rollback(obj);
            return -1;
        }
        obj.fk_tiennghi.id=re;
        
        re= new BLL_TINHTRANGPHAPLY().Them(obj.fk_tinhtrangphaply);
        if(re==-1)
        {
            Rollback(obj);
            return -1;
        }
        obj.fk_tinhtrangphaply.id=re;
        
        re= new BLL_TIENICH().Them(obj.fk_tienich);
        if(re==-1)
        {
            Rollback(obj);
            return -1;
        }
        obj.fk_tienich.id=re;
        
        re= new BLL_TTGD().Them(obj.fk_thongtin_gd);
        if(re==-1)
        {
            Rollback(obj);
            return -1;
        }
        obj.fk_thongtin_gd.id=re;
        
        re= new BLL_GIAGD().Them(obj.fk_gia_gd);
        if(re==-1)
        {
            Rollback(obj);
            return -1;
        }
        obj.fk_gia_gd.id=re;
        
        re= new BLL_DIACHI().Them(obj.fk_csh_diachi);
        if(re==-1)
        {
            Rollback(obj);
            return -1;
        }
        obj.fk_csh_diachi.id=re;
        
        //finally, add itseft info
        //định dạng ngày tháng cho trường ngaylaphd theo chuẩn SQL
        
        String sql = "INSERT INTO `thongtin_bds`(`ngay`, `csh_hoten`, `csh_gioitinh`,"
                + " `fk_csh_diachi`, `csh_dienthoai`, `csh_email`, `fk_thongtin_gd`,"
                + " `fk_loai_bds`, `fk_vitri`, `fk_dientich_namxaydung`,"
                + " `fk_hientrang`, `fk_tiennghi`, `fk_tienich`, `ghichu`,"
                + " `fk_user`, `fk_tinhtrangphaply`, `fk_gia_gd`)"
                + " VALUES ('"+ UTL_Date.ToMysqlValue(obj.ngay)+"','"+obj.csh_hoten+"',"
                + obj.csh_gioitinh+","+obj.fk_csh_diachi.id+",'"+obj.csh_dienthoai+"','"+obj.csh_email+"',"
                + obj.fk_thongtin_gd.id+","+obj.fk_loai_bds.id+","+obj.fk_vitri.id+","
                + obj.fk_dientich_namxaydung.id+","+obj.fk_hientrang.id+","+obj.fk_tiennghi.id+","
                + obj.fk_tienich.id+",'"+obj.ghichu+"',"+obj.fk_user.id+","
                + obj.fk_tinhtrangphaply.id+","+obj.fk_gia_gd.id+")";
        Boolean ree = db.Run(sql);
        if(!ree)
        {
            Rollback(obj);
            return -1;
        }
        return MaMoiNhat();
    }
    public Boolean Sua(DTO_TTBDS obj)
    {
        //sua cac thanh phan con truoc
        new BLL_DTNXD().Sua(obj.fk_dientich_namxaydung);
        new BLL_GIAGD().Sua(obj.fk_gia_gd);
        new BLL_HIENTRANG().Sua(obj.fk_hientrang);
        new BLL_LOAIBDS().Sua(obj.fk_loai_bds);
        new BLL_TTGD().Sua(obj.fk_thongtin_gd);
        new BLL_TIENICH().Sua(obj.fk_tienich);
        new BLL_TIENNGHI().Sua(obj.fk_tiennghi);
        new BLL_TINHTRANGPHAPLY().Sua(obj.fk_tinhtrangphaply);
        new BLL_USER().Sua(obj.fk_user);
        new BLL_VITRI().Sua(obj.fk_vitri);
        new BLL_DIACHI().Sua(obj.fk_csh_diachi);
        
        //sua thanh phan cha
        String sql = "UPDATE `thongtin_bds` SET "
                + "`ngay`='"+UTL_Date.ToMysqlValue(obj.ngay)+"',"
                + "`csh_hoten`='"+obj.csh_hoten+"',"
                + "`csh_gioitinh`="+obj.csh_gioitinh+","
                + "`fk_csh_diachi`='"+obj.fk_csh_diachi.id+"',"
                + "`csh_dienthoai`='"+obj.csh_dienthoai+"',"
                + "`csh_email`='"+obj.csh_email+"',"
                + "`fk_thongtin_gd`="+obj.fk_thongtin_gd.id+","
                + "`fk_loai_bds`="+obj.fk_loai_bds.id+","
                + "`fk_vitri`="+obj.fk_vitri.id+","
                + "`fk_dientich_namxaydung`="+obj.fk_dientich_namxaydung.id+","
                + "`fk_hientrang`="+obj.fk_hientrang.id+","
                + "`fk_tiennghi`="+obj.fk_tiennghi.id+","
                + "`fk_tienich`="+obj.fk_tienich.id+","
                + "`ghichu`='"+obj.ghichu+"',"
                + "`fk_user`="+obj.fk_user.id+","
                + "`fk_tinhtrangphaply`="+obj.fk_tinhtrangphaply.id+","
                + "`fk_gia_gd`="+obj.fk_gia_gd.id+""
                + " WHERE id="+obj.id;
        Boolean re = db.Run(sql);
        return re;
    }
    public Boolean Xoa(int id)
    {
        //load fk value
        DTO_TTBDS obj = Get(id);
        if(obj==null) return true;
        //rollback
        new BLL_DTNXD().Xoa(obj.fk_dientich_namxaydung.id);
        new BLL_HIENTRANG().Xoa(obj.fk_hientrang.id);
        new BLL_TINHTRANGPHAPLY().Xoa(obj.fk_tinhtrangphaply.id);
        new BLL_TIENICH().Xoa(obj.fk_tienich.id);
        new BLL_TIENNGHI().Xoa(obj.fk_tiennghi.id);
        new BLL_LOAIBDS().Xoa(obj.fk_loai_bds.id);
        new BLL_TTGD().Xoa(obj.fk_thongtin_gd.id);
        new BLL_VITRI().Xoa(obj.fk_vitri.id);
        new BLL_GIAGD().Xoa(obj.fk_gia_gd.id);
        new BLL_DIACHI().Xoa(obj.fk_csh_diachi.id);
        
        String sql = "DELETE FROM `thongtin_bds` WHERE id="+id;
        return db.Run(sql);
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from thongtin_bds";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_LOAIBDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    private void Rollback(DTO_TTBDS obj)
    {
        Xoa(obj.id);
    }
    public ArrayList<Integer> Filter_id(ArrayList<Integer> list, int id)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return re;
        String sql = "SELECT `id` FROM `thongtin_bds` where `id`="+id
                + " AND `id` IN ("+ UTL_String.From_Array(list)+")";
        ResultSet rs = db.Get(sql);
        if(rs==null) return re;
        try {
            while(rs.next())
            {
                re.add(rs.getInt("id"));
            }
            return re;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TTBDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    public ArrayList<Integer> Filter_ngay(ArrayList<Integer> list, Date from, Date to)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        if(from==null || to==null) return list;
        //and (convert(varchar(8),ngaylaphd,112)>='"+formatter.format(ngaylaphd_from)
        String sql = "SELECT `id` FROM `thongtin_bds` where `ngay` >= STR_TO_DATE('"+UTL_Date.ToMysqlValue(from)+"', '%Y-%m-%d %H:%i:%s')"
                + " AND `ngay` <= STR_TO_DATE('"+UTL_Date.ToMysqlValue(to)+"', '%Y-%m-%d %H:%i:%s')"
                + " AND `id` IN ("+ UTL_String.From_Array(list)+")";
        ResultSet rs = db.Get(sql);
        if(rs==null) return re;
        try {
            while(rs.next())
            {
                re.add(rs.getInt("id"));
            }
            return re;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TTBDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    public ArrayList<Integer> Get_All_Id()
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        String sql = "SELECT `id` FROM `thongtin_bds`";
        ResultSet rs = db.Get(sql);
        if(rs==null) return re;
        try {
            while(rs.next())
            {
                re.add(rs.getInt("id"));
            }
            return re;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TTBDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    
}
